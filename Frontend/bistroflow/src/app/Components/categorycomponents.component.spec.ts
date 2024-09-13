import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryComponentsComponent } from './categorycomponents.component';

describe('CategorycomponentsComponent', () => {
  let component: CategoryComponentsComponent;
  let fixture: ComponentFixture<CategoryComponentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CategoryComponentsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CategoryComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
