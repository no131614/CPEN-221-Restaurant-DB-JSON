package ca.ece.ubc.cpen221.mp5.formula;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.RestaurantDB;

public class In implements Formula {
    private String in;
    private RestaurantDB restDB;

    public In(String in, RestaurantDB restDB) {
        this.in = in;
        this.restDB = restDB;
    }

    public Set<Restaurant> find() {

        List<Restaurant> restaurants = new LinkedList<>(restDB.getRestaurants());
        Set<Restaurant> matchingRestaurants = new LinkedHashSet<>();
        for (Restaurant restaurant : restaurants) {

            Map m = restaurant.getMap();
            assert !m.isEmpty();

            LinkedList ll = (LinkedList) m.get("neighborhoods");
            for (Object o : ll) {
                o.toString();

                if (o.equals(in)) {
                    matchingRestaurants.add(restaurant);
                    break;
                }

            }

        }
        return new LinkedHashSet<>(matchingRestaurants);
    }
}
