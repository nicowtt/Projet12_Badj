import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: Object;
  private errorAuth: String;

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
            (data) => {
              this.token = data;
              console.log(data);
              resolve();
            },
            (error) => {
              this.errorAuth = "L'email ou le mot de passe n'est pas valide"
              reject(this.errorAuth);
            }
          );
      });
  }
  }

