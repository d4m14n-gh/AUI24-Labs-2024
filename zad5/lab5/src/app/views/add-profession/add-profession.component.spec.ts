import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProfessionComponent } from './add-profession.component';

describe('AddCategoryComponent', () => {
  let component: AddProfessionComponent;
  let fixture: ComponentFixture<AddProfessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddProfessionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddProfessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
