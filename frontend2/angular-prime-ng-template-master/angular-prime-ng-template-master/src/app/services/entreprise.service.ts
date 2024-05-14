import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Entreprise } from '../model/Entreprise';

@Injectable({
  providedIn: 'root'
})
export class EntrepriseService {
  private apiServiceUrl=environment.apiBaseUrl3;
  httpClient: any;
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('access_token'), // Assuming you store the token in localStorage
    })
  };
  constructor(private oauthService:OAuthService,private http:HttpClient) { }
  getAllEntreprises(): Observable<Entreprise[]> {
    return this.http.get<Entreprise[]>(`${this.apiServiceUrl}/Entreprise/all`, this.httpOptions);
  }

  getEntrepriseById(id: number): Observable<Entreprise> {
    return this.http.get<Entreprise>(`${this.apiServiceUrl}/${id}`, this.httpOptions);
  }

  addEntreprise(entreprise: Entreprise): Observable<Entreprise> {
    return this.http.post<Entreprise>(`${this.apiServiceUrl}/Entreprise/add`, entreprise, this.httpOptions);
  }

  updateEntreprise(id: number, updatedEntreprise: Entreprise): Observable<Entreprise> {
    return this.http.put<Entreprise>(`${this.apiServiceUrl}/Entreprise/update/${id}`, updatedEntreprise, this.httpOptions);
  }

  deleteEntreprise(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServiceUrl}/Entreprise/delete/${id}`, this.httpOptions);
  }
}
