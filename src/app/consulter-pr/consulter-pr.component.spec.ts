import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsulterPRComponent } from './consulter-pr.component';

describe('ConsulterPRComponent', () => {
  let component: ConsulterPRComponent;
  let fixture: ComponentFixture<ConsulterPRComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsulterPRComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsulterPRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
