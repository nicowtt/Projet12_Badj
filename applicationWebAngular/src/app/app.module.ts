import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SalesListComponent } from './sales-list/sales-list.component';
import {RouterModule, Routes} from "@angular/router";
import {SalesService} from "./services/sales.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { SignupComponent } from './auth/signup/signup.component';
import { SigninComponent } from './auth/signin/signin.component';
import {AuthService} from "./services/auth.service";
import {AuthGuardService} from "./services/auth-guard.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AlertComponent } from './alert/alert.component';
import {AlertService} from "./services/alert.service";

const appRoutes: Routes = [
  { path: 'sales', component: SalesListComponent},
  { path: 'auth/signin', component: SigninComponent },
  { path: '', redirectTo: 'sales', pathMatch: 'full'},
  { path: '**', redirectTo: 'sales'}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SalesListComponent,
    SignupComponent,
    AlertComponent,
    SigninComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    SalesService,
    AuthService,
    AuthGuardService,
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
