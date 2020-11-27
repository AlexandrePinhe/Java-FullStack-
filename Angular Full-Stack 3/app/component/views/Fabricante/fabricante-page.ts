import { fabricante } from 'src/app/models/fabricante.model';

export interface FabricantePage {
    content: fabricante[];
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
