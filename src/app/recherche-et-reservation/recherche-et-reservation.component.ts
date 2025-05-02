import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-recherche-et-reservation',
  templateUrl: './recherche-et-reservation.component.html',
  styleUrls: ['./recherche-et-reservation.component.css']
})
export class RechercheEtReservationComponent implements OnInit {
  data:any;
  nom_Client!:string;
  folderData={
    ndossier:'',
    clientName:'',
    branche:'',
    sousBrache:'',
    archiviste:'',
    agenceCode:'',
    dateAdded:'',
    nsinistre:'',
    typeDoss:'',
    etatDossier:'',
    npolice:'',
    vehicule:'',
    immatricule:''
    
    }

    folderConsultationData={
      naffaire:'',
      consultePar:'',
      dateConsultation:'',
      typeAffaire:'',
      dateRetour:'',
      archivisteRecuperation:'',
      archivisteConsultation:''
      
      
      }
      folderViewProassur={
        gestionnaire:''
      }

      typeDossier!:string
      numdossier!:string
      NomPer!:string
      DateCons!:Date
      dateRet!:Date
      name!:string;
      suggestionsNom!: any[];
      isActive=false;
      dateRetour!:Date;
      historys:any;

  constructor(private rechercheReservetion: RestapiService,private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    this.data=this.rechercheReservetion.griddata();
    if(this.data!= null){
      if(this.data.typeDossier=="Police"){
      this.folderData.ndossier=this.data.data[0].npolice;
      this.folderData.clientName=this.data.data[0].nomClient;
      this.folderData.branche=this.data.data[0].libBranche;
      this.folderData.sousBrache=this.data.data[0].libSousBranche;
      this.folderData.agenceCode=this.data.data[0].codeAgence;
      this.folderData.archiviste=this.data.data[0].utilisateur;
      this.folderData.dateAdded=this.formatDateTime2(new Date(this.data.data[0].dateAjout/*.split("+", 1)[0]*/));
      this.numdossier=this.folderData.ndossier
      if(this.data.data[1][0]!=null){
      this.folderConsultationData.naffaire=this.data.data[1][0].naffaire;
      this.folderConsultationData.consultePar=this.data.data[1][0].consulterPar;
      this.folderConsultationData.dateConsultation=this.data.data[1][0].dateConsultation;
      this.folderConsultationData.dateRetour=this.data.data[1][0].dateRetour;
      this.folderConsultationData.typeAffaire=this.data.data[1][0].typeAffaire;
      this.folderConsultationData.archivisteConsultation=this.data.data[1][0].archivisteCons
      }
    }else if(this.data.typeDossier=="Sinistre"){
      this.folderData.ndossier=this.data.data[0].nsinistre;
      this.folderData.clientName=this.data.data[0].nomClient;
      this.folderData.branche=this.data.data[0].libBranche;
      this.folderData.sousBrache=this.data.data[0].libSousBranche;
      this.folderData.agenceCode=this.data.data[0].codeAgence;
      this.folderData.archiviste=this.data.data[0].utilisateur;
      this.folderData.dateAdded=this.formatDateTime2(new Date(this.data.data[0].dateAjout/*.split("+", 1)[0]*/));
      this.folderData.npolice=this.data.data[0].npolice;
      this.folderData.typeDoss=this.data.data[0].typeDossier;
      this.folderData.etatDossier=this.data.data[0].etatDossier;
      this.folderViewProassur.gestionnaire=this.data.data[2]
      this.numdossier=this.folderData.ndossier
      if(this.data.data[1][0]!=null){
      this.folderConsultationData.naffaire=this.data.data[1][0].naffaire;
      this.folderConsultationData.consultePar=this.data.data[1][0].consulterPar;
      this.folderConsultationData.dateConsultation=this.data.data[1][0].dateConsultation;
      this.folderConsultationData.dateRetour=this.data.data[1][0].dateRetour;
      this.folderConsultationData.typeAffaire=this.data.data[1][0].typeAffaire;
      this.folderConsultationData.archivisteConsultation=this.data.data[1][0].archivisteCons
    }
    }else if(this.data.typeDossier=="Cxp"){
      this.folderData.ndossier=this.data.data[0][0].ncxp;
      this.folderData.clientName=this.data.data[0][0].nomClienCxp;
      this.folderData.vehicule=this.data.data[0][0].vehicule;
      this.folderData.sousBrache=this.data.data[0][0].libSousBranche;
      this.folderData.agenceCode=this.data.data[0][0].codeAgence;
      this.folderData.archiviste=this.data.data[0][0].utilisateur;
      this.folderData.dateAdded=this.formatDateTime2(new Date(this.data.data[0][0].dateAjout/*.split("+", 1)[0]*/));
      this.numdossier=this.folderData.ndossier
      if(this.data.data[1][0]!=null){
      this.folderConsultationData.naffaire=this.data.data[1][0].naffaire;
      this.folderConsultationData.consultePar=this.data.data[1][0].consulterPar;
      this.folderConsultationData.dateConsultation=this.data.data[1][0].dateConsultation;
      this.folderConsultationData.dateRetour=this.data.data[1][0].dateRetour;
      this.folderConsultationData.typeAffaire=this.data.data[1][0].typeAffaire;
      this.folderConsultationData.archivisteConsultation=this.data.data[1][0].archivisteCons
    }
    }
  }
    setTimeout(() => {
      var NAME=localStorage.getItem('name');
      if (NAME !== null) {
        this.name = NAME;
      }
    
  }, 500);
  this.typeDossier=this.data.typeDossier;
    
  this.getHistory();
  }


  AjoutConsultation(){
    if(this.NomPer==undefined || this.NomPer==""){
      Swal.fire({
        title: "Erreur",
        text: "Le champ nom personnel est obligatoire",
        icon: "error"
      });
    }else{
    this.rechercheReservetion.addConsultation(this.typeDossier,this.numdossier,this.NomPer,this.DateCons,this.dateRet,this.name).subscribe(()=>{
      Swal.fire({
        title: "Information",
        text: "La consultation a été enregistré avec succès !",
        icon: "success"
      });
      this.router.navigateByUrl('login/ArchiMenu/RechercheD');
      /*this.rechercheReservetion.getDossier(this.typeDossier,this.numdossier).subscribe(data=>{
        this.rechercheReservetion.getData(data,this.typeDossier);
        this.ngOnInit()
        
      })*/
        
      this.numdossier="";
      this.NomPer="";
      this.DateCons=new Date();
      this.dateRet=new Date();
      
    })
  }
}

  deleteConsutation(){
      Swal.fire({
        title: 'Confirmation',
        text: 'Merci de confirmer la récupération',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Récupérer',
        cancelButtonText: 'Annuler'
      }).then((result) => {
        if (result.isConfirmed) {
          if(this.dateRetour==null || this.dateRetour==undefined || this.dateRetour.toString()=="undefined" || this.dateRetour.toString()==""){
            this.dateRetour= new Date()
           let currentdate=this.formatDateTime(this.dateRetour);
           this.rechercheReservetion.deleteconsultation(this.numdossier,this.name,currentdate).subscribe(data=>{

           })
          }else{
            const specificDate = new Date(this.dateRetour);
            let currentdate=this.formatDateTime(specificDate);
            this.rechercheReservetion.deleteconsultation(this.numdossier,this.name,currentdate).subscribe(data=>{

            })
          }
         
          // Perform the action for confirmation (e.g., delete an item)
          Swal.fire(
            'Information',
            'Votre dossier a été récupéré avec succès.',
            'success'
          );
          this.router.navigateByUrl("login/ArchiMenu/RechercheD");
        } else if (result.dismiss === Swal.DismissReason.cancel) {
          Swal.fire(
            'Annulé',
            'Récupération annulée',
            'error'
          );
        }
      });
    
  }

  getNames(){
    this.rechercheReservetion.getNomPersonnel().subscribe(data => {
      this.suggestionsNom = data;
    });
  }

  retourDate(){
    this.isActive=true;
  }

   formatDateTime(date: Date): string {
    const year: string = date.getFullYear().toString();
    const month: string = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-based
    const day: string = date.getDate().toString().padStart(2, '0');
    const hours: string = date.getHours().toString().padStart(2, '0');
    const minutes: string = date.getMinutes().toString().padStart(2, '0');

    return `${day}-${month}-${year} à ${hours}:${minutes}`;
    //return `${day}-${month}-${year}T${hours}:${minutes}`;
}

formatDateTime2(date: Date): string {
  const year: string = date.getFullYear().toString();
  const month: string = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-based
  const day: string = date.getDate().toString().padStart(2, '0');
  const hours: string = date.getHours().toString().padStart(2, '0');
  const minutes: string = date.getMinutes().toString().padStart(2, '0');

  //return `${year}-${month}-${day}T${hours}:${minutes}`;
  //return `${day}/${month}/${year} à ${hours}h:${minutes}`;
  return `${day}-${month}-${year}`;
}

getHistory(){
  this.rechercheReservetion.getHistoryService(this.numdossier).subscribe(historys=>{
      console.log(historys);
      this.historys=historys;
  })
}

}
