import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutPRComponent } from './ajout-pr.component';

describe('AjoutPRComponent', () => {
  let component: AjoutPRComponent;
  let fixture: ComponentFixture<AjoutPRComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjoutPRComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjoutPRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
