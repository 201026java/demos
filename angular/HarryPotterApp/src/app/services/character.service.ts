import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HpChar } from '../models/HpChar';
@Injectable({
  providedIn: 'root'
})
export class CharacterService {

  constructor(private http :HttpClient) {}
   private headers = new HttpHeaders({'Content-Type' : 'application/json'});

   getAllCharacters() :Observable<HpChar[]>{
      return this.http.get<HpChar[]>('https://www.potterapi.com/v1/characters?key=$2a$10$QgJoZ5uC/T84QDNPRSzgvORTWeSgZ9rwtErI3NEV3665gcGufQiaq');
   }
}
