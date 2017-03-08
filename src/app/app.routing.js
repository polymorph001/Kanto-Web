"use strict";
var router_1 = require('@angular/router');
var home_component_1 = require('./components/home.component');
var about_component_1 = require('./components/about.component');
var employees_component_1 = require('./components/employees.component');
var appRoutes = [
    {
        path: '',
        component: home_component_1.HomeComponent
    },
    {
        path: 'about',
        component: about_component_1.AboutComponent
    },
    {
        path: 'employees',
        component: employees_component_1.EmployeesComponent
    }
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routing.js.map