import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserOrderCount } from '../Models/userordercount'
import {SalesData} from '../Models/categorysales'
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  private baseURL = `http://15.168.206.200:5001/api/v1/analytics`;

  constructor(private httpClient: HttpClient) { }

  getUserOrderCount(): Observable<UserOrderCount[]> {
    var res= this.httpClient.get<UserOrderCount[]>(`${this.baseURL}/user-order-count`);
    console.log(res);
    return res;
  }
  
  getSalesData(): Observable<{ date: string, totalSales: number }[]> {
    return this.httpClient.get<{ date: string, totalSales: number }[]>(`${this.baseURL}/datewise-total-sales`);
  }

  getCategorySalesData(): Observable<SalesData[]> {
    return this.httpClient.get<SalesData[]>(`${this.baseURL}/monthwise-category-sold`);
  }
}
