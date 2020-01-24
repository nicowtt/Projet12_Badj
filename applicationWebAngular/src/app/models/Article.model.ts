import { ArticleToyModel } from './ArticleToy.model';
import { ArticleObjectModel } from './ArticleObject.model';
import { ArticleBookModel } from './ArticleBook.model';
import { ArticleClotheModel } from './ArticleClothe.model';
import { Sale } from './Sale.model';
import { UserModel } from './User.model';
export class ArticleModel {
    id: number;
    category: string;
    type: string;
    saleNumber: number;
    price: number;
    dateRecord: Date;
    validateToSell: boolean;
    isSold: boolean;
    isStolen: boolean;
    isReturnOwner: boolean;
    user: UserModel;
    sale: Sale;
    clothe: ArticleClotheModel;
    toy: ArticleToyModel;
    book: ArticleBookModel;
    object: ArticleObjectModel;

    constructor() {}
}
