import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RestapiService } from '../restapi.service';
import { catchError, pipe } from 'rxjs';
import Swal from 'sweetalert2';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  selectedOption: string = 'personnel'; // Initial selected option
  username: string = '';
  password: string = '';
  showPassword: boolean = false;
  errorMessage: string = '';
  model: any={};
  getData: any;
  test: any;
  err: any;
  responseData: any;
  nameUser!:string;
  header!: HeaderComponent;
 
 

  constructor(private router: Router,private authService: RestapiService) {}

  ngOnInit(): void {
    // Handle radio button selection changes
    // Update 'selectedOption' property based on radio button selection
  
  }

  saveLocalName(){
    let originalString: string = this.model.username;
let stringWithSpaces: string = originalString.replace(/\./g, " ");

localStorage.setItem('name', stringWithSpaces);
console.log(localStorage.getItem('name'));
var storedValue= localStorage.getItem('name');
if(storedValue!=null)
this.authService.getNameUser(storedValue)
  }

  // Method to handle routing based on selected option
  handleRouting(): void {
    switch (this.selectedOption) {
      case 'personnel':
        this.loginPersonnel();
        localStorage.setItem('Status', 'Déconnexion');
        this.authService.sendClickEvent();
        break;
      case 'archiviste':
        this.loginArchiviste();
        localStorage.setItem('Status', 'Déconnexion');
        this.authService.sendClickEvent();
        break;
        case 'administrateur':
          this.loginAdmin()
          localStorage.setItem('Status', 'Déconnexion');
          this.authService.sendClickEvent();
        
        break;
      default:
        // Handle default route if no option is selected
    }
  }
    
  
    togglePasswordVisibility() {
      this.showPassword = !this.showPassword;
    }
    

    

    loginAdmin() {
       var username = this.model.username;
       var password = this.model.password;
       var profil =this.selectedOption
      
       this.authService.login(username,password,profil).subscribe(
        (response) => {
          // Gérer la réponse réussie ici
          this.responseData = response;
          console.log('Réponse de la connexion :', response);
        },
        (error) => {
          // Gérer l'erreur ici
          console.error('Erreur lors de la connexion :', error);
          if (error.status === 401) {
            Swal.fire({  
              icon: 'error',  
              title: '',  
              text: 'Nom utilisateur ou mot de passe invalide !',   
            })
            this.authService.checkUser(-1);
            this.responseData = 'Authentication failed';
          }
          else if(error.status === 200){
            this.router.navigateByUrl('login/Admin-menu');
            this.saveLocalName();
            this.authService.checkUser(1);
            
          }
        }
      );
    }

    loginPersonnel() {
      var username = this.model.username;
      var password = this.model.password;
      var profil =this.selectedOption
     
      this.authService.login(username,password,profil).subscribe(
       (response) => {
         // Gérer la réponse réussie ici
         this.responseData = response;
         console.log('Réponse de la connexion :', response);
       },
       (error) => {
         // Gérer l'erreur ici
         console.error('Erreur lors de la connexion :', error);
         if (error.status === 401) {
          Swal.fire({  
            icon: 'error',  
            title: '',  
            text: 'Nom utilisateur ou mot de passe invalide !',   
          })
          this.authService.checkUser(-1);
           this.responseData = 'Authentication failed';
         }
         else if(error.status === 200){
          this.router.navigateByUrl('login/demandePR');
          this.saveLocalName();
          this.authService.checkUser(1);
          this.authService.getUserName(username);
         }
       }
     );
   }


   loginArchiviste() {
    var username = this.model.username;
    var password = this.model.password;
    var profil =this.selectedOption
   
    this.authService.login(username,password,profil).subscribe(
     (response) => {
       // Gérer la réponse réussie ici
       this.responseData = response;
       console.log('Réponse de la connexion :', response);
     },
     (error) => {
       // Gérer l'erreur ici
       console.error('Erreur lors de la connexion :', error);
       if (error.status === 401) {
        Swal.fire({  
          icon: 'error',  
          title: '',  
          text: 'Nom utilisateur ou mot de passe invalide !',   
        })
        this.authService.checkUser(-1);
         this.responseData = 'Authentication failed';
       }
       else if(error.status === 200){
        this.router.navigateByUrl('login/ArchiMenu');
        this.saveLocalName();
        this.authService.checkUser(1);
       }
     }
   );
 }

 
      };
       
    

