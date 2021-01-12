import { curso } from "./curso.model";
import { professor } from "./professor.model";

export interface departamento{
    id?: number;
    nome: String;
    data_contratacao: Date;
    matricula_prof: professor;
    cod_curso: curso;
    tipo_departamento: number;
    departamento_ativo: boolean;
}