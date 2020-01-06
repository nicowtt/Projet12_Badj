import {Component, OnDestroy, OnInit} from '@angular/core';
import {Sale} from "../models/Sale.model";
import {Subscription} from "rxjs";
import {SalesService} from "../services/sales.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sales-list',
  templateUrl: './sales-list.component.html',
  styleUrls: ['./sales-list.component.css']
})
export class SalesListComponent implements OnInit, OnDestroy {

  sales: Sale[];
  salesSubscription: Subscription;
  saleConcerned: Sale;

  constructor(private salesService: SalesService, private router: Router) { }

  ngOnInit() {
    this.salesSubscription = this.salesService.salesSubject.subscribe(
      (sales: Sale[]) => {
        this.sales = sales;
      }
      );
    this.salesService.getSales();
    this.salesService.emmitSales();
  }

  ngOnDestroy() {
    this.salesSubscription.unsubscribe();
  }

  addArticles(id: number) {
    this.saleConcerned = this.sales[id];
    let saleId = this.saleConcerned.id;
    this.router.navigate(['/addArticles', saleId]);
  }
}
