import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import {CustExtBrowserXhr} from '../cust-ext-browser-xhr';
import {BrowserXhr } from '@angular/http';

@Injectable()

export class UsersServices {
    constructor(private http: Http) {
        console.log('UsersServices Initialised ...');
    }
    getUsers() {
        return this.http.get('https://helena-4a3f6.firebaseio.com/users')
        .map(res => res.json());

        
    }
}