import {Observable, Subject} from "rxjs";
import {Sale} from "../models/Sale.model";
import {Injectable} from "@angular/core";
// import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams, HttpRequest} from "@angular/common/http";
import {AlertService} from "./alert.service";
import {ApplicationHttpClientService} from "./ApplicationHttpClient.service";
// import {catchError, map} from "rxjs/operators";

@Injectable({ providedIn: 'root'})
export class SalesService {


  salesSubject = new Subject<any[]>();

  saleSubject = new Subject<any>();

  private sales: Sale[] = [];

  private sale: Sale;

  currentUsertoken : string;

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
   * for get next sales (after today)
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
          this.alertService.error("erreur reseau veuillez recommencer plus tard");
        }
      )
  };

  /**
   * for get only one sale
   * @param saleId
   */
  getOneSale(saleId){
    // this.currentUsertoken = localStorage.getItem('currentUserToken');
    // let header = new HttpHeaders();
    //  header.set("Authorization", "Bearer " + this.currentUsertoken);

    // let header = new HttpHeaders();
    // let other_header = header.set('Authorization', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicnVjZS5sZWVAZ21haWwuY29tIiwiZXhwIjoxNTc2NzA1OTUxLCJpYXQiOjE1NzY2ODc5NTF9.tHw_DBJN4PsmJEOonYVpF_jqx3a-ThxFANtokNkBkhcsEOlECN3Xm5rdYeJfWgk8P6r1WMLfHSYMPi9xXCAdwQ');
    // console.log(other_header.get('Authorization'));
    // console.log(header.get('Authorization'));

    // let header = new HttpHeaders({
    //   'Content-Type': 'test',
    //   'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicnVjZS5sZWVAZ21haWwuY29tIiwiZXhwIjoxNTc2NzA1OTUxLCJpYXQiOjE1NzY2ODc5NTF9.tHw_DBJN4PsmJEOonYVpF_jqx3a-ThxFANtokNkBkhcsEOlECN3Xm5rdYeJfWgk8P6r1WMLfHSYMPi9xXCAdwQ'
    // });

    // const header = new HttpHeaders()
    //   .set('Authorization', 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicnVjZS5sZWVAZ21haWwuY29tIiwiZXhwIjoxNTc2NzA1OTUxLCJpYXQiOjE1NzY2ODc5NTF9.tHw_DBJN4PsmJEOonYVpF_jqx3a-ThxFANtokNkBkhcsEOlECN3Xm5rdYeJfWgk8P6r1WMLfHSYMPi9xXCAdwQ');
    // console.log(header.get('Authorization'));

    // headers: new HttpHeaders().append("Authorization", "Bearer " + this.currentUsertoken);

    // console.log("le token: " + this.currentUsertoken);
    return this.http
      .get<any>('/OneSale/' + saleId)
      .subscribe(
        (data) => {
          this.sale = data;
          this.emmitSale();
          // console.log(this.sale);
        },
      (error) => {
      console.log(error);
      console.log("token dans header: " + error.headers.get('Authorization'));
      console.log("Content type dans header: " + error.headers.get('Content-Type'));
      }
      )
  };
}
