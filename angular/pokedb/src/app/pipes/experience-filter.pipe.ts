import { Pipe, PipeTransform } from '@angular/core';
import { Pokemon } from '../models/pokemon';

@Pipe({
  name: 'experienceFilter'
})
export class ExperienceFilterPipe implements PipeTransform {

  transform(value: Pokemon[], name: string, ...args: any[]): Pokemon[] {
    let sorted = value.sort( (first, second) => {
      return first.base_experience - second.base_experience;
    });

    if(name) {
      return sorted.filter( (poke) => {
        return poke.name.toLowerCase()
            .includes(name.toLowerCase());
      });
    }

    return sorted;
  }
}
