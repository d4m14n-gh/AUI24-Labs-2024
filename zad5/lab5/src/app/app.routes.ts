import { Routes } from '@angular/router';
import { EditProfessionComponent } from './views/edit-profession/edit-profession.component';
import { AllProfessionsComponent } from './views/all-professions/all-professions.component';
import { DetailsProfessionComponent } from './views/details-profession/details-profession.component';
import { EditCharacterComponent } from './views/edit-character/edit-character.component';
import { DetailsCharacterComponent } from './views/details-character/details-character.component';

export const routes: Routes = [
    { path: '', component: AllProfessionsComponent },
    { path: 'professions', component: AllProfessionsComponent },
    { path: 'professions/:id/edit', component: EditProfessionComponent },
    { path: 'professions/:id/details', component: DetailsProfessionComponent },
    { path: 'characters/:id/edit', component: EditCharacterComponent },
    { path: 'characters/:id/details', component: DetailsCharacterComponent },
];
