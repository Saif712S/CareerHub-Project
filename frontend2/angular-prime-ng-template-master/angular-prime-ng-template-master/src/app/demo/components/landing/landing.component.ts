import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthConfig, NullValidationHandler, OAuthService } from 'angular-oauth2-oidc';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { OffreEmploi } from 'src/app/model/OffreEmploi';
import { OffreEmploiService } from 'src/app/services/offre-emploi.service';
import { Observable, tap } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { FormGroup } from '@angular/forms';
import { LoginRequest } from 'src/app/model/LoginRequest';
import { AuthService } from 'src/app/services/auth.service';




@Component({
    selector: 'app-landing',
    templateUrl: './landing.component.html',
    styles: [`
        #hero{
            background: linear-gradient(0deg, rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 0.2)), radial-gradient(77.36% 256.97% at 77.36% 57.52%, #EEEFAF 0%, #C3E3FA 100%);
            height:700px;
            overflow:hidden;
        }

        .pricing-card:hover{
            border:2px solid var(--cyan-200) !important;
        }

        @media screen and (min-width: 768px) {
            #hero{
                -webkit-clip-path: ellipse(150% 87% at 93% 13%);
                clip-path: ellipse(150% 87% at 93% 13%);
                height: 530px;
            }
        }

        @media screen and (min-width: 1300px){
            #hero > img {
                position: absolute;
                transform:scale(1.2);
                top:15%;
            }

        #hero > div > p {
                max-width: 450px;
            }
        }

        @media screen and (max-width: 1300px){
            #hero {
                height: 600px;
            }

        #hero > img {
            position:static;
            transform: scale(1);
            margin-left: auto;
        }

        #hero > div {
            width: 100%;
        }

        #hero > div > p {
                width: 100%;
                max-width: 100%;
            }
        }
    `]
})
export class LandingComponent implements OnInit {
  
  offres!:OffreEmploi[];
  username!:string;
  submitted=false;
  isLoggedIn!:boolean;
  isAdmin!:boolean;
  @Input() isLogged!:boolean;
  @Input() isAdminn!:boolean;
  constructor(public layoutService: LayoutService, public router: Router,private oauthService:OAuthService,private offreEmploiService:OffreEmploiService) {     this.configure();
    this.oauthService.events.subscribe(event => {
      if (event.type === 'token_received') {
        const accessToken = this.oauthService.getAccessToken();
        console.log("Access token received:", accessToken);
        localStorage.setItem('access_token', accessToken);
      }
    });
  }
  authFlowConfig: AuthConfig = {
      issuer: 'http://localhost:8080/realms/CareerHub',
      requireHttps: false,
      redirectUri: window.location.origin,
      clientId: 'angular-client',
      responseType: 'code',
      scope: 'openid profile email offline_access',
  
      showDebugInformation: true,
    };
    configure():void{
      this.oauthService.configure(this.authFlowConfig);
      this.oauthService.tokenValidationHandler=new NullValidationHandler;
      this.oauthService.setupAutomaticSilentRefresh();
      this.oauthService.loadDiscoveryDocument().then(()=>this.oauthService.tryLogin())
      .then (()=>{
        if(this.oauthService.getIdentityClaims()){
          this.isLoggedIn=this.getIsLoggedIn();
          this.username=this.getUsername();
  
         // this.preferredUsername;
         // this.username=this.oauthService.()[`preferred_username`];
        }
      });
  
    }

    
  
    login():void{
      this.oauthService.initImplicitFlowInternal();
      this.oauthService.hasValidAccessToken();
      console.log("hello");
    
    this.oauthService.events.subscribe(event => {
      if (event.type === 'token_received') {
        // Token received, now you can access it
        const accessToken = this.oauthService.getAccessToken();

        console.log("Access token received:", accessToken);
        // Save token to localStorage
        localStorage.setItem('access_token', accessToken);
      }
      const refreshToken = this.oauthService.getRefreshToken();
      localStorage.setItem('refreshToken', refreshToken);
    });
  }
  refreshToken():string{
    return this.oauthService.getRefreshToken();
  }
      public getIsLoggedIn():boolean{
    
        return (this.oauthService.hasValidAccessToken() && this.oauthService.hasValidAccessToken());
      }
      private storeToken(token: string): void {
        // Implement your secure token storage logic here
        localStorage.setItem('access_token', token);
    }
      logout():void{
        this.oauthService.logOut();
    
      }
     
    
    
    
      public getIsAdmin():boolean{
        const token=this.oauthService.getAccessToken();
        const payload=token.split('.')[1];
        const payloadDecodedJson=atob(payload);
        const payloadDecoded= JSON.parse(payloadDecodedJson);
        const preferredUsername = payloadDecoded.preferred_username
        console.log(payloadDecoded);
        console.log(preferredUsername)
        return payloadDecoded.realm_access.roles.indexOf('Admin') !==-1;
    
      }
      public getUsername():string{
        const token=this.oauthService.getAccessToken();
        console.log(token);
        const payload=token.split('.')[1];
        const payloadDecodedJson=atob(payload);
        const payloadDecoded= JSON.parse(payloadDecodedJson);
        const preferredUsername = payloadDecoded.preferred_username
        console.log(preferredUsername);
       return preferredUsername;
      }
      ngOnInit(): void {
        this.offreEmploiService.getAllOffres().subscribe(offres=>this.offres=offres);
      }
     
    
    }
