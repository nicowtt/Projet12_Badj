import { UserService } from './../services/user.service';
import { Router } from '@angular/router';
import { ArticleModel } from './../models/Article.model';
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
              private authService: AuthService,
              private router: Router,
              private userService: UserService) { 
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
      // check token is valid
      this.userService.isTokenAlreadyValid(() => {
      this.articlesService.getAllArticlesForOneUser(this.currentUser.email, () => {
        this.articlesService.emitArticles();
      });
      })
    }
  }

  ngOnDestroy() {
    this.articlesSubscription.unsubscribe();
  }

  onDeleteArticle(id: number) {
    this.articleConcerned = this.articles[id];
    console.log('article concerné: ' + this.articleConcerned.id);
    this.articlesService.removeArticle(this.articleConcerned, () => {
      this.articlesService.getAllArticlesForOneUser(this.currentUser.email, () => {
        this.articlesService.emitArticles();
      });
    });
  }
}
