import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarroListComponent } from './carro-list.component';

describe('CarroListComponent', () => {
  let component: CarroListComponent;
  let fixture: ComponentFixture<CarroListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarroListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarroListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
