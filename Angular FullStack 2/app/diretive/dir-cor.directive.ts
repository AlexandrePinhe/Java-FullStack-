import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appDirCor]'
})
export class DirCorDirective {

  constructor(private element: ElementRef) { 
    element.nativeElement.style.color = '#e35e6b';
  }

}
