import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ConsultationParlotsService } from '../consultation-parlots.service';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-recuperer-dossiers',
  templateUrl: './recuperer-dossiers.component.html',
  styleUrls: ['./recuperer-dossiers.component.css']
})
export class RecupererDossiersComponent implements OnInit {

  constructor(private router: Router,private recuperation: ConsultationParlotsService,private rechercheService: RestapiService ) { }
  typeDossier!:string
  dossiers!: string
  selectedDate:Date | null = null;
  name!:string;
  listDossiers:any=[];
  data:any;
  typeAux!:string;


  ngOnInit(): void {
    setTimeout(() => {
      var NAME=localStorage.getItem('name');
      if (NAME !== null) {
        this.name = NAME;
      }
    
  }, 500);
    
  }

  recupererDossiers(){
    console.log(this.typeDossier)
    console.log(this.dossiers)
    console.log(this.selectedDate)
  }


  deleteConsutation(){
    this.typeAux=this.typeDossier;
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
        if(this.selectedDate==null || this.selectedDate==undefined || this.selectedDate.toString()=="undefined" || this.selectedDate.toString()==""){
          this.selectedDate= new Date()
         let currentdate=this.formatDateTime(this.selectedDate);
         this.listDossiers=this.splitStringByNChars(this.dossiers)
         this.recuperation.deleteconsultation(this.listDossiers,this.name,currentdate).subscribe(data=>{
          console.log(data);
          
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

          data[1].forEach((item: { dossier: string; message: string;  }) => {
            
            tableRows2 += `
              <tr>
                <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 153px;">${item.dossier}</td>
                <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 211px;">${item.message}</td>
                <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 50px;"">
              <button type="button" class="btn btn-info detailPoliceButton" data-dossier="${item.dossier}" style="width: 40px; height: 36px; margin-bottom: 3px;">
&#9851;              </button>
            </td>
              </tr>
            `;
            
          });
          
          // Call Swal.fire with the dynamically generated table
          Swal.fire({
            title: 'Details Récupération dossier(s)',
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
            <h1 style="color:green;">Journal des dossiers récupérés:</h1><br>
                 <table border="1" class="swErr2" style="width: 100%; text-align: center;">
                 <thead>
                  <tr>
                   <th width="148" style="text-align: center;">N° dossier</th>
                   <th width="207" style="text-align: center;">Message</th>
                   <th width="61" style="text-align: center;">Détails</th>
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
                    this.typeDossier=this.typeAux;
                    this.rechercheDossier(this.typeDossier, dossier);
                    console.log(this.typeDossier)
                    Swal.close();
                  }
                });
              });
            },
          });

          this.typeDossier=''
          this.dossiers=''
          this.selectedDate=null;

         })
        }else{
          const specificDate = new Date(this.selectedDate);
          let currentdate=this.formatDateTime(specificDate);
          this.listDossiers=this.splitStringByNChars(this.dossiers)
          this.recuperation.deleteconsultation(this.listDossiers,this.name,currentdate).subscribe(data=>{
           
            console.log(data);
          
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

          data[1].forEach((item: { dossier: string; message: string;  }) => {
            
            tableRows2 += `
              <tr>
                <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 153px;">${item.dossier}</td>
                <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 211px;">${item.message}</td>
                <td style="color: blue; border: 1px solid black; text-align: center; vertical-align: middle;width: 50px;"">
              <button type="button" class="btn btn-info detailPoliceButton" data-dossier="${item.dossier}" style="width: 40px; height: 36px; margin-bottom: 3px;">
&#9851;              </button>
            </td>
              </tr>
            `;
            
          });
          
          // Call Swal.fire with the dynamically generated table
          Swal.fire({
            title: 'Details Récupération dossier(s)',
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
            <h1 style="color:green;">Journal des dossiers récupérés:</h1><br>
                 <table border="1" class="swErr2" style="width: 100%; text-align: center;">
                 <thead>
                  <tr>
                   <th width="148" style="text-align: center;">N° dossier</th>
                   <th width="207" style="text-align: center;">Message</th>
                   <th width="61" style="text-align: center;">Détails</th>
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
                    this.typeDossier=this.typeAux;
                    this.rechercheDossier(this.typeDossier, dossier);
                    console.log(this.typeDossier)
                    Swal.close();
                  }
                });
              });
            },
          });
          this.dossiers=''
          this.selectedDate=null;

         })
              
        }
       
        // Perform the action for confirmation (e.g., delete an item)
        
        this.router.navigateByUrl("Dossier/RecupererDossiers");
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire(
          'Annulé',
          'Récupération annulée',
          'error'
        );
      }
    });
  
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

  rechercheDossier(type:string,numero:string) {
    //this.checkCategorie();
    this.rechercheService.getDossier(type, numero).subscribe(data => {
      console.log(data);
       this.data=data;
      this.rechercheService.getData(this.data,this.typeDossier);
      this.router.navigateByUrl('Dossier/recherche/consultation');
    });

  }

 
  

}
