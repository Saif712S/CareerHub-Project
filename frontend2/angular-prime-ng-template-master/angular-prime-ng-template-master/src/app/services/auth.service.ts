import { Injectable } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { SignupRequestPayload } from '../model/SignupRequestPayload';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AddAdminRequest } from '../model/AddAdminRequest';
import { LoginRequest } from '../model/LoginRequest';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServiceUrl=environment.apiBaseUrl1;
  httpClient: any;
  httpOption={headers:new HttpHeaders({'Content-type':'application/json'})}


  constructor(private oauthService:OAuthService,private http:HttpClient) { }
  public login():void{
    this.oauthService.initImplicitFlowInternal();
    this.oauthService.events.subscribe(event => {
      if (event.type === 'token_received') {
        // Save token to localStorage
        localStorage.setItem('access_token', this.oauthService.getAccessToken());
      }
    });
  
  }
public login2(loginRequest :LoginRequest){
  const loginUrl = `${this.apiServiceUrl}/user/login`;
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.http.post<any>(loginUrl, loginRequest, { headers }).pipe(
      tap((response: any) => {
        // Store access_token and refresh_token in local storage
        this.storeAuthTokensInLocalStorage(response);
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }
  getAccessToken(): string | null{
    return localStorage.getItem('access_token');
  }

  getUserRoles(): string[] | null {
    const accessToken = this.getAccessToken();
    if (!accessToken) {
      return null;
    }

    // Decode the access token to extract the payload
    const payload = this.decodeToken(accessToken);
    if (!payload) {
      return null;
    }

    // Extract the roles from the payload
    const roles: string[] = payload.realm_access.roles;
    return roles;
  }


  /**
   * The `decodeToken` function decodes a JWT token by extracting and parsing the payload from the
   * second part of the token.
   * @param {string} token - The `token` parameter is a string that represents a JSON Web Token (JWT).
   * The function `decodeToken` takes this JWT as input, decodes the payload from the token, and
   * returns it as an object. If there is an error during decoding, it catches the error and returns
   * `null
   * @returns The `decodeToken` function returns the decoded payload from the token if decoding is
   * successful. If an error occurs during decoding, it returns `null`.
   */
  private decodeToken(token: string): any | null {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload;
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }



  /**
   * The function `getUsernameFromToken` retrieves the username from a token if it exists, otherwise
   * returns null.
   * @returns The function `getUsernameFromToken()` returns a string value representing the username
   * extracted from the decoded payload of the access token. If the access token is not present, or if
   * the payload does not contain a preferred username, the function returns `null`.
   */
  getUsernameFromToken(): string | null {
    const accessToken = this.getAccessToken();
    if (!accessToken) {
      return null;
    }

    const payload = this.decodeToken(accessToken);
    console.log('Decoded Payload:', payload);

    if (!payload || !payload.preferred_username) {
      return null;
    }

    const username = payload.preferred_username;
    return username;
  }

  /**
   * The function `resetPassword` sends a POST request to the resetPassword endpoint with a
   * resetPasswordCommand object and returns an Observable.
   * @param {ResetPasswordCommand} resetPasswordCommand - ResetPasswordCommand is an object that
   * contains the necessary information to reset a user's password, such as the user's email address
   * and the new password.
   * @returns The `resetPassword` method returns an Observable that makes a POST request to the
   * `resetPasswordUrl` endpoint with the `resetPasswordCommand` data and headers. The response type is
   * set to 'text'. If there is an error during the request, the method catches the error and rethrows
   * it using `throwError`.
   */


  getUserRolesFromToken(): string[] | null {
    const accessToken = this.getAccessToken();
    if (!accessToken) {
      return null;
    }

    const payload = this.decodeToken(accessToken);
    console.log('Decoded Payload:', payload);

    if (!payload || !payload.realm_access.roles) {
      return null;
    }

    const roles = payload.realm_access.roles;
    return roles;
  }



  private storeAuthTokensInLocalStorage(response: any): void {
    if (response.access_token && response.refresh_token) {
      localStorage.setItem('access_token', (response.access_token));
      localStorage.setItem('refresh_token', (response.refresh_token));
    } else {
      console.error('Access token or refresh token is missing in the response.');
    }
  }
 
  public isLoggedIn():boolean{
    return (this.oauthService.hasValidIdToken() && this.oauthService.hasValidAccessToken());
  }
  public logout():void{
    this.oauthService.logOut();
  }
  public getUsername():string{
    const token=this.oauthService.getAccessToken();
    const payload=token.split('.')[1];
    const payloadDecodedJson=atob(payload);
    const payloadDecoded= JSON.parse(payloadDecodedJson);
    const preferredUsername = payloadDecoded.preferred_username
   return preferredUsername;
  }
  public getTOKEN():string{
    const token=this.oauthService.getAccessToken();


   return token;
  }
  public getUserId(): string {
    const token = this.oauthService.getAccessToken();
    const payload = token.split('.')[1];
    const payloadDecodedJson = atob(payload);
    const payloadDecoded = JSON.parse(payloadDecodedJson);
    const userId = payloadDecoded.sub; // Assuming the user ID is in the 'sub' claim
    return userId;
  }
  public getIsAdmin():boolean{
    const token=this.oauthService.getAccessToken();
    const payload=token.split('.')[1];
    const payloadDecodedJson=atob(payload);
    const payloadDecoded= JSON.parse(payloadDecodedJson);
    const preferredUsername = payloadDecoded.preferred_username
    console.log(payloadDecoded);
    console.log(preferredUsername)
    return payloadDecoded.realm_access.roles.indexOf('Admin') !==-1;

  }
  public getIsLoggedIn():boolean{

    return (this.oauthService.hasValidAccessToken() && this.oauthService.hasValidAccessToken());
  }
  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    console.log(signupRequestPayload)
    return this.http.post<any>(`${this.apiServiceUrl}/user/signup`, signupRequestPayload);
  }
  addRh(signupRequestPayload: SignupRequestPayload): Observable<any> {
    console.log(signupRequestPayload)
    return this.http.post<any>(`${this.apiServiceUrl}/user/AddResponsableRH`, signupRequestPayload, {
      headers: {
        'Authorization': `Bearer ${this.getTOKEN()}`,
        'Content-type':'application/json'

      }
    });
  }
  addAdmin(signupRequestPayload: AddAdminRequest): Observable<any> {
    console.log(signupRequestPayload)
    return this.http.post<any>(`${this.apiServiceUrl}/user/AddAdmin`, signupRequestPayload);
  }
  addUserByAdmin(signupRequestPayload: SignupRequestPayload): Observable<any> {
    console.log(signupRequestPayload)
    return this.http.post<any>(`${this.apiServiceUrl}/user/admin/addUserByAdmin`, signupRequestPayload, {
      headers: {
        'Authorization': `Bearer ${this.getTOKEN()}`,
        'Content-type':'application/json'

      }
    });
  }


}
