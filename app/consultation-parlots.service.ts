import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultationParlotsService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  rechercheFolderByLots(typeDossier: string,folders:string[],nameArchiviste:string,consulterPar:string,dateConsultation:string,dateRetour:string): Observable<any> {
  
    const listDossiers: ({ dossier: string } | { typeDossier: string; consuterPar: string; dateConsultation: string; dateRetour: string; })[] = [];
    folders.forEach(function (doss) {
      const obj = {dossier: doss};
      listDossiers.push(obj);
    });
    const consultation={typeDossier: typeDossier,consuterPar: consulterPar,dateConsultation:dateConsultation,dateRetour:dateRetour}
    listDossiers.push(consultation);

    return this.http.post(`${this.apiUrl}/Dossiers/recherche/${typeDossier}/${nameArchiviste}`, listDossiers);
}


deleteconsultation( folders:string[],nameArchiviste:string,dateRetour:string): Observable<any> {
  const listDossiers: { dossier: string; }[]= [];
  folders.forEach(function (doss) {
    const obj = {dossier: doss};
    listDossiers.push(obj);
  });
  return this.http.delete(`${this.apiUrl}/Dossiers/recuperationDossiers/${nameArchiviste}/${dateRetour}`,{
    body: listDossiers
  });
}

}
