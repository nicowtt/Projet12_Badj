import { AlertService } from './alert.service';
import {UserModel} from "../models/User.model";
import {Subject} from "rxjs";
import {Injectable} from "@angular/core";
import {ApplicationHttpClientService} from "./ApplicationHttpClient.service";

@Injectable({ providedIn: 'root'})
export class UserService {
  constructor(private http: ApplicationHttpClientService,
              private alertService : AlertService) { }

  private users: UserModel[] = [];
  userSubject = new Subject<UserModel[]>();

  emitUser() {
    this.userSubject.next(this.users.slice());
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
          this.alertService.error("Erreur, l'email existe déjà");
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

}
