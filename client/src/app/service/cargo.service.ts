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
export class CargoService {

  constructor(private http: HttpClient, public router: Router) {}

  updateCargoStatus(cargo: Cargo): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}/cargo/status`, cargo);
  }
}
