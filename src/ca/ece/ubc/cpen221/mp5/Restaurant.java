package ca.ece.ubc.cpen221.mp5;

import java.util.LinkedHashMap;
import java.util.Map;

public class Restaurant {
    private final Map restaurant;
    private final String JSONString;
    //private final String name;
   // private final Object categories;
    //private final Object neighborhoods;
    //private final String businessID;
    //private final double longitude;
    //private final double latitude;
    //private final double stars;
    //private final long price;
    
//TODO:Rep Invariants 
    public Restaurant(Map restJSON, String JSONString) {
        restaurant = restJSON;
        this.JSONString = JSONString;
        //name = (String) restJSON.get("name");
        //categories =   restJSON.get("categories");
        //neighborhoods =   restJSON.get("neighborhoods");
        //businessID = (String) restJSON.get("business_id");
        //longitude = (Double) restJSON.get("longitude");
        //latitude = (Double) restJSON.get("latitude");
        //stars = (Double) restJSON.get("stars");
        //price = (Long) restJSON.get("price");
    }
    public Map getMap(){
        return new LinkedHashMap<>(restaurant);
    }
    
    public String getJSONString(){
        return JSONString;
    }
    
    public String getName(){
        return (String) restaurant.get("name");
    }
    public String getBusinessID(){
        return (String) restaurant.get("business_id");
    }
    /*
    public double getX(){
        return latitude;
    }
   // public double getY(){
       // return longitude;
    //}
    
    public Object getCategories(){
        return categories;
    }
    
    public Object getNeighborhoods(){
        return neighborhoods;
    }
    
    public double getStars(){
        return stars;
    }
    
    public double getPrice(){
        return price;
    }
    */
}
