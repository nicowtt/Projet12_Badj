import { ArticlesService } from './../services/articles.service';
import { ArticleModel } from './../models/Article.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

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
              private articlesService: ArticlesService) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.saleId = id;
    this.articlesSubscription = this.articlesService.articleSubject.subscribe(
      (articles: any[]) => {
        this.articles = articles;
      }
    ) 
    console.log(this.articles);
  }

  // onDeleteArticle(id: number) {
  //   this.articleConcerned = this.articles[id];
  //   console.log('article concerné: ' + this.articleConcerned.id);
  //   this.articlesService.removeObjectAndGetNewList(this.articleConcerned, this.currentUser.email);
  //   this.articlesService.emitArticles();
  //   this.router.navigate(['/personalSpace']);
  //   this.alertService.success('L\'article a bien été supprimé', true);
  //   setTimeout(() => {
  //     this.alertService.clear();
  //   }, 3000);
  // }
}
