import { Directive, OnInit, TemplateRef, ViewContainerRef } from '@angular/core';
import { __importDefault } from 'tslib';
import {Input} from '@angular/core';


@Directive({
  selector: '[appPara]'
})
export class ParaDirective implements OnInit {

  @Input("paraCada") strs : string[];
  constructor(
    private container: ViewContainerRef,
    private template: TemplateRef<any>
    ) { }

  ngOnInit(): void{
    for(let str of this.strs){
       
    }
  }
}
