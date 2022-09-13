import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginComponent} from "./page/login/login.component";
import { UserComponent } from './page/user/user.component';
import { HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { AuthInterceptor} from "./service/authconfig.interceptor";
import { ReactiveFormsModule} from "@angular/forms";
import { SignupComponent } from './page/signup/signup.component';
import { ErrorInterceptor} from "./service/error.interceptor";
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { AdminComponent } from './page/admin/admin.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatTabsModule} from "@angular/material/tabs";
import {MatSelectModule} from "@angular/material/select";
import { CourierComponent } from './page/courier/courier.component';

export function tokenGetter() {
  return localStorage.getItem("token");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    SignupComponent,
    AdminComponent,
    CourierComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    ToastrModule.forRoot(),
    MatToolbarModule,
    MatIconModule,
    MatTabsModule,
    MatSelectModule,

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
