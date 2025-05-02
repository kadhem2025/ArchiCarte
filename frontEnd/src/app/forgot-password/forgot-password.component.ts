import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  email!: string;
  

  constructor(private forgotPassword: RestapiService) {}
  ngOnInit(): void {
  }

  requestPasswordReset() {
    this.forgotPassword.requestPasswordReset(this.email).subscribe(data=>{
      console.log(data);
    })
    
  }

}
