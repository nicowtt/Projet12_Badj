import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  signInForm: FormGroup;
  errorMessage: string;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.signInForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/[0-9a-zA-Z]{3,}/)]] // regex au moins 6 caractères alphanumérique
    });
  }

  onSubmit() { // ici on récupère les données du formulaire
    const email = this.signInForm.get('email').value;
    const password = this.signInForm.get('password').value;
    this.authService.signInUser(email, password);//.then(
    //   () => { // si ok
    //     this.router.navigate(['/sales']); // on redirige vers la page /sales
    //   },
    //   (error) => { // si erreur
    //     this.errorMessage = error; // on l'affiche dans le template
    //   }
    // );
  }

}
