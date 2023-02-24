
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit{
	
	// TODO Task 4 and Task 5
	// For View 3
  restaurant! : Restaurant[]
  form!: FormGroup;
  routeSub$!: Subscription;

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private restSvc: RestaurantService
  ) {}

  ngOnInit(): void{
    this.clearForm();
  }

  createForm(): FormGroup {
    return this.fb.group({
      comment: this.fb.control('', [Validators.required]),
    });
  }

  clearForm(): void {
    this.form = this.createForm();
  }

  submitForm(): void {
    const comment = this.form.get('comment')?.value;
    this.clearForm();
  }

  ngOnDestroy(): void {
    this.routeSub$.unsubscribe();
  }
}
