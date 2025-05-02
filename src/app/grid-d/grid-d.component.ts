import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-grid-d',
  templateUrl: './grid-d.component.html',
  styleUrls: ['./grid-d.component.css']
})
export class GridDComponent implements OnInit {
  data:any;
  num_Police:any
  nom_Client!:string;

  constructor(private gridService: RestapiService,private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    this.data=this.gridService.griddata();
    console.log(this.data[0]);
    this.nom_Client=this.data[0].nomClient
   // this.fetchData();
  }

  handleButtonClick(police:any) {
    if (police.etat === 1) {
      Swal.fire(
        '',
        'Le dossier est réservé!',
        'error'
      );
    } else {
      this.gridService.getPolice(this.router.url,police.npolice);
      this.router.navigateByUrl('login/ArchiMenu/RechercheD/gridD/reservation');
    }
  }

  fetchData() {
    
    this.gridService.getClientDataByCode(this.data[0].npolice).subscribe(aux => {
        console.log('Data:', aux.clientName);
        this.nom_Client=this.data[0].nomClient
        // Handle the data as needed
      });
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
        this.gridService.deleteconsultation(numDossier).subscribe(() => {
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

  deleteDossier(numDossier:string) {
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
        let typedossier;
        if(numDossier.length==10){
          typedossier="Police"
        }
        if(numDossier.length==11){
          typedossier="Sinistre"
        }
        if(numDossier.length==8){
          typedossier="Cxp"
        }
        this.gridService.deleteDossier(typedossier,numDossier).subscribe(() => {
          Swal.fire({
            title: "Supprimé !",
            text: "Votre dossier a été supprimé.",
            icon: "success"
          });
          
          this.router.navigateByUrl("login/ArchiMenu/RechercheD")
        });
        
        
      }
      
      
    });
  
  
    
  }
 
 
}
