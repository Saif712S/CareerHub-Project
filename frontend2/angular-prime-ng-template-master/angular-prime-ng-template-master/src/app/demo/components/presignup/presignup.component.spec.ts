import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PresignupComponent } from './presignup.component';

describe('PresignupComponent', () => {
  let component: PresignupComponent;
  let fixture: ComponentFixture<PresignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PresignupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PresignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
