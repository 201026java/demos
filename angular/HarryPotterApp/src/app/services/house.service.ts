import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { House } from '../models/House'

@Injectable({
  providedIn: 'root'
})
export class HouseService {

  constructor(private http :HttpClient) {}
  private headers = new HttpHeaders({'Content-Type' : 'application/json'});

  getAllHouses() :Observable<House[]>{
     return this.http.get<House[]>('https://www.potterapi.com/v1/houses?key=$2a$10$QgJoZ5uC/T84QDNPRSzgvORTWeSgZ9rwtErI3NEV3665gcGufQiaq');
  }
}
