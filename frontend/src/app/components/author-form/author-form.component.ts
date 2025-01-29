import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthorsService } from '../../services/authors.service';
import { AuthorCollection } from '../../models/author';

@Component({
  selector: 'app-author-form',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.css'],
})
export class AuthorFormComponent {
  name: string = '';
  surname: string = '';

  @Output() authorAdded = new EventEmitter<AuthorCollection>();

  constructor(private authorService: AuthorsService) {}

  clear(): void {
    this.name = '';
    this.surname = '';
  }

  addAuthor(): void {
    this.authorService
      .createAuthor({ name: this.name, surname: this.surname })
      .subscribe({
        next: (data: any) => {
          console.log('Author added:', data);
          this.authorAdded.emit({ id: data.id, surname: this.surname });
          this.clear();
        },
        error: (err: any) => {
          console.error('Error adding author:', err);
        },
      });
  }
}
