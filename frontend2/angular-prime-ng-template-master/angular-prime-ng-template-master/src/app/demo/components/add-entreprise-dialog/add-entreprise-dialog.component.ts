import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Entreprise } from 'src/app/model/Entreprise';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { EntrepriseService } from 'src/app/services/entreprise.service';


@Component({
  selector: 'app-add-entreprise-dialog',
  templateUrl: './add-entreprise-dialog.component.html',
  styleUrls: ['./add-entreprise-dialog.component.scss']
})
export class AddEntrepriseDialogComponent implements OnInit {

  public addForm !: FormGroup;
  entreprise!: Entreprise;



  constructor(public dialogRef: MatDialogRef<AddEntrepriseDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public entrepriseService: EntrepriseService, private formBuilder: FormBuilder
  ) {
    // Initialize the form with default values
    this.addForm = this.formBuilder.group({
      nom: ['', [Validators.required]],
      description: ['', [Validators.required]],
      logo: ['', [Validators.required]],
      secteur: ['', [Validators.required]],
      
    });
  }

  ngOnInit(): void {
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  addEntreprise(entreprise: Entreprise): void {
    this.entrepriseService.addEntreprise(entreprise).subscribe(
      (response) => {
        entreprise = response;

        this.dialogRef.close(entreprise);
      },
      (error) => {
        console.error('Error add plan:', error);
      }
    );
  }
}