import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { DemendePRComponent } from './demende-pr/demende-pr.component';
import { FormsModule } from '@angular/forms';
import { AjoutDComponent } from './ajout-d/ajout-d.component';
import { ArchiMenuComponent } from './archi-menu/archi-menu.component';
import { RechercheDComponent } from './recherche-d/recherche-d.component';
import { GridDComponent } from './grid-d/grid-d.component';
import { ReservationComponent } from './reservation/reservation.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { AjoutPRComponent } from './ajout-pr/ajout-pr.component';
import { AdminMenuComponent } from './admin-menu/admin-menu.component';
import { GererPRComponent } from './gerer-pr/gerer-pr.component';
import { ConsulterPRComponent } from './consulter-pr/consulter-pr.component';
import { DemandeArchiComponent } from './demande-archi/demande-archi.component';
import { GridArchiComponent } from './grid-archi/grid-archi.component';
import { RestapiService } from './restapi.service';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { LiteLivraisonComponent } from './lite-livraison/liste-livraison.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { PersonMenuComponent } from './person-menu/person-menu.component';
import { RechercheEtReservationComponent } from './recherche-et-reservation/recherche-et-reservation.component';
import { StatistiqueComponent } from './statistique/statistique.component';
import { ConsultationDossiersComponent } from './consultation-dossiers/consultation-dossiers.component';
import { RecupererDossiersComponent } from './recuperer-dossiers/recuperer-dossiers.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    DemendePRComponent,
    AjoutDComponent,
    ArchiMenuComponent,
    RechercheDComponent,
    GridDComponent,
    ReservationComponent,
    AjoutPRComponent,
    AdminMenuComponent,
    GererPRComponent,
    ConsulterPRComponent,
    DemandeArchiComponent,
    GridArchiComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    LiteLivraisonComponent,
    PersonMenuComponent,
    RechercheEtReservationComponent,
    StatistiqueComponent,
    ConsultationDossiersComponent,
    RecupererDossiersComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
    MatFormFieldModule,
    MatNativeDateModule,
    HttpClientModule,
    NgxPaginationModule,
    
    
  ],
  providers: [RestapiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
