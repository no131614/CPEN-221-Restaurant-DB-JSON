package ca.ece.ubc.cpen221.mp5.formula;

import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

public class StringLiteral implements Formula {

    private String string;
    
    public StringLiteral(String string){
        this.string = string;
    }
    
    @Override
    public Set<Restaurant> find() {
        
        // TODO Auto-generated method stub
        
        return null;
    }

}
