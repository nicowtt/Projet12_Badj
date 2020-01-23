import { UserModel } from '../models/User.model';
import { AuthService } from '../services/auth.service';
import { ArticleBookModel } from '../models/ArticleBook.model';
import { ArticleToyModel } from '../models/ArticleToy.model';
import { ArticleObjectModel } from '../models/ArticleObject.model';
import { AlertService } from '../services/alert.service';
import { ArticleClotheModel } from '../models/ArticleClothe.model';
import { ArticlesService } from '../services/articles.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SalesService} from '../services/sales.service';
import {Sale} from '../models/Sale.model';
import {Subscription} from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-add-articles',
  templateUrl: './add-articles.component.html',
  styleUrls: ['./add-articles.component.css']
})
export class AddArticlesComponent implements OnInit, OnDestroy {

  signForm: FormGroup;

  currentUser: UserModel;

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
              private alertService: AlertService,
              private datePipe: DatePipe,
              private authService: AuthService) {
                this.authService.currentUser.subscribe(x => this.currentUser = x);
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

  ngOnDestroy() {
    this.saleSubscription.unsubscribe();
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
        this.f.author.value === null ||
        this.f.price.value === null) {
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
      const dateRecord = new Date;
      // var dateRecord = this.datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm:ss');
      if (this.childrenSale === true) {
        articleClothe.category = this.signForm.get('category').value;
      }
      if (this.adultSale === true) {
        articleClothe.category = this.signForm.get('category2').value;
      }
      articleClothe.type = this.signForm.get('type').value;
      articleClothe.saleId = this.saleId;
      articleClothe.price = this.signForm.get('price').value;
      articleClothe.size = this.signForm.get('size').value;
      articleClothe.gender = this.signForm.get('gender').value;
      articleClothe.material = this.signForm.get('material').value;
      articleClothe.color = this.signForm.get('clotheColor').value;
      articleClothe.comment = this.signForm.get('comment').value;
      articleClothe.recordDate = dateRecord;
      articleClothe.isValidateToSell = false;
      articleClothe.isSold = false;
      articleClothe.isStolen = false;
      articleClothe.isReturnOwner = false;
      // set user who want to create this article
      articleClothe.userEmail = this.currentUser.email;
      // lunch service for add clothe article
      this.articlesService.addArticleClothe(articleClothe, () => {
        this.router.navigate(['sales']);
      });
    }

    // articleObject
    if (this.f.category.value === 'Puériculture et accessoires' || this.f.category2.value === 'Linge de maison' ||
    this.f.category3.value === 'Objet de décoration') {
      const articleObject = new ArticleObjectModel();
      const dateRecord = new Date;
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
      articleObject.saleId = this.saleId;
      articleObject.price = this.f.price.value;
      articleObject.brand = this.f.brand.value;
      articleObject.color = this.f.ObjectColor.value;
      articleObject.comment = this.f.comment.value;
      articleObject.recordDate = dateRecord;
      articleObject.isValidateToSell = false;
      articleObject.isSold = false;
      articleObject.isStolen = false;
      articleObject.isReturnOwner = false;
      // set user who want to create this article
      articleObject.userEmail = this.currentUser.email;
      // lunch service for add clothe article
      this.articlesService.addArticleObject(articleObject, () => {
        this.router.navigate(['sales']);
      });
    }

    // articleToy
    if (this.f.category3.value === 'Jouet') {
      const toyObject = new ArticleToyModel();
      const dateRecord = new Date;
      toyObject.category = this.f.category3.value;
      toyObject.type = this.f.type.value;
      toyObject.saleId = this.saleId;
      toyObject.brand = this.f.brand.value;
      toyObject.color = this.f.ToyColor.value;
      toyObject.price = this.f.price.value;
      toyObject.comment = this.f.comment.value;
      toyObject.recordDate = dateRecord;
      toyObject.isValidateToSell = false;
      toyObject.isSold = false;
      toyObject.isStolen = false;
      toyObject.isReturnOwner = false;
      // set user who want to create this article
      toyObject.userEmail = this.currentUser.email;
      // lunch service for add toy article
      this.articlesService.addToyObject(toyObject, () => {
        this.router.navigate(['sales']);
      }); 
    }

    // articleBook
    if (this.f.category3.value === 'Livre') {
      const bookObject = new ArticleBookModel();
      const dateRecord = new Date;
      bookObject.category = this.f.category3.value;
      bookObject.type = this.f.type.value;
      bookObject.saleId = this.saleId;
      bookObject.name = this.f.name.value;
      bookObject.author = this.f.author.value;
      bookObject.price = this.f.price.value;
      bookObject.comment = this.f.comment.value;
      bookObject.recordDate = dateRecord;
      bookObject.isValidateToSell = false;
      bookObject.isSold = false;
      bookObject.isStolen = false;
      bookObject.isReturnOwner = false;
      // set user who want to create this article
      bookObject.userEmail = this.currentUser.email;
      // lunch service for add book article
      this.articlesService.addBookObject(bookObject, () => {
        this.router.navigate(['sales']);
      });
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
