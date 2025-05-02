import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ajout-pr',
  templateUrl: './ajout-pr.component.html',
  styleUrls: ['./ajout-pr.component.css']
})
export class AjoutPRComponent implements OnInit {
 
  
  nom:string='';
  prenom:string='';
  email:string='';
  password :string='';
  confirmPassword :string='';
  profil :string='';
  additionalOption!: string;
  constructor(private profileService: RestapiService,private http: HttpClient) { }

  ngOnInit(): void {
  }
 checkPassword(password:string,confirmPassword:string) : boolean{
  if(password!= confirmPassword){
    return false;
  }
  else{
    return true
  }
 }

 addProfil() {
  if(this.checkPassword(this.password,this.confirmPassword)){
    this.profileService.addProfil(this.nom,this.prenom,this.email,this.password,this.additionalOption,this.profil).subscribe(() => {
      Swal.fire({
        title: "",
        text: "réalisé avec succès !",
        icon: "success"
      });
      this.nom='';
      this.prenom='';
      this.email='';
      this.password='';
      this.profil='';
      this.confirmPassword='';
    });
  }
  else{
    Swal.fire({  
      icon: 'error',  
      title: '',  
      text: 'Le mot de passe ne correspond pas',   
    })
  }
  }
}
