import { Component, OnInit, ViewChild } from '@angular/core';
import { AppSidebarComponent } from 'src/app/layout/app.sidebar.component';
import { AppTopBarComponent } from 'src/app/layout/app.topbar.component';

@Component({
  selector: 'app-presignup',
  templateUrl: './presignup.component.html',
  styleUrls: ['./presignup.component.scss']
})
export class PresignupComponent implements OnInit {
  @ViewChild(AppSidebarComponent) appSidebar!: AppSidebarComponent;

    @ViewChild(AppTopBarComponent) appTopbar!: AppTopBarComponent;

  constructor() { }

  ngOnInit(): void {
  }

}
