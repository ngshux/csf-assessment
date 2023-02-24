package vttp2022.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.RestaurantService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    
    @Autowired
    private RestaurantService restSvc;

    @GetMapping(path="/cuisines")
    public ResponseEntity<String> getCuisines(){

        List<String> cuisines = restSvc.getCuisines();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
    
        cuisines.forEach(c -> arrBuilder.add(c));
    
        JsonArray resp = arrBuilder.build();
    
        return ResponseEntity.ok(resp.toString());
      }

      @GetMapping(path="/{cuisine}/restaurants")
      public ResponseEntity<String> getRestaurants(@PathVariable String cuisine){
  
          List<Restaurant> restaurants = restSvc.getRestaurantsByCuisine(cuisine);
      
          JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      
          return ResponseEntity.ok(arrBuilder.build().toString());
        }
}
