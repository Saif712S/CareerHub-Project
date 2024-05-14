import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthConfig, NullValidationHandler, OAuthService } from 'angular-oauth2-oidc';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { LoginRequest } from 'src/app/model/LoginRequest';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        :host ::ng-deep .p-password input {
            width: 100%;
            padding:1rem;
        }

        :host ::ng-deep .pi-eye{
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }

        :host ::ng-deep .pi-eye-slash{
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }
    `]
})
export class LoginComponent implements OnInit {
  public show: boolean = false;
  public loginForm: FormGroup | any;
  public errorMessage: string = '';
  loginCommand: LoginRequest = { username: '', password: '' };
  submitted = false;
  isInputFocused: boolean = false;


  constructor(
    private formBuilder: FormBuilder,
    private loginService: AuthService,
    private router: Router
  ) {
    document.querySelector('body')?.classList.add('login-img');
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  log() {
    if (this.loginForm.valid) {
      this.loginCommand = this.loginForm.value;
      console.log(this.loginCommand);
      console.log(this.loginForm);

      this.loginService.login2(this.loginCommand)
        .subscribe((response: any) => {
          console.log('Login passed:', response);
          
          // Check if access token is present in local storage
          const accessToken = this.loginService.getAccessToken();
          if (!accessToken) {
            console.error('Access token not found in local storage');
            return;
          }
  
          // Retrieve user roles from the access token
          const userRoles = this.loginService.getUserRoles();
          console.log('User roles:', userRoles); // Log user roles
          
          // Check if user roles include 'Admin'
          if (userRoles && userRoles.includes('Admin Entreprise')) {
            this.router.navigate(['/dashboard']); // Redirect to dashboard for admin
          } else {
            this.router.navigate(['']); // Redirect to landing page for non-admin users
          }
        }, (error) => {
          console.error('Login error:', error);
          this.errorMessage = 'Le nom de l\'utilisateur ou le mot de passe est incorrect';
        });
    }
  }
  
  

  onSubmit(): void {
    this.submitted = true;
  
    if (this.loginForm.invalid) {
      return;
    }
  
    this.loginService.login2(this.loginForm.value)
      .subscribe(
        (response: any) => {
          //console.log('Login successful', response);
  
          if (response && response.access_token && response.refresh_token) {
            localStorage.setItem('access_token', response.access_token);
            localStorage.setItem('refresh_token', response.refresh_token);
            console.log('Login passed:', response);
            this.router.navigate(['/dashboard']);
            
        
          } else {
            console.error('Access token or refresh token not found in response');
          }
        },
        error => {
          console.error('Login failed', error);
          this.errorMessage = 'Le nom de l\'utilisateur ou le mot de passe est incorrect';
        }
      );
  }
 

  clearErrorMessage() {
    this.errorMessage = '';
    this.isInputFocused = true; 
  }

  showErrorMessage() {
    this.isInputFocused = false;
  }

  showPassword() {
    this.show = !this.show;
  }

  // Login With Google
  loginGoogle() {
  }

  // Login With Twitter
  loginTwitter(): void {
  }

  // Login With Facebook
  loginFacebook() {
  }

  ngOnDestroy() {
    document.querySelector('body')?.classList.remove('login-img');
  }
}