import { Injectable } from '@angular/core';

import { Credentials } from '../credentials';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  observe: 'response' as 'response'
};
const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  redirectToUrl: string = '/';

  constructor(private http: HttpClient) { }

  public register(credentials: Credentials) {
    let registerURL = API_URL + '/users';
    return this.http.post(registerURL, credentials, httpOptions);
  }
}
