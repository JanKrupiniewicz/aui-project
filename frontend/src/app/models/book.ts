import { AuthorCollection } from './author';

export interface BookCollection {
  id: string;
  title: string;
}

export interface CreateBook {
  title: string;
  authorId: string;
}

export interface ReadBook {
  id: string;
  title: string;
  author: AuthorCollection;
}
