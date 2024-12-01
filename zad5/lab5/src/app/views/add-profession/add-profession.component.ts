import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { v4 as uuidv4 } from 'uuid';
import { ProfessionDto } from '../../models/profession';
import { ProfessionService } from '../../services/profession.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-profession',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-profession.component.html',
  styleUrl: './add-profession.component.css'
})
export class AddProfessionComponent {
  addProfessionForm: FormGroup;
  constructor(private fb: FormBuilder, private professionService: ProfessionService, private router: Router) {
    this.addProfessionForm = this.fb.group({
      uuid: [{ value: uuidv4(), disabled: true}],
      name: ['', [Validators.required, Validators.minLength(3)]],
      unlockLevel: [0, [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit() {
    if (this.addProfessionForm.valid) {
      const profession: ProfessionDto = {
        unlockLevel: this.addProfessionForm.value.unlockLevel,
        name: this.addProfessionForm.value.name
      };
      this.professionService.putProfession(profession, this.addProfessionForm.getRawValue().uuid).subscribe(r=>this.professionService.triggerProfessionsUpdate()); 
      this.addProfessionForm = this.fb.group({
        uuid: [{ value: uuidv4(), disabled: true}],
        name: ['', [Validators.required, Validators.minLength(3)]],
        unlockLevel: [0, [Validators.required, Validators.min(0)]]
      });  
    }
  }

  regenerateUuid() {
    this.addProfessionForm.get('uuid')?.setValue(uuidv4());
  }
}
