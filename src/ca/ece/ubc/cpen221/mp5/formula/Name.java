package ca.ece.ubc.cpen221.mp5.formula;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class Name implements Formula {
    private String name;
    private RestaurantDB restDB;

    public Name(String name, RestaurantDB restDB) {
        this.name = name;
        this.restDB = restDB;
    }

    public Set<Restaurant> find() {

        List<Restaurant> restaurants = new LinkedList<>(restDB.getRestaurants());
        Set<Restaurant> matchingRestaurants = new LinkedHashSet<>();
        for (Restaurant restaurant : restaurants){
            String matching = (String) restaurant.getMap().get("name");
            if (matching.equals(name)){
                matchingRestaurants.add(restaurant);
            }
        }
        
        return new LinkedHashSet<>(matchingRestaurants);
    }
}
