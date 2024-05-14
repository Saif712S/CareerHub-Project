import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/User';
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServiceUrl=environment.apiBaseUrl1;
  httpClient: any;
  httpOption={headers:new HttpHeaders({'Content-type':'application/json'})}

  constructor(private http:HttpClient,private loginService:AuthService) { }
  
  public list():Observable<User[]>{
    
    return this.http.get<User[]>(`${this.apiServiceUrl}/user/admin/findAll`, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  public detail(userName:string):Observable<User>{
    return this.http.get<User>(`${this.apiServiceUrl}/user/ByName/${userName}`, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
 
  public update(id:number,user:User):Observable<any>{
    return this.http.put<any>(`${this.apiServiceUrl}/user/admin/UpdateUserByAdmin/${id}`,user, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  public delete(id:number):Observable<any>{
    return this.http.delete<any>(`${this.apiServiceUrl}/user/deleteAccount/${id}`, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  public deleteMyAccount():Observable<any>{
    return this.http.delete<any>(`${this.apiServiceUrl}/user/deleteMyAccount`, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  public updateprofile(user:User):Observable<any>{
    return this.http.put<any>(`${this.apiServiceUrl}/user/UpdateUser`,user, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
  assignRoleToUser(id: string, roleName: User): Observable<any> {
    return this.http.put<any>(`${this.apiServiceUrl}/user/admin/roles/${id}`,roleName, {
      headers: {
        'Authorization': `Bearer ${this.loginService.getTOKEN()}`,
        'Content-type':'application/json'

      }
    })
  }
}
