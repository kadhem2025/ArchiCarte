
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  status!:string;
  clickEventsubscription:Subscription;
  name!:string;

  constructor(private router: Router,private headerService:RestapiService) {
    this.clickEventsubscription=    this.headerService.getClickEvent().subscribe(()=>{
      this.ngOnInit();
      })
   }

  ngOnInit(): void {
    
    var logstatus = localStorage.getItem('Status');
    if(logstatus!=null){
      this.status=logstatus;
    }

    setTimeout(() => {
      var aux= this.headerService.returnCheckNumber();
    var url= this.router.url;
    console.log(url);
    if(url=="/login"  ){
      this.name="";
    }else{
      if(aux==1){
      setTimeout(() => {
        var NAME=localStorage.getItem('name');
        if (NAME !== null) {
          this.name = NAME;
        }
      
    }, 500);
  }

   
  }
    
  }, 500);

   
  
  }

  changeStatus(){
    this.router.navigateByUrl('login');
    localStorage.setItem('Status', 'Login');
    this.ngOnInit();
  }

  accueil(){
    console.log( this.router.url);
    var URL=this.router.url;
    if(URL=="/login"){
      this.router.navigateByUrl('login');
    }
    else if(URL=="/login/demandePR"){
      this.router.navigateByUrl('login/demandePR');
    }
    else if(URL.includes("/login/ArchiMenu")){
      this.router.navigateByUrl('login/ArchiMenu');
    }
    else if (URL.includes("/login/Admin-menu")){
      this.router.navigateByUrl('login/Admin-menu');
    }
    else if(URL.includes("Dossier/recherche/consultation")){
      this.router.navigateByUrl('login/ArchiMenu');
    }
    else if(URL.includes("Dossier/statistique")){
      this.router.navigateByUrl('login/ArchiMenu');
    }
    else if(URL.includes("Dossier/RecupererDossiers")){
      this.router.navigateByUrl('login/ArchiMenu');
    }
    else if(URL.includes("Dossier/ConsulationDossiers")){
      this.router.navigateByUrl('login/ArchiMenu');
    }

  }
  

}
