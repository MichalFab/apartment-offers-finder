import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Offer} from "../report/offer";


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class OffersService {

  private reportsURL = API_URL + '/offers';

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Offer[]> {
    return this.http.get<Offer[]>(this.reportsURL, httpOptions);
  }
}
