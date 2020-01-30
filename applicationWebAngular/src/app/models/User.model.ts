import { AddressModel } from './Address.model';

export class UserModel {
  id: number;
  name: string;
  lastName: string;
  password: string;
  email: string;
  phone: string;
  voluntary: boolean;
  responsible: boolean;
  token: Object;
  address: AddressModel;

  constructor() {}
}
