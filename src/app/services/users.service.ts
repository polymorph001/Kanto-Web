import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()

export class UsersServices {
    constructor(private http: Http) {
        console.log('UsersServices Initialised ...');
    }
    getUsers() {
        return this.http.get('https://helena-4a3f6.firebaseio.com/users.json')
        .map(res => res.json());
    }

}