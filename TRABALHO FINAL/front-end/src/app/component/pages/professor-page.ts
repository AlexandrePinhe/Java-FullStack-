import { aluno } from "src/app/models/aluno.model";
import { professor } from "src/app/models/professor.model";

export interface ProfessorPage{
 content: professor[];
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