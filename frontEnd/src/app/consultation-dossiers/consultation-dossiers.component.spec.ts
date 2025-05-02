import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationDossiersComponent } from './consultation-dossiers.component';

describe('ConsultationDossiersComponent', () => {
  let component: ConsultationDossiersComponent;
  let fixture: ComponentFixture<ConsultationDossiersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultationDossiersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultationDossiersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
