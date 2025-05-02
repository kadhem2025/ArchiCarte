import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeArchiComponent } from './demande-archi.component';

describe('DemandeArchiComponent', () => {
  let component: DemandeArchiComponent;
  let fixture: ComponentFixture<DemandeArchiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandeArchiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DemandeArchiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
