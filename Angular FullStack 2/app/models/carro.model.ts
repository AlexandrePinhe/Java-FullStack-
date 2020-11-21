import { fabricante } from './fabricante.model';
import { modelo } from './modelo.model';

export interface Carro{
    idCarro?: number;
    fabricante: fabricante;
    tipo: number;
    placa: string;
    ano: number;
    cor: string;
    modelo: modelo;
}
