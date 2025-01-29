import { Component, OnInit, signal } from '@angular/core';
import { AuthorsService } from '../../services/authors.service';
import { AuthorCollection } from '../../models/author';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AuthorFormComponent } from '../author-form/author-form.component';

@Component({
  selector: 'app-authors-list',
  imports: [CommonModule, RouterOutlet, RouterLink, AuthorFormComponent],
  templateUrl: './authors-list.component.html',
  styleUrls: ['./authors-list.component.css'],
})
export class AuthorsListComponent implements OnInit {
  authors = signal<AuthorCollection[]>([]);
  isLoading = signal(false);

  constructor(private authorService: AuthorsService) {}

  ngOnInit(): void {
    this.fetchAuthors();
  }

  fetchAuthors(): void {
    this.authorService.getAuthors().subscribe({
      next: (data: AuthorCollection[]) => {
        this.authors.set(data);
        this.isLoading.set(false);
      },
      error: (err: any) => {
        console.error('Error fetching authors:', err);
        this.isLoading.set(false);
      },
    });
  }

  addAuthorToList(author: AuthorCollection): void {
    this.authors.set([...this.authors(), author]);
  }

  deleteAuthor(authorId: string): void {
    this.authorService.deleteAuthor(authorId).subscribe({
      next: (data: any) => {
        const updatedAuthors = this.authors().filter(
          (author) => author.id !== authorId
        );
        this.authors.set(updatedAuthors);
      },
      error: (err: any) => {
        console.error('Error deleting author:', err);
      },
    });
  }
}
