import { Component, OnInit } from '@angular/core';
import { ExportAPIService } from '../export-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent implements OnInit {
  

  constructor(private exportService: ExportAPIService) { }
  entityType:any;
  typeExport:any;
  dateConsultation!:string;

  ngOnInit(): void {
  }



  exportExcel() {
    if(this.entityType==undefined|| this.entityType=="Choisir..."){
      Swal.fire({
        icon: 'error', // Icon type
        title: 'Erreur',
        text: 'Type dossier non valide!',
        background: '#fff', // Light red background
        color: '#721c24', // Text color
        confirmButtonColor: '#f44336', // Customize the button color
        confirmButtonText: 'Essayer à nouveau',
        // Optional background image
      });
    }else{
    if(this.typeExport=="dossiers"){
      if(this.dateConsultation==""){
        this.dateConsultation="undefined";
      }
      console.log(this.dateConsultation);
    this.exportService.downloadDossierExcel(this.entityType,this.dateConsultation);
    }else
    if(this.typeExport=="consultation"){
      if(this.dateConsultation==""){
        this.dateConsultation="undefined";
      }
      this.exportService.downloadConsultationExcel(this.entityType,this.dateConsultation);
  }else{
    Swal.fire({
      icon: 'error', // Icon type
      title: 'Erreur',
      text: 'Type exportation non valide!',
      background: '#fff', // Light red background
      color: '#721c24', // Text color
      confirmButtonColor: '#f44336', // Customize the button color
      confirmButtonText: 'Essayer à nouveau',
      // Optional background image
    });
  }
  }
}
  /** 
  if(this.typeExport=="dossiers"){
  if(this.entityType=="police" ) {
  this.exportService.downloadPoliceExcel();
}
else if(this.entityType=="sinistre" ){
  this.exportService.downloadSinistreExcel();
}
else if(this.entityType=="cxp"){
  this.exportService.downloadCxpExcel();
}
  }
  if(this.typeExport=="consultation"){

 if((this.entityType!=null && this.entityType!="Choisir...")){
  if((this.dateConsultation==null||this.dateConsultation.toString()=="")){
    this.exportService.downloadFiltreEntityTypeExcel(this.entityType);
  }
 }
   
  

else if(this.dateConsultation!=null && (this.entityType==null ||this.entityType=="Choisir...")  ){
  this.exportService.downloadFiltreDateExcel(this.dateConsultation);
}
else if(this.dateConsultation!=null && (this.entityType!=null ||this.entityType!="Choisir...") ){
  this.exportService.downloadFiltreTypeAndDateExcel(this.entityType,this.dateConsultation);
}else if((this.entityType==null ||this.entityType=="Choisir...") && (this.dateConsultation==null||this.dateConsultation.toString()=="")){
    this.exportService.downloadAllConsultationExcel();
}

}
}
*/
}

