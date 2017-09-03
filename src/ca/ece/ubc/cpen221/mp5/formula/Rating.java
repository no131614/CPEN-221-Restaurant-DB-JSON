package ca.ece.ubc.cpen221.mp5.formula;

import java.util.Set;
import java.util.*;
import ca.ece.ubc.cpen221.mp5.*;

public class Rating implements Formula {

    
    private double rating1, rating2;
    private RestaurantDB restDB;
    
    public Rating(double rating1, double rating2, RestaurantDB restDB) {
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.restDB = restDB;
    }
    
    
    @Override
    public Set<Restaurant> find() {
        List<Restaurant> restaurants = new LinkedList<>(restDB.getRestaurants());
        Set<Restaurant> matchingRestaurants = new LinkedHashSet<>();
        for (Restaurant restaurant : restaurants){
            double stars = (Double) restaurant.getMap().get("stars");
            if (stars >= rating1 && stars <= rating2){
                matchingRestaurants.add(restaurant);
            }
        }
        return new LinkedHashSet<>(matchingRestaurants);
    }

    
}
