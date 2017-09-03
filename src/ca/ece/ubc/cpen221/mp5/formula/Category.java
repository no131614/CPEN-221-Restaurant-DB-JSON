package ca.ece.ubc.cpen221.mp5.formula;

import ca.ece.ubc.cpen221.mp5.*;
import java.util.*;

public class Category implements Formula {

    private String category;
    private RestaurantDB restDB;

    public Category(String category, RestaurantDB restDB) {
        this.category = category;
        this.restDB = restDB;
    }

    public Set<Restaurant> find() {

        List<Restaurant> restaurants = new LinkedList<>(restDB.getRestaurants());
        Set<Restaurant> matchingRestaurants = new LinkedHashSet<>();
        for (Restaurant restaurant : restaurants) {

            Map m = restaurant.getMap();
            assert !m.isEmpty();

            LinkedList ll = (LinkedList) m.get("categories");
            for (Object o : ll) {
                o.toString();

                if (o.equals(category)) {
                    matchingRestaurants.add(restaurant);
                    break;
                }

            }

        }
        return new LinkedHashSet<>(matchingRestaurants);
    }

}
