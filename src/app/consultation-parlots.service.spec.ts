import { TestBed } from '@angular/core/testing';

import { ConsultationParlotsService } from './consultation-parlots.service';

describe('ConsultationParlotsService', () => {
  let service: ConsultationParlotsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsultationParlotsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
