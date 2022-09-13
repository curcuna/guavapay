import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../service/authentication.service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  constructor(
    public fb: FormBuilder,
    public authService: AuthenticationService,
    public router: Router
  ) {
    this.signupForm = this.fb.group({
      username: [''],
      password: [''],
    });
  }
  ngOnInit() {}
  registerUser() {
    this.authService.signUp(this.signupForm.value).subscribe((res) => {
        this.signupForm.reset();
        this.router.navigate(['login']);
    });
  }
}
