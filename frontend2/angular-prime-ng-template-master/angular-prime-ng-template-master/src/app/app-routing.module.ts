import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { NotfoundComponent } from './demo/components/notfound/notfound.component';
import { AppLayoutComponent } from "./layout/app.layout.component";
import { MydashboardComponent } from './demo/components/mydashboard/mydashboard.component';
import { SignupComponent } from './demo/components/auth/signup/signup.component';
import { PresignupComponent } from './demo/components/presignup/presignup.component';
import { LandingComponent } from './demo/components/landing/landing.component';
import { AdminsignupComponent } from './demo/components/auth/adminsignup/adminsignup.component';
import { CrudComponent } from './demo/components/pages/crud/crud.component';
import { FormLayoutComponent } from './demo/components/uikit/formlayout/formlayout.component';
import { InputDemoComponent } from './demo/components/uikit/input/inputdemo.component';
import { InvalidStateDemoComponent } from './demo/components/uikit/invalid/invalidstatedemo.component';
import { ButtonDemoComponent } from './demo/components/uikit/button/buttondemo.component';
import { TableDemoComponent } from './demo/components/uikit/table/tabledemo.component';
import { TreeDemoComponent } from './demo/components/uikit/tree/treedemo.component';
import { PanelsDemoComponent } from './demo/components/uikit/panels/panelsdemo.component';
import { OverlaysDemoComponent } from './demo/components/uikit/overlays/overlaysdemo.component';
import { MediaDemoComponent } from './demo/components/uikit/media/mediademo.component';
import { MessagesDemoComponent } from './demo/components/uikit/messages/messagesdemo.component';
import { FileDemoComponent } from './demo/components/uikit/file/filedemo.component';
import { ChartsComponent } from './demo/components/uikit/charts/charts.component';
import { MiscDemoComponent } from './demo/components/uikit/misc/miscdemo.component';
import { LoginComponent } from './demo/components/auth/login/login.component';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {path: '', component: LandingComponent},

            {path :'signup',component: SignupComponent},
            {path :'crud',component: CrudComponent},
            {path :'formlayout',component: FormLayoutComponent},
            {path :'Input',component: InputDemoComponent},
            {path :'invalidestate',component: InvalidStateDemoComponent},
            {path :'button',component: ButtonDemoComponent},
            {path :'table',component: TableDemoComponent},
            {path :'list',component: TreeDemoComponent},
            {path :'panel',component: PanelsDemoComponent},
            {path :'overlay',component: OverlaysDemoComponent},
            {path :'media',component: MediaDemoComponent},
            {path :'message',component: MessagesDemoComponent},
            {path :'file',component: FileDemoComponent},
            {path :'chart',component: ChartsComponent},
            {path :'misc',component: MiscDemoComponent},
            {path :'login',component: LoginComponent},




            

            {path :'adminSignup',component: AdminsignupComponent},
            {path :'presignup',component: PresignupComponent},


            {
                path: 'dashboard', component: AppLayoutComponent,
                children: [
                    { path: '', loadChildren: () => import('./demo/components/dashboard/dashboard.module').then(m => m.DashboardModule) },
                    { path: 'uikit', loadChildren: () => import('./demo/components/uikit/uikit.module').then(m => m.UikitModule) },
                    { path: 'utilities', loadChildren: () => import('./demo/components/utilities/utilities.module').then(m => m.UtilitiesModule) },
                    { path: 'documentation', loadChildren: () => import('./demo/components/documentation/documentation.module').then(m => m.DocumentationModule) },
                    { path: 'blocks', loadChildren: () => import('./demo/components/primeblocks/primeblocks.module').then(m => m.PrimeBlocksModule) },
                    { path: 'pages', loadChildren: () => import('./demo/components/pages/pages.module').then(m => m.PagesModule) },
              

                    // New Update Template
                    { path: 'mydashboard', component: MydashboardComponent },
                ],
            },
            { path: 'auth', loadChildren: () => import('./demo/components/auth/auth.module').then(m => m.AuthModule) },
            { path: 'landing', loadChildren: () => import('./demo/components/landing/landing.module').then(m => m.LandingModule) },
            { path: 'pages/notfound', component: NotfoundComponent },
            { path: '**', redirectTo: 'pages/notfound' },
        ], { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
