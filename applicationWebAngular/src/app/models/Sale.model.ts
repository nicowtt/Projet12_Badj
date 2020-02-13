import {AddressModel} from "./Address.model";

export class SaleModel {
  id: number;
  type: string;
  description: string;
  dateBegin: Date;
  dateEnd: Date;
  address: AddressModel;

  nbrArticlesPreRecordForUser: number;

  constructor() {}
}
