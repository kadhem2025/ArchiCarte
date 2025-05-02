import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GererPRComponent } from './gerer-pr.component';

describe('GererPRComponent', () => {
  let component: GererPRComponent;
  let fixture: ComponentFixture<GererPRComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GererPRComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GererPRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
