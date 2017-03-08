import { NgModule}      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule} from '@angular/http';
import { FormsModule } from '@angular/forms';


//import {CustExtBrowserXhr} from './cust-ext-browser-xhr';

//import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


import { AppComponent }  from './app.component';
import { UserComponent }  from './components/user.component';
import { AboutComponent }  from './components/about.component';
import { EmployeesComponent }  from './components/employees.component';

import { routing }  from './app.routing';

// bootstrap(AppComponent, [ HTTP_PROVIDERS, Provider(BrowserXhr,{useClass:CustExtBrowserXhr})
// ]);

@NgModule({
  imports:      [ BrowserModule, FormsModule, HttpModule, routing ],
  declarations: [ AppComponent, UserComponent, AboutComponent, EmployeesComponent ],
  bootstrap:    [ AppComponent ],
  //providers:    [ BrowserXhr, CustExtBrowserXhr ]
})
export class AppModule { 



}
