import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GridArchiComponent } from './grid-archi.component';

describe('GridArchiComponent', () => {
  let component: GridArchiComponent;
  let fixture: ComponentFixture<GridArchiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GridArchiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GridArchiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
