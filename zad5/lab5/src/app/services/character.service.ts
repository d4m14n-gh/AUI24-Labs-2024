import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CharacterDto, CharactersDto } from '../models/character';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {
  private charactersUpdateSubject = new Subject<void>();
  charactersUpdate$ = this.charactersUpdateSubject.asObservable();
  
  triggerCharactersUpdate(): void{
    this.charactersUpdateSubject.next();
  }
  constructor(private http: HttpClient) {
  }
  public getCharactersByProfession(professionUuid: string): Observable<CharactersDto[]>{
    return this.http.get<CharactersDto[]>("/api/professions/"+professionUuid+"/characters");
  }
  public deleteCharacter(uuid: string): Observable<any>{
    return this.http.delete("/api/characters/"+uuid);
  }
  public putCharacter(character: CharacterDto, uuid: string): Observable<any>{
    return this.http.put<any>("/api/characters/"+uuid, character);
  }
  public getCharacter(uuid: string): Observable<CharacterDto>{
    return this.http.get<CharacterDto>("/api/characters/"+uuid);
  }
}
