import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  userName!: string;
  newPassword!:string;
  confirmedPassword!:string;
  constructor(private changePassService:RestapiService,private http:HttpClient) { }

  ngOnInit(): void {
  }
  changePassword(){
    this.confirmPassword();
    this.changePassService.changePassword(this.userName,this.newPassword,this.confirmedPassword).subscribe(data=>{

      console.log(data);
      if(data==1){
        Swal.fire({
          title: "",
          text: "Mot de passe changé!",
          icon: "success"
        });

        this.userName="";
        this.newPassword="";
        this.confirmedPassword="";
      }
      else{
        Swal.fire({
          title: "",
          text: "Quelque chose cloche !",
          icon: "error"
        });
      }
    })
  }

  confirmPassword(){
    if(this.newPassword!=this.confirmedPassword){
      Swal.fire({
        title: "",
        text: "Mot de passe incorrect !",
        icon: "error"
      });
    }
  }
}
