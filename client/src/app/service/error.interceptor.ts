import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map} from "rxjs/operators";
import {ToastrService} from "ngx-toastr";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private toasterService : ToastrService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request)
      .pipe(
        map(res   => {
          return res
        }),
        catchError((error: HttpErrorResponse) => {
          this.toasterService.error(error.error, "", { positionClass: 'toast-top-center' });
          return throwError(error);
        })
      )
  }
}
