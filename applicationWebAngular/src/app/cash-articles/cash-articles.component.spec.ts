import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CashArticlesComponent } from './cash-articles.component';

describe('CashArticlesComponent', () => {
  let component: CashArticlesComponent;
  let fixture: ComponentFixture<CashArticlesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CashArticlesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CashArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
