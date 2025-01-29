import { RouterModule, Routes } from '@angular/router';
import { AuthorsListComponent } from './components/authors-list/authors-list.component';
import { AuthorDetailsComponent } from './components/author-details/author-details.component';
import { AuthorFormComponent } from './components/author-form/author-form.component';
import { EditAuthorFormComponent } from './components/edit-author-form/edit-author-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'authors', pathMatch: 'full' },
  { path: 'authors', component: AuthorsListComponent },
  { path: 'authors/add', component: AuthorFormComponent },
  { path: 'authors/:id', component: AuthorDetailsComponent },
  { path: 'authors/edit/:id', component: EditAuthorFormComponent },
  { path: '**', redirectTo: 'authors' },
];

export class AppRoutingModule {}
