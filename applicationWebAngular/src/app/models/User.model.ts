import {AdressModel} from "./Adress.model";

export class UserModel {

  name: string;
  lastName: string;
  password: string;
  email: string;
  phone: string;
  isVoluntary: boolean;
  isResponsible: boolean;
  addressId: AdressModel;

  constructor() {}
}
