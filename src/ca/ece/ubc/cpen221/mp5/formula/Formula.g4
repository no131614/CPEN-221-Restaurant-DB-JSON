grammar Formula;

// This puts "package formula;" at the top of the output Java files.
@header {
package formula;
}

// This adds code to the generated lexer and parser. Do not change these lines.
@members {
    // This method makes the lexer or parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        //removeErrorListeners();
        
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 *   *** Antlr requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */
 
WHITESPACE : [ \t\r\n]+ -> skip ;
AND : '&&';
OR  : '||';
IN : 'in';
CATEGORY : 'category';
NAME : 'name';
RATING : 'rating';
PRICE : 'price';
LPAREN : '(';
RPAREN : ')';
NUM : [1-5];
TEXT : ~[()&|."]+;



/*
 * These are the parser rules. They define the structures used by the parser.
 *    *** Antlr requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */
 

query  : orexpr EOF; 
orexpr : andexpr ( OR andexpr )*;
andexpr : atom ( AND atom )*;
atom : in | category | rating | price | name | LPAREN orexpr RPAREN;
in :  IN LPAREN string RPAREN;
category : CATEGORY LPAREN string RPAREN;
name : NAME LPAREN string RPAREN;
rating : RATING LPAREN range RPAREN;
price : PRICE LPAREN range RPAREN;
string : '"' TEXT '"';
range : NUM '..' NUM;