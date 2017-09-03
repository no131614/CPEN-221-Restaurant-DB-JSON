package ca.ece.ubc.cpen221.mp5.formula;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import ca.ece.ubc.cpen221.mp5.*;



public class FormulaFactory {
    
    /**
     * @param string must contain a well-formed formula string of boolean literals and operators..
     * @return Formula corresponding to the string
     */
   private static RestaurantDB restDB;
    
    public FormulaFactory(RestaurantDB restDB){
        this. restDB = restDB;
    }
    
    
    public static Set<Restaurant> parse(String string) {
        // Create a stream of tokens using the lexer.
        CharStream stream = new ANTLRInputStream(string);
        FormulaLexer lexer = new FormulaLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        FormulaParser parser = new FormulaParser(tokens);
        parser.reportErrorsAsExceptions();
        
        // Generate the parse tree using the starter rule.
        ParseTree tree = parser.query(); // "root" is the starter rule.
        
        // debugging option #1: print the tree to the console
       //System.err.println(tree.toStringTree(parser));

        // debugging option #2: show the tree in a window
       ((RuleContext)tree).inspect(parser);

        // debugging option #3: walk the tree with a listener
        //new ParseTreeWalker().walk(new FormulaListener_PrintEverything(), tree);
        
        // Finally, construct a Document value by walking over the parse tree.
        ParseTreeWalker walker = new ParseTreeWalker();
        FormulaListener_FormulaCreator listener = new FormulaListener_FormulaCreator();
        walker.walk(listener, tree);
        
        // return the Document value that the listener created
        return listener.getSet(); //listener.getFormula();
    }
    
    private static class FormulaListener_FormulaCreator extends FormulaBaseListener {
        private boolean foo = false;
        private boolean foo2 = false;
        private Stack<String>  stringStack = new Stack<String>();
        private Stack<Set<Restaurant>> setStack = new Stack<Set<Restaurant>>();
        private Stack<Set<Restaurant>> atomStack = new Stack<Set<Restaurant>>();
        private Stack<Set<Restaurant>> andStack = new Stack<Set<Restaurant>>();
        private Stack<Set<Restaurant>> orStack = new Stack<Set<Restaurant>>();
        private Stack<Set<Restaurant>> tempStack = new Stack<Set<Restaurant>>();
        
        @Override
        public void enterOrexpr(FormulaParser.OrexprContext ctx) {
           if(!atomStack.isEmpty()||foo == true){
               while(!atomStack.isEmpty()){
                   tempStack.add(atomStack.pop());
                   foo = true;
                   System.out.println("ENTERING OTHER OR");
               }
           }
           else{
               System.out.println("ENTERING FIRST OR");
               //foo = true;
           }
            
        }
        
        
        
        @Override
        public void exitString(FormulaParser.StringContext ctx) {
            TerminalNode token = ctx.TEXT();
            String text = token.getText();
            stringStack.push(text);
            System.out.println(text);
            //System.out.println("Exit String");
        }
        
        @Override
        public void exitRange(FormulaParser.RangeContext ctx) {
            TerminalNode token1 = ctx.NUM(0);
            String rating1 = token1.getText();
            System.out.println(rating1);
            stringStack.push(rating1);
            TerminalNode token2 = ctx.NUM(1);
            String rating2 = token2.getText();
            System.out.println(rating2);
            stringStack.push(rating2);
        
        }
        
        
        @Override
        public void exitCategory(FormulaParser.CategoryContext ctx) {
            String str = stringStack.pop();
            Category category = new Category(str, restDB);
            setStack.add(category.find());
            System.out.println("Exit Category");
        }
        
        @Override
        public void exitIn(FormulaParser.InContext ctx) {
            String str = stringStack.pop();
            In in = new In(str, restDB);
            setStack.add(in.find());
            System.out.println("Exit In");
        }
        
        
        @Override
        public void exitName(FormulaParser.NameContext ctx) {
            String str = stringStack.pop();
            Name name = new Name(str, restDB);
            setStack.add(name.find());
            System.out.println("Exit Name");
        }
        
        @Override
        public void exitRating(FormulaParser.RatingContext ctx) {
            String rating2 = stringStack.pop();
            String rating1 = stringStack.pop();
            double d1 = Double.parseDouble(rating1);
            double d2 = Double.parseDouble(rating2);
            Rating rating = new Rating(d1, d2, restDB);
            setStack.add(rating.find());
            System.out.println("Exit Rating");
        }
        
        @Override
        public void exitPrice(FormulaParser.PriceContext ctx) {
            String price2 = stringStack.pop();
            String price1 = stringStack.pop();
            long d1 = Long.parseLong(price1);
            long d2 = Long.parseLong(price2);
            Price price = new Price(d1, d2, restDB);
            setStack.add(price.find());
            System.out.println("Exit Price");
        }
        
        @Override
        public void exitAtom(FormulaParser.AtomContext ctx) {
            Set<Restaurant> newSet = new HashSet<Restaurant>();
            while(!setStack.isEmpty()){
                Set<Restaurant> sets = setStack.pop();
                newSet.addAll(sets);
                }
           if(foo2 == true){
               foo2 = false;
           }
           else{
            atomStack.add(newSet);
           }
            for (Restaurant r : newSet) {
                System.out.println(r.getJSONString());
            }
            System.out.println("Exit Atom");
        }
        
        @Override
        public void exitOrexpr(FormulaParser.OrexprContext ctx) {
            
            Set<Restaurant> newSet = new HashSet<Restaurant>();
            while(!andStack.isEmpty()){
            Set<Restaurant> sets = andStack.pop();
            newSet.addAll(sets);
            }
            
            if(foo == true){
                
                while(!tempStack.isEmpty()){
                    atomStack.add(tempStack.pop());
                }
                
               atomStack.add(newSet);
               for (Restaurant s : newSet) {
                   //System.out.println(" ");
                   //System.out.println(s+" TEMPSETS");
                  // System.out.println(" ");
               }
               System.out.println("Exit OTHER Orexpr");
               foo = false;
               foo2 = true;
            }
            
            else{
            
            orStack.add(newSet);
            for (Restaurant s : newSet) {
                System.out.println(s.getJSONString()+" FINAL RESULT");
            }
            System.out.println("Exit Orexpr");
            }
        }
        
        @Override
        public void exitAndexpr(FormulaParser.AndexprContext ctx) {
            Set<Restaurant> newSet = new HashSet<Restaurant>();
            newSet.addAll(atomStack.pop());
            
            for (Restaurant s : newSet) {
                System.out.println(s.getJSONString()+"FIRST AND SET");
            }
            
            while(!atomStack.isEmpty()){
            Set<Restaurant> sets = atomStack.pop();
            newSet.retainAll(sets);
            }
            
            for (Restaurant s : newSet) {
                System.out.println(s+"NewSetinAND");
            }
            andStack.add(newSet);
            
            
           
            System.out.println("Exit Andexpr");
        }
        
      
        @Override
        public void exitQuery(FormulaParser.QueryContext ctx) {
            // do nothing, because the top of the stack should have the node already in it
            
            if(orStack.size() > 1){
                Set<Restaurant> newSet = new HashSet<Restaurant>();
                orStack.pop();
                
                newSet = orStack.pop();
                if(newSet.isEmpty()&&!orStack.isEmpty()){
                    Set<Restaurant> sets = orStack.pop();
                    for (Restaurant s : sets) {
                        System.out.println(" ");
                        System.out.println(s+" CUR SETS");
                        System.out.println(" ");
                    }
                    //newSet.addAll(sets);
                    System.out.println("-------1");
                    
                    
                }
                
                else{
                    
                
                while(!orStack.isEmpty()){
                    
                    Set<Restaurant> sets = orStack.pop();
                    for (Restaurant s : sets) {
                        System.out.println(" ");
                        System.out.println(s+" CUROR SETS");
                        System.out.println(" ");
                    }
                    newSet.addAll(sets);
                    System.out.println("-------2");
                    
                }
                
                }
                
                orStack.add(newSet);
                System.out.println("Exit QUERY");
                
                
                
                
                
            }
            
            else{
            assert orStack.size() == 1;
            System.out.println("Exit QUERY");
            }
        }
        
        public Set<Restaurant> getSet() {
            return orStack.get(0);
        }
    }

    
    

    private static class FormulaListener_PrintEverything extends FormulaBaseListener {
        public void enterFormula(FormulaParser.StringContext ctx) { System.err.println("entering string " + ctx.getText()); }
        public void exitFormula(FormulaParser.StringContext ctx) { System.err.println("exiting string " + ctx.getText()); }

        public void enterConjunction(FormulaParser.QueryContext ctx) { System.err.println("entering query " + ctx.getText()); }
        public void exitConjunction(FormulaParser.QueryContext ctx) { System.err.println("exiting query " + ctx.getText()); }

        //public void enterLiteral(FormulaParser.LiteralContext ctx) { System.err.println("entering literal " + ctx.getText()); }
        //public void exitLiteral(FormulaParser.LiteralContext ctx) { System.err.println("exiting literal " + ctx.getText()); }
    }

}
