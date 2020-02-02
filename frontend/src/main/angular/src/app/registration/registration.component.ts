import { Component, OnInit } from '@angular/core';
import { Credentials} from "../auth/credentials";
import {RegisterService} from "../auth/services/register.service";
import {HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  credentials: Credentials = new Credentials('', '');
  confirmPassword: String;

  constructor(private router: Router, private registrationService: RegisterService) { }

  ngOnInit() {
  }

  public register(): void {
    this.registrationService.register(this.credentials)
      .subscribe((res: HttpResponse<any>) => {
        this.router.navigate(['/login']);
      });
  }
}
