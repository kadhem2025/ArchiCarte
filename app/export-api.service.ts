import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExportAPIService {


  private apiUrl = 'http://localhost:8080/api/export/excel';

  constructor(private http: HttpClient) {}


  /**
  downloadPoliceExcel(): void {
    this.http.get(`${this.apiUrl}/police`, { responseType: 'blob' }).subscribe((blob: Blob) => {
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.download = 'export_dossier_police.xlsx';
        link.click();
      });
  }
 
  downloadSinistreExcel(): void {
    this.http.get(`${this.apiUrl}/sinistre`, { responseType: 'blob' }).subscribe((blob: Blob) => {
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.download = 'data_export.xlsx';
        link.click();
      });
  }
  downloadCxpExcel(): void {
    this.http.get(`${this.apiUrl}/cxp`, { responseType: 'blob' }).subscribe((blob: Blob) => {
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.download = 'data_export.xlsx';
        link.click();
      });
  }

  downloadFiltreEntityTypeExcel(typedossier:string): void{
    this.http.get(`${this.apiUrl}/consultation/type/${typedossier}`, { responseType: 'blob' }).subscribe((blob: Blob) => {
      const downloadUrl = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = downloadUrl;
      link.download = 'data_export.xlsx';
      link.click();
    });
  }

  downloadFiltreDateExcel(dateConsultation:Date):void{
    this.http.get(`${this.apiUrl}/consultation/date/${dateConsultation}`, { responseType: 'blob' }).subscribe((blob: Blob) => {
      const downloadUrl = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = downloadUrl;
      link.download = 'data_export.xlsx';
      link.click();
    });
  }

  downloadFiltreTypeAndDateExcel(typedossier:string,dateConsultation:Date):void{
    this.http.get(`${this.apiUrl}/consultation/type/${typedossier}/date/${dateConsultation}`, { responseType: 'blob' }).subscribe((blob: Blob) => {
      const downloadUrl = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = downloadUrl;
      link.download = 'data_export.xlsx';
      link.click();
    });
  }

  downloadAllConsultationExcel():void{
    this.http.get(`${this.apiUrl}/consultation/all`, { responseType: 'blob' }).subscribe((blob: Blob) => {
      const downloadUrl = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = downloadUrl;
      link.download = 'data_export.xlsx';
      link.click();
    });
  }
    */

  downloadDossierExcel(typeDossier:string,dateConsultation:string): void {
    this.http.get(`${this.apiUrl}/dossier/${typeDossier}/${dateConsultation}`, { responseType: 'blob' }).subscribe((blob: Blob) => {
        const downloadUrl = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = downloadUrl;
        link.download = 'export_dossier_'+typeDossier+  ((dateConsultation == "undefined" || dateConsultation == undefined) ? "" : "_" + dateConsultation.replace("/","-")) + '.xlsx';
        /*if(typeDossier=="police"){
          link.download = 'export_dossier_police.xlsx';
        }
        if(typeDossier=="sinistre"){
          link.download = 'export_dossier_sinistre.xlsx';
        }
        if(typeDossier=="cxp"){
          link.download = 'export_dossier_cxp.xlsx';
        }*/
        
        link.click();
      });
  }



 downloadConsultationExcel(typeDossier:string,dateConsultation:string):void{
    this.http.get(`${this.apiUrl}/consultation/${typeDossier}/${dateConsultation}`, { responseType: 'blob' }).subscribe((blob: Blob) => {
      const downloadUrl = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = downloadUrl;
     /* if(typeDossier=="police"){
        link.download = 'export_consultation_police.xlsx';
      }
      if(typeDossier=="sinistre"){
        link.download = 'export_consultation_sinistre.xlsx';
      }
      if(typeDossier=="cxp"){
        link.download = 'export_consultation_cxp.xlsx';
      }*/
        link.download = 'export_consultation_'+typeDossier+  ((dateConsultation == "undefined" || dateConsultation == undefined) ? "" : "_" + dateConsultation.replace("/","-")) + '.xlsx';
      link.click();
    });
  }

}
