import { ArticleToyModel } from '../models/ArticleToy.model';
import { ArticleObjectModel } from '../models/ArticleObject.model';
import { ArticleClotheModel } from '../models/ArticleClothe.model';
import { ArticleBookModel } from '../models/ArticleBook.model';
import { Subject } from 'rxjs';
import { ApplicationHttpClientService } from './ApplicationHttpClient.service';
import {Injectable} from '@angular/core';

@Injectable({ providedIn: 'root'})
export class ArticlesService {

    constructor(private http: ApplicationHttpClientService) {}

    private articles: any[] = [];

    articleSubject = new Subject<any[]>();

    emitArticle() {
        this.articleSubject.next(this.articles.slice());
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
        return this.http.post('/newBookArticle', bookArticle);
    }
}
