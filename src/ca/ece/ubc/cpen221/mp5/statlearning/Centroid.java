package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.HashSet;
import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class Centroid {
    private Location location;
    private final Set<Restaurant> cluster;
    public Centroid(Location loc) {
        location = loc;
        cluster = new HashSet<Restaurant>();
    }
    public void addRestaurant(Restaurant restaurant){
        cluster.add(restaurant);
    }
    public Set<Restaurant> getCluster(){
        return new HashSet<Restaurant>();
    }
    public Location getLocation(){
        return new Location(location);
    }
    public void moveLocation(Location newLoc){
        location = new Location(newLoc);
    }
}
