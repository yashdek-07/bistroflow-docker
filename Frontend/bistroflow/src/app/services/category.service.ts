
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../Models/category';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = `http://15.168.206.200:5001/api/v1/category`;

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<any> {
  
    return this.http.get<any>(`${this.apiUrl}`);
  }

  getCategoryById(id: number): Observable<any> {
    return this.http.get<Category>(`${this.apiUrl}/getcatbyid/${id}`);
  }
  
  updateCategory(id: number, category: Category): Observable<any> {
    return this.http.put<Category>(`${this.apiUrl}/updatecat/${id}`, category);
  }
  

  createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.apiUrl}`, category);
  }
  
  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}

