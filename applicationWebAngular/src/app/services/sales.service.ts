import {Observable, Subject} from "rxjs";
import {Sale} from "../models/Sale.model";
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpClientModule} from "@angular/common/http";
import {AlertService} from "./alert.service";

@Injectable()
export class SalesService {


  saleSubject = new Subject<any[]>();

  private sales: Sale[] = [];

  constructor(private httpClient: HttpClient,
              private alertService: AlertService) {}

  // emmit method
  emmitSales() {
    this.saleSubject.next(this.sales);
  }

  /**
   * for get next sales (after today)
   */
  getSales() {
    this.httpClient
      .get<any[]>('http://localhost:9001/AfterTodaySales')
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

}
