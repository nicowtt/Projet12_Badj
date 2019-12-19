import {UserModel} from "../models/User.model";
import {Subject} from "rxjs";
import {Injectable} from "@angular/core";
import {ApplicationHttpClientService} from "./ApplicationHttpClient.service";

@Injectable({ providedIn: 'root'})
export class UserService {
  constructor(private http: ApplicationHttpClientService) { }

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
  AddUser(user: UserModel) {
    return this.http.post(`/newUser`, user);
  }

}
