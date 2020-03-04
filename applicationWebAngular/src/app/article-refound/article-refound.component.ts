import { ArticleModel } from './../models/Article.model';
import { ArticlesService } from './../services/articles.service';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-article-refound',
  templateUrl: './article-refound.component.html',
  styleUrls: ['./article-refound.component.css']
})
export class ArticleRefoundComponent implements OnInit, OnDestroy {

  saleId: number;
  userEmailConcerned: string;

  articlesSubscription: Subscription;
  articlesList: any[] = [];

  articleTotalNbr: number = 0;
  articleSoldNbr: number = 0;
  articleStolenNbr: number = 0;

  refoundTotal: number = 0;

  allArticleOk: boolean = false;
  p: any;

  constructor(private route: ActivatedRoute,
    private articlesService: ArticlesService) { }

  ngOnInit() {
    this.saleId = this.route.snapshot.params['saleId'];
    this.userEmailConcerned = this.route.snapshot.params['email'];
    this.articlesSubscription = this.articlesService.articleSubject.subscribe(
      (articles: any[]) => {
        this.articlesList = articles;
      }
    );
    this.articlesService.getAllArticlesForOnesaleAndOneUser(this.saleId, this.userEmailConcerned, () => {
      this.articlesService.emitArticles();
      this.countArticleTotal();
      this.countArticleSold();
      this.countArticleStolen();
      this.countRefoundTotal();
      this.checkIfAllArticleIsOk();
    })
  }

  ngOnDestroy() {
    this.articlesSubscription.unsubscribe();
  }

  countArticleSold() {
    this.articlesList.forEach(article => {
      if (article.sold) {
        this.articleSoldNbr++;
      }
    });
  }

  countArticleStolen() {
    this.articlesList.forEach(article => {
      if (article.stolen) {
        this.articleStolenNbr++;
      }
    });
  }

  countArticleTotal() {
    this.articlesList.forEach(article => {
      this.articleTotalNbr++;
    });
  }

  countRefoundTotal() {
    let refoundWithoutTenPerCent = 0;
    this.articlesList.forEach(article => {
      if (article.sold) {
        refoundWithoutTenPerCent = refoundWithoutTenPerCent + article.price;
      }
    });
    // remove 10% for badj
    let tenPerCent = refoundWithoutTenPerCent / 10;
    let refoundlessTenPerCentNbr = refoundWithoutTenPerCent - tenPerCent;
    // round up
    refoundlessTenPerCentNbr = Math.floor( refoundlessTenPerCentNbr * 10 ) / 10;
    // let refoundlessTenPerCentString = refoundlessTenPerCentNbr.toFixed(1);
    this.refoundTotal = +refoundlessTenPerCentNbr;
    console.log('result without round up :' + refoundWithoutTenPerCent);
    console.log('result less 10%: ' + refoundlessTenPerCentNbr);
  }

  onDeclareStolen(articleId: number) {
    this.articlesList.forEach(article => {
      if (article.id === articleId) {
        article.stolen = true;
        this.articlesService.updateArticle(article, () => {
          window.location.reload();
        })
      }
    });
  }

  onDeclareReturnOwner(articleId: number) {
    this.articlesList.forEach(article => {
      if (article.id === articleId) {
        article.returnOwner = true;
        this.articlesService.updateArticle(article, () => {
          window.location.reload();
        })
      }
    });
  }

  checkIfAllArticleIsOk() {
    let totalArticle = 0;
    let totalArticleSold =0;
    let totalArticleStolen = 0;
    let totalArticleReturnOwner = 0;
    let totalArticleSoldStolenAndReturnOwner = 0;
    // count total articles, total article sold, total article stolen
    this.articlesList.forEach(article => {
      totalArticle++;
      if (article.sold) { totalArticleSold++; }
      if (article.stolen) { totalArticleStolen++; }
      if (article.returnOwner) { totalArticleReturnOwner++; }
    });
    totalArticleSoldStolenAndReturnOwner = totalArticleSold + totalArticleStolen + totalArticleReturnOwner;
    if (totalArticle === totalArticleSoldStolenAndReturnOwner) { this.allArticleOk = true; }
  }
}
