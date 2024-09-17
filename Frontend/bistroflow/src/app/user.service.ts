import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

//Defining Springboot API routes
// const SIGN_UP_API_URL = 'http://localhost:8080/api/v1/user/signup';
// const LOG_IN_API_URL = 'http://localhost:8080/api/v1/user/login';

const SIGN_UP_API_URL = `http://15.168.206.200:5001/api/v1/user/signup`;
const LOG_IN_API_URL = `http://15.168.206.200:5001/api/v1/user/login`;

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  //Creating method to handle the sign-up endpoint
  signUp(userData: any): Observable<any> {
    return this.http.post(SIGN_UP_API_URL, userData);
  }

  login(userData: any): Observable<any> {
    return this.http.post<any>(LOG_IN_API_URL, userData);
  }

  // Store JWT in localStorage
  saveToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  // Retrieve JWT from localStorage
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Remove JWT from localStorage
  logout(): void {
    localStorage.removeItem('authToken');
  }

  // Add JWT to headers
  getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': token ? `Bearer ${token}` : ''
    });
  }

}
