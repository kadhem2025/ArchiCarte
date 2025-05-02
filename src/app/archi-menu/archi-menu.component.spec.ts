import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchiMenuComponent } from './archi-menu.component';

describe('ArchiMenuComponent', () => {
  let component: ArchiMenuComponent;
  let fixture: ComponentFixture<ArchiMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArchiMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArchiMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
