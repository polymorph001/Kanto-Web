import { Component } from '@angular/core';
import { PostsServices } from '../services/posts.service';



@Component({
moduleId: module.id,
  selector: 'user',
  templateUrl: 'user.component.html',
  providers: [PostsServices]
})
export class UserComponent {
  name: string;
  email: string;
  address: address;
  hobbies: string[];
  showHobbies: boolean;
  posts: Post[];


  constructor( private postsService: PostsServices) {
    this.name = 'Sam Smith';
    this.email = 'john@gmail.com';
    this.address = {
      street: '12 March St',
      city: 'Cape Town',
      country: 'South Africa',
    }
    this.hobbies = ['Music', 'Movies', 'Sports'];
    this.showHobbies = false;

this.postsService.getPosts().subscribe(posts => {
    this.posts = posts;
});
  }

  toggleHobbies() {
    if (this.showHobbies == true) {
      this.showHobbies = false;
    } else {
      this.showHobbies = true;
    }
  }

  // addHobby(hobby) {
  //   this.hobbies.push(hobby);
  // }

  // deleteHobby(i) {
  //     this.hobbies.splice(i, 1);
  // }



}

interface address {
  street: string;
  city: string;
  country: string;

}

interface Post {
    id: number;
    title: string;
    body: string;

}
