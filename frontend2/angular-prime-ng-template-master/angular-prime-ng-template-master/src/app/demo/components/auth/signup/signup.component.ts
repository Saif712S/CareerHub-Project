import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { SignupRequestPayload } from 'src/app/model/SignupRequestPayload';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styles:  [`
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
export class SignupComponent implements OnInit {

  valCheck: string[] = ['remember'];

    signupForm!: FormGroup;
    signupRequestPayload!:SignupRequestPayload;



    constructor(public layoutService: LayoutService,private authService:AuthService,
      private router: Router,
      private toastr: ToastrService,
      private formBuilder: FormBuilder) {this.signupForm = this.formBuilder.group({
        username: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        cin: ['', Validators.required]
      });
    this.signupRequestPayload ={
      username:'',
      email:'',
      password:'',
      firstName: '',
      lastName: '',
      cin: ''
  
    }}


  ngOnInit(): void {
  }
  initSignUpForm() {
    this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }
  signup() {
    if (this.signupForm.valid) {
      this.signupRequestPayload = this.signupForm.value;
      this.authService.signup(this.signupRequestPayload).subscribe(
        data => {
          console.log(data)
          this.router.navigate([''])
         },
        () => {
          this.toastr.error('Registration Failed! Please try again');
        }
      );
    } else {
      this.toastr.error('Registration Failed! Please try again');
    }

  }

}
