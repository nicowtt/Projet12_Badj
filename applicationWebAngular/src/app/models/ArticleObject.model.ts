
export class ArticleObjectModel {

    category: string;
    type: string;
    saleId: number;
    price: number;
    brand: string;
    color: string;
    comment: string;

    recordDate: Date;
    isValidateToSell: boolean;
    isSold: boolean;
    isStolen: boolean;
    isReturnOwner: boolean;

    userEmail: string;

    constructor() {}
}
