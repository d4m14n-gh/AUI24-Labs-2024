import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsProfessionComponent } from './details-profession.component';

describe('DetailsProfessionComponent', () => {
  let component: DetailsProfessionComponent;
  let fixture: ComponentFixture<DetailsProfessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsProfessionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetailsProfessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
