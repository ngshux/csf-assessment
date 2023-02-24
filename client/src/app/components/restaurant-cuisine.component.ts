import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent {
	
	// TODO Task 3
	// For View 2
  restaurants!: Restaurant[]

  constructor(private ar: ActivatedRoute, private router: Router, private svc: RestaurantService) {}

}
