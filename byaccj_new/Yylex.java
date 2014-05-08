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
    "\10\0\1\45\1\33\1\33\25\0\1\6\1\40\1\31\2\0\1\34"+
    "\1\42\1\0\1\34\1\34\1\34\1\34\1\34\1\34\1\34\1\34"+
    "\12\30\1\34\1\34\1\37\1\35\1\36\2\0\2\26\1\26\14\26"+
    "\1\23\2\26\1\44\7\26\1\34\1\0\1\34\1\34\1\21\1\0"+
    "\1\10\1\22\1\12\1\1\1\2\1\3\1\7\1\15\1\4\1\26"+
    "\1\32\1\24\1\11\1\5\1\17\2\26\1\13\1\14\1\16\1\20"+
    "\1\27\1\43\1\26\1\25\1\26\1\34\1\41\1\34\1\34\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\5\2\1\3\7\2\1\4\1\1\5\5"+
    "\2\1\2\2\1\6\4\2\1\7\11\2\1\0\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\7\2\1\17"+
    "\14\2\1\20\1\2\1\21\3\2\1\22\1\23\1\24"+
    "\1\2\1\25\4\2\1\26\2\2\1\27\1\2\1\30"+
    "\3\2\1\31\1\2\1\32\1\33\1\0\3\2\2\0"+
    "\3\2\3\0\1\2\1\34\1\2\3\0\2\2\1\35"+
    "\2\0\1\2\1\36\1\37\1\0\1\2\1\0\1\2"+
    "\1\0\1\2\1\0\1\40\1\0\1\41";

  private static int [] zzUnpackAction() {
    int [] result = new int[133];
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
    "\0\0\0\46\0\114\0\162\0\230\0\276\0\344\0\u010a"+
    "\0\u0130\0\u0156\0\u017c\0\u01a2\0\u01c8\0\u01ee\0\u0214\0\u023a"+
    "\0\u0260\0\46\0\u0286\0\u02ac\0\u02d2\0\u02f8\0\u031e\0\u0344"+
    "\0\u036a\0\u0390\0\46\0\u03b6\0\u03dc\0\u0402\0\u0428\0\344"+
    "\0\u044e\0\u0474\0\u049a\0\u04c0\0\u04e6\0\u050c\0\u0532\0\u0558"+
    "\0\u057e\0\u0260\0\46\0\46\0\46\0\46\0\46\0\46"+
    "\0\46\0\u05a4\0\u05ca\0\u05f0\0\u0616\0\u063c\0\u0662\0\u0688"+
    "\0\344\0\u06ae\0\u06d4\0\u06fa\0\u0720\0\u0746\0\u076c\0\u0792"+
    "\0\u07b8\0\u07de\0\u0804\0\u082a\0\u0850\0\344\0\u0876\0\344"+
    "\0\u089c\0\u08c2\0\u08e8\0\344\0\344\0\344\0\u090e\0\344"+
    "\0\u0934\0\u095a\0\u0980\0\u09a6\0\344\0\u09cc\0\u09f2\0\344"+
    "\0\u0a18\0\344\0\u0a3e\0\u0a64\0\u0a8a\0\344\0\u0ab0\0\344"+
    "\0\344\0\u0ad6\0\u0afc\0\u0b22\0\u0b48\0\u0b6e\0\u0b94\0\u0bba"+
    "\0\u0be0\0\u0c06\0\u0c2c\0\u0c52\0\u0c78\0\u0c9e\0\344\0\u0cc4"+
    "\0\u0cea\0\u0d10\0\u0d36\0\u0d5c\0\u0d82\0\46\0\u0da8\0\u0dce"+
    "\0\u0df4\0\344\0\46\0\u0e1a\0\u0e40\0\u0e66\0\u0e8c\0\u0eb2"+
    "\0\u0ed8\0\u0efe\0\344\0\u0f24\0\46";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[133];
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
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\2\7"+
    "\1\11\1\7\1\12\1\13\1\7\1\14\2\7\1\2"+
    "\1\15\1\16\3\7\1\17\1\20\1\21\1\7\1\10"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\47\0\1\7\1\34\3\7\1\0\10\7"+
    "\1\35\11\7\1\0\1\7\10\0\2\7\2\0\5\7"+
    "\1\0\15\7\1\36\4\7\1\0\1\7\10\0\2\7"+
    "\2\0\5\7\1\0\1\7\1\37\20\7\1\0\1\7"+
    "\10\0\2\7\2\0\2\7\1\40\1\7\1\41\1\0"+
    "\22\7\1\0\1\7\10\0\2\7\2\0\5\7\1\0"+
    "\22\7\1\0\1\7\10\0\2\7\7\0\1\10\24\0"+
    "\1\10\13\0\1\7\1\42\3\7\1\0\22\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\10\7\1\43"+
    "\11\7\1\0\1\7\10\0\2\7\2\0\5\7\1\0"+
    "\22\7\1\0\1\44\10\0\2\7\2\0\5\7\1\0"+
    "\4\7\1\45\4\7\1\46\10\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\10\7\1\47\11\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\15\7\1\50"+
    "\4\7\1\0\1\7\10\0\2\7\2\0\5\7\1\0"+
    "\10\7\1\51\11\7\1\0\1\7\10\0\2\7\31\0"+
    "\1\20\15\0\31\52\1\53\14\52\35\0\1\54\45\0"+
    "\1\55\45\0\1\56\45\0\1\57\51\0\1\60\46\0"+
    "\1\61\4\0\5\7\1\0\6\7\1\62\13\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\7\7\1\63"+
    "\12\7\1\0\1\7\10\0\2\7\2\0\2\7\1\64"+
    "\2\7\1\0\22\7\1\0\1\7\10\0\2\7\2\0"+
    "\5\7\1\0\5\7\1\65\14\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\5\7\1\66\14\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\15\7\1\67"+
    "\4\7\1\0\1\7\10\0\2\7\2\0\3\7\1\70"+
    "\1\7\1\0\7\7\1\71\12\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\7\7\1\72\12\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\11\7\1\73"+
    "\10\7\1\0\1\7\10\0\2\7\2\0\3\7\1\74"+
    "\1\7\1\0\22\7\1\0\1\7\10\0\2\7\2\0"+
    "\5\7\1\0\11\7\1\75\10\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\4\7\1\76\15\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\10\7\1\77"+
    "\11\7\1\0\1\7\10\0\2\7\2\0\5\7\1\0"+
    "\1\7\1\100\20\7\1\0\1\7\10\0\2\7\2\0"+
    "\3\7\1\101\1\7\1\0\22\7\1\0\1\7\10\0"+
    "\2\7\2\0\3\7\1\102\1\7\1\0\22\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\4\7\1\103"+
    "\15\7\1\0\1\7\10\0\2\7\2\0\3\7\1\104"+
    "\1\7\1\0\22\7\1\0\1\7\10\0\2\7\2\0"+
    "\5\7\1\0\10\7\1\105\11\7\1\0\1\7\10\0"+
    "\2\7\2\0\1\7\1\106\3\7\1\0\22\7\1\0"+
    "\1\7\10\0\2\7\2\0\5\7\1\0\5\7\1\107"+
    "\14\7\1\0\1\7\10\0\2\7\2\0\5\7\1\0"+
    "\7\7\1\110\12\7\1\0\1\7\10\0\2\7\2\0"+
    "\5\7\1\0\6\7\1\111\13\7\1\0\1\7\10\0"+
    "\2\7\2\0\4\7\1\112\1\0\22\7\1\0\1\7"+
    "\10\0\2\7\2\0\5\7\1\0\15\7\1\113\4\7"+
    "\1\0\1\7\10\0\2\7\2\0\1\7\1\114\3\7"+
    "\1\0\22\7\1\0\1\7\10\0\2\7\2\0\4\7"+
    "\1\115\1\0\22\7\1\0\1\7\10\0\2\7\2\0"+
    "\5\7\1\0\15\7\1\116\4\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\16\7\1\117\3\7\1\0"+
    "\1\7\10\0\2\7\2\0\1\120\4\7\1\0\22\7"+
    "\1\0\1\7\10\0\2\7\2\0\5\7\1\0\15\7"+
    "\1\121\4\7\1\0\1\7\10\0\2\7\2\0\3\7"+
    "\1\122\1\7\1\0\22\7\1\0\1\7\10\0\2\7"+
    "\2\0\4\7\1\123\1\0\22\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\2\7\1\124\17\7\1\0"+
    "\1\7\10\0\2\7\2\0\1\7\1\125\3\7\1\0"+
    "\22\7\1\0\1\7\10\0\2\7\2\0\5\7\1\0"+
    "\10\7\1\126\11\7\1\0\1\7\10\0\2\7\2\0"+
    "\1\127\4\7\1\0\22\7\1\0\1\7\10\0\2\7"+
    "\2\0\5\7\1\0\15\7\1\130\4\7\1\0\1\7"+
    "\10\0\2\7\2\0\1\7\1\131\3\7\1\0\22\7"+
    "\1\0\1\7\10\0\2\7\2\0\1\7\1\132\3\7"+
    "\1\0\22\7\1\0\1\7\10\0\2\7\2\0\4\7"+
    "\1\133\1\0\22\7\1\0\1\7\10\0\2\7\2\0"+
    "\1\7\1\134\3\7\1\0\22\7\1\0\1\7\10\0"+
    "\2\7\2\0\1\7\1\135\3\7\1\0\22\7\1\0"+
    "\1\7\10\0\2\7\2\0\1\136\4\7\1\0\22\7"+
    "\1\0\1\7\10\0\2\7\2\0\5\7\1\0\12\7"+
    "\1\137\7\7\1\0\1\7\10\0\2\7\2\0\5\7"+
    "\1\0\4\7\1\140\15\7\1\0\1\7\10\0\2\7"+
    "\2\0\5\7\1\0\1\141\21\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\142\22\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\7\7\1\143\12\7\1\0"+
    "\1\7\10\0\2\7\2\0\1\7\1\144\3\7\1\0"+
    "\13\7\1\145\6\7\1\0\1\7\10\0\2\7\10\0"+
    "\1\146\2\0\1\147\34\0\5\7\1\0\6\7\1\150"+
    "\13\7\1\0\1\7\10\0\2\7\2\0\4\7\1\151"+
    "\1\0\22\7\1\0\1\7\10\0\2\7\2\0\1\7"+
    "\1\152\3\7\1\0\22\7\1\0\1\7\10\0\2\7"+
    "\11\0\1\153\45\0\1\154\4\0\1\155\31\0\3\7"+
    "\1\156\1\7\1\0\22\7\1\0\1\7\10\0\2\7"+
    "\2\0\1\157\4\7\1\0\22\7\1\0\1\7\10\0"+
    "\2\7\2\0\5\7\1\0\1\160\21\7\1\0\1\7"+
    "\10\0\2\7\12\0\1\161\47\0\1\162\42\0\1\163"+
    "\36\0\4\7\1\164\1\0\22\7\1\0\1\7\10\0"+
    "\2\7\2\0\3\7\1\165\1\7\1\0\22\7\1\0"+
    "\1\7\10\0\2\7\3\0\1\166\44\0\1\167\57\0"+
    "\1\170\33\0\5\7\1\0\1\171\21\7\1\0\1\7"+
    "\10\0\2\7\2\0\4\7\1\172\1\0\22\7\1\0"+
    "\1\7\10\0\2\7\15\0\1\173\41\0\1\174\36\0"+
    "\5\7\1\0\6\7\1\175\13\7\1\0\1\7\10\0"+
    "\2\7\13\0\1\176\34\0\1\7\1\177\3\7\1\0"+
    "\22\7\1\0\1\7\10\0\2\7\17\0\1\200\30\0"+
    "\5\7\1\0\4\7\1\201\15\7\1\0\1\7\10\0"+
    "\2\7\3\0\1\202\44\0\1\7\1\203\3\7\1\0"+
    "\22\7\1\0\1\7\10\0\2\7\14\0\1\204\46\0"+
    "\1\205\31\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3914];
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
    "\1\0\1\11\17\1\1\11\10\1\1\11\16\1\1\0"+
    "\7\11\60\1\1\0\3\1\2\0\3\1\3\0\3\1"+
    "\3\0\2\1\1\11\2\0\2\1\1\11\1\0\1\1"+
    "\1\0\1\1\1\0\1\1\1\0\1\1\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[133];
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
    while (i < 140) {
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
        case 34: break;
        case 2: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;
          }
        case 35: break;
        case 3: 
          { /*do nothing*/
          }
        case 36: break;
        case 4: 
          { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
		return Parser.INTEGER;
          }
        case 37: break;
        case 5: 
          { return (int) yycharat(0);
          }
        case 38: break;
        case 6: 
          { System.err.println("Sorry, backspace doesn't work");
          }
        case 39: break;
        case 7: 
          { return Parser.IF;
          }
        case 40: break;
        case 8: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.STRING;
          }
        case 41: break;
        case 9: 
          { return Parser.OP_EQ;
          }
        case 42: break;
        case 10: 
          { return Parser.OP_GE;
          }
        case 43: break;
        case 11: 
          { return Parser.OP_LE;
          }
        case 44: break;
        case 12: 
          { return Parser.OP_NE;
          }
        case 45: break;
        case 13: 
          { return Parser.OP_LOR;
          }
        case 46: break;
        case 14: 
          { return Parser.OP_LAND;
          }
        case 47: break;
        case 15: 
          { return Parser.DECLR_INT;
          }
        case 48: break;
        case 16: 
          { return Parser.ELSE;
          }
        case 49: break;
        case 17: 
          { return Parser.INIT;
          }
        case 50: break;
        case 18: 
          { return Parser.TRUE;
          }
        case 51: break;
        case 19: 
          { return Parser.TURN;
          }
        case 52: break;
        case 20: 
          { return Parser.DECLR_BOOL;
          }
        case 53: break;
        case 21: 
          { return Parser.VOID;
          }
        case 54: break;
        case 22: 
          { return Parser.FALSE;
          }
        case 55: break;
        case 23: 
          { return Parser.SKILL;
          }
        case 56: break;
        case 24: 
          { return Parser.WHILE;
          }
        case 57: break;
        case 25: 
          { return Parser.METHOD;
          }
        case 58: break;
        case 26: 
          { return Parser.PLAYER;
          }
        case 59: break;
        case 27: 
          { return Parser.DECLR_STR;
          }
        case 60: break;
        case 28: 
          { return Parser.ROUND_END;
          }
        case 61: break;
        case 29: 
          { return Parser.GAME_DF;
          }
        case 62: break;
        case 30: 
          { return Parser.ROUND_BEGIN;
          }
        case 63: break;
        case 31: 
          { return Parser.CARD_DF;
          }
        case 64: break;
        case 32: 
          { yyparser.yylval = new ParserVal(yytext()); ;return Parser.STATLIST;
          }
        case 65: break;
        case 33: 
          { return Parser.CHARACTER_DF;
          }
        case 66: break;
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