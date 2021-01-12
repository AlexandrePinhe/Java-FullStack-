import { aluno } from "./aluno.model";
import { disciplina } from "./disciplina.model";

export interface curso{
    id?: number;
    nome: String;
    cod_disciplina: disciplina;
    data_matricula: Date;
    id_aluno: aluno;
}