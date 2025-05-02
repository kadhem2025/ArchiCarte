import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemendePRComponent } from './demende-pr.component';

describe('DemendePRComponent', () => {
  let component: DemendePRComponent;
  let fixture: ComponentFixture<DemendePRComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemendePRComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DemendePRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
