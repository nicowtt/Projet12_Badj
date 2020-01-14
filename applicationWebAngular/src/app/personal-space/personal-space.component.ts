import { ArticleModel } from './../models/Article.model';
import { ArticleObjectModel } from './../models/ArticleObject.model';
import { ArticleToyModel } from './../models/ArticleToy.model';
import { ArticleBookModel } from './../models/ArticleBook.model';
import { ArticleClotheModel } from './../models/ArticleClothe.model';
import { AuthService } from './../services/auth.service';
import { AlertService } from './../services/alert.service';
import { ArticlesService } from './../services/articles.service';
import { UserModel } from './../models/User.model';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-personal-space',
  templateUrl: './personal-space.component.html',
  styleUrls: ['./personal-space.component.css']
})
export class PersonalSpaceComponent implements OnInit, OnDestroy {

  articlesSubscription: Subscription;
  articles: any[];
  currentUser: UserModel;
  article: ArticleModel;
  articleConcerned: ArticleModel;

  constructor(private articlesService: ArticlesService,
              private alertService: AlertService,
              private authService: AuthService) { 
                this.authService.currentUser.subscribe(x => this.currentUser = x);
              }

  ngOnInit() {
    this.articlesSubscription = this.articlesService.articleSubject.subscribe(
      (articles: any[]) => {
        this.articles = articles;
      }
    )
    console.log('current user email: ' + this.currentUser.email);
    if (this.authService.currentUserValue) {
      this.articlesService.getAllArticlesForOneUser(this.currentUser.email);
      this.articlesService.emitArticles();
    }
  }

  ngOnDestroy() {
    this.articlesSubscription.unsubscribe();
  }

  onDeleteArticle(id: number) {
    this.articleConcerned = this.articles[id];
    console.log('article concerné: ' + this.articleConcerned.id);
  }

}
