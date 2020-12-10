import { fabricante } from './fabricante.model';

export interface modelo{
    modeloId?: number;
    nomeModelo: string;
    fabricante: fabricante;
}