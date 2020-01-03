import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {SalesService} from "../services/sales.service";
import {Sale} from "../models/Sale.model";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-add-articles',
  templateUrl: './add-articles.component.html',
  styleUrls: ['./add-articles.component.css']
})
export class AddArticlesComponent implements OnInit {

  signForm: FormGroup;
  // sale types
  childrenSale: boolean;
  adultSale: boolean;
  playSale: boolean;

  // categories types
  clothe: boolean;
  object: boolean;
  book: boolean;
  toy: boolean;

  saleSubscription: Subscription;
  saleConcerned : Sale;

  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private salesService: SalesService) {
  }

  ngOnInit() {
    this.initForm();
    const id = this.route.snapshot.params['id'];

    this.salesService.getOneSale(id);

    this.saleSubscription = this.salesService.saleSubject.subscribe(
      (sale: Sale) => {
        this.saleConcerned = sale;
        if (this.saleConcerned.description == 'Vêtements enfants') {
          this.childrenSale = true;
        } else if (this.saleConcerned.description == 'Vêtements adulte') {
          this.adultSale = true;
        } else if (this.saleConcerned.description == 'jouets Livres Cadeaux Bijoux') {
          this.playSale = true;
        } else {
          this.adultSale = false;
          this.childrenSale = false;
          this.playSale = false;
        }
      }
    );
  }

  // easy access to form fields
  get f() { return this.signForm.controls; }

  initForm() {
    this.signForm = this.formBuilder.group({
      category:['', [Validators.required]],
      category2:['', [Validators.required]],
      category3:['', [Validators.required]],

      size:['', [Validators.required]],
      gender:['', [Validators.required]],
      material:['', [Validators.required]],
      clotheColor:['', [Validators.required]],
      ObjectColor:['', [Validators.required]],
      ToyColor:['', [Validators.required]],
      brand:['', [Validators.required]],
      name:['', [Validators.required]],
      author:['', [Validators.required]],
      
      type:['', [Validators.required]],
      price:['', [Validators.required, Validators.pattern(/^[1-9]\d{0,4}(?:\.\d{1,2})?|\.\d{1,2}$/)]],
      comment:['']
    });


  }

  onSubmit() {
    this.submitted = true;

// stop here if form is invalid
    if (this.signForm.invalid) {
      return;
    }
  }

  /**
   * For adapt form when user choose a category
   * @param category 
   */
  onEditClick(category: any) {
    if(category == 'Vêtements') {
      this.clothe = true;
      this.object= false;
      this.book= false;
      this.toy= false;
    }
    else if (category == 'Livre') {
      this.clothe = false;
      this.object= false;
      this.book= true;
      this.toy= false;
    }
    else if (category == 'Puériculture et accessoires' || 'Linge de maison' || 'Objet de décoration') {
      this.clothe = false;
      this.object= true;
      this.book= false;
      this.toy= false;
    }
    else if (category == 'Jouet') {
      this.clothe = false;
      this.object= false;
      this.book= false;
      this.toy= true;
  }
}

}
