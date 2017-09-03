// Generated from Formula.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.formula;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormulaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, WHITESPACE=3, AND=4, OR=5, IN=6, CATEGORY=7, NAME=8, RATING=9, 
		PRICE=10, LPAREN=11, RPAREN=12, NUM=13, TEXT=14;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'"
	};
	public static final String[] ruleNames = {
		"T__1", "T__0", "WHITESPACE", "AND", "OR", "IN", "CATEGORY", "NAME", "RATING", 
		"PRICE", "LPAREN", "RPAREN", "NUM", "TEXT"
	};


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


	public FormulaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Formula.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20Z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\3\4\6\4&\n"+
		"\4\r\4\16\4\'\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\6\17W"+
		"\n\17\r\17\16\17X\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\3\2\5\5\2\13\f\17\17\"\"\3\2\63\67\7\2$$((*+\60"+
		"\60~~[\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5!\3\2\2\2"+
		"\7%\3\2\2\2\t+\3\2\2\2\13.\3\2\2\2\r\61\3\2\2\2\17\64\3\2\2\2\21=\3\2"+
		"\2\2\23B\3\2\2\2\25I\3\2\2\2\27O\3\2\2\2\31Q\3\2\2\2\33S\3\2\2\2\35V\3"+
		"\2\2\2\37 \7$\2\2 \4\3\2\2\2!\"\7\60\2\2\"#\7\60\2\2#\6\3\2\2\2$&\t\2"+
		"\2\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\b\4\2\2*\b"+
		"\3\2\2\2+,\7(\2\2,-\7(\2\2-\n\3\2\2\2./\7~\2\2/\60\7~\2\2\60\f\3\2\2\2"+
		"\61\62\7k\2\2\62\63\7p\2\2\63\16\3\2\2\2\64\65\7e\2\2\65\66\7c\2\2\66"+
		"\67\7v\2\2\678\7g\2\289\7i\2\29:\7q\2\2:;\7t\2\2;<\7{\2\2<\20\3\2\2\2"+
		"=>\7p\2\2>?\7c\2\2?@\7o\2\2@A\7g\2\2A\22\3\2\2\2BC\7t\2\2CD\7c\2\2DE\7"+
		"v\2\2EF\7k\2\2FG\7p\2\2GH\7i\2\2H\24\3\2\2\2IJ\7r\2\2JK\7t\2\2KL\7k\2"+
		"\2LM\7e\2\2MN\7g\2\2N\26\3\2\2\2OP\7*\2\2P\30\3\2\2\2QR\7+\2\2R\32\3\2"+
		"\2\2ST\t\3\2\2T\34\3\2\2\2UW\n\4\2\2VU\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3"+
		"\2\2\2Y\36\3\2\2\2\5\2\'X\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}