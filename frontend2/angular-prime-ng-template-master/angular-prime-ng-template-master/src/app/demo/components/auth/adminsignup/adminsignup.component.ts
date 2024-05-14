import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import { AddAdminRequest } from 'src/app/model/AddAdminRequest';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-adminsignup',
  templateUrl: './adminsignup.component.html',
  styleUrls: ['./adminsignup.component.scss']
})
export class AdminsignupComponent implements OnInit {
  valCheck: string[] = ['remember'];

  signupForm!: FormGroup;
  signupRequestPayload!:AddAdminRequest;



  constructor(public layoutService: LayoutService,private authService:AuthService,
    private router: Router,
    private toastr: ToastrService,
    private formBuilder: FormBuilder) {this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      cin: ['', Validators.required],
      nom_Entreprise: ['', Validators.required]
    });
  this.signupRequestPayload ={
    username:'',
    email:'',
    password:'',
    firstName: '',
    lastName: '',
    cin: '',
    nom_Entreprise:'',

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
    this.authService.addAdmin(this.signupRequestPayload).subscribe(
      data => {
        console.log(data)
        this.router.navigate(['/dashboard'])
       },
      () => {
        this.toastr.error('Registration With Sucess!');
      }
    );
  } else {
    this.toastr.error('Registration Failed! Please try again');
  }

}

}
