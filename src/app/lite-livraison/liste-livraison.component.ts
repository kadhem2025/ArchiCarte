import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-lite-livraison',
  templateUrl: './liste-livraison.component.html',
  styleUrls: ['./liste-livraison.component.css']
})
export class LiteLivraisonComponent implements OnInit {
  data:any;
  p:number=1;
  iteamsPerPage:number=6;
  totalProduct:any;
  
  
  constructor(private listResService: RestapiService,private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
   this.getListRes();
  }

getListRes(){
  this.listResService.getListReserervation().subscribe(data=>{
    console.log(data);
    this.data=data as any[];
    this.totalProduct=this.data.length;
    
   
  })
}

rechercheDossier(consulation:any){
  if (consulation.etat === 1) {
    Swal.fire(
      '',
      'Le dossier est réservé!',
      'error'
    );
  }else{

  Swal.fire({
    title: "Es-tu sûr?",
    text: "",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    cancelButtonText:"Annuler",
    confirmButtonText: "Oui, sauvegarder-le!"
  }).then((result) => {
    if (result.isConfirmed) {
      /** 
      this.listResService.addConsultation(consulation.codeDossier,consulation.namePer,consulation.dateRes,consulation.dateRet).subscribe(()=>{
        Swal.fire({
          title: "Sauvegardé !",
          text: "La sauvegarde est réussie !",
          icon: "success"
        });
        this.ngOnInit();

      })
        */
      
      
      
    }
    
  });
}
  
}

deleteConsultation(numDossier:string) {
  Swal.fire({
    title: "Es-tu sûr?",
    text: "Vous ne pourrez pas revenir en arrière !",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Oui, supprime-le!"
  }).then((result) => {
    if (result.isConfirmed) {
      /** 
      this.listResService.deleteconsultation(numDossier).subscribe(() => {
        this.ngOnInit();
  
      });
      */
      
      Swal.fire({
        title: "Supprimé !",
        text: "Votre dossier a été supprimé.",
        icon: "success"
      });
    }
    
  });


  
}

}
