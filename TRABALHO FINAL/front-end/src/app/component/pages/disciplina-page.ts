import { aluno } from "src/app/models/aluno.model";
import { disciplina } from "src/app/models/disciplina.model";

export interface DisciplinaPage{
 content: disciplina[];
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