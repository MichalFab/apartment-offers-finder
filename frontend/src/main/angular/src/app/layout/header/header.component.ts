import {Component, OnInit} from '@angular/core';
import {AuthService} from 'src/app/auth/services/auth.service';
import {ReportDataService} from 'src/app/report/report-data.service';
import {Offer} from "../../report/offer";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  offers: Offer[];

  constructor(public authService: AuthService, public reportDataService: ReportDataService) {
  }

  ngOnInit() {
  }

  public logout(): void {
    this.authService.logout();
  }
}
