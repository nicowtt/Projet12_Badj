import { AddressModel } from './../models/Address.model';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { AuthService } from './../services/auth.service';
import { UserService } from './../services/user.service';
import { UserModel } from './../models/User.model';
import { Component, OnInit } from '@angular/core';
import { typeWithParameters } from '@angular/compiler/src/render3/util';

@Component({
  selector: 'app-user-modification',
  templateUrl: './user-modification.component.html',
  styleUrls: ['./user-modification.component.css']
})
export class UserModificationComponent implements OnInit {

  userSubscription: Subscription;
  currentUser: UserModel;
  userToUpdate: UserModel;
  updateUserForm: FormGroup;
  submitted: boolean;
  userWantToChangeHisPass: boolean;

  constructor(private userService: UserService,
              private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router) { 
    this.authService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
    this.initForm();
    this.userSubscription = this.userService.userSubject.subscribe(
      (user: UserModel) => {
        this.userToUpdate = user;
      }
    )
    this.userService.getOneUser(this.currentUser, () => {
      this.initNgModel();
     })
    
  }

  initForm() {
    this.updateUserForm = this.formBuilder.group({
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

  initNgModel() {
    this.updateUserForm.get('name').setValue(this.userToUpdate.name);
    this.updateUserForm.get('lastName').setValue(this.userToUpdate.lastName);
    this.updateUserForm.get('email').setValue(this.userToUpdate.email);
    this.updateUserForm.get('phone').setValue(this.userToUpdate.phone);
    this.updateUserForm.get('street').setValue(this.userToUpdate.address.street);
    this.updateUserForm.get('postalCode').setValue(this.userToUpdate.address.postalCode);
    this.updateUserForm.get('city').setValue(this.userToUpdate.address.city);
  }

  // easy access to form fields
  get f() { return this.updateUserForm.controls; }

  onSubmit() {
    this.submitted = true;

     // stop here if form is invalid
     if (!this.userWantToChangeHisPass) {
      if (this.f.name.value === ""  || 
      this.f.lastName.value === ""  || 
      this.f.email.value === ""  ||
      this.f.phone.value === ""  ||
      this.f.street.value == ""  ||
      this.f.postalCode.value === ""  ||
      this.f.city.value === "") {
        console.log('formulaire error');
        return;
      }
    }
    if (this.userWantToChangeHisPass) {
      if (this.updateUserForm.invalid) {
        console.log('formulaire error');
         return;
        }
      }
     
    // mapping
    const userUpdate = new UserModel();
    userUpdate.id = this.userToUpdate.id;
    userUpdate.name = this.f.name.value;
    userUpdate.lastName = this.f.lastName.value;
    userUpdate.email = this.f.email.value;
    userUpdate.password = this.f.password.value;
    userUpdate.phone = this.f.phone.value;
    userUpdate.responsible = this.userToUpdate.responsible;
    userUpdate.voluntary = this.userToUpdate.voluntary;
    const adressUpdate = new AddressModel;
    adressUpdate.id = this.userToUpdate.address.id;
    adressUpdate.street = this.f.street.value;
    adressUpdate.postalCode = this.f.postalCode.value;
    adressUpdate.city = this.f.city.value;
    userUpdate.address = adressUpdate;
    
    if (!this.userWantToChangeHisPass) {
      userUpdate.password = this.userToUpdate.password;
      this.updateUserForm.get('password').setValue(this.userToUpdate.password);
      this.userService.updateUserAndAddressSamePassword(userUpdate, () => {
        this.router.navigate(['/personalSpace']);
      })
    }
    if (this.userWantToChangeHisPass) {
      userUpdate.password = this.updateUserForm.get('password').value;
      this.userService.updateUserAndAddressAndPassword(userUpdate, () => {
        this.router.navigate(['/personalSpace']);
      })
    }
  }

  changePass() {
    if (this.userWantToChangeHisPass) {
      this.userWantToChangeHisPass = false;
    } else {
      this.userWantToChangeHisPass = true;
    }
  }

}
