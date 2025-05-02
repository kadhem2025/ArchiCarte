import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutDComponent } from './ajout-d.component';

describe('AjoutDComponent', () => {
  let component: AjoutDComponent;
  let fixture: ComponentFixture<AjoutDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjoutDComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjoutDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
