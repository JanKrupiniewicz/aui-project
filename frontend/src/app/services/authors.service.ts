import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthorsService {
  private apiUrl = 'http://localhost:8080/api/authors';

  constructor(private http: HttpClient) {}

  getAuthors(): Observable<any> {
    return this.http.get(this.apiUrl, {
      responseType: 'json',
    });
  }

  getAuthorById(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  createAuthor(data: any): Observable<any> {
    return this.http.post(this.apiUrl, data);
  }

  updateAuthor(id: string, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, data);
  }

  deleteAuthor(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
