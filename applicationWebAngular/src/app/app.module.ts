import { ArticlesService } from './services/articles.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SalesListComponent } from './sales-list/sales-list.component';
import {RouterModule, Routes} from '@angular/router';
import {SalesService} from './services/sales.service';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import { SignupComponent } from './auth/signup/signup.component';
import { SigninComponent } from './auth/signin/signin.component';
import {AuthService} from './services/auth.service';
import {AuthGuardService} from './services/auth-guard.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AlertComponent } from './alert/alert.component';
import {AlertService} from './services/alert.service';
import {UserService} from './services/user.service';
import { AddArticlesComponent } from './add-articles/add-articles.component';
import {HeaderInterceptorService} from './services/header-interceptor.service';
import {ApplicationHttpClientService} from './services/ApplicationHttpClient.service';
import { DatePipe } from '@angular/common';
import { PersonalSpaceComponent } from './personal-space/personal-space.component';
import { ArticleValidationComponent } from './article-validation/article-validation.component';
import {AutocompleteLibModule} from 'angular-ng-autocomplete';
import { ArticleModificationComponent } from './article-modification/article-modification.component';

const appRoutes: Routes = [
  { path: 'sales', component: SalesListComponent},
  { path: 'auth/signin', component: SigninComponent },
  { path: 'auth/signup', component: SignupComponent },
  { path: 'addArticles/:id', canActivate: [AuthGuardService], component: AddArticlesComponent},
  { path: 'personalSpace', component: PersonalSpaceComponent},
  { path: 'articlesValidation/:id', component: ArticleValidationComponent},
  { path: 'articleModification/:id/:change', component: ArticleModificationComponent},
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
    SigninComponent,
    AddArticlesComponent,
    PersonalSpaceComponent,
    ArticleValidationComponent,
    ArticleModificationComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    AutocompleteLibModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HeaderInterceptorService, multi: true },
    SalesService,
    AuthService,
    AuthGuardService,
    AlertService,
    UserService,
    HeaderInterceptorService,
    ApplicationHttpClientService,
    ArticlesService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
