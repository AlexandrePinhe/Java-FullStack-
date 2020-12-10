import { usuario } from 'src/app/models/usuario.model';

export interface UsuarioPage {
    content: usuario[];
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