import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./page/login/login.component";
import { AuthGuardService as AuthGuard } from './service/auth-guard.service';
import { UserComponent } from "./page/user/user.component";
import {SignupComponent} from "./page/signup/signup.component";
import {AdminComponent} from "./page/admin/admin.component";
import {CourierComponent} from "./page/courier/courier.component";

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'user/:id', component: UserComponent, canActivate: [AuthGuard]},
  { path: 'admin/:id', component: AdminComponent, canActivate: [AuthGuard]},
  { path: 'courier/:id', component: CourierComponent, canActivate: [AuthGuard]},
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
