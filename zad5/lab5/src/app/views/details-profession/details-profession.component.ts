import { Component, OnDestroy, OnInit } from '@angular/core';
import { ProfessionDto } from '../../models/profession';
import { ProfessionService } from '../../services/profession.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CharactersDto } from '../../models/character';
import { CharacterService } from '../../services/character.service';
import { AddCharacterComponent } from "../add-character/add-character.component";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-details-profession',
  standalone: true,
  imports: [CommonModule, RouterLink, AddCharacterComponent, AddCharacterComponent],
  templateUrl: './details-profession.component.html',
  styleUrl: './details-profession.component.css'
})
export class DetailsProfessionComponent implements OnInit, OnDestroy{
  profession: ProfessionDto | null = null;
  professionId: string | null = null;
  characters: CharactersDto[] | null = null;
  subscription!: Subscription;
  
  constructor(
    private characterService: CharacterService,
    private professionService: ProfessionService,
    private route: ActivatedRoute
  ) {

  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  getProfession(){
    let id = this.route.snapshot.paramMap.get('id');
      if (id){
        this.professionService.getProfession(id).subscribe(rep => {
          this.profession = rep;
          this.professionId = id;
        });
        this.characterService.getCharactersByProfession(id).subscribe(
          rep => {
            this.characters = rep;
          }
        )
      }
  }
  ngOnInit(): void {
    this.getProfession();
    this.route.params.subscribe(params => {
      this.getProfession();
    });
    this.subscription = this.characterService.charactersUpdate$.subscribe(v=>this.getProfession());
  }
  removeCharacter(uuid: string){
    this.characterService.deleteCharacter(uuid).subscribe(
      w => {
        this.getProfession();
      }
    );
  }
}
