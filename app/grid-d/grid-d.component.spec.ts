import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GridDComponent } from './grid-d.component';

describe('GridDComponent', () => {
  let component: GridDComponent;
  let fixture: ComponentFixture<GridDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GridDComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GridDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
