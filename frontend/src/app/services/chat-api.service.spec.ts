import { TestBed } from '@angular/core/testing';

import { ChatAPIService } from './chat-api.service';

describe('ChatAPIService', () => {
  let service: ChatAPIService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChatAPIService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
