import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderDetails } from '../orderDetails';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/api/v1/orders';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/categories`);
  }

  getProductsByCategoryId(categoryId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/products/${categoryId}`);
  }

  saveOrder(orderRequest: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.baseUrl}/save`, orderRequest, { headers });
  }
}
