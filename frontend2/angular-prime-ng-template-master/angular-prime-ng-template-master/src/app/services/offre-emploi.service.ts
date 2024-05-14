import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OffreEmploi } from '../model/OffreEmploi';


@Injectable({
  providedIn: 'root'
})
export class OffreEmploiService {

  private apiServiceUrl=environment.apiBaseUrl2;
  constructor(private http: HttpClient) { }
  getAllOffres(): Observable<Array<OffreEmploi>> {
    return this.http.get<Array<OffreEmploi>>(`${this.apiServiceUrl}/offre-emploi/all`);
  }
  getoffrebyId(id :number):Observable<OffreEmploi> {
  return this.http.get<OffreEmploi>(`${this.apiServiceUrl}/offre-emploi/getOffreById/`+id);
}
  addOffre(offre:OffreEmploi){
    return this.http.post<OffreEmploi>(`${this.apiServiceUrl}/offre-emploi/add`,offre);

  }
  public getAllCandidature():Observable<any>{
    return this.http.get<any>(`${this.apiServiceUrl}/candidature/getCandidatures`, {
      headers: {
        'Authorization': `Bearer`,
        'Content-type':'application/json'

      }
    })
  }
}
