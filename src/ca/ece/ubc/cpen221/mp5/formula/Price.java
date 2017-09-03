package ca.ece.ubc.cpen221.mp5.formula;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class Price implements Formula{

    
    private long minPrice, maxPrice;
    private RestaurantDB restDB;
    
    public Price(long minPrice, long maxPrice, RestaurantDB restDB) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.restDB = restDB;
    }
    
    
    @Override
    public Set<Restaurant> find() {
        List<Restaurant> restaurants = new LinkedList<>(restDB.getRestaurants());
        Set<Restaurant> matchingRestaurants = new LinkedHashSet<>();
        for (Restaurant restaurant : restaurants){
            long price = (Long) restaurant.getMap().get("price");
            if (price >= minPrice && price <= maxPrice){
                matchingRestaurants.add(restaurant);
            }
        }
        return new LinkedHashSet<>(matchingRestaurants);
    }
}
