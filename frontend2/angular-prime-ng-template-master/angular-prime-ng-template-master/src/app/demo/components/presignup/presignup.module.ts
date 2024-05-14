import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { BadgeModule } from 'primeng/badge';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputSwitchModule } from 'primeng/inputswitch';
import { RippleModule } from 'primeng/ripple';
import { PresignupComponent } from './presignup.component';
import { AppMenuitemComponent } from 'src/app/layout/app.menuitem.component';
import { RouterModule } from '@angular/router';
import { AppFooterComponent } from 'src/app/layout/app.footer.component';
import { AppLayoutComponent } from 'src/app/layout/app.layout.component';
import { AppMenuComponent } from 'src/app/layout/app.menu.component';
import { AppSidebarComponent } from 'src/app/layout/app.sidebar.component';
import { AppTopBarComponent } from 'src/app/layout/app.topbar.component';
import { AppConfigModule } from 'src/app/layout/config/config.module';
import { AppLayoutModule } from 'src/app/layout/app.layout.module';

@NgModule({
    declarations: [
        PresignupComponent
       
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        InputTextModule,
        SidebarModule,
        BadgeModule,
        RadioButtonModule,
        InputSwitchModule,
        RippleModule,
        RouterModule,
        AppConfigModule
    ],
    exports: [PresignupComponent]
})
export class PresignupModule { }
