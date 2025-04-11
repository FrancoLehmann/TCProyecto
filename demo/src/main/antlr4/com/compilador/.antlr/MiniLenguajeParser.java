// Generated from c:/Users/Usuario/Desktop/2025/TC/demo/src/main/antlr4/com/compilador/MiniLenguaje.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MiniLenguajeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PA=1, PC=2, CA=3, CC=4, LA=5, LC=6, PYC=7, COMA=8, IGUAL=9, MAYOR=10, 
		MAYOR_IGUAL=11, MENOR=12, MENOR_IGUAL=13, EQL=14, DISTINTO=15, SUM=16, 
		RES=17, MUL=18, DIV=19, MOD=20, OR=21, AND=22, NOT=23, FOR=24, WHILE=25, 
		IF=26, ELSE=27, INT=28, CHAR=29, DOUBLE=30, VOID=31, RETURN=32, ID=33, 
		INTEGER=34, DECIMAL=35, CHARACTER=36, COMENTARIO_LINEA=37, COMENTARIO_BLOQUE=38, 
		HOLA_MUNDO=39, WS=40, OTRO=41;
	public static final int
		RULE_programa = 0, RULE_token = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "token"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'{'", "'}'", "';'", "','", "'='", 
			"'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'||'", "'&&'", "'!'", "'for'", "'while'", "'if'", "'else'", "'int'", 
			"'char'", "'double'", "'void'", "'return'", null, null, null, null, null, 
			null, "'\"Hola, mundo!\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PA", "PC", "CA", "CC", "LA", "LC", "PYC", "COMA", "IGUAL", "MAYOR", 
			"MAYOR_IGUAL", "MENOR", "MENOR_IGUAL", "EQL", "DISTINTO", "SUM", "RES", 
			"MUL", "DIV", "MOD", "OR", "AND", "NOT", "FOR", "WHILE", "IF", "ELSE", 
			"INT", "CHAR", "DOUBLE", "VOID", "RETURN", "ID", "INTEGER", "DECIMAL", 
			"CHARACTER", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "HOLA_MUNDO", "WS", 
			"OTRO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniLenguaje.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniLenguajeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MiniLenguajeParser.EOF, 0); }
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2336462209022L) != 0)) {
				{
				{
				setState(4);
				token();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(10);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TokenContext extends ParserRuleContext {
		public TerminalNode PA() { return getToken(MiniLenguajeParser.PA, 0); }
		public TerminalNode PC() { return getToken(MiniLenguajeParser.PC, 0); }
		public TerminalNode CA() { return getToken(MiniLenguajeParser.CA, 0); }
		public TerminalNode CC() { return getToken(MiniLenguajeParser.CC, 0); }
		public TerminalNode LA() { return getToken(MiniLenguajeParser.LA, 0); }
		public TerminalNode LC() { return getToken(MiniLenguajeParser.LC, 0); }
		public TerminalNode PYC() { return getToken(MiniLenguajeParser.PYC, 0); }
		public TerminalNode COMA() { return getToken(MiniLenguajeParser.COMA, 0); }
		public TerminalNode IGUAL() { return getToken(MiniLenguajeParser.IGUAL, 0); }
		public TerminalNode MAYOR() { return getToken(MiniLenguajeParser.MAYOR, 0); }
		public TerminalNode MAYOR_IGUAL() { return getToken(MiniLenguajeParser.MAYOR_IGUAL, 0); }
		public TerminalNode MENOR() { return getToken(MiniLenguajeParser.MENOR, 0); }
		public TerminalNode MENOR_IGUAL() { return getToken(MiniLenguajeParser.MENOR_IGUAL, 0); }
		public TerminalNode EQL() { return getToken(MiniLenguajeParser.EQL, 0); }
		public TerminalNode DISTINTO() { return getToken(MiniLenguajeParser.DISTINTO, 0); }
		public TerminalNode SUM() { return getToken(MiniLenguajeParser.SUM, 0); }
		public TerminalNode RES() { return getToken(MiniLenguajeParser.RES, 0); }
		public TerminalNode MUL() { return getToken(MiniLenguajeParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MiniLenguajeParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MiniLenguajeParser.MOD, 0); }
		public TerminalNode OR() { return getToken(MiniLenguajeParser.OR, 0); }
		public TerminalNode AND() { return getToken(MiniLenguajeParser.AND, 0); }
		public TerminalNode NOT() { return getToken(MiniLenguajeParser.NOT, 0); }
		public TerminalNode FOR() { return getToken(MiniLenguajeParser.FOR, 0); }
		public TerminalNode WHILE() { return getToken(MiniLenguajeParser.WHILE, 0); }
		public TerminalNode IF() { return getToken(MiniLenguajeParser.IF, 0); }
		public TerminalNode ELSE() { return getToken(MiniLenguajeParser.ELSE, 0); }
		public TerminalNode INT() { return getToken(MiniLenguajeParser.INT, 0); }
		public TerminalNode CHAR() { return getToken(MiniLenguajeParser.CHAR, 0); }
		public TerminalNode DOUBLE() { return getToken(MiniLenguajeParser.DOUBLE, 0); }
		public TerminalNode VOID() { return getToken(MiniLenguajeParser.VOID, 0); }
		public TerminalNode RETURN() { return getToken(MiniLenguajeParser.RETURN, 0); }
		public TerminalNode ID() { return getToken(MiniLenguajeParser.ID, 0); }
		public TerminalNode INTEGER() { return getToken(MiniLenguajeParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(MiniLenguajeParser.DECIMAL, 0); }
		public TerminalNode CHARACTER() { return getToken(MiniLenguajeParser.CHARACTER, 0); }
		public TerminalNode OTRO() { return getToken(MiniLenguajeParser.OTRO, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_token);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2336462209022L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u000f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0005\u0000\u0006\b\u0000\n\u0000\f\u0000\t\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0000\u0000\u0002\u0000"+
		"\u0002\u0000\u0001\u0002\u0000\u0001$))\r\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0002\f\u0001\u0000\u0000\u0000\u0004\u0006\u0003\u0002\u0001\u0000"+
		"\u0005\u0004\u0001\u0000\u0000\u0000\u0006\t\u0001\u0000\u0000\u0000\u0007"+
		"\u0005\u0001\u0000\u0000\u0000\u0007\b\u0001\u0000\u0000\u0000\b\n\u0001"+
		"\u0000\u0000\u0000\t\u0007\u0001\u0000\u0000\u0000\n\u000b\u0005\u0000"+
		"\u0000\u0001\u000b\u0001\u0001\u0000\u0000\u0000\f\r\u0007\u0000\u0000"+
		"\u0000\r\u0003\u0001\u0000\u0000\u0000\u0001\u0007";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}