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
    "\10\0\1\52\1\40\1\40\25\0\1\6\1\45\1\32\1\51\1\0"+
    "\1\41\1\47\1\0\1\41\1\41\1\41\1\41\1\41\1\30\1\41"+
    "\1\41\12\31\1\41\1\41\1\44\1\42\1\43\2\0\2\37\1\16"+
    "\14\37\1\26\2\37\1\35\7\37\1\41\1\0\1\41\1\41\1\12"+
    "\1\0\1\10\1\36\1\13\1\1\1\2\1\3\1\7\1\17\1\4"+
    "\1\37\1\33\1\24\1\11\1\5\1\22\1\23\1\37\1\14\1\15"+
    "\1\20\1\21\1\34\1\50\1\27\1\25\1\37\1\41\1\46\1\41"+
    "\1\41\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\5\2\1\3\11\2\1\4\1\5\1\1"+
    "\3\2\5\4\2\1\1\2\1\1\1\6\5\2\1\7"+
    "\1\10\1\11\14\2\1\0\1\12\4\2\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\2\1\21\7\2\1\22"+
    "\24\2\1\23\2\2\1\24\10\2\1\25\1\26\1\27"+
    "\1\2\1\30\2\2\1\31\3\2\1\32\1\33\7\2"+
    "\1\34\1\35\2\2\1\36\1\37\1\2\1\40\3\2"+
    "\1\41\2\2\1\42\2\2\1\43\1\44\1\0\1\45"+
    "\7\2\2\0\3\2\1\46\3\2\3\0\1\2\1\47"+
    "\1\2\1\50\2\2\3\0\4\2\1\51\2\0\2\2"+
    "\1\52\1\2\1\53\1\0\2\2\1\54\1\0\1\2"+
    "\1\55\1\0\1\56\2\0\1\57";

  private static int [] zzUnpackAction() {
    int [] result = new int[200];
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
    "\0\0\0\53\0\126\0\201\0\254\0\327\0\u0102\0\u012d"+
    "\0\u0158\0\u0183\0\u01ae\0\u01d9\0\u0204\0\u022f\0\u025a\0\u0285"+
    "\0\u02b0\0\u02db\0\u02db\0\u0306\0\u0331\0\u035c\0\u0387\0\53"+
    "\0\u03b2\0\u03dd\0\u0408\0\u0433\0\u045e\0\u0489\0\u04b4\0\u04df"+
    "\0\53\0\u050a\0\u0535\0\u0560\0\u058b\0\u05b6\0\u0183\0\u05e1"+
    "\0\u0183\0\u060c\0\u0637\0\u0662\0\u068d\0\u06b8\0\u06e3\0\u070e"+
    "\0\u0739\0\u0764\0\u078f\0\u07ba\0\u07e5\0\u0306\0\53\0\u0810"+
    "\0\u083b\0\u0866\0\u0891\0\53\0\53\0\53\0\53\0\53"+
    "\0\53\0\u08bc\0\53\0\u08e7\0\u0912\0\u093d\0\u0968\0\u0993"+
    "\0\u09be\0\u09e9\0\u0183\0\u0a14\0\u0a3f\0\u0a6a\0\u0a95\0\u0ac0"+
    "\0\u0aeb\0\u0b16\0\u0b41\0\u0b6c\0\u0b97\0\u0bc2\0\u0bed\0\u0c18"+
    "\0\u0c43\0\u0c6e\0\u0c99\0\u0cc4\0\u0cef\0\u0d1a\0\u0d45\0\u0183"+
    "\0\u0d70\0\u0d9b\0\u0183\0\u0dc6\0\u0df1\0\u0e1c\0\u0e47\0\u0e72"+
    "\0\u0e9d\0\u0ec8\0\u0ef3\0\u0183\0\u0183\0\u0183\0\u0f1e\0\u0183"+
    "\0\u0f49\0\u0f74\0\u0183\0\u0f9f\0\u0fca\0\u0ff5\0\u0183\0\u0183"+
    "\0\u1020\0\u104b\0\u1076\0\u10a1\0\u10cc\0\u10f7\0\u1122\0\u114d"+
    "\0\u0183\0\u1178\0\u11a3\0\u0183\0\u0183\0\u11ce\0\u0183\0\u11f9"+
    "\0\u1224\0\u124f\0\u0183\0\u127a\0\u12a5\0\u0183\0\u12d0\0\u12fb"+
    "\0\u0183\0\u0183\0\u1326\0\u0183\0\u1351\0\u137c\0\u13a7\0\u13d2"+
    "\0\u13fd\0\u1428\0\u1453\0\u147e\0\u14a9\0\u14d4\0\u14ff\0\u152a"+
    "\0\u0183\0\u1555\0\u1580\0\u15ab\0\u15d6\0\u1601\0\u162c\0\u1657"+
    "\0\u0183\0\u1682\0\u0183\0\u16ad\0\u16d8\0\u1703\0\u172e\0\u1759"+
    "\0\u1784\0\u17af\0\u17da\0\u1805\0\53\0\u1830\0\u185b\0\u1886"+
    "\0\u18b1\0\u0183\0\u18dc\0\53\0\u1907\0\u1932\0\u195d\0\u0183"+
    "\0\u1988\0\u19b3\0\u0183\0\u19de\0\u0183\0\u1a09\0\u1a34\0\53";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[200];
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
    "\1\12\1\13\1\2\1\14\1\15\1\16\1\17\1\12"+
    "\1\20\5\12\1\21\1\12\1\22\1\23\1\24\1\12"+
    "\1\25\1\26\1\27\1\12\1\10\1\30\1\31\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\1\40\1\41\54\0"+
    "\1\12\1\42\3\12\1\0\16\12\1\43\2\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\15\12\1\44\3\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\1\12\1\45\11\12\1\46"+
    "\5\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\2\12\1\47\1\12\1\50\1\0\6\12\1\51\12\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\5\12"+
    "\1\0\12\12\1\52\6\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\10\0\1\10\31\0\1\10\13\0\5\12"+
    "\1\0\1\12\1\53\17\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\1\12\1\54\3\12"+
    "\1\0\1\12\1\55\17\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\13\12\1\56\5\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\1\12"+
    "\1\57\3\12\1\0\13\12\1\60\5\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\21\12"+
    "\1\0\1\12\1\0\1\61\4\12\10\0\1\12\3\0"+
    "\5\12\1\0\1\12\1\62\17\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\5\12\1\63"+
    "\4\12\1\64\6\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\15\12\1\65\3\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\33\0\1\23\21\0"+
    "\32\66\1\67\20\66\1\0\5\12\1\0\13\12\1\70"+
    "\5\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\5\12\1\0\11\12\1\71\7\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\5\12\1\72"+
    "\5\12\1\73\5\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\44\0\1\74\52\0\1\75\52\0\1\76\52\0"+
    "\1\77\56\0\1\100\53\0\1\101\4\0\5\12\1\0"+
    "\10\12\1\102\10\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\53\0\1\103\2\0\2\12\1\104\2\12\1\0"+
    "\1\12\1\105\17\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\3\12\1\106\1\12\1\0\21\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\6\12\1\107\12\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\15\12\1\110\3\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\5\12\1\111\13\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\3\12\1\112\1\12\1\0\11\12\1\113"+
    "\7\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\5\12\1\0\2\12\1\114\16\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\2\12\1\115"+
    "\16\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\5\12\1\0\11\12\1\116\7\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\20\12\1\117"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\4\12"+
    "\1\120\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\11\12\1\121\7\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\12\12\1\122\6\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\3\12\1\123\1\12\1\0\21\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\5\12\1\124\13\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\12\12\1\125\6\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\5\12\1\126\13\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\1\12\1\127\17\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\3\12\1\130"+
    "\1\12\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\5\12\1\131\13\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\1\12\1\132"+
    "\3\12\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\13\12\1\133\5\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\3\12\1\134"+
    "\1\12\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\3\12\1\135\1\12\1\0\21\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\15\12\1\136\3\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\4\12\1\137\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\1\12\1\140\3\12"+
    "\1\0\21\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\5\12\1\0\6\12\1\141\12\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\1\12\1\142\3\12"+
    "\1\0\21\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\5\12\1\0\11\12\1\143\7\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\3\12"+
    "\1\144\15\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\1\12\1\145\3\12\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\10\12"+
    "\1\146\10\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\3\12\1\147\1\12\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\11\12"+
    "\1\150\7\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\5\12\1\0\12\12\1\151\6\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\4\12\1\152\1\0"+
    "\21\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\5\12\1\0\15\12\1\153\3\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\1\154\4\12\1\0\21\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\1\12"+
    "\1\155\3\12\1\0\21\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\4\12\1\156\1\0\21\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\16\12\1\157\2\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\1\160\4\12\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\3\12\1\161\1\12"+
    "\1\0\21\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\5\12\1\0\1\12\1\162\17\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\15\12"+
    "\1\163\3\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\5\12\1\0\15\12\1\164\3\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\4\12\1\165\1\0"+
    "\21\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\1\12\1\166\3\12\1\0\21\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\1\167\20\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\1\12"+
    "\1\170\3\12\1\0\21\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\1\12\1\171\17\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\5\12"+
    "\1\0\13\12\1\172\5\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\3\12\1\173\15\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\5\12"+
    "\1\0\13\12\1\174\5\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\2\12\1\175\16\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\3\12"+
    "\1\176\1\12\1\0\21\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\5\12\1\177\13\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\1\200"+
    "\4\12\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\15\12\1\201\3\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\1\12\1\202"+
    "\3\12\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\4\12\1\203\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\21\12"+
    "\1\0\1\12\1\0\1\204\4\12\10\0\1\12\3\0"+
    "\1\12\1\205\3\12\1\0\21\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\1\12\1\206\3\12\1\0"+
    "\21\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\5\12\1\0\5\12\1\207\13\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\4\12\1\210"+
    "\14\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\2\12\1\211\2\12\1\0\21\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\4\12\1\212\1\0\21\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\1\213"+
    "\4\12\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\12\12\1\214\6\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\4\12\1\215"+
    "\1\0\21\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\4\12\1\216\1\0\21\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\3\12\1\217"+
    "\15\12\1\0\1\12\1\0\2\12\1\220\2\12\10\0"+
    "\1\12\3\0\5\12\1\0\5\12\1\221\13\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\1\222\20\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\5\12\1\223\21\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\10\12\1\224\10\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\5\12"+
    "\1\0\3\12\1\225\15\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\1\12\1\226\17\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\5\12"+
    "\1\0\2\12\1\227\16\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\12\12\1\230\6\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\1\12"+
    "\1\231\3\12\1\0\21\12\1\0\1\12\1\0\3\12"+
    "\1\232\1\12\10\0\1\12\3\0\5\12\1\0\12\12"+
    "\1\233\6\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\11\0\1\234\3\0\1\235\40\0\5\12\1\0\14\12"+
    "\1\236\4\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\5\12\1\0\2\12\1\237\16\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\3\12"+
    "\1\240\15\12\1\0\1\12\1\0\5\12\10\0\1\12"+
    "\3\0\1\12\1\241\3\12\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\4\12\1\242\1\0"+
    "\21\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\1\12\1\243\3\12\1\0\21\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\2\12\1\244"+
    "\16\12\1\0\1\12\1\0\5\12\10\0\1\12\12\0"+
    "\1\245\52\0\1\246\6\0\1\247\34\0\5\12\1\0"+
    "\15\12\1\250\3\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\1\12\1\251\3\12\1\0\21\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\5\12\1\252\13\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\1\253\4\12\1\0\21\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\3\0\5\12\1\0\1\254"+
    "\20\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\5\12\1\0\2\12\1\255\16\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\13\0\1\256\55\0\1\257\46\0"+
    "\1\260\43\0\5\12\1\0\1\12\1\261\17\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\13\12\1\262\5\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\3\12\1\263\1\12\1\0\21\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\3\0\5\12\1\0"+
    "\1\12\1\264\17\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\4\0\1\265\51\0\1\266\65\0\1\267\37\0"+
    "\5\12\1\0\16\12\1\270\2\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\5\12\1\0\12\12\1\271"+
    "\6\12\1\0\1\12\1\0\5\12\10\0\1\12\3\0"+
    "\4\12\1\272\1\0\21\12\1\0\1\12\1\0\5\12"+
    "\10\0\1\12\3\0\5\12\1\0\5\12\1\273\13\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\17\0\1\274"+
    "\45\0\1\275\43\0\1\12\1\276\3\12\1\0\21\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\3\0\4\12"+
    "\1\277\1\0\21\12\1\0\1\12\1\0\5\12\10\0"+
    "\1\12\3\0\5\12\1\0\16\12\1\300\2\12\1\0"+
    "\1\12\1\0\5\12\10\0\1\12\15\0\1\301\40\0"+
    "\5\12\1\0\5\12\1\302\13\12\1\0\1\12\1\0"+
    "\5\12\10\0\1\12\3\0\1\303\4\12\1\0\21\12"+
    "\1\0\1\12\1\0\5\12\10\0\1\12\22\0\1\304"+
    "\33\0\5\12\1\0\6\12\1\305\12\12\1\0\1\12"+
    "\1\0\5\12\10\0\1\12\4\0\1\306\64\0\1\307"+
    "\53\0\1\310\35\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6751];
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
    "\1\0\1\11\25\1\1\11\10\1\1\11\24\1\1\0"+
    "\1\11\4\1\6\11\1\1\1\11\117\1\1\0\10\1"+
    "\2\0\7\1\3\0\6\1\3\0\4\1\1\11\2\0"+
    "\4\1\1\11\1\0\3\1\1\0\2\1\1\0\1\1"+
    "\2\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[200];
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
    while (i < 144) {
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
        case 48: break;
        case 2: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;
          }
        case 49: break;
        case 3: 
          { /*do nothing*/
          }
        case 50: break;
        case 4: 
          { return (int) yycharat(0);
          }
        case 51: break;
        case 5: 
          { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
		return Parser.INTEGER;
          }
        case 52: break;
        case 6: 
          { System.err.println("Sorry, backspace doesn't work");
          }
        case 53: break;
        case 7: 
          { return Parser.IF;
          }
        case 54: break;
        case 8: 
          { return Parser.IN;
          }
        case 55: break;
        case 9: 
          { return Parser.IS;
          }
        case 56: break;
        case 10: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.STRING;
          }
        case 57: break;
        case 11: 
          { return Parser.OP_EQ;
          }
        case 58: break;
        case 12: 
          { return Parser.OP_GE;
          }
        case 59: break;
        case 13: 
          { return Parser.OP_LE;
          }
        case 60: break;
        case 14: 
          { return Parser.OP_NE;
          }
        case 61: break;
        case 15: 
          { return Parser.OP_LOR;
          }
        case 62: break;
        case 16: 
          { return Parser.OP_LAND;
          }
        case 63: break;
        case 17: 
          { return Parser.DEBUG;
          }
        case 64: break;
        case 18: 
          { return Parser.DECLR_INT;
          }
        case 65: break;
        case 19: 
          { return Parser.ELSE;
          }
        case 66: break;
        case 20: 
          { return Parser.INIT;
          }
        case 67: break;
        case 21: 
          { return Parser.CARD;
          }
        case 68: break;
        case 22: 
          { return Parser.TRUE;
          }
        case 69: break;
        case 23: 
          { return Parser.TURN;
          }
        case 70: break;
        case 24: 
          { return Parser.VOID;
          }
        case 71: break;
        case 25: 
          { return Parser.DECLR_BOOL;
          }
        case 72: break;
        case 26: 
          { return Parser.DYING;
          }
        case 73: break;
        case 27: 
          { return Parser.FALSE;
          }
        case 74: break;
        case 28: 
          { return Parser.ROUND;
          }
        case 75: break;
        case 29: 
          { return Parser.SKILL;
          }
        case 76: break;
        case 30: 
          { return Parser.BREAK;
          }
        case 77: break;
        case 31: 
          { return Parser.WHILE;
          }
        case 78: break;
        case 32: 
          { return Parser.DEALER;
          }
        case 79: break;
        case 33: 
          { return Parser.METHOD;
          }
        case 80: break;
        case 34: 
          { return Parser.RETURN;
          }
        case 81: break;
        case 35: 
          { return Parser.PLAYER;
          }
        case 82: break;
        case 36: 
          { return Parser.DECLR_STR;
          }
        case 83: break;
        case 37: 
          { return Parser.FOREACH;
          }
        case 84: break;
        case 38: 
          { return Parser.CONTINUE;
          }
        case 85: break;
        case 39: 
          { return Parser.GAME_NM;
          }
        case 86: break;
        case 40: 
          { return Parser.ROUND_END;
          }
        case 87: break;
        case 41: 
          { return Parser.GAME_DF;
          }
        case 88: break;
        case 42: 
          { return Parser.ROUND_BEGIN;
          }
        case 89: break;
        case 43: 
          { return Parser.CARD_DF;
          }
        case 90: break;
        case 44: 
          { return Parser.ROUNDSUMMARY;
          }
        case 91: break;
        case 45: 
          { return Parser.MAX_ROUND;
          }
        case 92: break;
        case 46: 
          { return Parser.PLAYER_C;
          }
        case 93: break;
        case 47: 
          { return Parser.CHARACTER_DF;
          }
        case 94: break;
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
