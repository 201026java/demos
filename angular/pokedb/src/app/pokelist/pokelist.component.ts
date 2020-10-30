import { Component, OnInit } from '@angular/core';
import { PokeService } from 'src/app/services/poke.service';
import { Pokemon } from '../models/pokemon';
import Identifiable from '../models/identifiable';

declare global {
  interface Array<T> {
    pushSorted(elem: Identifiable): boolean;
  }
}

if(!Array.prototype.pushSorted) {
  Array.prototype.pushSorted = function<T extends Identifiable>(this: T[], elem: T): boolean {
    const arr: T[] = this;

    /**
     * This custom binarySearch function is specifically structured to be used with pushSorted
     *
     * If the element is found, -1 is returned
     *
     * Otherwise, the index where the element is to be inserted is returned
     */
    function binarySearch(elem: T, options: { start: number, end: number} = {start: -1, end: -1}): number {
      if(!elem) {
        throw new Error("elem was not a valid value");
      }

      if(options.start === -1 && options.end === -1) {
        return binarySearch(elem, {start: 0, end: arr.length});
      }

      if(options.start >= options.end) {
        return options.start;
      }

      const half = Math.floor(options.start + (options.end - options.start) / 2);

      if(elem.id === arr[half].id) {
        return -1;
      } else if(elem.id < arr[half].id) {
        return binarySearch(elem, { start: options.start, end: half });
      }

      return binarySearch(elem, { start: half + 1, end: options.end });
    }

    const index: number = binarySearch(elem);

    if(index === -1) {
      return false;
    }

    this.splice(index, 0, elem);

    return true;
  }
}

@Component({
  selector: 'app-pokelist',
  templateUrl: './pokelist.component.html',
  styleUrls: ['./pokelist.component.css']
})
export class PokelistComponent implements OnInit {

  public page: number = 0;
  public pokemon: Pokemon[] = [];
  public search: string = "";
  public currentTime: number = new Date().getTime();

  constructor(private pokeService: PokeService) { }

  ngOnInit(): void {
    setInterval( () => {
      this.currentTime = new Date().getTime();
    }, 1000);

    // setInterval( () => {
    //   this.fetchPage2();
    //   this.page++;
    // }, 1000);

    this.pokeService.getSubject().subscribe( (poke) => {
      this.pokemon.pushSorted(poke);
    }, (error) => {
      console.log(error);
    }, () => {
      console.log("Complete?");
    });
  }

  async fetchPage(): Promise<void> {
    this.pokemon = await this.pokeService.getPokemonPage(this.page);
  }

  async fetchPage2(): Promise<void> {
    await this.pokeService.getPokemonPage2(this.page);
  }

  startFetching(): void {
    setInterval( () => {
      if(this.page < 53) {
        this.fetchPage2();
        this.page++;
      }
    }, 1000);
  }
}
