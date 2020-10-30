import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Spell } from '../models/Spell'

@Injectable({
  providedIn: 'root'
})
export class SpellsService {

  constructor(private http :HttpClient) {}
  private headers = new HttpHeaders({'Content-Type' : 'application/json'});

  getAllSpells() :Observable<Spell[]>{
     return this.http.get<Spell[]>('https://www.potterapi.com/v1/spells?key=$2a$10$QgJoZ5uC/T84QDNPRSzgvORTWeSgZ9rwtErI3NEV3665gcGufQiaq');
  }
}
