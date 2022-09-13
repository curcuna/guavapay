import { Injectable } from '@angular/core';
import {AuthenticationService} from "./authentication.service";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {state} from "@angular/animations";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
  constructor(public authenticationService: AuthenticationService, public router: Router) {}
  canActivate(): boolean {
      if (this.authenticationService.isLoggedIn !== true) {
        window.alert("Access not allowed!");
        this.router.navigate(['login'])
      }
      return true;
  }
}
