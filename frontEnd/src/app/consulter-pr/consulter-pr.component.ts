import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-consulter-pr',
  templateUrl: './consulter-pr.component.html',
  styleUrls: ['./consulter-pr.component.css']
})
export class ConsulterPRComponent implements OnInit {
  userData={
  firstName:'',
  lastName:'',
  email:'',
  username:'',
  password:'',
  profil:''
  
  }
  aux!:string
  constructor(private consulterService: RestapiService,private http: HttpClient) { }

  ngOnInit(): void {
    this.consulterService.getPersonnel().subscribe(data =>{
      if(data!= null){
      this.userData.firstName= data[0];
      this.userData.lastName=data[1];
      this.userData.email=data[2]
      this.userData.username=data[3];
      this.aux=data[3];
      
      this.userData.password=data[4];
      this.userData.profil=data[5];
      }
      else{
        Swal.fire(
          '',
          'Invalid user!',
          'warning'
        );
      }
    })
  
  }
  deleteUser() {
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
        this.consulterService.deleteUser(this.userData.username,this.userData.profil).subscribe(() => {
     
    
        });

        Swal.fire({
          title: "Supprimé !",
          text: "Votre dossier a été supprimé.",
          icon: "success"
        });
      }
      this.userData.firstName='';
      this.userData.lastName='';
      this.userData.password='';
      this.userData.profil=''
      this.userData.username='';
    });


    
}

updateUser(){
  
  this.consulterService.updateUser(this.userData, this.aux).subscribe(()=>{
    Swal.fire({
      title: "",
      text: "Modifié avec succès !",
      icon: "success"
    });

  })
}
 
}
