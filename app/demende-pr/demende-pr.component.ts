import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-demende-pr',
  templateUrl: './demende-pr.component.html',
  styleUrls: ['./demende-pr.component.css']
})
export class DemendePRComponent implements OnInit {
  options!: any[];
  model: any={};
  namePersonnel!:string;
  inputs: any[] = [{ value: '' }];
  constructor(private personnelService: RestapiService,private http: HttpClient) { }

  ngOnInit(): void {
    this.personnelService.getOptions().subscribe(data => {
      this.options = data;
    });
    this.namePersonnel=this.personnelService.returnNameUser();
  }

  formatDate(date: Date): string {
    const day: number = date.getDate();
    const month: number = date.getMonth() + 1; // Months are zero-based, so we add 1
    const year: number = date.getFullYear();

    // Pad day and month with leading zeros if needed
    const formattedDay: string = day < 10 ? '0' + day : '' + day;
    const formattedMonth: string = month < 10 ? '0' + month : '' + month;

    // Return the formatted date string
    return formattedDay + '/' + formattedMonth + '/' + year;
}
  

  addReservation() {
  var code_dossier= this.model.code_dossier;
  var dateRes=this.model.dateRes;
  var dateRet=this.model.dateRet;
  var curenntDate1= new Date();
  var currentDate=this.formatDate(curenntDate1);
  var namePer=this.namePersonnel;
    this.personnelService.addReservation(code_dossier,dateRes,dateRet,currentDate,namePer).subscribe(() => {
      Swal.fire({
        title: "",
        text: "réalisé avec succès !",
        icon: "success"
      });
      this.clearForm();
    });
  }
   chechCodeTs(){
    var code_dossier= this.model.code_dossier;
    var typeDossier="";
    var userName=this.personnelService.returnlogin();
    if(code_dossier.length==8){
      typeDossier="Cxp"
    }
   else if(code_dossier.length==10){
      typeDossier="Police"
    }
    else if(code_dossier.length==11){
      typeDossier="Sinistre"
    }
    if(typeDossier==""){
      Swal.fire({
        title: 'erreur',
        text: "Le code saisi est incorrect, inexistant ou vous n'avez pas l'autorisation d'accéder à ce dossier.",
        icon: "error"
      });
    }
    this.personnelService.checkExisteFolder(typeDossier,code_dossier,userName).subscribe(data=>{
      console.log(data);
      if(data==true){

        this.personnelService.checkCodeDossier(code_dossier).subscribe(data=>{
          console.log(data[0].dateRes);
          if(data!=null){
            Swal.fire({
              title: 'erreur',
              text: 'Le dossier est réservé du  '+ data[0].dateRes + '  au  ' + data[0].dateRet,
              icon: "error"
            });
            this.model.code_dossier="";
          }
        })
      }
      else{
        Swal.fire({
          title: 'erreur',
          text: "Le code saisi est incorrect, inexistant ou vous n'avez pas l'autorisation d'accéder à ce dossier.",
          icon: "error"
        });
      }

    })
  
   }

  clearForm() {
    this.model = {
      code_dossier: '',
      dateRes: '',
      dateRet: '',
      archiviste:''
    };
  }

  maVariable: any;

  onSelectChange() {
    console.log(this.maVariable); // Vérifiez la valeur dans la console
  }
  
  addInput() {
    this.inputs.push({ value: '' });
  }

  removeInput() {
    if (this.inputs.length > 1) {
      this.inputs.pop();
    }
  }


  }


