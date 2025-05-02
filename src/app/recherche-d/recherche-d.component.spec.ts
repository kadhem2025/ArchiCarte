import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechercheDComponent } from './recherche-d.component';

describe('RechercheDComponent', () => {
  let component: RechercheDComponent;
  let fixture: ComponentFixture<RechercheDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RechercheDComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RechercheDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
