import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-ajout-d',
  templateUrl: './ajout-d.component.html',
  styleUrls: ['./ajout-d.component.css']
})
export class AjoutDComponent implements OnInit {
  typeDossier!: string;
  categorie!: string;
  inputN_dossier!: string;  
  inputannee!: string;
  isActive:boolean=true;
  listDossier:any=[];
   messages: string[] = [];
   existe!: string;
   name !: string;
  constructor(private ajoutDService: RestapiService,private http: HttpClient) { }

  ngOnInit(): void {
    setTimeout(() => {
      var NAME=localStorage.getItem('name');
      if (NAME !== null) {
        this.name = NAME;
      }
    
  }, 500);
  }

    async addDossier(){
    
    
      if(this.typeDossier == "Police"){
        
     
      /*  this.ajoutDService.addFolder_v2(this.typeDossier,this.listDossier).subscribe(data1=>{

          if(data1.length==0){

          
                Swal.fire({
                  icon: 'error',  
                  title: '',  
                  text: 'La police n° '+this.listDossier[0]+' est inexistante sur PROASSUR',   
          
                })
          
       
            
          }*/
        //  else {
            
              this.ajoutDService.addFolder(this.typeDossier,this.listDossier,this.name).subscribe(data=>{
                console.log(data)


                let tableRows = '';
                let tableRows2='';

                // Loop through the data to generate table rows
                data[0].forEach((item: { dossier: string; erreur: string; }) => {
                  if(item.erreur!=null){
                  tableRows += `
                    <tr>
                      <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;width: 176px;" >${item.dossier}</td>
                      <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">${item.erreur}</td>
                    </tr>
                  `;
                  }
                });

                data[1].forEach((item: { npolice: string; libBranche: string;  libSousBranche:string;  }) => {
                  
                  tableRows2 += `
                    <tr>
                      <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 153px;">${item.npolice}</td>
                      <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 207px;">${item.libBranche}</td>
                      <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;">${item.libSousBranche}</td>

                    </tr>
                  `;
                  
                });
                
                // Call Swal.fire with the dynamically generated table
                Swal.fire({
                  title: 'Details Ajout dossier(s)',
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

                  <br>
                  <br>
                  <br>

                  <!-- Second Table -->
                  <h1 style="color:green;">Journal des dossiers ajoutés:</h1><br>
                       <table border="1" class="swErr2" style="width: 100%; text-align: center;">
                       <thead>
                        <tr>
                         <th width="155" style="text-align: center;">N° dossier</th>
                         <th width="207" style="text-align: center;">Branche</th>
                         <th style="text-align: center;">Sous Branche</th>
                        </tr>
                      </thead>
                 <tbody>
                         <tr>
                          <td colspan="3">
                             <div style="overflow:scroll; height:150px;">
                               <table class="swErr2" style="width: 100%; text-align: center;">
                                    ${tableRows2}
                               </table>
                           </div>
                         </td>
                       </tr>
                 </tbody>
          </table>
       </div>
  `,

                  
                  confirmButtonText: 'Fermer',
                  width: '800px'
                });
                
              


              /**  if(data==null){
                  this.messages.push("La police n°  "+this.listDossier[0]+" est deja existant dans la base archive");
                }else if(data[0]!=null){
                  this.messages.push(`<span style='color:green'>  La police n° ${this.listDossier[0]} est ajouté avec succès.</span> <br>
                    Détails Police : <br>
                    <b>Branche :</b> ${data[0].libBranche} <br>
                    <b>Sous Branche :</b> ${data[0].libSousBranche} <br>
                    <b>Code Agence :</b> ${data[0].codeAgence} <br>
                    <b>Nom client :</b> ${data[0].nomClient} 
                  `);
                }
                 
                Swal.fire({
                  title: 'Information!',
                  html: this.messages.join('<br>'),
                  icon: 'info',
                  confirmButtonText: 'OK',
                  customClass: {
                    popup: 'swal-wide' // Custom class for wider alert
                  }
                });*/ 
                this.inputN_dossier='';
                this.typeDossier='';
                this.categorie='';
                this.inputannee='';
                
              
              
                
              
                
              })
              this.messages=[''];
            
        //  }
       // })


       
      }else if(this.typeDossier == "Sinistre"){
        

      /*  this.ajoutDService.addFolder_v2(this.typeDossier,this.listDossier).subscribe(data1=>{
          if(data1.length==0){
            Swal.fire({
              icon: 'error',  
              title: '',  
              text: 'Le sinistre n° '+this.listDossier[0]+' est inexistant sur PROASSUR',   
      
            })
            
          }*/
       //   else {
            
              this.ajoutDService.addFolder(this.typeDossier,this.listDossier,this.name).subscribe(data=>{
                console.log(data)
             /**  if(data==null){
                  this.messages.push("Le sinistre n° "+this.listDossier[0]+" est deja existant dans la base archive");
                }
               else if(data[0]!=null){
                  this.messages.push(`<span style='color:green'>Le sinistre n° ${this.listDossier[0]} est ajouté avec succés </span> <br>
                    Détails Sinistre : <br>
                    <b>Numéro police :</b> ${data[0].npolice} ( Code Agence= ${data[0].codeAgence} ) <br>
                    <b>Branche :</b> ${data[0].libBranche} <br>
                    <b>Sous Branche :</b> ${data[0].libSousBranche} <br>
                    <b>Type Dossier :</b> ${data[0].typeDossier} <br>
                    <b>Etat Dossier :</b> ${data[0].etatDossier}<br>
                    <b>Gestionnaire :</b> ${data[0].gestionnaire}<br>
                    

                  `);
                }
                 
                Swal.fire({
                  title: 'Information!',
                  html: this.messages.join('<br>'),
                  icon: 'info',
                  confirmButtonText: 'OK',
                  customClass: {
                    popup: 'swal-wide' // Custom class for wider alert
                  }
                }); */  

                let tableRows = '';
                let tableRows2='';

                // Loop through the data to generate table rows
                data[0].forEach((item: { dossier: string; erreur: string; }) => {
                  if(item.erreur!=null){
                  tableRows += `
                    <tr>
                      <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;width: 174px;" >${item.dossier}</td>
                      <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">${item.erreur}</td>
                    </tr>
                  `;
                  }
                });

                data[1].forEach((item: { nsinistre: string; libBranche: string; typeDossier: string; }) => {
                  tableRows2 += `
                    <tr>
                      <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 158px;">${item.nsinistre}</td>
                      <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 215px;">${item.libBranche}</td>
                      <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;">${item.typeDossier}</td>
                    </tr>
                  `;
                  
                });
                
                // Call Swal.fire with the dynamically generated table
                Swal.fire({
                  title: 'Details Ajout dossier(s)',
                  html: `
                  <h1 style="color:red;">Rapport des erreurs:</h1><br>
                   <table border="1" class="swErr1" style="width: 100%; text-align: center;">
                    <thead>
                        <tr>
                            <th width="176" style="text-align: center;">N° dossier</th>
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

                  <br>
                  <br>
                  <br>

                  <!-- Second Table -->
                  <h1 style="color:green;">Journal des dossiers ajoutés:</h1><br>
                       <table border="1" class="swErr2" style="width: 100%; text-align: center;">
                       <thead>
                        <tr>
                         <th width="160" style="text-align: center;">N° dossier</th>
                         <th  width="215" style="text-align: center;">Branche</th>
                         <th style="text-align: center;">Type Dossier</th>
                        </tr>
                      </thead>
                 <tbody>
                         <tr>
                          <td colspan="3">
                             <div style="overflow:scroll; height:150px;">
                               <table class="swErr2" style="width: 100%; text-align: center;">
                                    ${tableRows2}
                               </table>
                           </div>
                         </td>
                       </tr>
                 </tbody>
          </table>
       </div>
  `,

                  
                  confirmButtonText: 'Fermer',
                  width: '800px'
                });
                this.inputN_dossier='';
                this.typeDossier='';
                this.categorie='';
                this.inputannee='';
                
                
              })
              this.messages=[''];
       //   }
      //  })
       
      
      }else if(this.typeDossier == "Cxp"){
        
       /* this.ajoutDService.addFolder_v2(this.typeDossier,this.listDossier[0]).subscribe(data1=>{
          if(data1.length==0){
            Swal.fire({
              icon: 'error',  
              title: '',  
              text: 'Le dossier CXP n° '+this.listDossier[0]+' est inexistant sur PROASSUR',   
      
            })
            
          }*/
         // else {
              this.ajoutDService.addFolder(this.typeDossier,this.listDossier,this.name).subscribe(data=>{
                console.log(data)
             /**   if(data==null){
                  this.messages.push("le dossier cxp n° "+this.listDossier[0]+" est deja existant dans la base archive");
                }
                else if(data[0]!=null){
                  this.messages.push(` <span style='color:green'> Le dossier cxp n° ${this.listDossier[0]} est ajouté avec succés </span>  <br>
                    
                    Détails dossier cxp : <br>
                    <b>Dossier Cxp :</b> ${data[0].ncxp} <br>
                    <b>Code Agence :</b> ${data[0].codeAgence} <br>
                    <b>Nom Client :</b> ${data[0].nomClienCxp}<br>
                  `);
                }
                Swal.fire({
                  title: 'Information!',
                  html: this.messages.join('<br>'),
                  icon: 'info',
                  confirmButtonText: 'OK',
                  customClass: {
                    popup: 'swal-wide' // Custom class for wider alert
                  }
                });*/ 

                let tableRows = '';
                let tableRows2='';

                // Loop through the data to generate table rows
                data[0].forEach((item: { dossier: string; erreur: string; }) => {
                  if(item.erreur!=null){
                  tableRows += `
                    <tr>
                      <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;width: 176px;">${item.dossier}</td>
                      <td style="color: red; border: 1px solid black; text-align: center; vertical-align: middle;">${item.erreur}</td>
                    </tr>
                  `;
                  }
                });

                data[1].forEach((item: { ncxp: string; nomClienCxp: string;  villeClientcxp:string; }) => {
                  
                  tableRows2 += `
                    <tr>
                      <td style="color: darkgreen; border: 1px solid black; text-align: center; vertical-align: middle;width: 128px;">${item.ncxp}</td>
                      <td style="color: darkgreen; border: 1px solid black; text-align: center; vertical-align: middle;width: 375px;">${item.nomClienCxp}</td>
                      <td style="color: darkgreen; border: 1px solid black; text-align: center; vertical-align: middle;">${item.villeClientcxp}</td>

                    </tr>
                  `;
                  
                });
                
                // Call Swal.fire with the dynamically generated table
                Swal.fire({
                  title: 'Details Ajout dossier(s)',
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

                  <br>
                  <br>
                  <br>

                  <!-- Second Table -->
                  <h1 style="color:green;">Journal des dossiers ajoutés:</h1><br>
                       <table border="1" class="swErr2" style="width: 100%; text-align: center;">
                       <thead>
                        <tr>
                            <th width="130" style="text-align: center;">N° dossier</th>
                            <th width="375" style="text-align: center;">Nom client</th>
                            <th style="text-align: center;">Ville client</th>
                        </tr>
                      </thead>
                 <tbody>
                         <tr>
                          <td colspan="3">
                             <div style="overflow:scroll; height:150px;">
                               <table class="swErr2" style="width: 100%; text-align: center;">
                                    ${tableRows2}
                               </table>
                           </div>
                         </td>
                       </tr>
                 </tbody>
          </table>
       </div>
  `,

                  
                  confirmButtonText: 'Fermer',
                  width: '800px'
                });
                this.inputN_dossier='';
                this.typeDossier='';
                this.categorie='';
                this.inputannee='';
               
                
              })
              this.messages=[''];
            
        //  }
       // })
        
      
      }
    
  
      
  }
  
  

  



  AjoutListDossiers(){
    if(this.typeDossier==null || this.typeDossier==""){
      Swal.fire({  
        icon: 'error',  
        title: '',  
        text: 'Vous devez indiquer le type de dossier.',   
      })
    }
    this.listDossier= this.splitStringByNChars(this.inputN_dossier);
    this.addDossier();

    
    
  }

  splitStringByNChars(input: string): string[] {
    
    const result: string[] = [];
    let tempStr = "";
  
    for (const char of input) {
      if (char !== " " && char !== "\n" && char !== "\t") {  // Ignorer les espaces
        tempStr += char;
      }
      if(this.typeDossier=="Police"){
        if (tempStr.length === 10) {  // Quand la chaîne temporaire atteint 10 caractères
          result.push(tempStr);
          tempStr = "";
        }
      }else if(this.typeDossier=="Sinistre"){
        if (tempStr.length === 11) {  // Quand la chaîne temporaire atteint 11 caractères
          result.push(tempStr);
          tempStr = "";
        }
      }else if(this.typeDossier=="Cxp"){
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

 


 

}
