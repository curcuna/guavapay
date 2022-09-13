import { Injectable } from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {Router} from "@angular/router";
import {Person} from "../domain/person";
import {environment} from "../../environments/environment";
import {Order} from "../domain/order";
import {Cargo} from "../domain/cargo";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, public router: Router) {}

  getAllOrders(): Observable<any> {
    return this.http
      .get<any>(`${environment.apiUrl}/order/admin`);
  }

  getAllOrdersAssignedToCourier(courierId: number): Observable<any> {
    return this.http
      .get<any>(`${environment.apiUrl}/cargo/person/`+ courierId);
  }


  assignCourier(cargo: Cargo): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}/cargo`, cargo);
  }

  updateOrder(order: Order): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}/order/status`, order);
  }

  //
  // cancelOrder(orderId: number): Observable<any> {
  //   return this.http.put<any>(`/order/`+ orderId, null);
  // }
  //
  // changeDestination(order: Order): Observable<any> {
  //   return this.http.put<any>(`/order/destination`, order);
  // }
}
