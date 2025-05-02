import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiteLivraisonComponent } from './liste-livraison.component';

describe('LiteLivraisonComponent', () => {
  let component: LiteLivraisonComponent;
  let fixture: ComponentFixture<LiteLivraisonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LiteLivraisonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LiteLivraisonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
