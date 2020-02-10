import { UserService } from './../services/user.service';
import { Subscription } from 'rxjs';
import { UserModel } from './../models/User.model';
import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit, OnDestroy {

  userSubscription: Subscription;
  usersList: any[] = [];
  usersEmailList: string[] = [];
  userFilteredList: UserModel[] = [];
  // autocompletion
  keyword = 'email';
  autocompletionEmails = [ ];
  emailFilter: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userSubscription = this.userService.usersSubject.subscribe(
      (users: any[]) => {
        this.usersList = users;
      }
    )
    this.userService.getAllUsers(() => {
      this.userService.emitUsers();
      this.getUserEmailList();
    })
  }

  ngOnDestroy() {
    this.userSubscription.unsubscribe();
  }

  onDeclareVoluntary(userId: number) {
    this.usersList.forEach(user => {
      if (user.id === userId) {
        user.voluntary = true;
        this.userService.updateUserAndAddressSamePassword(user, () => {
        })
      }
    });
  }

  onDeclareVoluntaryOff(userId: number) {
    this.usersList.forEach(user => {
      if (user.id === userId) {
        user.voluntary = false;
        this.userService.updateUserAndAddressSamePassword(user, () => {})
      }
    });
  }

  onDeclareResponsible(userId: number) {
    this.usersList.forEach(user => {
      if (user.id === userId) {
        user.responsible = true;
        this.userService.updateUserAndAddressSamePassword(user, () => {})
      }
    });
  }

  onDeclareResponsibleOff(userId: number) {
    this.usersList.forEach(user => {
      if (user.id === userId) {
        user.responsible = false;
        this.userService.updateUserAndAddressSamePassword(user, () => {})
      }
    });
  }

  getUserEmailList() {
    this.usersList.forEach(user => {
      this.usersEmailList.push(user.email);
    });
  }

  selectEvent(emailIn: any) {
    this.userFilteredList = [];
    this.usersList.forEach(user => {
      if (user.email === emailIn) {
        this.userFilteredList.push(user);
        this.emailFilter= true;
      }
    });
  }

  onChangeSearch(emailIn: any) {
    this.usersList.forEach(user => {
      if (user.email === emailIn) {
        this.userFilteredList.push(user);
      }
    });
  }

  onFocused($event) {
  }

  onStopFilter() {
    this.emailFilter = false;
    window.location.reload(); 
  }
}

      
