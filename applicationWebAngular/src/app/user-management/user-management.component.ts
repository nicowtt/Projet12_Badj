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

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userSubscription = this.userService.usersSubject.subscribe(
      (users: any[]) => {
        this.usersList = users;
      }
    )
    this.userService.getAllUsers(() => {
      this.userService.emitUsers();
    })
  }

  ngOnDestroy() {
    this.userSubscription.unsubscribe();
  }

  onDeclareVoluntary(userId: number) {
    this.usersList.forEach(user => {
      if (user.id === userId) {
        user.voluntary = true;
        this.userService.updateUser(user, () => {
        })
      }
    });
  }

  onDeclareVoluntaryOff(userId: number) {
    this.usersList.forEach(user => {
      if (user.id === userId) {
        user.voluntary = false;
        this.userService.updateUser(user, () => {
        })
      }
    });
  }
}
