import { AlertService } from '../services/alert.service';
import { FormGroup } from '@angular/forms';
import { ArticlesService } from '../services/articles.service';
import { ArticleModel } from '../models/Article.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-article-validation',
  templateUrl: './article-validation.component.html',
  styleUrls: ['./article-validation.component.css']
})
export class ArticleValidationComponent implements OnInit, OnDestroy {

  saleId: number;
  articlesSubscription: Subscription;
  articles: any[];
  article: ArticleModel;
  articleConcerned: ArticleModel;

  keyword = 'email';
  autocompletionEmails = [ ];
  searchForm: FormGroup;
  searchEmail: string;
  submitted = false;
  articlesFilteredByMail: Array<any> = [];

  toChange: string;

  constructor(private route: ActivatedRoute,
              private articlesService: ArticlesService,
              private router: Router,
              private arlertService: AlertService) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.saleId = id;
    this.articlesSubscription = this.articlesService.articleSubject.subscribe(
      (articles: any[]) => {
        this.articles = articles;
      }
    );
    this.articlesService.getAllArticlesForOneSale(this.saleId, () => {
      // this.articlesService.emitArticles();
    });
    // get emails for autocompletion
    setTimeout(() => {
      let doublon = 0;
      this.articles.forEach(article => {
      if (this.autocompletionEmails.length === 0) {
        this.autocompletionEmails.push(article.user.email);
      } else {
        // avoid doublon
        for (let i = 0; i < this.autocompletionEmails.length; i++) {
          if (this.autocompletionEmails[i] === article.user.email) { doublon++; }
        }
        if (doublon === 0) { this.autocompletionEmails.push(article.user.email); }
        doublon = 0;
      }
    });
    }, 500);
  }

  ngOnDestroy() {
    this.articlesSubscription.unsubscribe();
  }

  validateArticle(id: number) {
    this.articleConcerned = this.articles[id];
    this.articleConcerned.validateToSell = true;
    this.articlesService.updateArticle(this.articleConcerned, () => {
      this.articlesService.getAllArticlesForOneSale(this.saleId, () => {
        this.articlesService.emitArticles();
      });
    });
  }

  selectEvent(item: any) {
    // do something with selected item
    this.submitted = true;
    this.articlesFilteredByMail = [];
    this.searchEmail = item;
    this.articles.forEach(article => {
      if (article.user.email === this.searchEmail) {
        this.articlesFilteredByMail.push(article);
      }
    });
  }

  onChangeSearch(search: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.
  }

  onFocused(e: any) {
    // do something
  }

  changeCharacteristics(id: number, characteristic: string) {
    this.articleConcerned = this.articles[id];
    if (this.articleConcerned.validateToSell) {
      this.arlertService.error('L\'article à été validé, il n\'est plus modifiable!');
      setTimeout(() => {
        this.arlertService.clear();
      }, 3000);
    } else {
      if (characteristic === 'size') { this.router.navigate(['/articleModification/' + this.articleConcerned.id + '/size']); }
      if (characteristic === 'comment') { this.router.navigate(['/articleModification/' + this.articleConcerned.id + '/comment']); }
      if (characteristic === 'price') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/price']); }
      if (characteristic === 'gender') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/gender']); }
      if (characteristic === 'material') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/material']); }
      if (characteristic === 'color') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/color']); }
      if (characteristic === 'type') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/type']); }
      if (characteristic === 'brand') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/brand']);}
      if (characteristic === 'bookName') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/bookName']); }
      if (characteristic === 'bookAuthor') { this.router.navigate(['/articleModification/'+ this.articleConcerned.id + '/bookAuthor']); }
    }
  }

  onDeleteArticle(articleId: number) {
    this.articles.forEach(article => {
      if (article.id === articleId) { this.articleConcerned = article; }
    });
    this.articlesService.removeArticle(this.articleConcerned, () => {
      this.articlesService.getAllArticlesForOneSale(this.saleId, () => {
        // this.articlesService.emitArticles();
      });
      window.location.reload();
    });
  }
}
