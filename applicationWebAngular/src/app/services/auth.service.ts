import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../models/User.model";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  userIsValide: Object;
  isAuth: boolean;

  constructor(private HttpClient: HttpClient) { }


  /**
   * for check if email and password is ok
   * @param email
   * @param password
   */
  signInUser(email: string, password: string) {
    const userObject = {
      email: '',
      password: ''
    };
    userObject.email = email;
    userObject.password = password;

    return new Promise(
      (resolve, reject) => {
        this.HttpClient
          .post('http://localhost:9001/checkUserLogIn', userObject)
          .subscribe(
            (result) => {
              this.userIsValide = result;
              if (this.userIsValide === true) {
                this.isAuth = true;
              } else {
                this.isAuth = false;
              }
              console.log('utilisateur validé: ' + this.isAuth);
            },
            (error) => {
              console.log('Erreur d envoi' + error);
            }
          );
      });
  }
  }

