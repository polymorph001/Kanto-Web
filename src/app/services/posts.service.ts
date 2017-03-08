import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()

export class PostsServices {
    constructor(private http: Http) {
        console.log('PostsServices Initialised ...');
    }
    getPosts() {
        return this.http.get('https://jsonplaceholder.typicode.com/posts')
        .map(res => res.json());

        
    }
}