import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FirstnameTableComponent } from './firstname-table.component';

describe('FirstnameTableComponent', () => {
  let component: FirstnameTableComponent;
  let fixture: ComponentFixture<FirstnameTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FirstnameTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FirstnameTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
