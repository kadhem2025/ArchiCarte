import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  Ndossier!: string;
  NamePersonnel!: string;
  dateRes!:Date;
  dateRet!:Date;
  suggestionsNom!: any[];
  data:any

  constructor(private reservationService: RestapiService,private http: HttpClient) { }

  ngOnInit(): void {
    const data=this.reservationService.setPolice();
    if(data.lien=="/login/ArchiMenu/RechercheD/gridD"){
      this.Ndossier= data.police;
      data.lien='';

    }
    else{
      this.data=this.reservationService.setconsultation()
      console.log(this.data);
      this.Ndossier=this.data.codeDossier;
      this.NamePersonnel=this.data.namePer;
      this.dateRes=this.data.dateRes;
      this.dateRet=this.data.dateRet;

      
    }
     
  }

  getNames(){
    this.reservationService.getNomPersonnel().subscribe(data => {
      this.suggestionsNom = data;
    });
  }
/** 
  AjoutConsultation(){
    this.reservationService.addConsultation(this.Ndossier,this.NamePersonnel,this.dateRes,this.dateRet).subscribe(()=>{
      Swal.fire({
        title: "",
        text: "La sauvegarde est réussie !",
        icon: "success"
      });
      this.Ndossier="";
      this.NamePersonnel="";
      this.dateRes=new Date();
      this.dateRet=new Date();
      
    })
  }
    */
  onDateSelected(event: MatDatepickerInputEvent<Date>): void {
    // Handle the selected date
    console.log('Selected date:', event.value);
  }
  
}
