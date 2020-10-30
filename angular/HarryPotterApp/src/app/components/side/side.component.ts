import { Component, OnInit } from '@angular/core';
import { HpChar } from 'src/app/models/HpChar';
import { CharacterService } from 'src/app/services/character.service';

@Component({
  selector: 'app-side',
  templateUrl: './side.component.html',
  styleUrls: ['./side.component.css']
})
export class SideComponent implements OnInit {

  constructor(private characterService :CharacterService) { }

  ngOnInit(): void {
  }
  characterList :Array<HpChar> = [];

  displayAllCharacters(){
    this.characterService.getAllCharacters().subscribe(
      (response) => {
        this.characterList = response;
        console.log(this.characterList);
      },
      (response) => {
        console.log("something went wrong");
      }
    );
  }
}
