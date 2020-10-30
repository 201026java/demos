import Identifable from './identifiable';

export class Pokemon implements Identifable {
  public id: number;
  public name: string;
  public height: number;
  public weight: number;
  public base_experience: number;
  public sprites: { front_shiny: string, front_default: string };
}
