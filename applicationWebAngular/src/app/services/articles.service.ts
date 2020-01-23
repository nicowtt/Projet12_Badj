import { ArticleModel } from '../models/Article.model';
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
    private article: ArticleModel;

    articleSubject = new Subject<any[]>();
    articleOneSubject = new Subject<ArticleModel>();

    emitArticles() {
        this.articleSubject.next(this.articles);
        this.articleOneSubject.next(this.article);
      }

    /**
     * for persist clothe article
     * @param clotheArticle (clothe)
     */
    addArticleClothe(clotheArticle: ArticleClotheModel, onSuccess: Function) {
        return this.http
        .post('/NewClotheArticle', clotheArticle)
        .subscribe((response) => {
          this.recordArticleAlertOk(response);
          onSuccess();
        },
        (error) => {
          this.recordArticleAlertNok(error);
        });
    }

    /**
     * for persist object article
     * @param objectArticle (object)
     */
    addArticleObject(objectArticle: ArticleObjectModel, onSuccess: Function) {
        return this.http
        .post('/NewObjectArticle', objectArticle)
        .subscribe((response) => {
          this.recordArticleAlertOk(response);
          onSuccess();
        },
        (error) => {
          this.recordArticleAlertNok(error);
        });
    }

    /**
     * for persist toy article
     * @param toyArticle (toy)
     */
    addToyObject(toyArticle: ArticleToyModel, onSuccess: Function) {
        return this.http
        .post('/NewToyArticle', toyArticle)
        .subscribe(
          (response) => {
            this.recordArticleAlertOk(response);
            onSuccess();
          },
          (error) => {
            this.recordArticleAlertNok(error);
          });
    }

    /**
     * for persist book article
     * @param bookArticle (book)
     */
    addBookObject(bookArticle: ArticleBookModel, onSuccess: Function) {
        return this.http
        .post('/NewBookArticle', bookArticle)
        .subscribe(
          (response) => {
            this.recordArticleAlertOk(response);
            onSuccess();
          },
          (error) => {
            this.recordArticleAlertNok(error);
          });
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
                this.alertNetworkOff(error);
            }
        );
    }

  /**
   * 
   * @param article To remove article
   */
    removeArticle(article: ArticleModel, onSuccess: Function) {
        return this.http.post<any>('/RemoveArticle', article)
        .subscribe(
            (response) => {
                this.removeArticleAlertOk(response);
                onSuccess();
            },
            (error) => {
              this.recordArticleAlertNok(error);
            }
        );
    }

    /**
     * get all articles for one sale
     * @param saleId -> input
     */
    getAllArticlesForOneSale(saleId: number, onSuccess: Function) {
        return this.http
        .get<any[]>('/AllArticlesForSale/' + saleId)
        .subscribe(
            (response) => {
                this.articles = response;
                this.emitArticles();
                onSuccess();
            },
            (error) => {
              this.alertNetworkOff(error);
            }
        );
    }

    /**
     * to update article
     * @param article -> input
     */
    updateArticle(article: ArticleModel, onSuccess: Function) {
        return this.http
        .post('/UpdateArticle', article)
        .subscribe(
            (response) => {
              onSuccess();
            },
            (error) => {
              this.alertNetworkOff(error);
            }
        );
    }

    /**
     * to update article
     * @param articleId -> input
     */
    getOneArticle(articleId: number, onSuccess: Function) {
        return this.http
        .get<ArticleModel>('/getOneArticle/' + articleId)
        .subscribe(
            (response) => {
                this.article = response;
                this.emitArticles();
                onSuccess();
            },
            (error) => {
              this.alertNetworkOff(error);
            }
        );
    }

    recordArticleAlertOk(response: any) {
      this.alertService.success('article enregistré', true);
      this.timeOutOffAlert5000();
    }

    recordArticleAlertNok(error: any) {
      this.alertService.error('Erreur, l\'article n\'as pas été enregistré.', true);
      this.timeOutOffAlert5000();
    }

    removeArticleAlertOk(response: any) {
      this.alertService.success('Article effacé.', true);
      setTimeout(() => {
        this.alertService.clear();
      }, 3000);        
    }

  removeArticleAlertNok(error: any) {
    this.alertService.error('erreur lors de la suppression de l\'article.', true);
    setTimeout(() => {
      this.alertService.clear();
    }, 3000);
  }

  alertNetworkOff(error: any) {
    this.alertService.error('erreur reseau veuillez recommencer plus tard.', true);
    this.timeOutOffAlert5000();
  }

  timeOutOffAlert5000() {
    setTimeout(() => {
      this.alertService.clear();
    }, 5000);
  }
}
