import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProfessionService } from '../../services/profession.service';
import { CharacterService } from '../../services/character.service';
import { ActivatedRoute } from '@angular/router';
import { ProfessionsDto } from '../../models/profession';
import { CharacterDto } from '../../models/character';

@Component({
  selector: 'app-details-character',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './details-character.component.html',
  styleUrl: './details-character.component.css'
})
export class DetailsCharacterComponent {
  character: CharacterDto | null = null;
  professionName: string = "";
  characterId: string | null = null;
  allProfessions: ProfessionsDto[] = []
  
  constructor(private professionService: ProfessionService, private characterService: CharacterService, private route: ActivatedRoute) {
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
        });
      }
  }
  ngOnInit(): void {
    this.getCharacter();
    this.route.params.subscribe(params => {
      this.getCharacter();
    });
  }
}
