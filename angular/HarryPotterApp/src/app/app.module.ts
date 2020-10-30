import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CharacterComponent } from './components/character/character.component';
import { HttpClientModule } from '@angular/common/http';
import { SideComponent } from './components/side/side.component';
import { HousesComponent } from './components/houses/houses.component';
import { SpellsComponent } from './components/spells/spells.component';

@NgModule({
  declarations: [
    AppComponent,
    CharacterComponent,
    SideComponent,
    HousesComponent,
    SpellsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
