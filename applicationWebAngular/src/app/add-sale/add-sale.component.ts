import { Router } from '@angular/router';
import { SalesService } from './../services/sales.service';
import { AddressModel } from './../models/Address.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { SaleModel } from '../models/Sale.model';
import { LiveAnnouncer } from '@angular/cdk/a11y';

@Component({
  selector: 'app-add-sale',
  templateUrl: './add-sale.component.html',
  styleUrls: ['./add-sale.component.css']
})
export class AddSaleComponent implements OnInit {

  saleForm: FormGroup;

  // sale types
  saleType = ['Bourse de printemps', 'Bourse d\'automne', 'Bourse de noël'];

  springAndAutomnSale: boolean;
  noelSale: boolean;
  saleTypeConcerned: string;
  choiceTypeOk: boolean;

  // sales description
  descriptionsForSpringAndAutomnSale = ['Vêtements enfants', 'Vêtements adultes'];
  descriptionsForNoelSale = ['jouets Livres Cadeaux Bijoux'];

  submitted = false;

  minDate: Date;
  todayDate: Date = new Date();

  constructor(private formBuilder: FormBuilder,
    private liveAnnouncer: LiveAnnouncer,
    private salesService: SalesService,
    private router: Router) {
    // min date < today month
    let year = this.todayDate.getFullYear();
    let month = this.todayDate.getMonth();
    let day = this.todayDate.getDay();
    this.minDate = new Date(year, month, day);
  }

  ngOnInit() {
    this.initForm();
  }

  // easy access to form fields
  get f() { return this.saleForm.controls; }

  initForm() {
    this.saleForm = this.formBuilder.group({
      type: [null, [Validators.required]],
      description: [null, [Validators.required]],
      dateBegin: [null, [Validators.required]],
      dateEnd: [null, [Validators.required]],
      street: [null, [Validators.required]],
      postalCode: [null, [Validators.required]],
      city: [null, [Validators.required]],
    })
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.saleForm.get('dateBegin').value === null ||
      this.saleForm.get('type').value === null ||
      this.saleForm.get('description').value === null ||
      this.saleForm.get('dateEnd').value === null ||
      this.saleForm.get('street').value === null ||
      this.saleForm.get('postalCode').value === null ||
      this.saleForm.get('city').value === null) {
        this.liveAnnouncer.clear();
        return;
    }

    // mapper
    const newSale = new SaleModel();
    newSale.type = this.f.type.value;
    newSale.description = this.f.description.value;
    newSale.dateBegin = this.saleForm.get('dateBegin').value;
    newSale.dateEnd = this.saleForm.get('dateEnd').value;
    const address = new AddressModel();
    address.street = this.f.street.value;
    address.postalCode = this.f.postalCode.value;
    address.city = this.f.city.value;
    newSale.address = address;
    // send new sale
    this.salesService.addNewSale(newSale, () => {
      this.router.navigate(['Sales']);
    });
    this.liveAnnouncer.clear();
  }

  /**
   * For adapt form when user choose a category
   * @param category from form
   */
  onEditClick(type: any) {
    this.saleTypeConcerned = this.f.type.value;
    if (this.saleTypeConcerned === 'Bourse de printemps' || 
        this.saleTypeConcerned === 'Bourse d\'automne') {
      this.allTypeToFalse();
      this.choiceTypeOk = true;
      this.springAndAutomnSale = true;
    }
    if (this.saleTypeConcerned === 'Bourse de noël') {
      this.allTypeToFalse();
      this.choiceTypeOk = true;
      this.noelSale = true;
    }
  }

  allTypeToFalse() {
    this.springAndAutomnSale = false;
    this.noelSale = false;
  }
}
