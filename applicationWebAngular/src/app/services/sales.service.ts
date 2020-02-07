import { AuthService } from './auth.service';
import { UserModel } from './../models/User.model';
import {Observable, Subject} from 'rxjs';
import {SaleModel} from '../models/Sale.model';
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

  private sales: SaleModel[] = [];

  private sale: SaleModel;

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

  getAllSales() {
    this.http
      .get<any[]>('/AllSales')
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

  addNewSale(sale: SaleModel, onSuccess: Function) {
    return this.http
      .post('/NewSale', sale)
      .subscribe(
        (data) => {
        this.alertService.success('La nouvelle bourse est bien enregistré', true)
        setTimeout(() => {
          this.alertService.clear();
        }, 3000);
        onSuccess();
      },
        (error) => {
          this.alertService.error('Erreur la nouvelle bourse n\'a pas été enregistré')
          setTimeout(() => {
            this.alertService.clear();
          }, 3000);
        });
  }

  deleteSale(sale: SaleModel, onSuccess: Function) {
    return this.http
      .post('/RemoveSale', sale)
      .subscribe(
        (data) => {
        this.alertService.success('La bourse à bien été supprimée.', true)
        setTimeout(() => {
          this.alertService.clear();
        }, 3000);
        onSuccess();
      },
        (error) => {
          this.alertService.error('La bourse est en cours il est impossible de la supprimer')
          setTimeout(() => {
            this.alertService.clear();
          }, 3000);
        });
  }

  /**
   * for get only one sale by date
   * @param date
   */
  getOneSaleByDate(date: Date) {
    return this.http
      .get<any>('/getSaleByDate/' + date)
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
