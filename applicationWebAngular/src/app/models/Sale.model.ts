import {AddressModel} from "./Address.model";

export class Sale {
  id: number;
  type: string;
  description: string;
  dateBegin: Date;
  dateEnd: Date;
  address: AddressModel;

  constructor() {}
}
