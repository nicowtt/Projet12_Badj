import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleRefoundComponent } from './article-refound.component';

describe('ArticleRefoundComponent', () => {
  let component: ArticleRefoundComponent;
  let fixture: ComponentFixture<ArticleRefoundComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleRefoundComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleRefoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
