import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-recherche-d',
  templateUrl: './recherche-d.component.html',
  styleUrls: ['./recherche-d.component.css']
})
export class RechercheDComponent implements OnInit {
  typeDossier!: string;

  NumDossier!: string;
  data: any;
  nameArchiviste: any;

  constructor(private rechercheService: RestapiService, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    var NAME = localStorage.getItem('name');
    this.nameArchiviste = NAME;
  }


  rechercheDossier() {
    //this.checkCategorie();
    this.rechercheService.getDossier(this.typeDossier, this.NumDossier).subscribe(data => {
      console.log(data);
       this.data=data;
      this.rechercheService.getData(this.data,this.typeDossier);
      if (data[0] == null || data[0].length==0) {
        if(this.typeDossier == "Police"){
          Swal.fire(
            '',
            'La police N° '+ this.NumDossier + ' n\'existe pas dans la base archive!',
            'warning'
          );
        }
        else if(this.typeDossier == "Sinistre") {
          Swal.fire(
            '',
            'Le sinistre N° '+ this.NumDossier + ' n\'existe pas dans la base archive !',
            'warning'
          );

        }
        else if(this.typeDossier == "Cxp") {
          Swal.fire(
            '',
            'Le dossier Cxp N° '+ this.NumDossier + ' n\'existe pas dans la base archive !',
            'warning'
          );

        }
      }else if(data[0]!=null){
        this.router.navigateByUrl('Dossier/recherche/consultation');
        //this.router.navigateByUrl('login/ArchiMenu/RechercheD');
      }
    });

  }
/** 
      let showHistorical = true;
      if (data[1].length == 0) {
        showHistorical = false;
      }

      Swal.fire({
        title: 'Checkout form',
        html: `
            <form id="checkoutForm" class="needs-validation" novalidate >
              <div class="row g-4"  >
                <div class="col-sm-6" style="text-align:left;margin-top: 12px;margin-bottom: 0;">
                  <label for="NumeroPolice" class="form-label">Numero Police</label>
                  <input type="text" class="form-control" id="NumeroPolice"   value="${data[0].npolice}"readonly >
                </div>
                <div class="col-sm-6" style="text-align:left;margin-top: 12px;margin-bottom: 0;">
                  <label for="nomclient" class="form-label" >Nom Client</label>
                  <input type="text" class="form-control" id="nomclient"  value="${data[0].nomClient}" readonly>
                </div>
                <div class="col-sm-6" style="text-align:left;margin-top: 12px;margin-bottom: 0;">
                  <label for="branche" class="form-label">Branche</label>
                  <div class="input-group has-validation">
                    <input type="text" class="form-control" id="branche" value="${data[0].libBranche}" readonly>
                  </div>
                </div>
                <div class="col-sm-6" style="text-align:left;margin-top: 12px;margin-bottom: 0;">
                  <label for="sousBranche" class="form-label">Sous Branche</label>
                  <input type="text" class="form-control" id="sousBranche" value="${data[0].libSousBranche}" readonly>
                  <div class="invalid-feedback">Please enter a valid email address.</div>
                </div>
                <div class="col-sm-6" style="text-align:left;margin-top: 12px;margin-bottom: 0;">
                  <label for="user" class="form-label">Archiviste</label>
                  <input type="text" class="form-control" id="user" value="${data[0].utilisateur}" readonly>
                </div>
                <div class="col-sm-6" style="text-align:left;margin-top: 12px;margin-bottom: 0;">
                  <label for="codeAgence" class="form-label">Code Agence</label>
                  <input type="text" class="form-control" id="codeAgence" value="${data[0].codeAgence}" readonly>
                </div>
                <div class="col-sm-6" style="text-align:left;margin-top: 12px;margin-bottom: 0;">
                  <label for="dataAjout" class="form-label">Date Ajout dossier</label>
                  <input type="text" class="form-control" id="dataAjout" value="${data[0].dateAjout}" readonly>
                </div>
                
                ${showHistorical ? `
                  <div class="card" style="width: 500px; margin-top: 20px;">
                    <label for="historique" class="form-label">Historique Dossier</label>
                    <div class="card-header text-primary d-flex justify-content-between align-items-center">
                      <h5 class="card-title mb-0">${data[1][0].naffaire}</h5>
                      <span class="badge bg-primary rounded-pill">1</span>
                    </div>
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                          <h6 class="my-0">Consulter Par</h6>
                          <small class="text-muted">${data[1][0].consulterPar} Le</small>
                        </div>
                        <span class="text-muted">${data[1][0].dateConsultation}</span>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        <div>
                          <h6 class="my-0">${data[1][0].typeAffaire}</h6>
                          <small class="text-muted">Date retour estimé</small>
                        </div>
                        <span class="text-muted">${data[1][0].dateRetour}</span>
                      </li>
                    </ul>
                  </div>

                ` : `
                      
                <!-- State Selection -->
                <div class="col-md-4">
                    <label for="state" class="form-label">State</label>
                    <select class="form-select" id="state" required>
                        <option value="">Choose...</option>
                        <option>Police</option>
                        <option>Sinistre</option>
                        <option>Cxp</option>
                    </select>
                    <div class="invalid-feedback">
                        Please provide a valid state.
                    </div>
                </div>

                <!-- Numero Dossier -->
                <div class="col-sm-6" style="text-align: left; margin-top: 12px; margin-bottom: 0;">
                    <label for="NumeroDossier" class="form-label">Numero Police</label>
                    <input type="text" class="form-control" id="NumeroDossier">
                </div>

                <!-- Nom Personnel Technique -->
                <div class="col-sm-6" style="text-align: left; margin-top: 12px; margin-bottom: 0;">
                    <label for="NomPer" class="form-label">Nom Personnel Technique</label>
                    <input type="text" class="form-control" id="NomPer">
                </div>

                <!-- Date Consultation -->
                <div class="col-sm-6" style="text-align: left; margin-top: 12px; margin-bottom: 0;">
                    <label for="datecons" class="form-label">Date Consultation</label>
                    <input type="date" class="form-control" id="datecons">
                </div>

                <!-- Date Retour -->
                <div class="col-sm-6" style="text-align: left; margin-top: 12px; margin-bottom: 0;">
                    <label for="dateret" class="form-label">Date Retour</label>
                    <input type="date" class="form-control" id="dateret">
                </div>

                <!-- Confirmation Button -->
                <button type="button" class="btn btn-primary" style="width: 220px;height: 50px;margin-left: 431px;">Confirmer la Consultation</button>

                   
               `}

              </div>
            </form>
          `,
        showCancelButton: true,
        width: '1200px',
        confirmButtonText: 'Retour',
      }).then((result) => {
        if (result.isConfirmed) {
          this.rechercheService.deleteconsultation(data[1][0].naffaire).subscribe(data => {
            console.log(data)
          })
          console.log(result.value);  // Handle form submission here
          Swal.fire('Form Submitted!', 'Thank you for your submission.', 'success');
        }
      });
      */

       
    

  checkCategorie() {
    if (this.typeDossier == null) {
      Swal.fire(
        '',
        'Type de dossier est non null!',
        'error'
      );
    }
    else if (this.typeDossier == 'Police' && this.NumDossier.length > 10) {
      Swal.fire(
        '',
        'Numéro de dossier est incorrect',
        'error'
      );
      this.NumDossier = '';
    }
    else if (this.typeDossier == 'Sinistre' && this.NumDossier.length > 11) {
      Swal.fire(
        '',
        'Numéro de dossier est incorrect',
        'error'
      );
      this.NumDossier = '';
    }
    else if (this.typeDossier == 'Cxp' && this.NumDossier.length > 8) {
      Swal.fire(
        '',
        'Numéro de dossier est incorrect',
        'error'
      );
      this.NumDossier = '';
    }
  }

}
