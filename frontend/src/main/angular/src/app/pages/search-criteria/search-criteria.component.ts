import {Component, OnInit} from '@angular/core';
import {ReportDataService} from '../../report/report-data.service';
import {SearchCriteria} from "./search-criteria";
import { Router } from '@angular/router';

@Component({
  selector: 'app-report-creation',
  templateUrl: './search-criteria.component.html',
  styleUrls: ['./search-criteria.component.scss']
})
export class SearchCriteriaComponent implements OnInit {
  searchCriteria: SearchCriteria = new SearchCriteria('', '', 0, 0, null, null, null, null, null, null, null, false);
  cities = ['krakow', 'torun', 'lublin', 'lodz','olsztyn','warszawa','opole','rzeszow','bialystok','gdansk','katowice','kielce','poznan','szczecin'];
  minRooms = ['1','2','3','4','5','6','7','8'];
  maxRooms = ['1','2','3','4','5','6','7','8'];
  constructor(private reportDataService: ReportDataService, private router: Router) {
      }

  ngOnInit() {
    this.fetchUserSearchCriteria();
  }


  private  fetchUserSearchCriteria() {
    this.reportDataService.getUserSearchCreateria()
      .then(searchCriteria => this.searchCriteria = searchCriteria);
  }
  public createNewReport(): void {

    this.reportDataService.saveReportCreation(this.searchCriteria)
      .subscribe(
        (data) => {
          // Page redirect when getting response
          this.router.navigate(['/']);
        },
        (error) => {
          console.log("err", error);
        });
  }
}
