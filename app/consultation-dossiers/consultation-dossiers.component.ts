import { Component, OnInit, Renderer2 } from '@angular/core';
import { ConsultationParlotsService } from '../consultation-parlots.service';
import Swal from 'sweetalert2';
import { RestapiService } from '../restapi.service';
import {  Router } from '@angular/router';
import { ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-consultation-dossiers',
  templateUrl: './consultation-dossiers.component.html',
  styleUrls: ['./consultation-dossiers.component.css']
})
export class ConsultationDossiersComponent implements OnInit {

  constructor(private consulattionService: ConsultationParlotsService,private rechercheService : RestapiService ,private router: Router ,private renderer: Renderer2, private el: ElementRef) { }
  

  ngOnInit(): void {

    setTimeout(() => {
      var NAME=localStorage.getItem('name');
      if (NAME !== null) {
        this.name = NAME;
      }
    
  }, 500);
  }
  consultationGroup={
    consulterPar:'',
    dossierList:'',
    selectedDossierType:'',
    dateConsultation:'',
    dateRetour:''
  }

  listDossier:any=[];
  name !: string;
  data : any;
  suggestionsNom!: any[];
  

  onConsult() {
    this.listDossier = this.splitStringByNChars(this.consultationGroup.dossierList);
    if(this.consultationGroup.selectedDossierType == "Police"){
    this.consulattionService.rechercheFolderByLots(
      this.consultationGroup.selectedDossierType,
      this.listDossier,
      this.name,
      this.consultationGroup.consulterPar,
      this.consultationGroup.dateConsultation,
      this.consultationGroup.dateRetour
    ).subscribe(data => {
      console.log(data);
      console.log(this.consultationGroup.selectedDossierType);
      let tableRows = '';
      let tableRows2 = '';
      if(data[0][0]!=undefined){
      data[0][0].forEach((item: { dossier: string; erreur: string; }) => {
        if (item.erreur != null) {
          tableRows += `
          <tr>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle; width: 176px;">
              ${item.dossier}
            </td>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">
              ${item.erreur}
            </td>
          </tr>
          `;
        }
      });
    }
      if(data[1][0]!=undefined){
      data[1][0].forEach((item: { npolice: string; libBranche: string; libSousBranche: string; }) => {
        tableRows2 += `
          <tr>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 153px;">
              ${item.npolice}
            </td>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 207px;">
              ${item.libBranche}
            </td>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;">
              ${item.libSousBranche}
            </td>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">
              <button type="button" class="btn btn-info detailPoliceButton" data-dossier="${item.npolice}" style="width: 40px; height: 36px; margin-bottom: 3px;">
&#9851;              </button>
            </td>
          </tr>
        `;
      });
    }
  
      // Call Swal.fire with the dynamically generated table
      Swal.fire({
        title: 'Details Consultation dossier(s)',
        html: `
          <h1 style="color:red;">Rapport des erreurs:</h1><br>
          <table border="1" class="swErr1" style="width: 100%; text-align: center;">
            <thead>
              <tr>
                <th width="178" style="text-align: center;">N° dossier</th>
                <th style="text-align: center;">Message</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td colspan="2">
                  <div style="overflow:scroll; height:150px;">
                    <table class="swErr1" style="width: 100%; text-align: center;">
                      ${tableRows}
                    </table>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
          <br><br><br>
          <h1 style="color:green;">Journal des dossiers consultés:</h1><br>
          <table border="1" class="swErr2" style="width: 100%; text-align: center;">
            <thead>
              <tr>
                <th width="155" style="text-align: center;">N° dossier</th>
                <th width="207" style="text-align: center;">Branche</th>
                <th width="207" style="text-align: center;">Sous Branche</th>
                <th  style="text-align: center;">Détails</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td colspan="4">
                  <div style="overflow:scroll; height:150px;">
                    <table class="swErr2" style="width: 100%; text-align: center;">
                      ${tableRows2}
                    </table>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        `,
        confirmButtonText: 'Fermer',
        width: '800px',
        didOpen: () => {
          // Attach the event listener to the button after the Swal is rendered
          const buttons = document.querySelectorAll('.detailPoliceButton');
  
          // Log the number of buttons found to ensure they are correctly selected
          console.log(`${buttons.length} buttons found.`);
  
          buttons.forEach(button => {
            console.log('Attaching listener to button:', button);  // Debugging log
            button.addEventListener('click', (event) => {
              const dossier = (event.target as HTMLElement).getAttribute('data-dossier');
              console.log('Button clicked, dossier:', dossier);  // Debugging log
              if (dossier) {
                this.rechercheDossier(this.consultationGroup.selectedDossierType, dossier);
                Swal.close();
              }
            });
          });
        },
        
        
      });
     
    });

   








  }else if(this.consultationGroup.selectedDossierType == "Sinistre"){
    this.consulattionService.rechercheFolderByLots(
      this.consultationGroup.selectedDossierType,
      this.listDossier,
      this.name,
      this.consultationGroup.consulterPar,
      this.consultationGroup.dateConsultation,
      this.consultationGroup.dateRetour
    ).subscribe(data => {
      console.log(data);
  
      let tableRows = '';
      let tableRows2 = '';
      if(data[0][0]!=undefined){
      data[0][0].forEach((item: { dossier: string; erreur: string; }) => {
        if (item.erreur != null) {
          tableRows += `
          <tr>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle; width: 176px;">
              ${item.dossier}
            </td>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">
              ${item.erreur}
            </td>
           
          </tr>
          `;
        }
      });
    }
      if(data[1][0]!=undefined){
      data[1][0].forEach((item: { nsinistre: string; libBranche: string; typeDossier: string; }) => {
        tableRows2 += `
          <tr>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 153px;">
              ${item.nsinistre}
            </td>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 207px;">
              ${item.libBranche}
            </td>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 207px;">
              ${item.typeDossier}
            </td>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">
              <button type="button" class="btn btn-info detailSinistreButton" data-dossier="${item.nsinistre}" style="width: 40px; height: 36px; margin-bottom: 3px;">
&#9851;              </button>
            </td>
          </tr>
        `;
      });
    }
  
      // Call Swal.fire with the dynamically generated table
      Swal.fire({
        title: 'Details Consultation dossier(s)',
        html: `
          <h1 style="color:red;">Rapport des erreurs:</h1><br>
          <table border="1" class="swErr1" style="width: 100%; text-align: center;">
            <thead>
              <tr>
                <th width="178" style="text-align: center;">N° dossier</th>
                <th style="text-align: center;">Message</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td colspan="2">
                  <div style="overflow:scroll; height:150px;">
                    <table class="swErr1" style="width: 100%; text-align: center;">
                      ${tableRows}
                    </table>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
          <br><br><br>
          <h1 style="color:green;">Journal des dossiers Consultés:</h1><br>
          <table border="1" class="swErr2" style="width: 100%; text-align: center;">
            <thead>
              <tr>
                <th width="155" style="text-align: center;">N° dossier</th>
                <th width="207" style="text-align: center;">Branche</th>
                <th width="207" style="text-align: center;">Type Dossier</th>
                <th  style="text-align: center;">Détails</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td colspan="4">
                  <div style="overflow:scroll; height:150px;">
                    <table class="swErr2" style="width: 100%; text-align: center;">
                      ${tableRows2}
                    </table>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        `,
        confirmButtonText: 'Fermer',
        width: '800px',
        didOpen: () => {
          // Attach the event listener to the button after the Swal is rendered
          const buttons = document.querySelectorAll('.detailSinistreButton');
  
          // Log the number of buttons found to ensure they are correctly selected
          console.log(`${buttons.length} buttons found.`);
  
          buttons.forEach(button => {
            console.log('Attaching listener to button:', button);  // Debugging log
            button.addEventListener('click', (event) => {
              const dossier = (event.target as HTMLElement).getAttribute('data-dossier');
              console.log('Button clicked, dossier:', dossier);  // Debugging log
              if (dossier) {
                this.rechercheDossier(this.consultationGroup.selectedDossierType, dossier);
                Swal.close();
              }
            });
          });
        }
      });
      
    });

  






  }else if(this.consultationGroup.selectedDossierType == "Cxp"){
    this.consulattionService.rechercheFolderByLots(
      this.consultationGroup.selectedDossierType,
      this.listDossier,
      this.name,
      this.consultationGroup.consulterPar,
      this.consultationGroup.dateConsultation,
      this.consultationGroup.dateRetour
    ).subscribe(data => {
      console.log(data);
  
      let tableRows = '';
      let tableRows2 = '';
      if(data[0][0]!=undefined){
      data[0][0].forEach((item: { dossier: string; erreur: string; }) => {
        if (item.erreur != null) {
          tableRows += `
          <tr>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle; width: 176px;">
              ${item.dossier}
            </td>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">
              ${item.erreur}
            </td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">
            
          </tr>
          `;
        }
      });
    }
      if(data[1][0]!=undefined){
      data[1][0].forEach((item: { ncxp: string; nomClienCxp: string; villeClientcxp: string; }) => {
        tableRows2 += `
          <tr>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 153px;">
              ${item.ncxp}
            </td>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 207px;">
              ${item.nomClienCxp}
            </td>
            <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;">
              ${item.villeClientcxp}
            </td>
            <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">
              <button type="button" class="btn btn-info detailCxpButton" data-dossier="${item.ncxp}" style="width: 40px; height: 36px; margin-bottom: 3px;">
                &#9851;
              </button>
            </td>
          </tr>
        `;
      });
    }
  
      // Call Swal.fire with the dynamically generated table
      Swal.fire({
        title: 'Details Consultation dossier(s)',
        html: `
          <h1 style="color:red;">Rapport des erreurs:</h1><br>
          <table border="1" class="swErr1" style="width: 100%; text-align: center;">
            <thead>
              <tr>
                <th width="178" style="text-align: center;">N° dossier</th>
                <th style="text-align: center;">Message</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td colspan="2">
                  <div style="overflow:scroll; height:150px;">
                    <table class="swErr1" style="width: 100%; text-align: center;">
                      ${tableRows}
                    </table>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
          <br><br><br>
          <h1 style="color:green;">Journal des dossiers consultés:</h1><br>
          <table border="1" class="swErr2" style="width: 100%; text-align: center;">
            <thead>
              <tr>
                <th width="155" style="text-align: center;">N° dossier</th>
                <th width="207" style="text-align: center;">Nom clien</th>
                <th width="207" style="text-align: center;">Ville client</th>
                <th  style="text-align: center;">Détails</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td colspan="4">
                  <div style="overflow:scroll; height:150px;">
                    <table class="swErr2" style="width: 100%; text-align: center;">
                      ${tableRows2}
                    </table>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        `,
        confirmButtonText: 'Fermer',
        width: '800px',
        didOpen: () => {
          // Attach the event listener to the button after the Swal is rendered
          const buttons = document.querySelectorAll('.detailCxpButton');
  
          // Log the number of buttons found to ensure they are correctly selected
          console.log(`${buttons.length} buttons found.`);
  
          buttons.forEach(button => {
            console.log('Attaching listener to button:', button);  // Debugging log
            button.addEventListener('click', (event) => {
              const dossier = (event.target as HTMLElement).getAttribute('data-dossier');
              console.log('Button clicked, dossier:', dossier);  // Debugging log
              if (dossier) {
                this.rechercheDossier(this.consultationGroup.selectedDossierType, dossier);
                Swal.close();
              }
            });
          });
        }
      });
      
    });
    
  }
  
  }
  
  


  splitStringByNChars(input: string): string[] {
    
    const result: string[] = [];
    let tempStr = "";
  
    for (const char of input) {
      if (char !== " " && char !== "\n" && char !== "\t") {  // Ignorer les espaces
        tempStr += char;
      }
      if(this.consultationGroup.selectedDossierType=="Police"){
        if (tempStr.length === 10) {  // Quand la chaîne temporaire atteint 10 caractères
          result.push(tempStr);
          tempStr = "";
        }
      }else if(this.consultationGroup.selectedDossierType=="Sinistre"){
        if (tempStr.length === 11) {  // Quand la chaîne temporaire atteint 11 caractères
          result.push(tempStr);
          tempStr = "";
        }
      }else if(this.consultationGroup.selectedDossierType=="Cxp"){
        if (tempStr.length === 8) {  // Quand la chaîne temporaire atteint 8 caractères
          result.push(tempStr);
          tempStr = "";
        }
      }

  
      
    }
  
    // Ajouter les caractères restants s'il y en a
    if (tempStr.length > 0) {
      result.push(tempStr);
    }
  
  
    return result;
  }



  rechercheDossier(type:string,numero:string) {
    //this.checkCategorie();
    this.rechercheService.getDossier(type, numero).subscribe(data => {
      console.log(data);
       this.data=data;
      this.rechercheService.getData(this.data,this.consultationGroup.selectedDossierType);
      this.router.navigateByUrl('Dossier/recherche/consultation');
    });

  }

  getNames(){
    this.rechercheService.getNomPersonnel().subscribe(data => {
      this.suggestionsNom = data;
    });
  }


}
