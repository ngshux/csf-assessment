package vttp2022.csf.assessment.server.repositories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;

@Repository
public class RestaurantRepository {

	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method

	@Autowired
	private MongoTemplate mongoTemplate;

		// db.restaurants.distinct("cuisine")
	
		public List<String> getCuisines() {
		
		return mongoTemplate.getCollection("restaurants").distinct("cuisine", String.class).into(new ArrayList<>());
	}

	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	
		// db.restaurants.find({cuisine:"Chinese"})
	
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		List<Document> restList = mongoTemplate.find(
			Query.query(Criteria.where("cuisine").is(cuisine)),Document.class,"restaurants");
        List<Restaurant> rest = new LinkedList<>();
        if (restList.size() > 0) {
            for (Document d : restList) {
				Restaurant newR = new Restaurant();
				newR.setName(d.getString("name"));
                rest.add(newR);
            }}
		return rest;
	}

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	
		// db.restaurants.find({name:"May May Kitchen"})
	
	public Optional<Restaurant> getRestaurant(String name) {
		List<Document> restList = mongoTemplate.find(
			Query.query(Criteria.where("name").is(name)),Document.class,"restaurants");
			
		Restaurant newR = new Restaurant();
        if (restList.size() > 0) {
            for (Document d : restList) {
				newR.setName(d.getString("name"));
				newR.setCuisine(d.getString("cuisine"));
				newR.setRestaurantId(d.getString("restaurantid"));
            }}
		return Optional.of(newR);
	}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	public void addComment(Comment comment) {
		// Implmementation in here
		Document d = new Document();
		d.append("name", comment.getName());
		d.append("rating",comment.getRating());
		d.append("restaurantid",comment.getRestaurantId());
		d.append("text",comment.getText());

		mongoTemplate.insert(d,"comments");
	}
	
	// You may add other methods to this class

}
