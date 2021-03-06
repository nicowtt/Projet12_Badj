import { AddressModel } from './../../models/Address.model';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserModel} from "../../models/User.model";
import {UserService} from "../../services/user.service";
import {first} from "rxjs/operators";
import {AlertService} from "../../services/alert.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private alertService: AlertService) {

    // redirect to home if already logged in
    if (this.authService.currentUserValue) {
      this.router.navigate(['/Sales']);
    }
  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.signUpForm = this.formBuilder.group({
      name:['', [Validators.required]],
      lastName:['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phone:['', [Validators.required]],
      street:['', [Validators.required]],
      postalCode:['', [Validators.required]],
      city:['', [Validators.required]],
      password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{3,}/)]]
    });
  }

  // easy access to form fields
  get f() { return this.signUpForm.controls; }

  onSubmit() {

    this.submitted = true;

    // stop here if form is invalid
    if (this.signUpForm.invalid) {
      console.log('formulaire error');
      return;
    }
    // mapp from form
    this.loading = true;
    const newUser = new UserModel();
    newUser.name = this.f.name.value;
    newUser.lastName = this.f.lastName.value;
    newUser.email = this.f.email.value;
    newUser.password = this.f.password.value;
    newUser.phone = this.f.phone.value;
    newUser.responsible = false;
    newUser.voluntary = false;
    const newAdress = new AddressModel;
    newAdress.street = this.f.street.value;
    newAdress.postalCode = this.f.postalCode.value;
    newAdress.city = this.f.city.value;
    newUser.address = newAdress;

    this.userService.AddUser(newUser, () => {
      this.router.navigate(['auth/signin']);
    }, () => {
      this.loading = false;
    })
  }

}
