/* The following code was generated by JFlex 1.5.1 */

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.5.1
 * from the specification file <tt>GameWizard.flex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\1\46\1\33\1\33\25\0\1\6\1\40\1\30\2\0\1\34"+
    "\1\42\1\0\1\34\1\34\1\34\1\34\1\34\1\34\1\34\1\34"+
    "\12\27\1\34\1\34\1\37\1\35\1\36\2\0\17\32\1\25\2\32"+
    "\1\44\7\32\1\34\1\0\1\34\1\34\1\12\1\0\1\10\1\45"+
    "\1\13\1\1\1\2\1\3\1\7\1\16\1\4\1\32\1\31\1\23"+
    "\1\11\1\5\1\21\1\22\1\32\1\14\1\15\1\17\1\20\1\26"+
    "\1\43\1\32\1\24\1\32\1\34\1\41\1\34\1\34\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\5\2\1\3\6\2\1\4\1\1\5\5"+
    "\2\1\3\2\1\6\3\2\1\7\10\2\1\0\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\7\2\1\17"+
    "\14\2\1\20\6\2\1\21\3\2\1\22\2\2\1\23"+
    "\4\2\1\24\1\2\1\25\2\2\1\26\2\2\1\27"+
    "\1\2\1\30\1\31\1\0\3\2\2\0\3\2\3\0"+
    "\1\2\1\32\1\2\3\0\2\2\1\33\2\0\2\2"+
    "\1\34\1\0\2\2\1\0\2\2\1\0\1\35\1\2"+
    "\1\0\1\2\1\0\1\2\1\36\4\2\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\47\0\116\0\165\0\234\0\303\0\352\0\u0111"+
    "\0\u0138\0\u015f\0\u0186\0\u01ad\0\u01d4\0\u01fb\0\u0222\0\u0249"+
    "\0\47\0\u0270\0\u0297\0\u02be\0\u02e5\0\u030c\0\u0333\0\u035a"+
    "\0\u0381\0\u03a8\0\47\0\u03cf\0\u03f6\0\u041d\0\u015f\0\u0444"+
    "\0\u046b\0\u0492\0\u04b9\0\u04e0\0\u0507\0\u052e\0\u0555\0\u0249"+
    "\0\47\0\47\0\47\0\47\0\47\0\47\0\47\0\u057c"+
    "\0\u05a3\0\u05ca\0\u05f1\0\u0618\0\u063f\0\u0666\0\u015f\0\u068d"+
    "\0\u06b4\0\u06db\0\u0702\0\u0729\0\u0750\0\u0777\0\u079e\0\u07c5"+
    "\0\u07ec\0\u0813\0\u083a\0\u015f\0\u0861\0\u0888\0\u08af\0\u08d6"+
    "\0\u08fd\0\u0924\0\u015f\0\u094b\0\u0972\0\u0999\0\u015f\0\u09c0"+
    "\0\u09e7\0\u015f\0\u0a0e\0\u0a35\0\u0a5c\0\u0a83\0\u015f\0\u0aaa"+
    "\0\u015f\0\u0ad1\0\u0af8\0\u015f\0\u0b1f\0\u0b46\0\u015f\0\u0b6d"+
    "\0\u015f\0\u015f\0\u0b94\0\u0bbb\0\u0be2\0\u0c09\0\u0c30\0\u0c57"+
    "\0\u0c7e\0\u0ca5\0\u0ccc\0\u0cf3\0\u0d1a\0\u0d41\0\u0d68\0\u015f"+
    "\0\u0d8f\0\u0db6\0\u0ddd\0\u0e04\0\u0e2b\0\u0e52\0\47\0\u0e79"+
    "\0\u0ea0\0\u0ec7\0\u0eee\0\47\0\u0f15\0\u0f3c\0\u0f63\0\u0f8a"+
    "\0\u0fb1\0\u0fd8\0\u0fff\0\u015f\0\u1026\0\u104d\0\u1074\0\u109b"+
    "\0\u10c2\0\47\0\u10e9\0\u1110\0\u1137\0\u115e\0\u015f";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\2\2\12\1\14\1\12\1\15\5\12"+
    "\1\16\1\12\1\17\1\20\2\12\1\10\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\1\33\50\0\1\12\1\34\3\12\1\0\21\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\14\12\1\35"+
    "\4\12\1\0\2\12\10\0\3\12\2\0\5\12\1\0"+
    "\1\12\1\36\17\12\1\0\2\12\10\0\3\12\2\0"+
    "\2\12\1\37\1\12\1\40\1\0\21\12\1\0\2\12"+
    "\10\0\3\12\2\0\5\12\1\0\11\12\1\41\7\12"+
    "\1\0\2\12\10\0\3\12\7\0\1\10\24\0\1\10"+
    "\14\0\5\12\1\0\1\12\1\42\17\12\1\0\2\12"+
    "\10\0\3\12\2\0\5\12\1\0\21\12\1\0\2\12"+
    "\10\0\3\12\2\0\1\12\1\43\3\12\1\0\21\12"+
    "\1\0\2\12\10\0\3\12\2\0\1\12\1\44\3\12"+
    "\1\0\21\12\1\0\1\45\1\12\10\0\3\12\2\0"+
    "\5\12\1\0\5\12\1\46\13\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\14\12\1\47\4\12\1\0"+
    "\2\12\10\0\3\12\30\0\1\17\17\0\30\50\1\51"+
    "\16\50\35\0\1\52\46\0\1\53\46\0\1\54\46\0"+
    "\1\55\52\0\1\56\47\0\1\57\5\0\5\12\1\0"+
    "\7\12\1\60\11\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\10\12\1\61\10\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\12\12\1\62\6\12\1\0"+
    "\2\12\10\0\3\12\2\0\2\12\1\63\2\12\1\0"+
    "\1\12\1\64\17\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\6\12\1\65\12\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\14\12\1\66\4\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\10\12\1\67"+
    "\10\12\1\0\2\12\10\0\3\12\2\0\5\12\1\0"+
    "\2\12\1\70\16\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\2\12\1\71\16\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\10\12\1\72\10\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\5\12\1\73"+
    "\13\12\1\0\2\12\10\0\3\12\2\0\3\12\1\74"+
    "\1\12\1\0\21\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\11\12\1\75\7\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\1\12\1\76\17\12\1\0"+
    "\2\12\10\0\3\12\2\0\3\12\1\77\1\12\1\0"+
    "\21\12\1\0\2\12\10\0\3\12\2\0\5\12\1\0"+
    "\5\12\1\100\13\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\12\12\1\101\6\12\1\0\2\12\10\0"+
    "\3\12\2\0\3\12\1\102\1\12\1\0\21\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\14\12\1\103"+
    "\4\12\1\0\2\12\10\0\3\12\2\0\1\12\1\104"+
    "\3\12\1\0\21\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\6\12\1\105\12\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\3\12\1\106\15\12\1\0"+
    "\2\12\10\0\3\12\2\0\1\12\1\107\3\12\1\0"+
    "\21\12\1\0\2\12\10\0\3\12\2\0\5\12\1\0"+
    "\7\12\1\110\11\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\17\12\1\111\1\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\14\12\1\112\4\12\1\0"+
    "\2\12\10\0\3\12\2\0\1\12\1\113\3\12\1\0"+
    "\21\12\1\0\2\12\10\0\3\12\2\0\5\12\1\0"+
    "\15\12\1\114\3\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\14\12\1\115\4\12\1\0\2\12\10\0"+
    "\3\12\2\0\3\12\1\116\1\12\1\0\21\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\14\12\1\117"+
    "\4\12\1\0\2\12\10\0\3\12\2\0\4\12\1\120"+
    "\1\0\21\12\1\0\2\12\10\0\3\12\2\0\1\12"+
    "\1\121\3\12\1\0\21\12\1\0\2\12\10\0\3\12"+
    "\2\0\1\12\1\122\3\12\1\0\21\12\1\0\2\12"+
    "\10\0\3\12\2\0\5\12\1\0\12\12\1\123\6\12"+
    "\1\0\2\12\10\0\3\12\2\0\5\12\1\0\3\12"+
    "\1\124\15\12\1\0\2\12\10\0\3\12\2\0\5\12"+
    "\1\0\12\12\1\125\6\12\1\0\2\12\10\0\3\12"+
    "\2\0\1\12\1\126\3\12\1\0\21\12\1\0\2\12"+
    "\10\0\3\12\2\0\5\12\1\0\14\12\1\127\4\12"+
    "\1\0\2\12\10\0\3\12\2\0\1\12\1\130\3\12"+
    "\1\0\21\12\1\0\2\12\10\0\3\12\2\0\1\12"+
    "\1\131\3\12\1\0\21\12\1\0\2\12\10\0\3\12"+
    "\2\0\4\12\1\132\1\0\21\12\1\0\2\12\10\0"+
    "\3\12\2\0\1\12\1\133\3\12\1\0\21\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\5\12\1\134"+
    "\13\12\1\0\2\12\10\0\3\12\2\0\2\12\1\135"+
    "\2\12\1\0\21\12\1\0\2\12\10\0\3\12\2\0"+
    "\4\12\1\136\1\0\21\12\1\0\2\12\10\0\3\12"+
    "\2\0\1\137\4\12\1\0\21\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\5\12\1\140\13\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\5\12\1\141"+
    "\13\12\1\0\2\12\10\0\3\12\2\0\5\12\1\0"+
    "\1\142\20\12\1\0\2\12\10\0\3\12\2\0\5\12"+
    "\1\143\21\12\1\0\2\12\10\0\3\12\2\0\5\12"+
    "\1\0\3\12\1\144\15\12\1\0\2\12\10\0\3\12"+
    "\2\0\5\12\1\0\1\12\1\145\17\12\1\0\2\12"+
    "\10\0\3\12\2\0\5\12\1\0\3\12\1\146\15\12"+
    "\1\0\2\12\10\0\3\12\10\0\1\147\3\0\1\150"+
    "\34\0\5\12\1\0\13\12\1\151\5\12\1\0\2\12"+
    "\10\0\3\12\2\0\5\12\1\0\2\12\1\152\16\12"+
    "\1\0\2\12\10\0\3\12\2\0\5\12\1\0\14\12"+
    "\1\153\4\12\1\0\2\12\10\0\3\12\11\0\1\154"+
    "\46\0\1\155\5\0\1\156\31\0\5\12\1\0\14\12"+
    "\1\157\4\12\1\0\2\12\10\0\3\12\2\0\1\12"+
    "\1\160\3\12\1\0\21\12\1\0\2\12\10\0\3\12"+
    "\2\0\3\12\1\161\1\12\1\0\21\12\1\0\2\12"+
    "\10\0\3\12\12\0\1\162\51\0\1\163\42\0\1\164"+
    "\37\0\5\12\1\0\1\12\1\165\17\12\1\0\2\12"+
    "\10\0\3\12\2\0\5\12\1\0\6\12\1\166\12\12"+
    "\1\0\2\12\10\0\3\12\3\0\1\167\45\0\1\170"+
    "\61\0\1\171\33\0\5\12\1\0\15\12\1\172\3\12"+
    "\1\0\2\12\10\0\3\12\2\0\5\12\1\0\10\12"+
    "\1\173\10\12\1\0\2\12\10\0\3\12\16\0\1\174"+
    "\41\0\1\175\37\0\1\12\1\176\3\12\1\0\21\12"+
    "\1\0\2\12\10\0\3\12\2\0\1\12\1\177\3\12"+
    "\1\0\21\12\1\0\2\12\10\0\3\12\14\0\1\200"+
    "\34\0\5\12\1\0\5\12\1\201\13\12\1\0\2\12"+
    "\10\0\3\12\2\0\4\12\1\202\1\0\21\12\1\0"+
    "\2\12\10\0\3\12\20\0\1\203\30\0\5\12\1\0"+
    "\6\12\1\204\12\12\1\0\2\12\10\0\3\12\2\0"+
    "\3\12\1\205\1\12\1\0\21\12\1\0\2\12\10\0"+
    "\3\12\3\0\1\206\45\0\4\12\1\207\1\0\21\12"+
    "\1\0\2\12\10\0\3\12\15\0\1\210\33\0\5\12"+
    "\1\0\1\211\20\12\1\0\2\12\10\0\3\12\16\0"+
    "\1\212\32\0\5\12\1\0\3\12\1\213\15\12\1\0"+
    "\2\12\10\0\3\12\2\0\5\12\1\0\13\12\1\214"+
    "\5\12\1\0\2\12\10\0\3\12\2\0\5\12\1\0"+
    "\12\12\1\215\6\12\1\0\2\12\10\0\3\12\2\0"+
    "\5\12\1\0\5\12\1\216\13\12\1\0\2\12\10\0"+
    "\3\12\2\0\5\12\1\0\10\12\1\217\10\12\1\0"+
    "\2\12\10\0\3\12\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4485];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\16\1\1\11\11\1\1\11\14\1\1\0"+
    "\7\11\63\1\1\0\3\1\2\0\3\1\3\0\3\1"+
    "\3\0\2\1\1\11\2\0\2\1\1\11\1\0\2\1"+
    "\1\0\2\1\1\0\2\1\1\0\1\1\1\0\1\1"+
    "\1\11\5\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[143];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader
             (in, java.nio.charset.Charset.forName("UTF-8")));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 138) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

    // numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1;
          }
        case 32: break;
        case 2: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;
          }
        case 33: break;
        case 3: 
          { /*do nothing*/
          }
        case 34: break;
        case 4: 
          { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
		return Parser.INTEGER;
          }
        case 35: break;
        case 5: 
          { return (int) yycharat(0);
          }
        case 36: break;
        case 6: 
          { System.err.println("Sorry, backspace doesn't work");
          }
        case 37: break;
        case 7: 
          { return Parser.IF;
          }
        case 38: break;
        case 8: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.STRING;
          }
        case 39: break;
        case 9: 
          { return Parser.OP_EQ;
          }
        case 40: break;
        case 10: 
          { return Parser.OP_GE;
          }
        case 41: break;
        case 11: 
          { return Parser.OP_LE;
          }
        case 42: break;
        case 12: 
          { return Parser.OP_NE;
          }
        case 43: break;
        case 13: 
          { return Parser.OP_LOR;
          }
        case 44: break;
        case 14: 
          { return Parser.OP_LAND;
          }
        case 45: break;
        case 15: 
          { return Parser.DECLR_INT;
          }
        case 46: break;
        case 16: 
          { return Parser.ELSE;
          }
        case 47: break;
        case 17: 
          { return Parser.TRUE;
          }
        case 48: break;
        case 18: 
          { return Parser.DECLR_BOOL;
          }
        case 49: break;
        case 19: 
          { return Parser.FALSE;
          }
        case 50: break;
        case 20: 
          { return Parser.SKILL;
          }
        case 51: break;
        case 21: 
          { return Parser.WHILE;
          }
        case 52: break;
        case 22: 
          { return Parser.DEALER;
          }
        case 53: break;
        case 23: 
          { return Parser.METHOD;
          }
        case 54: break;
        case 24: 
          { return Parser.PLAYER;
          }
        case 55: break;
        case 25: 
          { return Parser.DECLR_STR;
          }
        case 56: break;
        case 26: 
          { return Parser.GAME_NM;
          }
        case 57: break;
        case 27: 
          { return Parser.GAME_DF;
          }
        case 58: break;
        case 28: 
          { return Parser.CARD_DF;
          }
        case 59: break;
        case 29: 
          { return Parser.PLAYER_C;
          }
        case 60: break;
        case 30: 
          { return Parser.CHARACTER_DF;
          }
        case 61: break;
        case 31: 
          { return Parser.GAME_PORT;
          }
        case 62: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
