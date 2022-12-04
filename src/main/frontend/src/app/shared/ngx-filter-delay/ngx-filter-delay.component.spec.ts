import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NgxFilterDelayComponent } from './ngx-filter-delay.component';

describe('NgxFilterDelayComponent', () => {
  let component: NgxFilterDelayComponent;
  let fixture: ComponentFixture<NgxFilterDelayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NgxFilterDelayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NgxFilterDelayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
