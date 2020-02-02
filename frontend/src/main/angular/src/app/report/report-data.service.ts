import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Offer} from './offer';
import {SearchCriteria} from "../pages/search-criteria/search-criteria";
import {share} from "rxjs/operators";
import {AuthService} from "../auth/services/auth.service";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ReportDataService {

  private reportsURL = API_URL + '/report';

  constructor(private http: HttpClient, public auth: AuthService) {
  }

  public findAll(): Observable<Offer[]> {
    return this.http.get<Offer[]>(this.reportsURL, httpOptions);
  }

  public getUserSearchCreateria(): Promise<SearchCriteria> {
    return fetch(this.reportsURL, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': this.auth.getToken()
      }
    }).then(response => {
      if (!response.ok) {
        throw new Error(response.statusText)
      }
      return response.json()
    });
  }

  public saveReportCreation(report: SearchCriteria): Observable<SearchCriteria> {
    this.http.post<SearchCriteria>(this.reportsURL, report).pipe(share());
    return this.http.post<SearchCriteria>(this.reportsURL, report);
  }

}
