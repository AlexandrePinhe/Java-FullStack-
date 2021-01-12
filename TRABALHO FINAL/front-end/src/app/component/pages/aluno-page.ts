import { aluno } from "src/app/models/aluno.model";

export interface AlunoPage{
 content: aluno[];
 pageable: string;
 totalElements: number;
 last: boolean;
 totalPages: number;
 size: number;
 number: number;
 sort: Sort;
 numberOfElements: number;
 first: boolean;
 empty: boolean;
}

export interface Sort{
  sorted: boolean;
  unsorted: boolean;
  empty: boolean;
}
