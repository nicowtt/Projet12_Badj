import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleValidationComponent } from './article-validation.component';

describe('ArticleValidationComponent', () => {
  let component: ArticleValidationComponent;
  let fixture: ComponentFixture<ArticleValidationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleValidationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
