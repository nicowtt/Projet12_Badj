import {AdressModel} from "./Adress.model";

export class Sale {

  type: string;
  description: string;
  dateBegin: Date;
  dateEnd: Date;
  address: AdressModel;

  constructor() {}
}
