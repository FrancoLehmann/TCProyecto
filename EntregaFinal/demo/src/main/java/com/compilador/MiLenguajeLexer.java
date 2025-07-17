// Generated from com\compilador\MiLenguaje.g4 by ANTLR 4.9.3
package com.compilador;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiLenguajeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FOR=1, WHILE=2, IF=3, ELSE=4, INT=5, CHAR=6, DOUBLE=7, VOID=8, STRING=9, 
		RETURN=10, BREAK=11, CONTINUE=12, BOOL=13, TRUE=14, FALSE=15, PA=16, PC=17, 
		CA=18, CC=19, LA=20, LC=21, PYC=22, COMA=23, IGUAL=24, MAYOR=25, MAYOR_IGUAL=26, 
		MENOR=27, MENOR_IGUAL=28, EQL=29, DISTINTO=30, SUM=31, RES=32, MUL=33, 
		DIV=34, MOD=35, OR=36, AND=37, NOT=38, INTEGER=39, DECIMAL=40, CHARACTER=41, 
		STRING_LITERAL=42, ID=43, COMENTARIO_LINEA=44, COMENTARIO_BLOQUE=45, WS=46, 
		OTRO=47;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"FOR", "WHILE", "IF", "ELSE", "INT", "CHAR", "DOUBLE", "VOID", "STRING", 
			"RETURN", "BREAK", "CONTINUE", "BOOL", "TRUE", "FALSE", "PA", "PC", "CA", 
			"CC", "LA", "LC", "PYC", "COMA", "IGUAL", "MAYOR", "MAYOR_IGUAL", "MENOR", 
			"MENOR_IGUAL", "EQL", "DISTINTO", "SUM", "RES", "MUL", "DIV", "MOD", 
			"OR", "AND", "NOT", "INTEGER", "DECIMAL", "CHARACTER", "STRING_LITERAL", 
			"ID", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "WS", "OTRO", "LETRA", 
			"DIGITO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'for'", "'while'", "'if'", "'else'", "'int'", "'char'", "'double'", 
			"'void'", "'String'", "'return'", "'break'", "'continue'", "'bool'", 
			"'true'", "'false'", "'('", "')'", "'['", "']'", "'{'", "'}'", "';'", 
			"','", "'='", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'||'", "'&&'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FOR", "WHILE", "IF", "ELSE", "INT", "CHAR", "DOUBLE", "VOID", 
			"STRING", "RETURN", "BREAK", "CONTINUE", "BOOL", "TRUE", "FALSE", "PA", 
			"PC", "CA", "CC", "LA", "LC", "PYC", "COMA", "IGUAL", "MAYOR", "MAYOR_IGUAL", 
			"MENOR", "MENOR_IGUAL", "EQL", "DISTINTO", "SUM", "RES", "MUL", "DIV", 
			"MOD", "OR", "AND", "NOT", "INTEGER", "DECIMAL", "CHARACTER", "STRING_LITERAL", 
			"ID", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "WS", "OTRO"
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


	public MiLenguajeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiLenguaje.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0142\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&"+
		"\3&\3&\3\'\3\'\3(\6(\u00ef\n(\r(\16(\u00f0\3)\6)\u00f4\n)\r)\16)\u00f5"+
		"\3)\3)\6)\u00fa\n)\r)\16)\u00fb\3*\3*\3*\3*\5*\u0102\n*\3*\3*\3+\3+\3"+
		"+\3+\7+\u010a\n+\f+\16+\u010d\13+\3+\3+\3,\3,\5,\u0113\n,\3,\3,\3,\7,"+
		"\u0118\n,\f,\16,\u011b\13,\3-\3-\3-\3-\7-\u0121\n-\f-\16-\u0124\13-\3"+
		"-\3-\3.\3.\3.\3.\7.\u012c\n.\f.\16.\u012f\13.\3.\3.\3.\3.\3.\3/\6/\u0137"+
		"\n/\r/\16/\u0138\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\u012d\2\63\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\2c\2\3\2\b\5\2\f\f\17\17))\6\2"+
		"\f\f\17\17$$^^\4\2\f\f\17\17\5\2\13\f\17\17\"\"\4\2C\\c|\3\2\62;\2\u014c"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3e\3"+
		"\2\2\2\5i\3\2\2\2\7o\3\2\2\2\tr\3\2\2\2\13w\3\2\2\2\r{\3\2\2\2\17\u0080"+
		"\3\2\2\2\21\u0087\3\2\2\2\23\u008c\3\2\2\2\25\u0093\3\2\2\2\27\u009a\3"+
		"\2\2\2\31\u00a0\3\2\2\2\33\u00a9\3\2\2\2\35\u00ae\3\2\2\2\37\u00b3\3\2"+
		"\2\2!\u00b9\3\2\2\2#\u00bb\3\2\2\2%\u00bd\3\2\2\2\'\u00bf\3\2\2\2)\u00c1"+
		"\3\2\2\2+\u00c3\3\2\2\2-\u00c5\3\2\2\2/\u00c7\3\2\2\2\61\u00c9\3\2\2\2"+
		"\63\u00cb\3\2\2\2\65\u00cd\3\2\2\2\67\u00d0\3\2\2\29\u00d2\3\2\2\2;\u00d5"+
		"\3\2\2\2=\u00d8\3\2\2\2?\u00db\3\2\2\2A\u00dd\3\2\2\2C\u00df\3\2\2\2E"+
		"\u00e1\3\2\2\2G\u00e3\3\2\2\2I\u00e5\3\2\2\2K\u00e8\3\2\2\2M\u00eb\3\2"+
		"\2\2O\u00ee\3\2\2\2Q\u00f3\3\2\2\2S\u00fd\3\2\2\2U\u0105\3\2\2\2W\u0112"+
		"\3\2\2\2Y\u011c\3\2\2\2[\u0127\3\2\2\2]\u0136\3\2\2\2_\u013c\3\2\2\2a"+
		"\u013e\3\2\2\2c\u0140\3\2\2\2ef\7h\2\2fg\7q\2\2gh\7t\2\2h\4\3\2\2\2ij"+
		"\7y\2\2jk\7j\2\2kl\7k\2\2lm\7n\2\2mn\7g\2\2n\6\3\2\2\2op\7k\2\2pq\7h\2"+
		"\2q\b\3\2\2\2rs\7g\2\2st\7n\2\2tu\7u\2\2uv\7g\2\2v\n\3\2\2\2wx\7k\2\2"+
		"xy\7p\2\2yz\7v\2\2z\f\3\2\2\2{|\7e\2\2|}\7j\2\2}~\7c\2\2~\177\7t\2\2\177"+
		"\16\3\2\2\2\u0080\u0081\7f\2\2\u0081\u0082\7q\2\2\u0082\u0083\7w\2\2\u0083"+
		"\u0084\7d\2\2\u0084\u0085\7n\2\2\u0085\u0086\7g\2\2\u0086\20\3\2\2\2\u0087"+
		"\u0088\7x\2\2\u0088\u0089\7q\2\2\u0089\u008a\7k\2\2\u008a\u008b\7f\2\2"+
		"\u008b\22\3\2\2\2\u008c\u008d\7U\2\2\u008d\u008e\7v\2\2\u008e\u008f\7"+
		"t\2\2\u008f\u0090\7k\2\2\u0090\u0091\7p\2\2\u0091\u0092\7i\2\2\u0092\24"+
		"\3\2\2\2\u0093\u0094\7t\2\2\u0094\u0095\7g\2\2\u0095\u0096\7v\2\2\u0096"+
		"\u0097\7w\2\2\u0097\u0098\7t\2\2\u0098\u0099\7p\2\2\u0099\26\3\2\2\2\u009a"+
		"\u009b\7d\2\2\u009b\u009c\7t\2\2\u009c\u009d\7g\2\2\u009d\u009e\7c\2\2"+
		"\u009e\u009f\7m\2\2\u009f\30\3\2\2\2\u00a0\u00a1\7e\2\2\u00a1\u00a2\7"+
		"q\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6"+
		"\7p\2\2\u00a6\u00a7\7w\2\2\u00a7\u00a8\7g\2\2\u00a8\32\3\2\2\2\u00a9\u00aa"+
		"\7d\2\2\u00aa\u00ab\7q\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad\7n\2\2\u00ad"+
		"\34\3\2\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7w\2\2\u00b1"+
		"\u00b2\7g\2\2\u00b2\36\3\2\2\2\u00b3\u00b4\7h\2\2\u00b4\u00b5\7c\2\2\u00b5"+
		"\u00b6\7n\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7g\2\2\u00b8 \3\2\2\2\u00b9"+
		"\u00ba\7*\2\2\u00ba\"\3\2\2\2\u00bb\u00bc\7+\2\2\u00bc$\3\2\2\2\u00bd"+
		"\u00be\7]\2\2\u00be&\3\2\2\2\u00bf\u00c0\7_\2\2\u00c0(\3\2\2\2\u00c1\u00c2"+
		"\7}\2\2\u00c2*\3\2\2\2\u00c3\u00c4\7\177\2\2\u00c4,\3\2\2\2\u00c5\u00c6"+
		"\7=\2\2\u00c6.\3\2\2\2\u00c7\u00c8\7.\2\2\u00c8\60\3\2\2\2\u00c9\u00ca"+
		"\7?\2\2\u00ca\62\3\2\2\2\u00cb\u00cc\7@\2\2\u00cc\64\3\2\2\2\u00cd\u00ce"+
		"\7@\2\2\u00ce\u00cf\7?\2\2\u00cf\66\3\2\2\2\u00d0\u00d1\7>\2\2\u00d18"+
		"\3\2\2\2\u00d2\u00d3\7>\2\2\u00d3\u00d4\7?\2\2\u00d4:\3\2\2\2\u00d5\u00d6"+
		"\7?\2\2\u00d6\u00d7\7?\2\2\u00d7<\3\2\2\2\u00d8\u00d9\7#\2\2\u00d9\u00da"+
		"\7?\2\2\u00da>\3\2\2\2\u00db\u00dc\7-\2\2\u00dc@\3\2\2\2\u00dd\u00de\7"+
		"/\2\2\u00deB\3\2\2\2\u00df\u00e0\7,\2\2\u00e0D\3\2\2\2\u00e1\u00e2\7\61"+
		"\2\2\u00e2F\3\2\2\2\u00e3\u00e4\7\'\2\2\u00e4H\3\2\2\2\u00e5\u00e6\7~"+
		"\2\2\u00e6\u00e7\7~\2\2\u00e7J\3\2\2\2\u00e8\u00e9\7(\2\2\u00e9\u00ea"+
		"\7(\2\2\u00eaL\3\2\2\2\u00eb\u00ec\7#\2\2\u00ecN\3\2\2\2\u00ed\u00ef\5"+
		"c\62\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1P\3\2\2\2\u00f2\u00f4\5c\62\2\u00f3\u00f2\3\2\2\2"+
		"\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7"+
		"\3\2\2\2\u00f7\u00f9\7\60\2\2\u00f8\u00fa\5c\62\2\u00f9\u00f8\3\2\2\2"+
		"\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fcR\3"+
		"\2\2\2\u00fd\u0101\7)\2\2\u00fe\u0102\n\2\2\2\u00ff\u0100\7^\2\2\u0100"+
		"\u0102\13\2\2\2\u0101\u00fe\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\3"+
		"\2\2\2\u0103\u0104\7)\2\2\u0104T\3\2\2\2\u0105\u010b\7$\2\2\u0106\u010a"+
		"\n\3\2\2\u0107\u0108\7^\2\2\u0108\u010a\13\2\2\2\u0109\u0106\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2"+
		"\2\2\u010c\u010e\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\7$\2\2\u010f"+
		"V\3\2\2\2\u0110\u0113\5a\61\2\u0111\u0113\7a\2\2\u0112\u0110\3\2\2\2\u0112"+
		"\u0111\3\2\2\2\u0113\u0119\3\2\2\2\u0114\u0118\5a\61\2\u0115\u0118\5c"+
		"\62\2\u0116\u0118\7a\2\2\u0117\u0114\3\2\2\2\u0117\u0115\3\2\2\2\u0117"+
		"\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2"+
		"\2\2\u011aX\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u011d\7\61\2\2\u011d\u011e"+
		"\7\61\2\2\u011e\u0122\3\2\2\2\u011f\u0121\n\4\2\2\u0120\u011f\3\2\2\2"+
		"\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0125"+
		"\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0126\b-\2\2\u0126Z\3\2\2\2\u0127\u0128"+
		"\7\61\2\2\u0128\u0129\7,\2\2\u0129\u012d\3\2\2\2\u012a\u012c\13\2\2\2"+
		"\u012b\u012a\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012e\3\2\2\2\u012d\u012b"+
		"\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0131\7,\2\2\u0131"+
		"\u0132\7\61\2\2\u0132\u0133\3\2\2\2\u0133\u0134\b.\2\2\u0134\\\3\2\2\2"+
		"\u0135\u0137\t\5\2\2\u0136\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0136"+
		"\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013b\b/\2\2\u013b"+
		"^\3\2\2\2\u013c\u013d\13\2\2\2\u013d`\3\2\2\2\u013e\u013f\t\6\2\2\u013f"+
		"b\3\2\2\2\u0140\u0141\t\7\2\2\u0141d\3\2\2\2\17\2\u00f0\u00f5\u00fb\u0101"+
		"\u0109\u010b\u0112\u0117\u0119\u0122\u012d\u0138\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}