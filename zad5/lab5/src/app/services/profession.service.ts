import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { ProfessionDto, ProfessionsDto } from '../models/profession';

@Injectable({
  providedIn: 'root'
})
export class ProfessionService {
  private porfessionsUpdateSubject = new Subject<void>();
  porfessionsUpdate$ = this.porfessionsUpdateSubject.asObservable();
  
  triggerProfessionsUpdate(): void{
    this.porfessionsUpdateSubject.next();
  }
  constructor(private http: HttpClient) {
  }
  public getProfession(uuid: string): Observable<ProfessionDto>{
    return this.http.get<ProfessionDto>("/api/professions/"+uuid);
  }
  public getProfessions(): Observable<ProfessionsDto[]>{
    return this.http.get<ProfessionsDto[]>("/api/professions");
  }
  public deleteProfession(uuid: string): Observable<any>{
    return this.http.delete<any>("/api/professions/"+uuid);
  }
  public putProfession(profession: ProfessionDto, uuid: string): Observable<any>{
    return this.http.put<any>("/api/professions/"+uuid, profession);
  }
  public patchProfession(profession: ProfessionDto, uuid: string): Observable<any>{
    return this.http.patch<any>("/api/professions/"+uuid, profession);
  }
}
