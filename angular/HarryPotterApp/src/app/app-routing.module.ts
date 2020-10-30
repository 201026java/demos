import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CharacterComponent } from './components/character/character.component';
import { HousesComponent } from './components/houses/houses.component';
import { SpellsComponent } from './components/spells/spells.component';

const routes: Routes = [
  {path : 'character', component : CharacterComponent},
  {path : 'houses', component : HousesComponent},
  {path : 'spells', component : SpellsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
