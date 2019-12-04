import {UserModel} from "../models/User.model";
import {Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({ providedIn: 'root'})
export class UserService {
  constructor(private http: HttpClient) { }

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
    return this.http.post(`http://localhost:9001/newUser`, user);
  }

}
