
export class ArticleBookModel {
    category: string;
    type: string;
    saleId: number;
    price: number;
    name: string;
    author: string;
    comment: string;

    recordDate: Date;
    isValidateToSell: boolean;
    isSold: boolean;
    isStolen: boolean;
    isReturnOwner: boolean;

    userEmail: string;

    constructor() {}
}
