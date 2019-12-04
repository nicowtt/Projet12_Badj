import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {UserModel} from "../models/User.model";
import {map} from "rxjs/operators";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentUserSubject: BehaviorSubject<UserModel>;
  public currentUser: Observable<UserModel>;


  constructor(private http: HttpClient) {
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
    return this.http.post<any>('http://localhost:9001/checkUserLogIn', { email, password })
      .pipe(map(user => {
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
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
    return this.http.post<any>('http://localhost:9001/newUser', {newUser})
      .pipe(map(newUser => {
        console.log(newUser);
          return newUser;
      }
      ));
  }
  }

