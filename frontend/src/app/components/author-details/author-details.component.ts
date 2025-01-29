import { Component, Input, OnInit, signal } from '@angular/core';
import { ReadAuthor } from '../../models/author';
import { AuthorsService } from '../../services/authors.service';
import { BooksService } from '../../services/books.service';
import { CommonModule } from '@angular/common';
import { BookCollection } from '../../models/book';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-author-details',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './author-details.component.html',
  styleUrls: ['./author-details.component.css'],
})
export class AuthorDetailsComponent implements OnInit {
  @Input() id: string = '-1';
  books = signal<BookCollection[]>([]);
  newBookTitle: string = '';
  editingBookId: string | null = null;
  editingBookTitle: string = '';
  author: ReadAuthor = {
    id: '-1',
    name: '',
    surname: '',
  };

  constructor(
    private authorService: AuthorsService,
    private bookService: BooksService
  ) {}

  ngOnInit(): void {
    this.fetchAuthor();
    this.fetchAuthorBooks();
  }

  fetchAuthor(): void {
    this.authorService.getAuthorById(this.id).subscribe({
      next: (data: ReadAuthor) => {
        this.author = data;
      },
      error: (err: any) => {
        console.error('Error fetching author:', err);
      },
    });
  }

  fetchAuthorBooks(): void {
    this.bookService.getAuthorsBooks(this.id).subscribe({
      next: (data: any) => {
        this.books.set(data);
      },
      error: (err: any) => {
        console.error('Error fetching books:', err);
      },
    });
  }

  deleteAuthor(): void {
    this.authorService.deleteAuthor(this.id).subscribe({
      next: () => {
        console.log('Author deleted');
      },
      error: (err: any) => {
        console.error('Error deleting author:', err);
      },
    });
  }

  deleteBook(bookId: string): void {
    this.bookService.deleteBook(bookId).subscribe({
      next: () => {
        this.books.update((prev) => prev.filter((book) => book.id !== bookId));
      },
      error: (err: any) => {
        console.error('Error deleting book:', err);
      },
    });
  }

  addBook(): void {
    this.bookService
      .createBook({ title: this.newBookTitle, authorId: this.id })
      .subscribe({
        next: (data: any) => {
          this.books.update((prev) => [
            ...prev,
            { id: data, title: this.newBookTitle },
          ]);
          this.newBookTitle = '';
        },
        error: (err: any) => {
          console.error('Error creating book:', err);
        },
      });
  }

  editBook(bookId: string): void {
    const bookToEdit = this.books().find((book) => book.id === bookId);
    if (bookToEdit) {
      this.editingBookId = bookId;
      this.editingBookTitle = bookToEdit.title;
    }
  }

  updateBook(): void {
    if (this.editingBookId) {
      this.bookService
        .updateBook(this.editingBookId, { title: this.editingBookTitle })
        .subscribe({
          next: () => {
            this.books.update((prev) =>
              prev.map((book) =>
                book.id === this.editingBookId
                  ? { ...book, title: this.editingBookTitle }
                  : book
              )
            );
            this.editingBookId = null;
            this.editingBookTitle = '';
          },
          error: (err: any) => {
            console.error('Error updating book:', err);
          },
        });
    }
  }

  cancelEdit(): void {
    this.editingBookId = null;
    this.editingBookTitle = '';
  }
}
