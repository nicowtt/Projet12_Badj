import { Injectable } from '@angular/core';
import {CanActivate, Router} from "@angular/router";
import {Observable} from "rxjs";
import {HttpHeaders} from "@angular/common/http";
import {ApplicationHttpClientService} from "./ApplicationHttpClient.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  currentUsertoken : String;

  constructor(private router: Router,
              private http: ApplicationHttpClientService) {  }

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    return new Promise(
      (resolve, reject) => {
        this.currentUsertoken = localStorage.getItem('currentUserToken');
        const header = new HttpHeaders();
        header.set("Authorization", "Bearer " + this.currentUsertoken);
        this.http
          .get<any>('/userStateChanged', { headers : header})
          .subscribe(
            (data) => {
              const userStateOK = data;
              console.log(userStateOK);

            if (data) { // si il y a un objet
              resolve(true); // il resolve, l'utilisateur a le droit de passer sur cette route
            } else {
              this.router.navigate(['/auth', 'signin']); // sinon l'utilisateur est re-routé vers signin (attention a l'url en deux partie)
              resolve(false);
            }
            });
      }
    );
  }
}
