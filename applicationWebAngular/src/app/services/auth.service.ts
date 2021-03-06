import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {UserModel} from '../models/User.model';
import {map} from 'rxjs/operators';
import {ApplicationHttpClientService} from './ApplicationHttpClient.service';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject: BehaviorSubject<UserModel>;
  public currentUser: Observable<UserModel>;

  private userInProgress: UserModel;


  constructor(private http: ApplicationHttpClientService) {
    this.currentUserSubject = new BehaviorSubject<UserModel>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): UserModel {
    return this.currentUserSubject.value;
  }

  /**
   * User sign in
   * @param email
   * @param password
   */
  signInUser(email, password) {
    return this.http.post<any>('/checkUserLogIn', { email, password })
      .pipe(map(user => {
        if (user && user.token) {
          this.userInProgress = user;
          // console.log('only token: ' + this.userInProgress.token);
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          localStorage.setItem('currentUserToken', JSON.stringify(this.userInProgress.token));
          this.currentUserSubject.next(user);
          console.log('local quand sigIn: ' + localStorage.getItem('currentUser'));
        }
        return user;
      }));
  }

  /**
   * User logOut
   */
  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    // console.log('local quand logOut: ' + localStorage.getItem('currentUser'));
  }

  /**
   * for create new user
   * @param email
   * @param password
   */
  createNewUser(newUser: Object) {
    console.log(newUser);
    return this.http.post<any>('/newUser', {newUser})
      .pipe(map(newUser => {
        console.log(newUser);
          return newUser;
      }
      ));
  }
  }

