package ca.ece.ubc.cpen221.mp5.statlearning;

public class Location {
    private double x;
    private double y;

    public Location(Location loc){
        x = loc.getX();
        y = loc.getY();
    }
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public double getDistanceTo(Location loc){
        double difX = Math.abs(x - loc.getX());
        double difY = Math.abs(y - loc.getX());
        double distance = Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));
        return distance;
    }
}
