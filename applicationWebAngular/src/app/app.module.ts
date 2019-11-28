import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SalesListComponent } from './sales-list/sales-list.component';
import {RouterModule, Routes} from "@angular/router";
import {SalesService} from "./services/sales.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";

const appRoutes: Routes = [
  { path: 'sales', component: SalesListComponent},
  { path: '', redirectTo: 'sales', pathMatch: 'full'},
  { path: '**', redirectTo: 'sales'}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SalesListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    SalesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
