import { UserService } from './../services/user.service';
import { ArticlesService } from './../services/articles.service';
import { AlertService } from './../services/alert.service';
import { AuthService } from './../services/auth.service';
import { UserModel } from './../models/User.model';
import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {Sale} from '../models/Sale.model';
import {Subscription} from 'rxjs';
import {SalesService} from '../services/sales.service';
import {Router} from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-sales-list',
  templateUrl: './sales-list.component.html',
  styleUrls: ['./sales-list.component.css']
})
export class SalesListComponent implements OnInit, OnDestroy {

  sales: Sale[];
  salesSubscription: Subscription;
  saleConcerned: Sale;
  currentUser: UserModel;
  saleId: number;

  // modal searchEmail
  modalRef: BsModalRef;
  userEmailsSubscription: Subscription;
  // autocompletion
  userEmailsListForAutocompletion: string[ ];
  keyword = 'email';

  // -pre-record article limits
  userArticleLimitForAdultClothe = 4;
  userVoluntaryArticleLimitForAdultClothe = this.userArticleLimitForAdultClothe * 2;

  userArticleLimitForChildrenClothe = 2;
  userVoluntaryArticleLimitForChildrenClothe = this.userArticleLimitForChildrenClothe * 2;

  userArticleLimitForBookToyAndObject = 6;
  userVoluntaryArticleLimitForBookToyAndObject = this.userArticleLimitForBookToyAndObject * 2;
  
  constructor(private salesService: SalesService,
              private router: Router,
              private authService: AuthService,
              private alertService: AlertService,
              private articlesService: ArticlesService,
              private modalService: BsModalService,
              private userService: UserService) {
                this.authService.currentUser.subscribe(x => this.currentUser = x);
               }

  ngOnInit() {
    // subscription
    this.salesSubscription = this.salesService.salesSubject.subscribe(
      (sales: Sale[]) => {
        this.sales = sales;
      }
      );
    this.userEmailsSubscription = this.userService.listEmailsSubject.subscribe(
      (userEmails: string[]) => {
        this.userEmailsListForAutocompletion = userEmails;
      }
    );
    // if user is connected
    if (this.authService.currentUserValue) {
      console.log('user connected is voluntary: ' + this.currentUser.voluntary);
      console.log('user connected is responsible: ' + this.currentUser.responsible);
      //check token
      this.userService.isTokenAlreadyValid( () => {
      //get list of sales with nbr of article already record
      this.salesService.getSalesCurrentUserIsPresent(this.currentUser.email);
      this.salesService.emmitSales();
      // if voluntary -> get allUserEmails for validation (autocompletion)
      if (this.authService.currentUserValue.voluntary) {
        this.userService.getAllUserEmail(() => {
          this.userService.emitUserEmail();
        })
      }
      });
    } else {
      this.salesService.getSales();
      this.salesService.emmitSales();
    }
  }

  ngOnDestroy() {
    this.salesSubscription.unsubscribe();
  }

  /**
   * for pre-record new article on bdd
   * @param id -> sale Id
   */
  addArticles(id: number) {
    // find sale Id
    this.saleConcerned = this.sales[id];
    this.saleId = this.saleConcerned.id;
    // if user is connected
    if (this.authService.currentUserValue) {
      // for adult clothe sale
      // if user isn't voluntary
      console.log(this.saleConcerned.description);
      if (this.saleConcerned.description === 'Vêtements adulte') {
        if (this.currentUser.voluntary === false
          && this.saleConcerned.nbrArticlesPreRecordForUser < this.userArticleLimitForAdultClothe) {
          this.router.navigate(['/addArticles', this.saleId]);
        } else {
          if (this.currentUser.voluntary === false ) {
            this.alertArticleMax();
          }
        }
      }
      // if user is voluntary
      if (this.saleConcerned.description === 'Vêtements adulte') {
        if (this.currentUser.voluntary === true && this.saleConcerned.nbrArticlesPreRecordForUser < this.userVoluntaryArticleLimitForAdultClothe) {
          this.router.navigate(['/addArticles', this.saleId]);
        } else {
          if (this.currentUser.voluntary === true ) {
            this.alertArticleMax();
          }
        }
      }

      // for children clothe sale
      // if user isn't voluntary
      console.log(this.saleConcerned.description);
      if (this.saleConcerned.description === 'Vêtements enfants') {
        if (this.currentUser.voluntary === false
          && this.saleConcerned.nbrArticlesPreRecordForUser < this.userArticleLimitForChildrenClothe) {
          this.router.navigate(['/addArticles', this.saleId]);
        } else {
          if (this.currentUser.voluntary === false) {
            this.alertArticleMax();
          }
        }
      }
      // if user is voluntary
      if (this.saleConcerned.description === 'Vêtements enfants') {
        if (this.currentUser.voluntary === true && this.saleConcerned.nbrArticlesPreRecordForUser < this.userVoluntaryArticleLimitForChildrenClothe) {
          this.router.navigate(['/addArticles', this.saleId]);
        } else {
          if (this.currentUser.voluntary === true) {
            this.alertArticleMax();
          }
        }
      }

      // for toy book or object sale
      // if user isn't voluntary
      console.log(this.saleConcerned.description);
      if (this.saleConcerned.description === 'jouets Livres Cadeaux Bijoux') {
        if (this.currentUser.voluntary === false
          && this.saleConcerned.nbrArticlesPreRecordForUser < this.userArticleLimitForBookToyAndObject) {
          this.router.navigate(['/addArticles', this.saleId]);
        } else {
          if (this.currentUser.voluntary === false) {
            this.alertArticleMax();
          }
        }
      }
      // if user is voluntary
      if (this.saleConcerned.description === 'jouets Livres Cadeaux Bijoux') {
        if (this.currentUser.voluntary === true && this.saleConcerned.nbrArticlesPreRecordForUser < this.userVoluntaryArticleLimitForBookToyAndObject) {
          this.router.navigate(['/addArticles', this.saleId]);
        } else {
          if (this.currentUser.voluntary === true) {
            this.alertArticleMax();
          }
        }
      }
    } else {
      this.router.navigate(['/addArticles', this.saleId]);
    }
  }
  /**
   * For validate article
   */
  articlesValidation(id: number) {
    // find sale Id
    this.saleConcerned = this.sales[id];
    this.saleId = this.saleConcerned.id;
    this.articlesService.getAllArticlesForOneSale(this.saleId, () => {
      // this.articlesService.emitArticles();
      this.router.navigate(['/articlesValidation', this.saleId]);
    });
  }

  alertArticleMax() {
    this.alertService.error("Vous avez atteint la limite max d'articles pour cette vente.");
            setTimeout(() => {
              this.alertService.clear();
            }, 3000);
  }
  /**
   * show email modal
   * @param findEmailModal 
   */
  findEmailWithModal(findEmailModal: TemplateRef<any>) {
    this.modalRef = this.modalService.show(findEmailModal);
  }

  /**
   * get email for create new article 
   * @param emailIn -> from email modal
   * @param saleId 
   */
  selectedEmailOnModal(emailIn: any, saleId: number) {
    // do something with selected emailIn
    console.log('email de la personne pour enregistrer l\'article: ' + emailIn);
    console.log('bourse id concernée: ' + saleId);
    this.router.navigate(['addArticles/' + saleId + '/' + emailIn]);
    }
}
