import { Component, OnInit } from '@angular/core';
import { SpellsService } from 'src/app/services/spells.service'
import { Spell } from 'src/app/models/Spell';

@Component({
  selector: 'app-spells',
  templateUrl: './spells.component.html',
  styleUrls: ['./spells.component.css']
})
export class SpellsComponent implements OnInit {

  constructor(private spellService :SpellsService) { }

  ngOnInit(): void {
    this.displayAllSpells();
  }
  spellList :Array<Spell>;

  displayAllSpells(){
    this.spellService.getAllSpells().subscribe(
      (response) => {
        this.spellList = response;
        console.log(this.spellList);
      },
      (response) => {
        console.log("something went wrong")
      }
    );
  }
  getSpellInfo(spell :Spell){

  }
}
