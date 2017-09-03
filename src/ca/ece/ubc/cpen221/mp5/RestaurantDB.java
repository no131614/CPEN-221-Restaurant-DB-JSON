package ca.ece.ubc.cpen221.mp5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ca.ece.ubc.cpen221.mp5.formula.FormulaFactory;
// TODO: This class represents the Restaurant Database.
// Define the internal representation and 
// state the rep invariant and the abstraction function.

public class RestaurantDB {
     Map<String, Restaurant> restaurants;
     Map<String, Review> reviews;
     Map<String, User> users;
     Collection<Restaurant> collectionRestaurants;
     Collection<Review> collectionReviews;
     String filename1;
     String filename2;
     String filename3;

    /**
     * Create a database from the Yelp dataset given the names of three files:
     * <ul>
     * <li>One that contains data about the restaurants;</li>
     * <li>One that contains reviews of the restaurants;</li>
     * <li>One that contains information about the users that submitted reviews.
     * </li>
     * </ul>
     * The files contain data in JSON format.
     * 
     * @param restaurantJSONfilename
     *            the filename for the restaurant data
     * @param reviewsJSONfilename
     *            the filename for the reviews
     * @param usersJSONfilename
     *            the filename for the users
     * @return
     */
    public static void main(String[] args) {
        

        //String restaurantName = "Cafe 3";
        //String businessId = "FWadSZw0G7HsgKXq7gHTnw";
        //System.out.println(test.randomReview(restaurantName));
        //System.out.println(test.getRestaurant(businessId));

        String str = "(category(\"Chinese\")&&name(\"1sdf\"))||(name(\"Sun Hong Kong Restaurant\")&&in(\"Telegraph Ave\"))";
        
       /* Set<Restaurant> rest = RestaurantDB.query(str);

        for (Restaurant res : rest) {
            System.out.println("--------------");
            System.out.println("Name: " + res.getName());

            LinkedList list = (LinkedList) res.getNeighborhoods();
            System.out.println("Neighborhoods : ");
            for (Object o : list) {
                System.out.println(o.toString());
            }

            LinkedList list2 = (LinkedList) res.getCategories();
            System.out.println("Categories : ");
            for (Object o : list2) {
                System.out.println(o.toString());
            }

            System.out.println("Rating stars: " + res.getStars());

            System.out.println("Price : " + res.getPrice());

            System.out.println("--------------");
            */
       // }

    }

    public RestaurantDB(String restaurantJSONfilename, String reviewsJSONfilename, String usersJSONfilename) {
        // TODO: Implement this method
        restaurants = new LinkedHashMap<>();
        reviews = new LinkedHashMap<>();
        users = new LinkedHashMap<>();
        this.filename1 = restaurantJSONfilename;
        this.filename2 = reviewsJSONfilename;
        this.filename3 = usersJSONfilename;
        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory() {
            public List<?> creatArrayContainer() {
                return new LinkedList<Object>();
            }

            public Map<?, ?> createObjectContainer() {
                return new LinkedHashMap<Object, Object>();
            }

        };
        try {
            BufferedReader restsLineReader = new BufferedReader(new FileReader(restaurantJSONfilename));
            BufferedReader reviewsLineReader = new BufferedReader(new FileReader(reviewsJSONfilename));
            BufferedReader usersLineReader = new BufferedReader(new FileReader(usersJSONfilename));
            while (restsLineReader.ready()) {
                String line = restsLineReader.readLine();
                Map<?, ?> restaurant = (Map<?, ?>) parser.parse(line, containerFactory);
                restaurants.put((String) restaurant.get("business_id"), new Restaurant(restaurant, line));
            }
            while (reviewsLineReader.ready()) {
                String line = reviewsLineReader.readLine();
                Map<?, ?> review = (Map<?, ?>) parser.parse(reviewsLineReader.readLine(), containerFactory);
                reviews.put((String) review.get("review_id"), new Review(review, line));
            }
            while (usersLineReader.ready()) {
                Map<?, ?> user = (Map<?, ?>) parser.parse(usersLineReader.readLine(), containerFactory);
                users.put((String) user.get("user_id"), new User(user));
            }
            collectionRestaurants = restaurants.values();
            collectionReviews = reviews.values();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a random review given a restaurant name.
     * 
     * @param restaurantName
     * @return a random review in JSON format for the corresponding
     *         restaurantName or "No reviews for this restaurant." if the
     *         restaurant does not have reviews
     */
    public String randomReview(String restaurantName) {
        List<String> businessIDs = new LinkedList<>();
        for (Restaurant restaurant : collectionRestaurants) {
            if (restaurant.getName().equals(restaurantName)) {
                businessIDs.add(restaurant.getBusinessID());
            }
        }
        if (businessIDs.isEmpty()) {
            return "No reviews for this restaurant.";
        } else {
            List<Review> poolReviews = new ArrayList<>();
            for (String businessID : businessIDs) {
                for (Review review : collectionReviews) {
                    if (review.getMap().get("business_id").equals(businessID)) {
                        poolReviews.add(review);
                    }
                }
            }
            Random random = new Random();
            return poolReviews.get(random.nextInt(poolReviews.size())).getJSONString();
        }
    }

    /**
     * 
     * @param businessId
     *            business_id of a restaurant
     * @return restaurant of the businessId details in JSON format or
     *         "No restaurant for this business ID." if no restaurants matches
     *         the business ID.
     */
    public String getRestaurant(String businessId) {
        if (restaurants.containsKey(businessId)) {
            return restaurants.get(businessId).getJSONString();
        } else {
            return "No restaurant for this business ID.";
        }
    }

    /**
     * Adds a restaurant into the restaurant database.
     * 
     * @requires: the restaurant to not be in the database already
     * @param restaurantDetailsJSON
     *            (in JSON format)
     */
    public String addRestaurant(String restaurantDetailsJSON) {
        JSONParser parser = new JSONParser();
        try {
            Map<?, ?> newRestaurant = (Map<?, ?>) parser.parse(restaurantDetailsJSON);
            restaurants.put((String) newRestaurant.get("business_id"),
                    new Restaurant(newRestaurant, restaurantDetailsJSON));
            return "{\"STATUS\": \"Restaurant has been added\"}";
        } catch (ParseException e) {
            e.printStackTrace();
            return "{\"STATUS\": \"Fail to add restaurant\"}";
        }
    }

    /**
     * Adds a review into the review database.
     * 
     * @param reviewDetailsJSON
     *            (in JSON format)
     */
    public  String addReview(String reviewDetailsJSON) {
        JSONParser parser = new JSONParser();
        try {
            Map<?, ?> newReview = (Map<?, ?>) parser.parse(reviewDetailsJSON);
            reviews.put((String) newReview.get("review_id"), new Review(newReview, reviewDetailsJSON));
            return "{\"STATUS\": \"A review has been added\"}";
        } catch (ParseException e) {
            e.printStackTrace();
            return "{\"STATUS\": \"A review has been added\"}";
        }
    }

    /**
     * Adds a user into the user database.
     * 
     * @param userDetailsJSON
     *            (in JSON format)
     */
    public  String addUser(String userDetailsJSON) {
        JSONParser parser = new JSONParser();
        try {
            Map<?, ?> newUser = (Map<?, ?>) parser.parse(userDetailsJSON);
            users.put((String) newUser.get("user_id"), new User(newUser));
            return "{\"STATUS\": \"User has been added\"}";
        } catch (ParseException e) {
            e.printStackTrace();
            return "{\"STATUS\": \"Fail to add User\"}";
        }
    }

    public Map<String, Restaurant> getMapRestaurants() {
        return new LinkedHashMap<String, Restaurant>(restaurants);
    }

    public List<Restaurant> getRestaurants() {
        return new LinkedList<Restaurant>(collectionRestaurants);
    }

    public  Set<Restaurant> query(String queryString) {
        RestaurantDB database = new RestaurantDB(filename1, filename2, filename3);
        FormulaFactory factory = new FormulaFactory(database);
        Set<Restaurant> sets = factory.parse(queryString);

        return sets;

    }

}
