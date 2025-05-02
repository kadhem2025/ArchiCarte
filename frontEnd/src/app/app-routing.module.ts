import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DemendePRComponent } from './demende-pr/demende-pr.component';
import { AjoutDComponent } from './ajout-d/ajout-d.component';
import { ArchiMenuComponent } from './archi-menu/archi-menu.component';
import { RechercheDComponent } from './recherche-d/recherche-d.component';
import { GridDComponent } from './grid-d/grid-d.component';
import { ReservationComponent } from './reservation/reservation.component';
import { AjoutPRComponent } from './ajout-pr/ajout-pr.component';
import { AdminMenuComponent } from './admin-menu/admin-menu.component';
import { GererPRComponent } from './gerer-pr/gerer-pr.component';
import { ConsulterPRComponent } from './consulter-pr/consulter-pr.component';
import { DemandeArchiComponent } from './demande-archi/demande-archi.component';
import { GridArchiComponent } from './grid-archi/grid-archi.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { LiteLivraisonComponent } from './lite-livraison/liste-livraison.component';
import { PersonMenuComponent } from './person-menu/person-menu.component';
import { RechercheEtReservationComponent } from './recherche-et-reservation/recherche-et-reservation.component';
import { StatistiqueComponent } from './statistique/statistique.component';
import { ConsultationDossiersComponent } from './consultation-dossiers/consultation-dossiers.component';
import { RecupererDossiersComponent } from './recuperer-dossiers/recuperer-dossiers.component';

const routes: Routes = [
  {path: "" , component: LoginComponent},
  {path: "login" , component: LoginComponent},
  {path: "login/demandePR", component: DemendePRComponent},
  {path: "login/ArchiMenu/AjoutD", component: AjoutDComponent },
  {path: "login/ArchiMenu" , component: ArchiMenuComponent},
  {path: "login/ArchiMenu/RechercheD" , component: RechercheDComponent},
  {path: "login/ArchiMenu/RechercheD/gridD",component:GridDComponent},
  {path: "login/ArchiMenu/RechercheD/gridD/reservation",component:ReservationComponent},
  {path: "login/Admin-menu/AjoutPR", component: AjoutPRComponent},
  {path: "login/Admin-menu" , component: AdminMenuComponent},
  {path: "login/Admin-menu/GererPR", component: GererPRComponent},
  {path: "login/Admin-menu/GererPR/consulterPr", component: ConsulterPRComponent},
  {path: "login/ArchiMenu/demande-archi", component: DemandeArchiComponent },
  {path: "login/ArchiMenu/demande-archi/grid-archi", component: GridArchiComponent},
  {path: "login/forgotPassword",component : ForgotPasswordComponent},
  {path: "login/changePassord/lkjsdhsdquydqsdqomiqd54868qdsbqsdjjgqsgvqs5454sdqskjbqsgazuhsjhgsd11156efjdn",component:ChangePasswordComponent},
  {path: "login/ArchiMenu/listReservation", component: LiteLivraisonComponent},
  {path: "login/person-menu", component: PersonMenuComponent},
  {path: "login/person-menu/transfert", component: DemendePRComponent},
  {path: "Dossier/recherche/consultation", component: RechercheEtReservationComponent},
  {path: "Dossier/statistique", component:StatistiqueComponent},
  {path: "Dossier/ConsulationDossiers", component :ConsultationDossiersComponent},
  {path: "Dossier/RecupererDossiers", component : RecupererDossiersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
