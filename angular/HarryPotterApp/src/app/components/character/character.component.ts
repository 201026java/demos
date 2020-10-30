import { Component, OnInit } from '@angular/core';
import { CharacterService } from 'src/app/services/character.service';
import { HpChar } from 'src/app/models/HpChar';

@Component({
  selector: 'app-character',
  templateUrl: './character.component.html',
  styleUrls: ['./character.component.css']
})
export class CharacterComponent implements OnInit {

  constructor(private characterService :CharacterService) { }

  ngOnInit(): void {
    this.displayAllCharacters();
  }

  selectedCharacter :HpChar = new HpChar();
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
  getCharacterInfo(characterToDisplay :HpChar){
    this.selectedCharacter = characterToDisplay;
    console.log(this.selectedCharacter.name)
  }

}
