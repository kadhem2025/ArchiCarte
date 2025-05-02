
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {
  private apiUrl = 'http://localhost:8080';
  private apiproassur = 'http://localhost:9090';

  data :any= {};
  data1 :any= {};
  n_police:any={};
  email!:string;
  nameUser!: string;
  module:any;
  status!:string
  Url!:string;
  numberChecked!:number;
  userName!:string;

  private subject = new Subject<any>();

  constructor(private http: HttpClient) {}

  login(username: string, password: string, profil: string): Observable<LoginResult> {
    const loginData = { username, password ,profil};

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.apiUrl}/login`, loginData, { headers });
     
  }

  // ... other methods

  
    addReservation(codeDossier: string,dateRes: Date,dateRet: Date,currentDate:string,namePer:string): Observable<any> {
      const reservation={codeDossier, dateRes, dateRet,currentDate,namePer};
      return this.http.post(`${this.apiUrl}/login/demandePR`, reservation);
    }

  getOptions(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/login/demandePR`);
  }
  addProfil(firstName: string,lastName: string,email:string,password: string,department:string,profil:string): Observable<any> {
    const profile={firstName, lastName,email, password,department,profil};
    return this.http.post(`${this.apiUrl}/login/Admin-menu/AjoutPR`, profile);
  }

  getNom(profil:string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login/Admin-menu/GererPR`, profil);
  }
  getNomPersonnel(): Observable<any> {
    return this.http.get(`${this.apiUrl}/login/ArchiMenu/RechercheD/gridD/reservation`);
  }

  getPersonnel(): Observable<any> {
    const data1=this.data;
    return this.http.post(`${this.apiUrl}/login/Admin-menu/GererPR/consulterPr`, data1);
  }
  sendData(profil:string,name:string){
    this.data={profil,name}
    return this.data;
  }

  deleteUser(login: string,profil:string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/login/Admin-menu/GererPR/consulterPr/${login}/${profil}`);
}

updateUser(newUserUdate: any,aux:string): Observable<any> {
  
  return this.http.put<any>(`${this.apiUrl}/login/Admin-menu/GererPR/consulterPr/${aux}`, newUserUdate);
}

addFolder(typeDossier: string,folders:string[],nameArchiviste:string): Observable<any> {
  
  const listDossiers: { dossier: string; }[]= [];
  folders.forEach(function (doss) {
    const obj = {dossier: doss};
    listDossiers.push(obj);
  });
 
  ///const obj1 = JSON.stringify(obj);
  return this.http.post(`${this.apiUrl}/Dossier/AjoutD/${typeDossier}/${nameArchiviste}`, listDossiers);
}

getDossier(typeDossier: string,n_Dossier: string): Observable<any[]> {
 // return this.http.get<any[]>(`${this.apiUrl}/login/ArchiMenu/RechercheD/${TypeDossier}/${n_Dossier}`);
  return this.http.get<any[]>(`${this.apiUrl}/Dossier/RechercheD/${typeDossier}/${n_Dossier}`);
}

getData(data:any,typeDossier:string){
  this.data1={data,typeDossier};
  return this.data1;
}
griddata(){
  return this.data1;
}
getPolice(url:string,police:any){
  this.n_police=police;
  this.Url=url;
}
setPolice(){
  var police=this.n_police
  var lien=this.Url
  const data={police,lien}
  return data;
}

getconsultation(module1:any){
  this.module=module1;
  return this.module;
}
setconsultation(){
  return this.module;
}

addConsultation(typeAffaire:string,naffaire: string,consulterPar:string,dateConsultation:Date,dateRetour:Date,archivisteCons:string): Observable<any> {
  const consulation={typeAffaire, naffaire,consulterPar,dateConsultation,dateRetour,archivisteCons};
  return this.http.post(`${this.apiUrl}/login/ArchiMenu/RechercheD/gridD/reservation`, consulation);
}

requestPasswordReset(email:string) {
  return this.http.post(`${this.apiUrl}/login/forgotPassword`,email)
    
}

changePassword(username:string,password:string,confirmedPassword:string){
const userinfo={username,password,confirmedPassword}
return this.http.post(`${this.apiUrl}/login/changePassord/lkjsdhsdquydqsdqomiqd54868qdsbqsdjjgqsgvqs5454sdqskjbqsgazuhsjhgsd11156efjdn`,userinfo)
}
getNameUser(name:string){
this.nameUser=name;
}
returnNameUser(){
  return this.nameUser;
}

getListReserervation(){
  return this.http.get(`${this.apiUrl}/login/listReservation`)
}

checkCodeDossier(codeDossier: string): Observable<any> {
  
  return this.http.get(`${this.apiUrl}/login/demandePR/${codeDossier}`);

}


sendClickEvent() {
  this.subject.next(0);
}
getClickEvent(): Observable<any>{ 
  return this.subject.asObservable();
}
getClientDataByCode(uid: string): Observable<any> {
  return this.http.get( `${this.apiproassur}/contrats/${uid}`);
}

deleteconsultation( numDossier: string,nameArchiviste:string,dateRetour:string): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/login/ArchiMenu/listReservation/${numDossier}/${nameArchiviste}/${dateRetour}`);
}

checkExisteFolder(typeDossier:string,numFolder:string,username:string): Observable<any>{
  return this.http.get(`${this.apiUrl}/login/checkDossier/${typeDossier}/${numFolder}/${username}`);
}

checkUser(aux:number){
  this.numberChecked=aux;
}
returnCheckNumber(){
  return this.numberChecked;
}
getUserName(username: string){
  this.userName=username;
}
returnlogin(){
  return this.userName;
}

deleteDossier(typedossier:string | undefined ,numDossier:string): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/login/ArchiMenu/deleteDossier/${typedossier}/${numDossier}`);
}

addFolder_v2(typeDossier:string,folders:string[]): Observable<any>{
  const listDossiers: { dossier: string; }[]= [];
  folders.forEach(function (doss) {
    const obj = {dossier: doss};
    listDossiers.push(obj);
  });
  return this.http.post(`${this.apiUrl}/Dossier/checkdossier/proasurDb/${typeDossier}`,listDossiers)

}

getHistoryService(codeDossier: string): Observable<any> {
  
  return this.http.get(`${this.apiUrl}/history/Dossier/${codeDossier}`);

}

}

interface LoginResult {
  success: boolean;
  data?: any; // Optional data for successful login
  error?: string; // Error message for failed login
}

