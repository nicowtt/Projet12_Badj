import { ArticlesService } from './../services/articles.service';
import { ArticleModel } from './../models/Article.model';
import { SalesService } from './../services/sales.service';
import { Subscription } from 'rxjs';
import { SaleModel } from './../models/Sale.model';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-results-sales',
  templateUrl: './results-sales.component.html',
  styleUrls: ['./results-sales.component.css']
})
export class ResultsSalesComponent implements OnInit, OnDestroy {
  
  displayOneSaleResult: boolean;
  //sale
  sales: SaleModel[];
  salesSubscription: Subscription;
  saleConcerned: SaleModel;
  saleId: number;
  
  // articles for one sale
  articles: any[];
  articlesSubscription: Subscription;
  nbrTotalArticlesSale: number;
  nbrArticlesSold: number;
  perCentOfarticleSold: number;
  nbrArticleStolen: number;
  perCentOfArticleStolen: number;
  nbrArticleReturnOwner: number;
  perCentOfArticleReturnOwner: number;
  totalAssociativeArticlesSoldWin: number;
  

  constructor(private salesService: SalesService,
              private articlesService: ArticlesService) { }

  ngOnInit() {
    this.nbrTotalArticlesSale = 0;
    this.nbrArticlesSold = 0;
    this.perCentOfarticleSold = 0;
    this.nbrArticleStolen = 0;
    this.perCentOfArticleStolen = 0;
    this.nbrArticleReturnOwner = 0;
    this.perCentOfArticleReturnOwner = 0;
    this.totalAssociativeArticlesSoldWin = 0;
    // subscription
    this.salesSubscription = this.salesService.salesSubject.subscribe(
      (sales: SaleModel[]) => {
        this.sales = sales;
      });
    this.articlesSubscription = this.articlesService.articleSubject.subscribe(
      (articles: any[]) => {
        this.articles = articles;
      });
    
    // get all sales
    this.salesService.getAllSales();
    this.salesService.emmitSales();
    this.displayOneSaleResult = false;
  }

  ngOnDestroy() {
    this.salesSubscription.unsubscribe();
  }

  seeResults(saleId: number) {
    this.displayOneSaleResult = true;
    // display info for one sale
    this.sales.forEach(sale => {
      if (sale.id === saleId) {
        this.saleConcerned = sale;
      }
    });
    //display info for articles on this sale
    this.articlesService.getAllArticlesForOneSale(saleId, () => { 
      this.totalArticlesNumber();
      this.totalArticlesSold();
      this.totalArticlesStolen();
      this.totalArticleReturnOwner();
      this.calcPercentArticleSold();
      this.calcPercentArticleStolen();
      this.calcPercentArticleReturnOwner();
      this.calcAssociativeWin();
    })
  }

  totalArticlesNumber() {
    this.articles.forEach(article => {
      this.nbrTotalArticlesSale++;
    });
  }

  totalArticlesSold() {
    this.articles.forEach(article => {
      if (article.sold) { 
        this.nbrArticlesSold++; 
      }
    });
  }

  totalArticlesStolen() {
    this.articles.forEach(article => {
      if (article.stolen) { 
        this.nbrArticleStolen++; 
      }
    });
  }

  totalArticleReturnOwner() {
    this.articles.forEach(article => {
      if (article.returnOwner) { 
        this.nbrArticleReturnOwner++;
      }
    });
  }

  calcPercentArticleSold() {
    this.perCentOfarticleSold = (this.nbrArticlesSold * 100) / this.nbrTotalArticlesSale;
  }

  calcPercentArticleStolen() {
    this.perCentOfArticleStolen = (this.nbrArticleStolen * 100) / this.nbrTotalArticlesSale;
  }

  calcPercentArticleReturnOwner() {
    this.perCentOfArticleReturnOwner = (this.nbrArticleReturnOwner * 100) / this.nbrTotalArticlesSale;
  }

  calcAssociativeWin() {
    let cashArticleResult: number = 0;
    let refoundArticleResult: number = 0;
    let associativeWinOnOneArticle: number = 0

    this.articles.forEach(article => {
      if (article.sold) { 
        // cash article
          // add 10%
          let tenPercentAdded = article.price / 10;
          let resultWithTenPerCentNumber = article.price + tenPercentAdded;
            // round up for associative advantage
            cashArticleResult = Math.ceil( resultWithTenPerCentNumber * 10 ) / 10;
        // refound article
          // remove 10%
          // remove 10% for badj
          let tenPerCentRemoved = article.price / 10;
          let refoundlessTenPerCentNbr = article.price - tenPerCentRemoved;
            // round down for associative advantage
            refoundArticleResult = Math.floor( refoundlessTenPerCentNbr * 10 ) / 10;
        // total win for one article
        associativeWinOnOneArticle = cashArticleResult - refoundArticleResult;
        this.totalAssociativeArticlesSoldWin = this.totalAssociativeArticlesSoldWin + associativeWinOnOneArticle;
        // reset variable
        cashArticleResult = 0;
        refoundArticleResult = 0;
        associativeWinOnOneArticle = 0;
      }
    });
  }
}
