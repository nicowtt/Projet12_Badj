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

  private usersList: UserModel[] = [];
  usersSubject = new Subject<UserModel[]>();

  private user: UserModel;
  userSubject = new Subject<UserModel>();


  listEmailsSubject = new Subject<string[]>();  
  userEmails: string[];

  emitUsers() {
    this.usersSubject.next(this.usersList.slice());
  }

  emitUserEmail() {
    this.listEmailsSubject.next(this.userEmails.slice());
  }

  emitUser() {
    this.userSubject.next(this.user);
  }

  /**
   * add new user
   * @param user
   * @constructor
   */
  AddUser(user: UserModel, onSucces: Function, onError:Function) {
    return this.http
    .post(`/newUser`, user)
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

  getAllUsers(onSucces: Function) {
    return this.http
    .get<any[]>('/AllUsers')
    .subscribe(
      (response) => {
        this.usersList = response;
        this.emitUsers();
        onSucces();
      },
      (error) => {
        console.log(error);
      }
    )
  }

  updateUserAndAddressSamePassword(user: UserModel, onSuccess: Function) {
    return this.http
    .post<UserModel>('/updateUserAndAddressSamePassword', user)
    .subscribe(
      (response) => {
        this.alertService.success('Utilisateur à été mis à jour', true);
        setTimeout(() => {
          this.alertService.clear();
        }, 3000);
        onSuccess();
      },
      (error) => {
        this.alertNetworkOff(error);
      }
    );
  }

  updateUserAndAddressAndPassword(user: UserModel, onSuccess: Function) {
  return this.http
    .post<UserModel>('/updateUserAndAddressAndPassword', user)
    .subscribe(
      (response) => {
        this.alertService.success('Utilisateur et son mot de passe ont été mis à jour', true);
        setTimeout(() => {
          this.alertService.clear();
        }, 3000);
        onSuccess();
      },
      (error) => {
        this.alertNetworkOff(error);
      }
    );
  }

  getOneUser(user: UserModel, onSuccess: Function) {
    return this.http
    .post<UserModel>('/getOneUser', user)
    .subscribe(
      (response) => {
        this.user = response;
        this.emitUser();
        onSuccess();
      },
      (error) => {
        this.alertNetworkOff(error);
      }
    );
  }

  alertNetworkOff(error: any) {
    this.alertService.error('erreur reseau veuillez recommencer plus tard.', true);
    this.timeOutOffAlert5000();
  }

  timeOutOffAlert5000() {
    setTimeout(() => {
      this.alertService.clear();
    }, 5000);
  }
}
