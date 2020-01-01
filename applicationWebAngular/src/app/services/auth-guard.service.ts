import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {Observable} from "rxjs";
import {HttpHeaders, HttpErrorResponse} from "@angular/common/http";
import {ApplicationHttpClientService} from "./ApplicationHttpClient.service";
import { AlertService } from './alert.service';
import { UserModel } from '../models/User.model';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  currentUsertoken : string;
  user : UserModel;

  constructor(private router: Router,
              private http: ApplicationHttpClientService,
              private alertService: AlertService) {  }

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    return new Promise(
      (resolve, reject) => {
        // find email userInProgress
        // let userInProgress = JSON.parse(localStorage.getItem('currentUser'));
        this.http
          .get<any>('/userStateChanged')
          .subscribe(
            (data) => {
              const userStillOk = data;
              console.log('User still authorized ?: ' + userStillOk);
            if (data) { 
              resolve(true);
            } else {
              this.router.navigate(['/auth', 'signin']);
              resolve(false);
            }
            },
            (error) => {
              if ( error.status === 401) {
                this.router.navigate(['/auth', 'signin']);
              }
              this.alertService.error('Vous devez être connecté pour avoir accés à cette fonction')
            });
      }
    );
  }
}
