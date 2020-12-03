import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: "data"
})
export class  DataPipe implements PipeTransform{

    transform(str: string){
      return str.substr(8,2) + '/' + str.substr(5,2) + '/' + str.substr(0,4);
    }
}