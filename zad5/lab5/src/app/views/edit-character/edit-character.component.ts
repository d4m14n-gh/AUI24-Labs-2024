import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CharacterService } from '../../services/character.service';
import { ActivatedRoute } from '@angular/router';
import { CharacterDto } from '../../models/character';
import { ProfessionsDto } from '../../models/profession';
import { ProfessionService } from '../../services/profession.service';

@Component({
  selector: 'app-edit-character',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './edit-character.component.html',
  styleUrl: './edit-character.component.css'
})
export class EditCharacterComponent {
  character: CharacterDto | null = null;
  professionName: string = "";
  characterId: string | null = null;
  editCharacterForm: FormGroup;
  allProfessions: ProfessionsDto[] = []
  
  constructor(
    private fb: FormBuilder,
    private professionService: ProfessionService,
    private characterService: CharacterService, 
    private route: ActivatedRoute
  ) {
    this.editCharacterForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      level: [0, [Validators.required, Validators.min(0)]],
      profession: ['']
    });
  }
  getCharacter(){
    let id = this.route.snapshot.paramMap.get('id');
      if (id){
        this.professionService.getProfessions().subscribe(
          ret => {
            this.allProfessions = ret;
            if (this.character){
              let names = ret.filter(value=>value.id==this.character?.professionId).map(val => val.name);
              if (names)
                this.professionName = names[0];
            }
          }
        )
        this.characterService.getCharacter(id).subscribe(rep => {
          this.character = rep;
          this.characterId = id;
          this.editCharacterForm = this.fb.group({
            name: [this.character.name, [Validators.required, Validators.minLength(3)]],
            level: [this.character.level, [Validators.required, Validators.min(0)]],
            profession: [this.character.professionId] // , [Validators.required]
          });
        });
      }
  }
  ngOnInit(): void {
    this.getCharacter();
    this.route.params.subscribe(params => {
      this.getCharacter();
    });
  }
  onSubmit() {
    if (this.editCharacterForm.valid&&this.character&&this.characterId) {
      const character: CharacterDto = {
        level: this.editCharacterForm.value.level,
        name: this.editCharacterForm.value.name,
        professionId: this.editCharacterForm.value.profession,
      };
      this.characterService.putCharacter(character, this.characterId).subscribe(
        p => this.getCharacter()
      );
    }
  }
}
