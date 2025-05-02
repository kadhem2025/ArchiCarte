import { Component, OnInit, ViewChild } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { HttpClient } from '@angular/common/http';
import { ConsulterPRComponent } from '../consulter-pr/consulter-pr.component';

@Component({
  selector: 'app-gerer-pr',
  templateUrl: './gerer-pr.component.html',
  styleUrls: ['./gerer-pr.component.css']
})
export class GererPRComponent implements OnInit {
  @ViewChild(ConsulterPRComponent) childComponent!: ConsulterPRComponent;
  suggestionsNom!: any[];
  profil!:string;
  inputpersonnel!: string;
  constructor(private gereprService: RestapiService,private http: HttpClient) { }

  ngOnInit(): void {
    
  }
  getNames(){
    this.gereprService.getNom(this.profil).subscribe(data => {
      this.suggestionsNom = data;
    });
  }

  sendData() {
    this.gereprService.sendData(this.profil,this.inputpersonnel);
  }

}
