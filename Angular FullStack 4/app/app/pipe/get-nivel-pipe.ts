import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: "nivel_user"
})
export class Nivel_Usuario  implements PipeTransform{
    
    transform(str: string){
        type Options = "Administrador" | "Gestor" | "Comum";

        
        return null;
      }
  }