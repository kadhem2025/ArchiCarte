import { TestBed } from '@angular/core/testing';

import { ExportAPIService } from './export-api.service';

describe('ExportAPIService', () => {
  let service: ExportAPIService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExportAPIService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
