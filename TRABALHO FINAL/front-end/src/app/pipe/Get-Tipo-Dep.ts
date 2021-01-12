import { Pipe, PipeTransform } from '@angular/core';
import { DepartamentoTipo } from '../component/departamento/Departamento-Tipo';

@Pipe({
    name: 'getDept'
  })
  export class GetTipoDep implements PipeTransform {
  
    tipos : DepartamentoTipo[] = [
      {id: 1, desc: "biologicas"},
      {id: 2, desc: "exatas"},
      {id: 3, desc: "humanas"}
    ]
  
    transform(id: number): string {
      
      for(let tipo of this.tipos ){
        if(tipo.id == id){
          return tipo.desc;
        }
      }
  
      return "--";
    }
  
  }