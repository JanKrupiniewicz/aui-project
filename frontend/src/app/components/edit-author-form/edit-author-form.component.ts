import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  signal,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AuthorCollection, ReadAuthor } from '../../models/author';
import { AuthorsService } from '../../services/authors.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-author-form',
  imports: [CommonModule, RouterOutlet, RouterLink, FormsModule],
  templateUrl: './edit-author-form.component.html',
  styleUrls: ['./edit-author-form.component.css'],
})
export class EditAuthorFormComponent {
  @Input() id: string = '-1';
  name: string = '';
  surname: string = '';

  fetchedName: string = '';
  fetchedSurname: string = '';

  constructor(private authorService: AuthorsService) {}

  ngOnInit(): void {
    this.fetchAuthor();
  }

  fetchAuthor(): void {
    this.authorService.getAuthorById(this.id).subscribe({
      next: (data: ReadAuthor) => {
        this.name = data.name;
        this.surname = data.surname;
        this.fetchedName = data.name;
        this.fetchedSurname = data.surname;
      },
      error: (err: any) => {
        console.error('Error fetching author:', err);
      },
    });
  }

  editAuthor(): void {
    this.authorService
      .updateAuthor(this.id, { name: this.name, surname: this.surname })
      .subscribe({
        next: (data: any) => {
          console.log('Author updated:', data);
        },
        error: (err: any) => {
          console.error('Error updating author:', err);
        },
      });
  }
}
