export interface AuthorCollection {
  id: string;
  surname: string;
}

export interface CreateAuthor {
  name: string;
  surname: string;
}

export interface ReadAuthor {
  id: string;
  name: string;
  surname: string;
}
