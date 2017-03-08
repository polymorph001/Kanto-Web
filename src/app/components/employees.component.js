"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var users_service_1 = require('../services/users.service');
var EmployeesComponent = (function () {
    function EmployeesComponent(usersService) {
        var _this = this;
        this.usersService = usersService;
        this.usersService.getUsers().subscribe(function (users) {
            _this.users = users;
        });
    }
    EmployeesComponent = __decorate([
        core_1.Component({
            selector: 'employees',
            template: "\n  <h1>List of all Employees</h1>\n  \n  <ul>\n    <li *ngFor=\"let user of users\"> {{user.FirstName}} {{user.MiddleName}} {{user.LastName}} </li>\n\n\n  </ul>\n  ",
            providers: [users_service_1.UsersServices]
        }), 
        __metadata('design:paramtypes', [users_service_1.UsersServices])
    ], EmployeesComponent);
    return EmployeesComponent;
}());
exports.EmployeesComponent = EmployeesComponent;
//# sourceMappingURL=employees.component.js.map