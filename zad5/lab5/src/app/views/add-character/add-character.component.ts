import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CharacterDto } from '../../models/character';
import { ProfessionsDto } from '../../models/profession';
import { ProfessionService } from '../../services/profession.service';
import { CharacterService } from '../../services/character.service';
import { ActivatedRoute, Router } from '@angular/router';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-add-character',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-character.component.html',
  styleUrl: './add-character.component.css'
})
export class AddCharacterComponent {
  addCharacterForm: FormGroup; 
  
  constructor(
    private fb: FormBuilder,  
    private characterService: CharacterService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.addCharacterForm = this.fb.group({
      uuid: [{ value: uuidv4(), disabled: true}],
      name: ['', [Validators.required, Validators.minLength(3)]],
      level: [0, [Validators.required, Validators.min(0)]]
    });
  }
  ngOnInit(): void {
  }
  onSubmit() {
    if (this.addCharacterForm.valid) {
      let professionId = this.route.snapshot.paramMap.get('id');
      if (professionId){
        const character: CharacterDto = {
          level: this.addCharacterForm.value.level,
          name: this.addCharacterForm.value.name,
          professionId: professionId,
        };
        let characterId = this.addCharacterForm.getRawValue().uuid;
        this.characterService.putCharacter(character, characterId).subscribe(v=>this.characterService.triggerCharactersUpdate());  
        this.addCharacterForm = this.fb.group({
          uuid: [{ value: uuidv4(), disabled: true}],
          name: ['', [Validators.required, Validators.minLength(3)]],
          level: [0, [Validators.required, Validators.min(0)]],
        });
      }
    }
  }
}
