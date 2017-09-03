package ca.ece.ubc.cpen221.mp5;

import java.util.LinkedHashMap;
import java.util.Map;

// TODO: Use this class to represent a Yelp user.

public class User {
    private final Map user;
    public User(Map userJSON) {
        user = userJSON;
    }
    public Map getMap(){
        return new LinkedHashMap<>(user);
    }
}
