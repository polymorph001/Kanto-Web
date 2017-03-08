import { Component } from '@angular/core';
import { UsersServices } from '../services/users.service';

@Component({
  selector: 'employees',
  template: `
  <h1>List of all Employees</h1>
  
  <ul>
    <li *ngFor="let user of users"> {{user.FirstName}} {{user.MiddleName}} {{user.LastName}} </li>


  </ul>
  `,
  providers: [UsersServices]
})
export class EmployeesComponent {
      users: Users[];

      constructor(private usersService: UsersServices) {
          this.usersService.getUsers().subscribe (users => {
              this.users = users;
            })
      }
}

interface Users {
    FirstName: string;
    LastName: string;

}
