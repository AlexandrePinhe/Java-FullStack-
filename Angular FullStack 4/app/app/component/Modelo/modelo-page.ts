import { fabricante } from 'src/app/models/fabricante.model';
import { modelo } from 'src/app/models/modelo.model';

export interface ModeloPage {
    content: modelo[];
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
  
  export interface Sort {
    sorted: boolean;
    unsorted: boolean;
    empty: boolean;
  }