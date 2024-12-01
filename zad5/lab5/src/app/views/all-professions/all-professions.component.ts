import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ProfessionService } from '../../services/profession.service';
import { HttpClientModule } from '@angular/common/http';
import { ProfessionsDto } from '../../models/profession';
import { RouterLink, RouterModule } from '@angular/router';
import { AddProfessionComponent } from "../add-profession/add-profession.component";
import { Subscription, take } from 'rxjs';

@Component({
  selector: 'app-all-professions',
  standalone: true,
  imports: [CommonModule, RouterLink, AddProfessionComponent],
  templateUrl: './all-professions.component.html',
  styleUrl: './all-professions.component.css'
})
export class AllProfessionsComponent implements OnInit, OnDestroy {
  professions: ProfessionsDto[] = [];
  subscription!: Subscription;

  constructor(private professionService: ProfessionService){
    
  }
  getProfessions(): void{
    let sub = this.professionService.getProfessions().subscribe(
      response => {
        this.professions = response;
      }
    );
  }

  ngOnInit(): void {
    this.getProfessions();
    this.subscription = this.professionService.porfessionsUpdate$.subscribe(v=>this.getProfessions())
  }

  removeProfession(professionId: string){
    this.professions = this.professions.filter(element => element.id!=professionId);
    this.professionService.deleteProfession(professionId).subscribe();
  }

  ngOnDestroy(): void{
    this.subscription.unsubscribe();
  }
}
