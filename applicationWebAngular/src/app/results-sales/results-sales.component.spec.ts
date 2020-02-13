import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultsSalesComponent } from './results-sales.component';

describe('ResultsSalesComponent', () => {
  let component: ResultsSalesComponent;
  let fixture: ComponentFixture<ResultsSalesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultsSalesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultsSalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
