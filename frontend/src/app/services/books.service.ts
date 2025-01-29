import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateBook } from '../models/book';

@Injectable({
  providedIn: 'root',
})
export class BooksService {
  private apiUrl = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) {}

  getAuthorsBooks(authorId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/author/${authorId}`);
  }

  getBooks(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getBookById(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  createBook(data: CreateBook): Observable<any> {
    return this.http.put(this.apiUrl, data);
  }

  updateBook(id: string, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, data);
  }

  deleteBook(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
