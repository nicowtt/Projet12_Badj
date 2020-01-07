
export class ArticleObjectModel{

    category: string;
    type: string;
    saleNumber: number;
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