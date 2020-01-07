import { ArticleBookModel } from '../models/ArticleBook.model';
import { ArticleToyModel } from '../models/ArticleToy.model';
import { ArticleObjectModel } from '../models/ArticleObject.model';
import { AlertService } from '../services/alert.service';
import { ArticleClotheModel } from '../models/ArticleClothe.model';
import { ArticlesService } from '../services/articles.service';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SalesService} from '../services/sales.service';
import {Sale} from '../models/Sale.model';
import {Subscription} from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-articles',
  templateUrl: './add-articles.component.html',
  styleUrls: ['./add-articles.component.css']
})
export class AddArticlesComponent implements OnInit {

  signForm: FormGroup;
  // sale types
  childrenSale: boolean; // -> type category
  adultSale: boolean; // -> type category 2
  playSale: boolean; // -> type category 3

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
  saleConcerned: Sale;
  saleId: number;

  submitted = false;
  returnUrl: any;
  loading: boolean;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private salesService: SalesService,
              private articlesService: ArticlesService,
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
        if (this.saleConcerned.description === 'Vêtements enfants') {
          this.childrenSale = true;
        } else if (this.saleConcerned.description === 'Vêtements adulte') {
          this.adultSale = true;
        } else if (this.saleConcerned.description === 'jouets Livres Cadeaux Bijoux') {
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
      category: [null, [Validators.required]],
      category2: [null, [Validators.required]],
      category3: [null, [Validators.required]],
      size: [null, [Validators.required]],
      gender: [null, [Validators.required]],
      material: [null, [Validators.required]],
      clotheColor: [null, [Validators.required]],
      ObjectColor: [null, [Validators.required]],
      ToyColor: [null, [Validators.required]],
      brand: [null, [Validators.required]],
      name: [null, [Validators.required]],
      author: [null, [Validators.required]],
      type: [null, [Validators.required]],
      price: [null, [Validators.required, Validators.pattern(/^[1-9]\d{0,4}(?:\.\d{1,2})?|\.\d{1,2}$/)]],
      comment: [null]
    });
  }

  onSubmit() {
    this.submitted = true;
    // error return
    // if there is no category choosen -> display form error
    if (this.signForm.get('category').value === null && this.signForm.get('category2').value === null &&
    this.signForm.get('category3').value === null) {
      console.log('formulaire non valide');
      return;
    }
    // if there is null on clothe category -> display form error (clothe)
    if (this.signForm.get('category').value === 'Vêtements' || this.signForm.get('category2').value === 'Vêtements') {
      if (this.signForm.get('size').value === null ||
        this.signForm.get('gender').value === null ||
        this.signForm.get('material').value === null ||
        this.signForm.get('clotheColor').value === null ||
        this.signForm.get('type').value === null ||
        this.signForm.get('price').value === null) {
        console.log('passage erreur form category Vêtements (clothe)');
        return;
      }
    }
    // if there is null on object category -> display form error (object)
    if (this.signForm.get('category').value === 'Puériculture et accessoires' ||
    this.signForm.get('category2').value === 'Linge de maison' ||
    this.signForm.get('category3').value === 'Objet de décoration') {
        if (this.f.type.value === null || this.f.brand.value === null || this.f.ObjectColor.value === null ||
          this.f.price.value === null ) {
        console.log('passage error from Puériculture et accessoires, linge de maison (object)');
        return;
      }
    }
    // if there is null on book category -> display form error (book)
    if (this.signForm.get('category3').value === 'Livre') {
      if (this.f.type.value === null ||
        this.f.name.value === null ||
        this.f.author.value === null) {
        console.log('passage error from book (book)');
        return;
      }
    }
    // if there is null on toy category -> display form error (toy)
    if (this.signForm.get('category3').value === 'Jouet') {
        if (this.f.type.value === null ||
          this.f.brand.value === null ||
          this.f.ToyColor.value === null ||
          this.f.price.value === null ) {
        console.log('passage error from jouet(toy)');
        return;
      }
    }
    // get input form
    // articleClothe
    if (this.f.category.value === 'Vêtements' ||
      this.f.category2.value === 'Vêtements') {
      const articleClothe = new ArticleClotheModel();
      if (this.childrenSale === true) {
        articleClothe.category = this.signForm.get('category').value;
      }
      if (this.adultSale === true) {
        articleClothe.category = this.signForm.get('category2').value;
      }
      articleClothe.type = this.signForm.get('type').value;
      articleClothe.saleNumber = this.saleId;
      articleClothe.price = this.signForm.get('price').value;
      articleClothe.size = this.signForm.get('size').value;
      articleClothe.gender = this.signForm.get('gender').value;
      articleClothe.material = this.signForm.get('material').value;
      articleClothe.color = this.signForm.get('clotheColor').value;
      articleClothe.comment = this.signForm.get('comment').value;
      // lunch service for add clothe article
      this.articlesService.addArticleClothe(articleClothe)
      .subscribe(
        res => {
          this.alertService.success('article enregistré', true);
          this.router.navigate(['sales']);
        },
        (error: HttpErrorResponse) => {
          this.alertService.error(error.message);
        }
      );
    }

    // articleObject
    if (this.f.category.value === 'Puériculture et accessoires' || this.f.category2.value === 'Linge de maison' ||
    this.f.category3.value === 'Objet de décoration') {
      const articleObject = new ArticleObjectModel();
      if (this.childrenSale === true) {
        articleObject.category = this.f.category.value;
      }
      if (this.adultSale === true) {
        articleObject.category = this.f.category2.value;
      }
      if (this.playSale === true) {
        articleObject.category = this.f.category3.value;
      }
      articleObject.type = this.f.type.value;
      articleObject.saleNumber = this.saleId;
      articleObject.price = this.f.price.value;
      articleObject.brand = this.f.brand.value;
      articleObject.color = this.f.ObjectColor.value;
      articleObject.comment = this.f.comment.value;
      // lunch service for add clothe article
      this.articlesService.addArticleObject(articleObject)
      .subscribe(
        res => {
          this.alertService.success('article enregistré', true);
          this.router.navigate(['sales']);
        },
        (error: HttpErrorResponse) => {
          this.alertService.error(error.message);
        }
      );
    }

    // articleToy
    if (this.f.category3.value === 'Jouet') {
      const toyObject = new ArticleToyModel();
      toyObject.category = this.f.category3.value;
      toyObject.type = this.f.type.value;
      toyObject.brand = this.f.brand.value;
      toyObject.color = this.f.ToyColor.value;
      toyObject.price = this.f.price.value;
      toyObject.comment = this.f.comment.value;
      // lunch service for add toy article
      this.articlesService.addToyObject(toyObject)
      .subscribe(
        res => {
          this.alertService.success('article enregistré', true);
          this.router.navigate(['sales']);
        },
        (error: HttpErrorResponse) => {
          this.alertService.error(error.message);
        }
      );
    }

    // articleBook
    if (this.f.category3.value === 'Livre') {
      const bookObject = new ArticleBookModel();
      bookObject.category = this.f.category3.value;
      bookObject.type = this.f.type.value;
      bookObject.name = this.f.name.value;
      bookObject.author = this.f.author.value;
      bookObject.price = this.f.price.value;
      bookObject.comment = this.f.comment.value;
      // lunch service for add book article
      this.articlesService.addBookObject(bookObject)
      .subscribe(
        res => {
          this.alertService.success('article enregistré', true);
          this.router.navigate(['sales']);
        },
        (error: HttpErrorResponse) => {
          this.alertService.error(error.message);
        }
      );
    }
  }

  /**
   * For adapt form when user choose a category
   * @param category from form
   */
  onEditClick(category: any) {
    if (category === 'Vêtements') {
      this.allCategoryToFalse();
      this.clothe = true;
    }
    if (category === 'Livre') {
      this.allCategoryToFalse();
      this.book = true;

    }
    if (category === 'Puériculture et accessoires' ||
      category === 'Linge de maison' ||
      category === 'Objet de décoration') {
      console.log(category);
      this.allCategoryToFalse();
      this.object = true;
    }
    if (category === 'Jouet') {
      this.allCategoryToFalse();
      this.toy = true;
    }
  }
  /**
   * For put all category to false
   */
  allCategoryToFalse() {
    this.clothe = false;
    this.object = false;
    this.book = false;
    this.toy = false;
  }
}
