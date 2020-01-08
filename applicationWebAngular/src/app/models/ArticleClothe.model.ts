
export class ArticleClotheModel {
    category: string;
    type: string;
    saleId: number;
    price: number;
    size: string;
    gender: string;
    material: string;
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
