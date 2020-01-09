import { AuthService } from './auth.service';
import { UserModel } from './../models/User.model';
import {Observable, Subject} from 'rxjs';
import {Sale} from '../models/Sale.model';
import {Injectable} from '@angular/core';
// import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams, HttpRequest} from "@angular/common/http";
import {AlertService} from './alert.service';
import {ApplicationHttpClientService} from './ApplicationHttpClient.service';
import { HttpClient } from '@angular/common/http';
// import {catchError, map} from "rxjs/operators";

@Injectable({ providedIn: 'root'})
export class SalesService {


  salesSubject = new Subject<any[]>();

  saleSubject = new Subject<any>();

  private sales: Sale[] = [];

  private sale: Sale;

  // currentUsertoken : string;

  constructor(
              private alertService: AlertService,
              private http: ApplicationHttpClientService) {}

  // emmit method
  emmitSales() {
    this.salesSubject.next(this.sales);

  }

  emmitSale() {
    this.saleSubject.next(this.sale);
  }

  /**
   * for get next sales (after today) whithout user connected
   */
  getSales() {
    this.http
      .get<any[]>('/AfterTodaySales')
      .subscribe(
        (response) => {
          this.sales = response;
          this.emmitSales();
        },
        (error) => {
          console.log('Erreur de chargement !' + error);
          this.alertService.error('erreur reseau veuillez recommencer plus tard');
        }
      );
  }

  /**
   * for get next sales (after today) when user is connected
   */
  getSalesCurrentUserIsPresent(currentUser: string) {
    return this.http
      .get<any[]>('/AfterTodaySales/' + currentUser)
      .subscribe(
        (response) => {
          this.sales = response;
          this.emmitSales();
        },
        (error) => {
          console.log('Erreur de chargement !' + error);
          this.alertService.error('erreur reseau veuillez recommencer plus tard');
        }
      );
  }

  /**
   * for get only one sale
   * @param saleId
   */
  getOneSale(saleId) {
    return this.http
      .get<any>('/OneSale/' + saleId)
      .subscribe(
        (data) => {
          this.sale = data;
          this.emmitSale()
        },
      (error) => {
      console.log(error);
      }
      );
  }
}
