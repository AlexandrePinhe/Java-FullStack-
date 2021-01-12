import { aluno } from "src/app/models/aluno.model";
import { curso } from "src/app/models/curso.model";

export interface CursoPage{
 content: curso[];
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