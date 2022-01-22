import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FirstnameTranslationTableComponent } from './firstname-translation-table.component';

describe('FirstnameTranslationTableComponent', () => {
  let component: FirstnameTranslationTableComponent;
  let fixture: ComponentFixture<FirstnameTranslationTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FirstnameTranslationTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FirstnameTranslationTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
