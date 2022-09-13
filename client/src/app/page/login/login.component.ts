import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../service/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  signinForm: FormGroup;

  constructor(
    public fb: FormBuilder,
    public authService: AuthenticationService,
    public router: Router
  ) {
    this.signinForm = this.fb.group({
      username: [''],
      password: [''],
    });
  }
  ngOnInit(): void {}

  loginUser() {
    this.authService.login(this.signinForm.value);
  }

  setCredentials(username:string, password : string): void{
    this.signinForm.setValue({
      username: username,
      password: password
    });
  }

  setUserCredentials(): void{
   this.setCredentials("person1", "pass1");
  }

  setAdminCredentials(): void{
    this.setCredentials("person2", "pass2");
  }

  setCourierCredentials(): void{
    this.setCredentials("person3", "pass3");
  }
}
