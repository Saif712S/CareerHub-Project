import { Component, ElementRef, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from "./service/app.layout.service";
import { AddEntrepriseComponent } from '../demo/components/add-entreprise/add-entreprise.component';
import { MatDialog } from '@angular/material/dialog';
import { AddEntrepriseDialogComponent } from '../demo/components/add-entreprise-dialog/add-entreprise-dialog.component';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent {

    items!: MenuItem[];

    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

    @ViewChild('topbarmenu') menu!: ElementRef;

    constructor(public layoutService: LayoutService,public dialog :MatDialog) { }
    openDialog(): void {
        console.log('opening')

        const dialogRef = this.dialog.open(AddEntrepriseDialogComponent, {
          maxWidth: '50%',
          width: 'auto',
          data: {},
          position: {
            top: '10%',
            left: '30%',
            transform: 'translateX(-50%) translateY(-50%)'
          },
          panelClass: 'custom-dialog-container'
        } as any);
      
        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        });
      }
}
