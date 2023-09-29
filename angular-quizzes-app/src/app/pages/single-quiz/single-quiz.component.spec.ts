import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleQuizComponent } from './single-quiz.component';

describe('SingleQuizComponent', () => {
  let component: SingleQuizComponent;
  let fixture: ComponentFixture<SingleQuizComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SingleQuizComponent]
    });
    fixture = TestBed.createComponent(SingleQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
