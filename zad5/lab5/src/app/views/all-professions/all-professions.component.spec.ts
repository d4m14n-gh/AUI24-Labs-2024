import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllProfessionsComponent } from './all-professions.component';

describe('AllProfessionsComponent', () => {
  let component: AllProfessionsComponent;
  let fixture: ComponentFixture<AllProfessionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllProfessionsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AllProfessionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
