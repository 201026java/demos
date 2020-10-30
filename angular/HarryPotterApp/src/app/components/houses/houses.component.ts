import { Component, OnInit } from '@angular/core';
import { HouseService } from 'src/app/services/house.service'
import { House } from 'src/app/models/House';

@Component({
  selector: 'app-houses',
  templateUrl: './houses.component.html',
  styleUrls: ['./houses.component.css']
})
export class HousesComponent implements OnInit {

  constructor(private houseService :HouseService) { }

  ngOnInit(): void {
    this.displayAllHouses();
  }
  houseList :Array<House>;
  selectedHouse :House;
  color1 :string = "white";
  color2 :string= "white";
  

  displayAllHouses(){
    this.houseService.getAllHouses().subscribe(
      (response) => {
        this.houseList = response;
        console.log(this.houseList);
      },
      (response) => {
        console.log("something went wrong")
      }
    );
  }
  getHouseInfo(house :House){
    this.selectedHouse = house;
    if(house.colors[0]=="scarlet"){
      this.color1 = "darkred";
    }
    else this.color1 = house.colors[0];
    if(house.colors[1]==" bronze"){
      this.color2 = "darkorange";
    }
    else this.color2 = house.colors[1];
  }

}
