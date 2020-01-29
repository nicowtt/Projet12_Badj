import { AlertService } from './../services/alert.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ArticlesService } from './../services/articles.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ArticleModel } from './../models/Article.model';
import { Component, OnInit, OnDestroy } from '@angular/core';


@Component({
  selector: 'app-cash-articles',
  templateUrl: './cash-articles.component.html',
  styleUrls: ['./cash-articles.component.css']
})
export class CashArticlesComponent implements OnInit, OnDestroy {
  
  articlesCashList: ArticleModel[] = [];
  articleConcerned: ArticleModel;
  articleSubscription: Subscription;
  articleForUrgentValidate: ArticleModel;

  saleId: number;

  articleForm: FormGroup;
  submitted = false;

  result: number;
  resultWithTenPerCent: number;

  givenForm: FormGroup;
  refund: number;

  constructor(private formBuilder: FormBuilder,
              private articlesService: ArticlesService,
              private route: ActivatedRoute,
              private alertService: AlertService,
              private router: Router) { }

  ngOnInit() {
    this.initForm();
    this.saleId = this.route.snapshot.params['saleId'];
    // get article concerned from back
    this.articleSubscription = this.articlesService.articleOneSubject.subscribe(
      (article: ArticleModel) => {
        this.articleConcerned = article;
      }
    )
  }

  ngOnDestroy() {

  }

  // easy access to form fields
  get f() { return this.articleForm.controls; }
  get g() { return this.givenForm.controls; }

  initForm() {
    this.articleForm = this.formBuilder.group({
    articleNumber: [null, [Validators.required, Validators.pattern(/^[1-9]\d{0,1}/)]]
    });

    this.givenForm = this.formBuilder.group({
      givenNumber: [null, [Validators.required, Validators.pattern(/^[1-9]\d{0,1}/)]]
    })
  }

  onSubmitArticle() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.articleForm.invalid) {
      return;
    }


    // get article on bdd and add on articleCashList
    this.articlesService.getOneArticleWithSaleNumberAndSaleId(this.f.articleNumber.value, this.saleId, () => {
      // add article only if present on sale concerned 
      if (this.articleConcerned === null) {
        this.alertArticleDontExist();
      } else {
        // error if article is already sold
        if (this.articleConcerned.sold) {
          this.alertArticleAlreadySold();
        } else {
          // error if article is return to owner
          if (this.articleConcerned.returnOwner) {
            this.alertArticleIsReturnOwner();
          } else {
            if (this.articleConcerned.sale.id == this.saleId) {
              // avoid doublon
              let doublon = false;
              this.articlesCashList.forEach(article => {
                if (article.id === this.articleConcerned.id) { doublon = true; }
              });
              if (!doublon && !this.articleConcerned.sold) {
                this.articlesCashList.push(this.articleConcerned);
              }
            } else {
              this.alertArticleDontExist();
            }
          }
        }
      }

      this.articleForm.reset();
      this.findResult();
      this.findResultWith10PourCent();
    })
  }

  urgentValidateArticle(articleId: number) {
    this.articlesCashList.forEach(article => {
      if (article.id === articleId) {
        this.articleForUrgentValidate = article;
        this.articleForUrgentValidate.validateToSell = true;
        this.articlesService.updateArticle(this.articleForUrgentValidate, () => {
          article.validateToSell = true;
        })
      }
    });
  }

  removeArticle(indexId: number) {
    this.articlesCashList.splice(indexId,1);
    this.findResult();
    this.findResultWith10PourCent();
    this.givenForm.reset();
    this.refund = 0;
  }

  alertArticleDontExist() {
    this.alertService.error('Ce numéro d\'article n\'existe pas!');
    setTimeout(() => {
      this.alertService.clear();
    }, 1500);
  }

  alertArticleAlreadySold() {
    this.alertService.error('Cette référence d\'article à déja été vendu');
    setTimeout(() => {
      this.alertService.clear();
    }, 2000);
  }

  alertArticleDoesntValidate() {
    this.alertService.error('Cet article n\'est pas validé, impossible de le vendre');
    setTimeout(() => {
      this.alertService.clear();
    }, 2000);
  }

  alertOneArticleIsNotValidate() {
    this.alertService.error('Tous les articles doivent être validé avant l\'encaissement !');
    setTimeout(() => {
      this.alertService.clear();
    }, 2000);
  }

  alertArticleIsReturnOwner() {
    this.alertService.error('Cet article à été déclaré rendu au propriétaire');
    setTimeout(() => {
      this.alertService.clear();
    }, 3000);
  }

  findResult() {
    this.result = 0;
    this.articlesCashList.forEach(article => {
      this.result = this.result + article.price;
      console.log('result: ' + this.result)
    });
  }

  findResultWith10PourCent() {
    this.resultWithTenPerCent = 0;
    let tenPercent = this.result / 10;
    let resultWithTenPerCentNumber = this.result + tenPercent;
    let resultWithTenPerCentString = resultWithTenPerCentNumber.toFixed(1);
    this.resultWithTenPerCent = +resultWithTenPerCentString;
    console.log('result without round up :' + resultWithTenPerCentNumber);
  }

  onSubmitGiven() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.givenForm.invalid) {
      return;
    }
    let given = this.g.givenNumber.value;
    // if result > 0 -> given how to refund
    if (this.resultWithTenPerCent > 0) {
      this.refund = given - this.resultWithTenPerCent;

    }
  }

  validateCash() {
    // avoid validate if one article is not validate
    let allArticleValidate = true;
    this.articlesCashList.forEach(article => {
      if (article.validateToSell === false) {
        allArticleValidate = false;
        this.alertOneArticleIsNotValidate();
      }
    });
    if (allArticleValidate) {
      this.articlesCashList.forEach(article => {
        article.sold = true;
        this.articlesService.updateArticle(article, () => {
          this.alertService.success('la transaction est validé', true);
          setTimeout(() => {
            window.location.reload();
            this.alertService.clear();
          }, 3000);
        })
      });
    }
  }
}
