package ca.ece.ubc.cpen221.mp5;

import java.util.LinkedHashMap;
import java.util.Map;

// TODO: Use this class to represent a Yelp review.

public class Review {
    private final Map review;
    private final String JSONString;
    public Review(Map reviewJSON, String JSONString) {
        review = reviewJSON;
        this.JSONString = JSONString;
    }
    public Map getMap(){
        return new LinkedHashMap<>(review);
    }
    public String getJSONString(){
        return JSONString;
    }
}
