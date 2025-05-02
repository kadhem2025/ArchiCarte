import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechercheEtReservationComponent } from './recherche-et-reservation.component';

describe('RechercheEtReservationComponent', () => {
  let component: RechercheEtReservationComponent;
  let fixture: ComponentFixture<RechercheEtReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RechercheEtReservationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RechercheEtReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
