import { AlertService } from './../services/alert.service';
import { ClotheModel } from './../models/Clothe.model';
import { ArticleClotheModel } from './../models/ArticleClothe.model';
import { ArticlesService } from './../services/articles.service';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {SalesService} from "../services/sales.service";
import {Sale} from "../models/Sale.model";
import {Subscription} from "rxjs";
import { HttpErrorResponse } from '@angular/common/http';

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

  // categories
  categories = ['Vêtements', 'Puériculture et accessoires'];
  categories2 = ['Vêtements', 'Linge de maison'];
  categories3 = ['Jouet', 'Objet de décoration', 'Livre'];

  // gender
  genders = ['Homme / Garçon', 'Femme / Fille'];

  // categories types
  clothe: boolean;
  object: boolean;
  book: boolean;
  toy: boolean;

  // other
  saleSubscription: Subscription;
  saleConcerned : Sale;
  saleId: number;

  submitted = false;
  returnUrl: any;
  loading: boolean;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private salesService: SalesService,
              private ArticlesService: ArticlesService,
              private alertService: AlertService) {
  }

  ngOnInit() {
    this.initForm();
    const id = this.route.snapshot.params['id'];
    this.saleId = id;

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
      category:[null, [Validators.required]],
      category2:[null, [Validators.required]],
      category3:[null, [Validators.required]],
      size:[null, [Validators.required]],
      gender:[null, [Validators.required]],
      material:[null, [Validators.required]],
      clotheColor:[null, [Validators.required]],
      ObjectColor:[null, [Validators.required]],
      ToyColor:[null, [Validators.required]],
      brand:[null, [Validators.required]],
      name:[null, [Validators.required]],
      author:[null, [Validators.required]],
      type:[null, [Validators.required]],
      price:[null, [Validators.required, Validators.pattern(/^[1-9]\d{0,4}(?:\.\d{1,2})?|\.\d{1,2}$/)]],
      comment:[null]
    });
  }

  onSubmit() {
    this.submitted = true;
    // error return
    // if there is no category choosen
    if (this.signForm.get('category').value === null) {
      console.log('formulaire non valide');
      return;
    }
    // if there is null on one category 'Vêtements' 
    if (this.signForm.get('category').value === 'Vêtements') {
      if (this.signForm.get('size').value === null || this.signForm.get('gender').value === null|| this.signForm.get('material').value === null
       || this.signForm.get('clotheColor').value === null|| this.signForm.get('type').value === null|| this.signForm.get('price').value === null) {
        console.log('passage erreur category Vêtements ou Puériculture et accessoires')
        return;
      }
    }
    // other
    
    // get input form
    const articleClothe = new ArticleClotheModel();

    if (this.childrenSale === true) {
      articleClothe.category = this.signForm.get('category').value;
    }
    if (this.adultSale === true) {
      articleClothe.category = this.signForm.get('category2').value;
    }
    if (this.playSale === true) {

    }
    articleClothe.type = this.signForm.get('type').value;
    articleClothe.saleNumber = this.saleId;
    articleClothe.price = this.signForm.get('price').value;
    articleClothe.size = this.signForm.get('size').value;
    articleClothe.gender = this.signForm.get('gender').value;
    articleClothe.material = this.signForm.get('material').value;
    articleClothe.color = this.signForm.get('clotheColor').value;
    articleClothe.comment = this.signForm.get('comment').value;

    console.log('Clothe article to record:' + articleClothe);
    
    // lunch service for add article
    this.ArticlesService.addArticleClothe(articleClothe)
    .subscribe(
      res => {
        this.alertService.success('article enregistré', true);
        this.router.navigate(['sales']);
      },
      (error: HttpErrorResponse) => {
        this.alertService.error(error.message)
      }
    );
  }

  /**
   * For adapt form when user choose a category
   * @param category 
   */
  onEditClick(category: any) {
    if(category == 'Vêtements') {
      this.allCategoryToFalse();
      this.clothe = true;
    }
    else if (category == 'Livre') {
      this.allCategoryToFalse();
      this.book= true;

    }
    else if (category == 'Puériculture et accessoires' || 'Linge de maison' || 'Objet de décoration') {
      this.allCategoryToFalse();
      this.object= true;
    }
    else if (category == 'Jouet') {
      this.allCategoryToFalse();
      this.toy= true;
    }
  }
  
  /**
   * For put all category to false
   */
  allCategoryToFalse() {
    this.clothe = false;
    this.object= false;
    this.book= false;
    this.toy= false;
  }
}
