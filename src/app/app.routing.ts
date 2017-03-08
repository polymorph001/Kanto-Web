import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {UserComponent} from './components/user.component';
import {AboutComponent} from './components/about.component';
import {EmployeesComponent} from './components/employees.component';

const appRoutes: Routes = [
    {
        path: '',
        component: UserComponent
    },
        {
        path: 'about',
        component: AboutComponent
    },
            {
        path: 'employees',
        component: EmployeesComponent
    }

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
