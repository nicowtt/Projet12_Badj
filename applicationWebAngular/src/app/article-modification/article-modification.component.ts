import { FormBuilder } from '@angular/forms';
import { FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticlesService } from './../services/articles.service';
import { ArticleModel } from './../models/Article.model';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-article-modification',
  templateUrl: './article-modification.component.html',
  styleUrls: ['./article-modification.component.css']
})
export class ArticleModificationComponent implements OnInit, OnDestroy {

  articleConcerned: ArticleModel;
  articleSubscription: Subscription;
  toChange: string;

  modificationForm: FormGroup;
  submitted = false;

  // categories types
  clothe: boolean;
  object: boolean;
  book: boolean;
  toy: boolean;

  // gender
  genders = ['Homme / Garçon', 'Femme / Fille'];

  constructor(private articlesService: ArticlesService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private router: Router,
              ) { }

  ngOnInit() {
    this.initForm();
    // get article id
    const id = this.route.snapshot.params['id'];
    // get article concerned from back
    this.articleSubscription = this.articlesService.articleOneSubject.subscribe(
      (article: ArticleModel) => {
        this.articleConcerned = article;
      }
    )
    this.articlesService.getOneArticle(id);
    this.articlesService.emitArticles();
    setTimeout(() => {
      this.toChange = this.route.snapshot.params['change'];
      console.log('to change:' + this.toChange);
      // find type of article
    if (this.articleConcerned.book !== null ) {
      this.book = true;
    }
    if (this.articleConcerned.object !== null ) {
      this.object = true;
    }
    if (this.articleConcerned.clothe !== null ) {
      this.clothe = true;
    }
    if (this.articleConcerned.toy !== null ) {
      this.toy = true;
    }
    }, 500);
  }

  ngOnDestroy() {
    this.articleSubscription.unsubscribe();
  }

  initForm() {
    this.modificationForm = this.formBuilder.group({
      size: [null, [Validators.required]],
      gender: [null, [Validators.required]],
      material: [null, [Validators.required]],
      color: [null, [Validators.required]],
      type: [null, [Validators.required]],
      brand: [null, [Validators.required]],
      bookName: [null, [Validators.required]],
      bookAuthor: [null, [Validators.required]],
      price: [null, [Validators.required, Validators.pattern(/^[1-9]\d{0,4}(?:\.\d{1,2})?|\.\d{1,2}$/)]],
      comment: [null]
    });
  }

  // easy access to form fields
  get f() { return this.modificationForm.controls; }

  onSubmit() {
    this.submitted = true;
    // error return
    if (this.toChange !== 'comment') {
      if (this.f.size.value === null && this.f.price.value === null && this.f.gender.value === null &&
        this.f.material.value === null && this.f.color.value === null && this.f.type.value === null &&
        this.f.brand.value === null && this.f.bookName.value === null && this.f.bookAuthor.value === null) {
        console.log('formulaire non valide');
        return;
      }
    }
    
  if (this.toChange === 'size') {this.updateSizeOnArticleConcerned();}
  if (this.toChange === 'price') {this.updatePriceOnArticleConcerned();}
  if (this.toChange === 'comment') {this.updateCommentOnArticleConcerned();}
  if (this.toChange === 'gender') {this.updateGenderOnArticleConcerned();}
  if (this.toChange === 'material') {this.updateMaterialOnArticleConcerned();}
  if (this.toChange === 'color') {this.updateColorOnArticleConcerned();}
  if (this.toChange === 'type') {this.updateTypeOnArticleConcerned();}
  if (this.toChange === 'brand') {this.updateBrandOnArticleConcerned();}
  if (this.toChange === 'bookName') {this.updateBookNameOnArticleConcerned();}
  if (this.toChange === 'bookAuthor') {this.updateBookAuthorOnArticleConcerned();}
  }

  updateCommentOnArticleConcerned() {
    if (this.book) {
      this.articleConcerned.book.comment = this.f.comment.value;
      console.log('update comment article -> ' + this.articleConcerned.book.comment);
    }
    if (this.clothe) {
      this.articleConcerned.clothe.comment = this.f.comment.value;
      console.log('update comment article -> ' + this.articleConcerned.clothe.comment);
    }
    if (this.object) {
      this.articleConcerned.object.comment = this.f.comment.value;
      console.log('update comment article -> ' + this.articleConcerned.object.comment);
    }
    if (this.toy) {
      this.articleConcerned.toy.comment = this.f.comment.value;
      console.log('update comment article -> ' + this.articleConcerned.toy.comment);
    }
    this.updateArticle();
  }

  updateColorOnArticleConcerned() {
    if (this.clothe) {
      this.articleConcerned.clothe.color = this.f.color.value;
      console.log('update color article -> ' + this.articleConcerned.clothe.color);
    }
    if (this.object) {
      this.articleConcerned.object.color = this.f.color.value;
      console.log('update color article -> ' + this.articleConcerned.object.color);
    }
    if (this.toy) {
      this.articleConcerned.toy.color = this.f.color.value;
      console.log('update color article -> ' + this.articleConcerned.toy.color);
    }
    this.updateArticle();
  }

  updateBrandOnArticleConcerned() {
    if (this.object) {
      this.articleConcerned.object.brand = this.f.brand.value;
      console.log('update brand article -> ' + this.articleConcerned.object.brand);
    }
    if (this.toy) {
      this.articleConcerned.toy.brand = this.f.brand.value;
      console.log('update brand article -> ' + this.articleConcerned.toy.brand);
    }
    this.updateArticle();
  }

  updateTypeOnArticleConcerned() {
    this.articleConcerned.type = this.f.type.value;
    console.log('update type article -> ' + this.articleConcerned.type);
    this.updateArticle();

  }

  updatePriceOnArticleConcerned() {
    this.articleConcerned.price = this.f.price.value;
    console.log('update price article -> ' + this.articleConcerned.price);
    this.updateArticle();
  }

  updateSizeOnArticleConcerned() {
    this.articleConcerned.clothe.size = this.f.size.value;
    console.log('update size article -> ' + this.articleConcerned.clothe.size);
    this.updateArticle();
  }

  updateGenderOnArticleConcerned() {
    this.articleConcerned.clothe.gender = this.f.gender.value;
    console.log('update genre article -> ' + this.articleConcerned.clothe.gender);
    this.updateArticle();
  }

  updateMaterialOnArticleConcerned() {
    this.articleConcerned.clothe.material = this.f.material.value;
    console.log('update material article -> ' + this.articleConcerned.clothe.material);
    this.updateArticle();
  }

  updateBookNameOnArticleConcerned() {
    this.articleConcerned.book.name = this.f.bookName.value;
    console.log('update bookName article -> ' + this.articleConcerned.book.name);
    this.updateArticle();
  }

  updateBookAuthorOnArticleConcerned() {
    this.articleConcerned.book.author = this.f.bookAuthor.value;
    console.log('update bookAuthor article -> ' + this.articleConcerned.book.author);
    this.updateArticle();
  }

  updateArticle() {
    this.articlesService.updateArticle(this.articleConcerned);
    setTimeout(() => {
    this.router.navigate(['articlesValidation/'+ this.articleConcerned.sale.id]);
    },500);
  }
}
