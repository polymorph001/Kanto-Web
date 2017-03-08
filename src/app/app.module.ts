import { NgModule}      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule} from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent }  from './app.component';
import { HomeComponent }  from './components/home.component';
import { AboutComponent }  from './components/about.component';
import { EmployeesComponent }  from './components/employees.component';

import { routing }  from './app.routing';

// bootstrap(AppComponent, [ HTTP_PROVIDERS, Provider(BrowserXhr,{useClass:CustExtBrowserXhr})
// ]);

@NgModule({
  imports:      [ BrowserModule, FormsModule, HttpModule, routing ],
  declarations: [ AppComponent, HomeComponent, AboutComponent, EmployeesComponent ],
  bootstrap:    [ AppComponent ],
})

export class AppModule { 



}
