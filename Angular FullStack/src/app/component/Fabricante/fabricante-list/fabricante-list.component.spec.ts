import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FabricanteListComponent } from './fabricante-list.component';

describe('FabricanteListComponent', () => {
  let component: FabricanteListComponent;
  let fixture: ComponentFixture<FabricanteListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FabricanteListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FabricanteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
