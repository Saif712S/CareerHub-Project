import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEntrepriseDialogComponent } from './add-entreprise-dialog.component';

describe('AddEntrepriseDialogComponent', () => {
  let component: AddEntrepriseDialogComponent;
  let fixture: ComponentFixture<AddEntrepriseDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEntrepriseDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEntrepriseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
