import { Component, OnInit } from '@angular/core';
import { HpChar } from '../app/models/hpChar';
import { CharacterService } from '../app/services/character.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
    
  }

  
  title = 'HarryPotterApp';
 


}
