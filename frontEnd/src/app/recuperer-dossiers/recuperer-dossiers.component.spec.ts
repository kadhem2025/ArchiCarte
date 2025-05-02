import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecupererDossiersComponent } from './recuperer-dossiers.component';

describe('RecupererDossiersComponent', () => {
  let component: RecupererDossiersComponent;
  let fixture: ComponentFixture<RecupererDossiersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecupererDossiersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecupererDossiersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
