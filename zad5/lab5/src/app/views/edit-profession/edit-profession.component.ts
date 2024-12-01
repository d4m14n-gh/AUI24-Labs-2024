import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProfessionDto } from '../../models/profession';
import { ProfessionService } from '../../services/profession.service';
import { Subscription } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-edit-profession',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './edit-profession.component.html',
  styleUrl: './edit-profession.component.css'
})
export class EditProfessionComponent implements OnInit {
  profession: ProfessionDto | null = null;
  professionId: string | null = null;
  editProfessionForm: FormGroup;
  
  constructor(private fb: FormBuilder, private professionService: ProfessionService, private route: ActivatedRoute) {
    this.editProfessionForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      unlockLevel: [0, [Validators.required, Validators.min(0)]]
    });
  }
  getProfession(){
    let id = this.route.snapshot.paramMap.get('id');
      if (id){
        this.professionService.getProfession(id).subscribe(rep => {
          this.profession = rep;
          this.professionId = id;
          this.editProfessionForm = this.fb.group({
            name: [this.profession.name, [Validators.required, Validators.minLength(3)]],
            unlockLevel: [this.profession.unlockLevel, [Validators.required, Validators.min(0)]]
          });
        });
      }
  }
  ngOnInit(): void {
    this.getProfession();
    this.route.params.subscribe(params => {
      this.getProfession();
    });
  }
  onSubmit() {
    if (this.editProfessionForm.valid&&this.profession&&this.professionId) {
      const profession: ProfessionDto = {
        unlockLevel: this.editProfessionForm.value.unlockLevel,
        name: this.editProfessionForm.value.name,
      };
      this.professionService.patchProfession(profession, this.professionId).subscribe(
        p => {this.getProfession();}
      );
    }
  }
}

