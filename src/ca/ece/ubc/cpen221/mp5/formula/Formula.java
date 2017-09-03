package ca.ece.ubc.cpen221.mp5.formula;

import java.util.Set;

import ca.ece.ubc.cpen221.mp5.Restaurant;

/*
   Data type definition:
   Formula = BooleanLiteral(value:boolean) + And(left,right:Formula)
 */

public interface Formula {
    
    public Set<Restaurant> find();
    
}
