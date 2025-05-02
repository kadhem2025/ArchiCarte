import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-archi-menu',
  templateUrl: './archi-menu.component.html',
  styleUrls: ['./archi-menu.component.css']
})
export class ArchiMenuComponent implements OnInit {

  constructor( private router: Router) { }

  ngOnInit(): void {
  }

  goToPath(){
      this.router.navigateByUrl("Dossier/ConsulationDossiers")
  }

  goPath(){
    this.router.navigateByUrl('Dossier/RecupererDossiers');
  }

}
