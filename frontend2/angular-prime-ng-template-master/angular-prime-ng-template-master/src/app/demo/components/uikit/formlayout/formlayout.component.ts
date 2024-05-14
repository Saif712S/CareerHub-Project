import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Entreprise } from 'src/app/model/Entreprise';
import { EntrepriseService } from 'src/app/services/entreprise.service';

@Component({
    templateUrl: './formlayout.component.html'
})
export class FormLayoutComponent {
    
    entrepriseForm!: FormGroup;
    entreprise!:Entreprise;

    selectedState: any;

    dropdownItems = [
        { name: 'Option 1', code: 'Option 1' },
        { name: 'Option 2', code: 'Option 2' },
        { name: 'Option 3', code: 'Option 3' }
    ];
    constructor(private entrepriseService:EntrepriseService,
        private router: Router,
        private toastr: ToastrService,
        private formBuilder: FormBuilder) {this.entrepriseForm = this.formBuilder.group({
            nom: ['', Validators.required],
            description: ['', [Validators.required, Validators.email]],
            logo: ['', Validators.required],
            secteur: ['', Validators.required],
          
        });
      this.entreprise ={
        nom:'',
        description:'',
        logo:'',
        secteur: '',
       
    
      }}
      initEntrepriseForm() {
        this.entrepriseForm = this.formBuilder.group({
            nom: ['', Validators.required],
            description: ['', [Validators.required, Validators.email]],
            logo: ['', Validators.required],
            secteur: ['', Validators.required],
        });
      }

      signup() {
        if (this.entrepriseForm.valid) {
          this.entreprise = this.entrepriseForm.value;
          this.entrepriseService.addEntreprise(this.entreprise).subscribe(
            data => {
              console.log(data)
              this.router.navigate(['/dashboard'])
             },
            () => {
              this.toastr.error('EntrepriseAddedSuccessfully');
            }
          );
        } else {
          this.toastr.error('Registration Failed! Please try again');
        }
    
      }
  
  
}
