import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {UserModel} from "../models/User.model";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  currentUser: UserModel;

  constructor(private authService: AuthService) {
    this.authService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
  }

  /**
   *  when user clic on SignOut
   */
  onSignOut() {
    this.authService.logout();
  }

}
