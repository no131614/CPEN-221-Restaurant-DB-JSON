package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.Set;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ca.ece.ubc.cpen221.mp5.*;
public class Algorithms {

	/**
	 * Use k-means clustering to compute k clusters for the restaurants in the
	 * database.
	 * 
	 * @param db
	 * @return
	 */
	public static List<Set<Restaurant>> kMeansClustering(int k, RestaurantDB db) {
		// TODO: Implement this method
	    LinkedHashMap<Integer, Set<Restaurant>> map = new LinkedHashMap<>();
	   // Map<String, Restaurant> mapRestaurants = new LinkedHashMap<String, Restaurant>(db.getRestaurants());
	    //for (Restaurant restaurant : mapRestaurants.values()){
	        
	    //}
	    
		return null;
	}

	public static String convertClustersToJSON(List<Set<Restaurant>> clusters) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
		// TODO: Implement this method
		return null;
	}

	public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
		// TODO: Implement this method
		return null;
	}
}