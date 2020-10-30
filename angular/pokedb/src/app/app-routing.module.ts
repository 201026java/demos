import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PokelistComponent } from './pokelist/pokelist.component';

const routes: Routes = [
  {path: "list", component: PokelistComponent },
  {path: "**", redirectTo: "list", pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
