import {Observable, Subject} from "rxjs";
import {Sale} from "../models/Sale.model";
import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpClientModule} from "@angular/common/http";

@Injectable()
export class SalesService {


  saleSubject = new Subject<any[]>();

  private sales: Sale[] = [];

  constructor(private httpClient: HttpClient) {}

  // emmit method
  emmitSales() {
    this.saleSubject.next(this.sales);
  }

  getSales() {
    this.httpClient
      .get<any[]>('http://localhost:9001/AllSales')
      .subscribe(
        (response) => {
          this.sales = response;
          this.emmitSales();
        },
        (error) => {
          console.log('Erreur de chargement !' + error)
        }
      )
  };

}
