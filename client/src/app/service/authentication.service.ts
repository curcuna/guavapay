import { Injectable } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";
import {Person} from "../domain/person";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  headers = new HttpHeaders().set('Content-Type', 'application/json');
  currentUser!: Person;

  constructor(private http: HttpClient, public router: Router) {}

  signUp(user: Person): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'text/plain, */*',
        'Content-Type': 'application/json' // We send JSON
      }),
      responseType: 'text' as 'json'  // We accept plain text as response.
    };
    return this.http
      .post<any>(`${environment.apiUrl}/authentication/signup`, user,  httpOptions);
  }

  createCourier(courier: Person): Observable<any> {
    return this.http
      .post<any>(`${environment.apiUrl}/authentication/signup/courier`, courier);
  }

  login(user: Person) {
    return this.http
      .post<any>(`${environment.apiUrl}/authentication/login`, user)
      .subscribe((res: any) => {
        localStorage.setItem('access_token', res.token);
        this.currentUser = res.person;
        if(this.currentUser.roles.includes('ROLE_USER')){
          this.router.navigate(['user/' + this.currentUser?.id]);
        } else if(this.currentUser.roles.includes('ROLE_ADMIN')){
          this.router.navigate(['admin/' + this.currentUser?.id]);
        } else if(this.currentUser.roles.includes('ROLE_COURIER')){
          this.router.navigate(['courier/' + this.currentUser?.id]);
        }
      });
  }

  getToken() {
    return localStorage.getItem('access_token');
  }
  get isLoggedIn(): boolean {
    let authToken = localStorage.getItem('access_token');
    return authToken !== null ? true : false;
  }
  doLogout() {
    let removeToken = localStorage.removeItem('access_token');
    if (removeToken == null) {
      this.router.navigate(['login']);
    }
  }

  // Error
  handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      msg = error.error.message;
    } else {
      // server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }

  getAllCouriers(): Observable<any> {
    return this.http
      .get<any>(`${environment.apiUrl}/authentication/courier`);
  }
}
