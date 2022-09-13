import { Injectable } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";
import {Person} from "../domain/person";
import {environment} from "../../environments/environment";
import {Order} from "../domain/order";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, public router: Router) {}

  getOrders(userId: number ): Observable<any> {
    return this.http
      .get<any>(`${environment.apiUrl}/order/person/` + userId);
  }

  createOrder(order: Order): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}/order`, order);
  }

  cancelOrder(orderId: number): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}/order/`+ orderId, null);
  }

  changeDestination(order: Order): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}/order/destination`, order);
  }
}
