import { ArticlesService } from './../services/articles.service';
import { ArticleModel } from './../models/Article.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-article-validation',
  templateUrl: './article-validation.component.html',
  styleUrls: ['./article-validation.component.css']
})
export class ArticleValidationComponent implements OnInit {

  saleId: number;
  articlesSubscription: Subscription;
  articles: any[];
  article: ArticleModel;
  articleConcerned: ArticleModel;

  constructor(private route: ActivatedRoute,
              private articlesService: ArticlesService,
              private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.saleId = id;
    this.articlesSubscription = this.articlesService.articleSubject.subscribe(
      (articles: any[]) => {
        this.articles = articles;
      }
    ) 
    this.articlesService.getAllArticlesForOneSale(this.saleId);
    this.articlesService.emitArticles();
  }

  validateArticle(id: number) {
    this.articleConcerned = this.articles[id];
    this.articleConcerned.validateToSell = true;
    this.articlesService.validateArticle(this.articleConcerned);
    // update list of article for this sale
    setTimeout(() => {
      this.articlesService.getAllArticlesForOneSale(this.saleId)
    }, 1000);
  }

}
