import { Router } from '@angular/router';
import { AlertService } from './alert.service';
import {UserModel} from "../models/User.model";
import {Subject} from "rxjs";
import {Injectable} from "@angular/core";
import {ApplicationHttpClientService} from "./ApplicationHttpClient.service";

@Injectable({ providedIn: 'root'})
export class UserService {
  constructor(private http: ApplicationHttpClientService,
              private alertService : AlertService,
              private router: Router) { }

  private users: UserModel[] = [];
  userSubject = new Subject<UserModel[]>();


  listEmailsSubject = new Subject<string[]>();  
  userEmails: string[];

  emitUser() {
    this.userSubject.next(this.users.slice());
  }

  emitUserEmail() {
    this.listEmailsSubject.next(this.userEmails.slice());
  }

  /**
   * add new user
   * @param user
   * @constructor
   */
  AddUser(user: UserModel, onSucces: Function, onError:Function) {
    return this.http.post(`/newUser`, user)
    .subscribe(
      res => {
        this.alertService.success('Utilisateur enregistré', true);
        setTimeout(() => {
          this.alertService.clear();
        }, 3000);
        onSucces();
      },
      (error: any) => {
        if (error.error === "email already exist") {
          this.alertService.error("Erreur, l'email existe déjà", true);
          setTimeout(() => {
            this.alertService.clear();
          }, 3000);
        } else {
          this.alertService.error(error.message);
          setTimeout(() => {
            this.alertService.clear();
          }, 3000);
        }
        onError();
      }
    );
  }

  getAllUserEmail(onSucces: Function) {
    return this.http
    .get<any[]>('/allUserEmails')
    .subscribe(
      (response) => {
        this.userEmails = response;
        this.emitUserEmail();
        onSucces();
      },
      (error) => {
        console.log(error);
      }
    )
  }

  isTokenAlreadyValid(onSucces: Function): Promise<boolean> | boolean {
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
              onSucces();
            } else {
              this.router.navigate(['/auth', 'signin']);
              resolve(false);
            }
            },
            (error) => {
              if ( error.status === 401) {
                this.router.navigate(['/auth', 'signin']);
              }
              this.alertService.error('Ooups, vous devez vous (re)connecter pour avoir accés à cette fonction.')
            });
      }
    );
  }
}
