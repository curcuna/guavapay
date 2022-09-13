import { Component } from '@angular/core';
import {UserService} from "./service/user.service";
import {AuthenticationService} from "./service/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  constructor(public authenticationService: AuthenticationService) {
  }

  logout(): void{
    this.authenticationService.doLogout();
  }
}
