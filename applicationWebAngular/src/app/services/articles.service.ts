import { ArticleModel } from './../models/Article.model';
import { AlertService } from './alert.service';
import { ArticleToyModel } from '../models/ArticleToy.model';
import { ArticleObjectModel } from '../models/ArticleObject.model';
import { ArticleClotheModel } from '../models/ArticleClothe.model';
import { ArticleBookModel } from '../models/ArticleBook.model';
import { Subject } from 'rxjs';
import { ApplicationHttpClientService } from './ApplicationHttpClient.service';
import {Injectable} from '@angular/core';

@Injectable({ providedIn: 'root'})
export class ArticlesService {

    constructor(private http: ApplicationHttpClientService,
                private alertService: AlertService) {}

    private articles: any[] = [];

    articleSubject = new Subject<any[]>();

    emitArticles() {
        this.articleSubject.next(this.articles);
      }

    /**
     * for persist clothe article
     * @param clotheArticle (clothe)
     */
    addArticleClothe(clotheArticle: ArticleClotheModel) {
        return this.http.post('/NewClotheArticle', clotheArticle);
    }

    /**
     * for persist object article
     * @param objectArticle (object)
     */
    addArticleObject(objectArticle: ArticleObjectModel) {
        return this.http.post('/NewObjectArticle', objectArticle);
    }

    /**
     * for persist toy article
     * @param toyArticle (toy)
     */
    addToyObject(toyArticle: ArticleToyModel) {
        return this.http.post('/NewToyArticle', toyArticle);
    }

    /**
     * for persist book article
     * @param bookArticle (book)
     */
    addBookObject(bookArticle: ArticleBookModel) {
        return this.http.post('/NewBookArticle', bookArticle);
    }

    /**
     * get all articles for one user
     * @param userEmail (current user email)
     */
    getAllArticlesForOneUser(userEmail: string) {
        return this.http
        .get<any[]>('/AllArticlesForId/' + userEmail)
        .subscribe(
            (response) => {
                this.articles = response;
                this.emitArticles();
            },
            (error) => {
                console.log('Erreur de chargement !' + error);
                this.alertService.error('erreur reseau veuillez recommencer plus tard');
            }
        );
    }

    /**
     * remove article
     * @param article 
     */
    removeObjectAndGetNewList(article: ArticleModel, userEmail: string) {
        return this.http.post<any>('/RemoveArticleAndGetNewList/'+ userEmail, article)
        .subscribe(
            (response) => {
                this.articles = response;
                this.emitArticles();
            }
        )
    }

    /**
     * get all articles for one sale
     * @param saleId 
     */
    getAllArticlesForOneSale(saleId: number) {
        return this.http
        .get<any[]>('/AllArticlesForSale/' + saleId)
        .subscribe(
            (response) => {
                this.articles = response;
                this.emitArticles();
            },
            (error) => {
                console.log('Erreur de chargement !' + error);
                this.alertService.error('erreur reseau veuillez recommencer plus tard');
            }
        ); 
    }

    /**
     * to update article
     * @param article 
     */
    validateArticle(article: ArticleModel) {
        return this.http
        .post('/UpdateArticle', article)
        .subscribe(
            (response) => {},
            (error) => {
                this.alertService.error('erreur reseau veuillez recommencer plus tard')
            }
        )
    }
}
