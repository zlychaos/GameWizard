//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 15 "GameWizard.y"
  import java.io.*;
  import java.util.*;
  import java.util.ArrayList;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short GAME_DF=257;
public final static short CARD_DF=258;
public final static short CHARACTER_DF=259;
public final static short GAME_NM=260;
public final static short PLAYER_C=261;
public final static short PLAYER=262;
public final static short SKILL=263;
public final static short DEALER=264;
public final static short METHOD=265;
public final static short MAX_ROUND=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short WHILE=269;
public final static short TRUE=270;
public final static short FALSE=271;
public final static short OP_EQ=272;
public final static short OP_LE=273;
public final static short OP_GE=274;
public final static short OP_NE=275;
public final static short OP_LOR=276;
public final static short OP_LAND=277;
public final static short INIT=278;
public final static short ROUND_END=279;
public final static short ROUND_BEGIN=280;
public final static short ROUND=281;
public final static short TURN=282;
public final static short DYING=283;
public final static short DECLR_INT=284;
public final static short DECLR_STR=285;
public final static short DECLR_BOOL=286;
public final static short VOID=287;
public final static short INTEGER=288;
public final static short STRING=289;
public final static short ID=290;
public final static short CARD=291;
public final static short FOREACH=292;
public final static short ROUNDSUMMARY=293;
public final static short IN=294;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
   49,   52,    0,    1,    2,   50,   53,   53,   55,   56,
   54,   51,   57,   57,   59,   58,    3,    3,    3,   38,
   38,   39,   39,   60,   40,   61,   41,   42,   62,   43,
   63,   45,   64,   44,   65,   46,   47,   47,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
   48,   48,   37,    7,    6,    6,   24,   26,   25,   25,
   27,   36,   36,   35,   35,   34,   34,   33,   33,   32,
   32,   28,   31,   31,   29,   29,   30,   30,   30,   23,
   23,   23,   18,   18,   16,   16,   19,   19,   20,   20,
   20,   20,   20,   20,   22,   21,   21,    8,   15,   15,
   17,   17,   13,   13,   13,   13,   14,   14,   14,   12,
   12,   12,   12,   12,   11,   11,   11,   10,   10,    9,
    9,    5,    5,
};
final static short yylen[] = {                            2,
    0,    0,    8,    4,   12,    4,    2,    1,    0,    0,
   15,    4,    2,    1,    0,    6,    5,    5,    0,    5,
    5,    2,    1,    0,   12,    0,    5,    6,    0,    5,
    0,    5,    0,    5,    0,    5,    1,    1,    1,    2,
    1,    2,    1,    2,    1,    2,    1,    2,    1,    2,
    8,    8,    1,    5,    5,    7,    1,    2,    1,    1,
    2,    1,    3,    1,    3,    1,    4,    1,    3,    1,
    3,    1,    3,    2,    1,    4,    1,    1,    1,    1,
    1,    1,    1,    3,    1,    1,    3,    1,    1,    1,
    1,    1,    1,    1,    3,    4,    4,    1,    1,    2,
    1,    1,    1,    3,    3,    3,    1,    3,    3,    1,
    3,    3,    3,    3,    1,    3,    3,    1,    3,    1,
    3,    1,    3,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    2,    0,    4,    0,    0,    0,    0,    0,    0,
    0,    6,    7,    0,    0,   14,    0,    0,    0,    9,
   15,   12,   13,   26,    0,    0,    0,    0,    0,    0,
    0,    0,    3,    0,    0,    0,    0,   79,    0,    0,
   91,   92,   81,   82,   80,   38,   89,   90,   83,   78,
    0,    0,   53,    0,  101,  102,    0,   47,   39,   43,
    0,    0,    0,    0,    0,    0,    0,   98,   99,    0,
    0,    0,   88,   93,   94,   77,   41,    0,   57,   59,
   60,    0,    0,   72,   45,    0,   49,    0,    0,   35,
    0,    0,    0,    0,    0,    0,    0,    0,   74,    0,
    0,   48,   40,   44,   42,   46,   50,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  100,    0,    0,    0,    0,   58,   66,    0,   62,
    0,    0,   27,   29,    0,    0,    0,    0,    0,    0,
    0,    0,   16,    0,    0,    0,   73,   87,  123,  103,
    0,    0,    0,    0,    0,    0,    0,    0,  104,  105,
  106,    0,    0,    0,   84,    0,   95,    0,    0,    0,
    0,    0,   31,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   96,   97,    0,    0,   68,   65,
   63,   76,    0,    0,   33,   28,   36,    0,   17,   18,
    0,    0,    0,    0,    0,    0,   54,    0,   67,    0,
   70,   30,    0,    0,    0,    0,   21,    0,   20,   22,
    0,    0,    0,   69,    0,   32,    0,    5,   10,    0,
   56,    0,    0,   71,   34,    0,    0,   52,   51,    0,
    0,    0,    0,   11,    0,   24,    0,    0,    0,   25,
};
final static short yydgoto[] = {                          1,
    4,    9,   46,   67,   68,   69,   70,   71,   72,   73,
   74,   75,   76,   77,   78,   79,   80,   81,   82,   83,
   84,   85,   86,   87,   88,   89,   90,   91,   92,   93,
   94,  220,  200,  139,  140,  141,   95,  105,  214,  215,
   28,   36,   99,  185,  146,   43,   96,   97,    2,    7,
   12,   19,   16,   17,   38,  246,   25,   26,   39,  257,
   40,  182,  204,  224,  147,
};
final static short yysindex[] = {                         0,
    0, -224,  -85, -187, -184,   16, -150,   52,  -11, -172,
   29,    0, -168,    0, -164,   42, -172, -154, -141,   79,
   18,    0,    0,   19,  -91,    0,   20, -142, -116,    0,
    0,    0,    0,    0,   23, -134,   95, -133, -133,  974,
 -124,   35,    0, -129,  103, -101,  -95,    0,  130,  131,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  132, 1009,    0,   -8,    0,    0, 1149,    0,    0,    0,
  112, -102,  -99, -251,  -59,   -6,  -26,    0,    0,   -8,
  -24,  -23,    0,    0,    0,    0,    0,  117,    0,    0,
    0, -111,   89,    0,    0,   56,    0,   61,  -94,    0,
  133, -230,  149,  135,   65,   -8,   -8, -162,    0, 1044,
  150,    0,    0,    0,    0,    0,    0,   -8,   -8,   -8,
   -8,   -8,   -8,   -8,   -8,   -8,   -8,   -8,   -8,   -8,
   -8,    0,   -8,  -96,   -8,  -87,    0,    0,  -43,    0,
  152,  -82,    0,    0,   84,  -71,  974,  -57,  151,  154,
  -51,  140,    0,  204,  205,  -41,    0,    0,    0,    0,
  -99, -251,  -59,  -59,  -26,  -26,  -26,  -26,    0,    0,
    0,   -6,   -6,  155,    0,  157,    0,  -28,  172, -111,
  161,  974,    0,  124,  136,  141,  212, -133, -133,   21,
 -237,  160,  160,  -10,    0,    0,  196,  172,    0,    0,
    0,    0,  171,  974,    0,    0,    0,    9,    0,    0,
  259,  208,  179,  210,   15,   47,    0, -218,    0,  -38,
    0,    0,  191,  974,  258,  195,    0,   54,    0,    0,
  160,  279,  283,    0,  172,    0,  206,    0,    0,  290,
    0,  160,  160,    0,    0,  974,   70,    0,    0,  211,
   69,  213,  294,    0,  214,    0,  974,  215,  225,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  260,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   86,   91,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  227,    0,    0,    0,
   37, 1079,  895,  825,  536,   72,  361,    0,    0,    0,
  -33,    2,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   75,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   -7,    0,
  296,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  930,  860,  755,  790,  396,  431,  466,  501,    0,    0,
    0,  107,  142,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -157, -157,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  266, 1114,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -34,  304, 1343,  -54,  -37,   97,    0,  248,
  250,  -36,  -42,  -22,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -27,    0,    0,    0,    0,  263,    0,
 -177,    0, -178,    0,  188,    0,  -16,    0,  158,    0,
    0,    0,    0,    0,    0,    0, -113,  -12,    0,    0,
    0,    0,  355,    0,    0,    0,    0,  350,    0,    0,
    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1578;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         85,
  125,   32,  126,   85,   47,  235,   85,   85,   85,   85,
   85,   85,  113,   85,  216,  217,  131,  179,  130,  221,
  121,  134,  136,  122,   66,   85,   85,   85,   85,  114,
  129,   64,    3,  186,   86,  127,   64,    5,   86,  115,
  128,   86,   86,   86,   86,   86,   86,  178,   86,  212,
  116,   64,  213,  241,  117,  113,  244,  149,  150,   85,
   86,   86,   86,   86,  248,  249,  133,  135,  203,  103,
    6,  232,  114,  103,  233,    8,  103,  103,  103,  103,
  103,  103,  115,  103,  163,  164,  234,  172,  173,   85,
  223,   85,   85,  116,   86,  103,  103,  117,  103,   48,
  165,  166,  167,  168,  107,   19,   10,   19,   11,   13,
  237,  107,  107,   14,  107,  107,  107,   65,   15,   18,
   20,   53,   54,   55,   86,   21,   86,   86,   60,  103,
  107,  107,  250,  107,   22,   24,   27,   29,   35,  109,
   30,   31,   34,  258,   37,   41,  109,  109,   42,  109,
  109,  109,   44,  209,  210,   98,   45,  100,  101,  103,
  102,  103,  103,  103,  107,  109,  109,  104,  109,  106,
  107,  108,  118,  119,  108,  137,  132,  120,  138,  142,
  143,  108,  108,  144,  108,  108,  108,  145,  151,  153,
  158,  148,  152,  175,  107,  180,  107,  107,   24,  109,
  108,  108,  177,  108,   66,  181,  183,  184,  187,  188,
  190,   64,  189,  123,  124,  160,  160,  160,  160,  160,
  160,  160,  160,  169,  170,  171,  160,  160,   85,  109,
  191,  109,  109,   85,  108,   85,   85,   85,   85,   85,
   85,   85,   85,   85,  192,  193,  205,  195,  194,  196,
   85,   85,   85,  202,   85,   85,   85,   85,   85,  197,
  206,   51,   52,   86,  108,  207,  108,  108,   86,  208,
   86,   86,   86,   86,   86,   86,   86,   86,   86,   57,
   58,   59,   62,  218,  211,   86,   86,   86,  219,   86,
   86,   86,   86,   86,  198,  222,  225,   65,  103,  226,
  227,  228,  229,  103,  213,  103,  103,  103,  103,  103,
  103,  103,  103,  103,  231,  236,  238,  239,  240,  242,
  103,  103,  103,  243,  103,  103,  103,  103,  103,  247,
  245,  251,  253,  107,  255,  252,  256,  254,  107,  259,
  107,  107,  107,  107,  107,  107,  107,  107,  107,  260,
   19,   37,    8,   19,   61,  107,  107,  107,   23,  107,
  107,  107,  107,  107,   75,  110,  161,  201,  109,  162,
  156,   23,  230,  109,   33,  109,  109,  109,  109,  109,
  109,  109,  109,  109,    0,    0,    0,    0,    0,    0,
  109,  109,  109,  110,  109,  109,  109,  109,  109,    0,
  110,  110,    0,  108,  110,    0,    0,    0,  108,    0,
  108,  108,  108,  108,  108,  108,  108,  108,  108,  110,
  110,    0,  110,    0,    0,  108,  108,  108,  113,  108,
  108,  108,  108,  108,    0,  113,  113,    0,    0,  113,
    0,   51,   52,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  110,  113,  113,    0,  113,    0,   57,
   58,   59,    0,  114,    0,    0,    0,    0,    0,    0,
  114,  114,    0,    0,  114,    0,    0,    0,    0,    0,
    0,    0,    0,  110,    0,  110,  110,    0,  113,  114,
  114,    0,  114,    0,    0,    0,    0,    0,  111,    0,
    0,    0,    0,    0,    0,  111,  111,    0,    0,  111,
    0,    0,    0,    0,    0,    0,    0,    0,  113,    0,
  113,  113,    0,  114,  111,  111,    0,  111,    0,    0,
    0,    0,    0,  112,    0,    0,    0,    0,    0,    0,
  112,  112,    0,    0,  112,    0,    0,    0,    0,    0,
    0,    0,    0,  114,    0,  114,  114,    0,  111,  112,
  112,    0,  112,    0,    0,    0,    0,    0,  115,    0,
    0,    0,    0,    0,    0,  115,  115,    0,    0,  115,
    0,    0,    0,    0,    0,    0,    0,    0,  111,    0,
  111,  111,    0,  112,  115,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  110,  112,    0,  112,  112,  110,  115,  110,
  110,  110,  110,  110,  110,  110,  110,  110,    0,    0,
    0,    0,    0,    0,  110,  110,  110,    0,  110,  110,
  110,  110,  110,    0,    0,    0,    0,  113,  115,    0,
  115,  115,  113,    0,  113,  113,  113,  113,  113,  113,
  113,  113,  113,    0,    0,    0,    0,    0,    0,  113,
  113,  113,    0,  113,  113,  113,  113,  113,    0,    0,
    0,    0,  114,    0,    0,    0,    0,  114,    0,  114,
  114,  114,  114,  114,  114,  114,  114,  114,    0,    0,
    0,    0,    0,    0,  114,  114,  114,    0,  114,  114,
  114,  114,  114,    0,    0,    0,    0,  111,    0,    0,
    0,    0,  111,    0,  111,  111,  111,  111,  111,  111,
  111,  111,  111,    0,    0,    0,    0,    0,    0,  111,
  111,  111,    0,  111,  111,  111,  111,  111,    0,    0,
    0,    0,  112,    0,    0,    0,    0,  112,    0,  112,
  112,  112,  112,  112,  112,  112,  112,  112,    0,    0,
    0,    0,    0,    0,  112,  112,  112,  116,  112,  112,
  112,  112,  112,    0,  116,  116,    0,  115,  116,    0,
    0,    0,  115,    0,  115,  115,  115,  115,    0,    0,
  115,  115,  115,  116,    0,    0,    0,    0,    0,  115,
  115,  115,  117,  115,  115,  115,  115,  115,    0,  117,
  117,    0,    0,  117,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  116,  117,    0,
    0,    0,    0,    0,    0,    0,    0,  118,    0,    0,
    0,    0,    0,    0,  118,  118,    0,    0,  118,    0,
    0,    0,    0,    0,    0,    0,    0,  116,    0,  116,
  116,    0,  117,  118,    0,    0,    0,    0,    0,    0,
    0,    0,  119,    0,    0,    0,    0,    0,    0,  119,
  119,    0,    0,  119,    0,    0,    0,    0,    0,    0,
    0,    0,  117,    0,  117,  117,    0,  118,  119,    0,
    0,    0,    0,    0,    0,    0,    0,  120,    0,    0,
    0,    0,    0,    0,  120,  120,    0,    0,  120,    0,
    0,    0,    0,    0,    0,    0,    0,  118,    0,  118,
  118,    0,  119,  120,    0,    0,    0,    0,    0,    0,
    0,    0,  121,    0,    0,    0,    0,    0,    0,  121,
  121,    0,    0,  121,    0,    0,    0,    0,    0,    0,
    0,    0,  119,    0,  119,  119,    0,  120,  121,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   66,    0,    0,    0,
    0,    0,    0,   64,    0,    0,  116,  120,    0,  120,
  120,  116,  121,  116,  116,  116,  116,    0,    0,  116,
  116,  116,   63,    0,    0,    0,    0,    0,  116,  116,
  116,   66,  116,  116,  116,  116,  116,    0,   64,    0,
    0,  117,  121,    0,  121,  121,  117,    0,  117,  117,
  117,  117,    0,    0,  117,  117,  117,   63,    0,    0,
    0,    0,    0,  117,  117,  117,   66,  117,  117,  117,
  117,  117,    0,   64,    0,    0,  118,    0,    0,    0,
    0,  118,    0,  118,  118,  118,   62,    0,    0,   65,
  118,  118,   63,    0,    0,    0,    0,    0,  118,  118,
  118,  122,  118,  118,  118,  118,  118,    0,  122,  122,
    0,  119,  122,    0,    0,    0,  119,    0,  119,  119,
  119,   62,    0,  109,   65,  119,  119,  122,    0,    0,
    0,    0,    0,  119,  119,  119,   55,  119,  119,  119,
  119,  119,    0,   55,    0,    0,  120,    0,    0,    0,
    0,  120,    0,  120,  120,  120,   62,    0,  157,   65,
  120,  122,   55,    0,    0,    0,    0,    0,  120,  120,
  120,   66,  120,  120,  120,  120,  120,    0,   64,    0,
    0,  121,    0,    0,    0,    0,  121,    0,  121,  121,
  121,  122,    0,  122,  122,  121,    0,   63,    0,    0,
    0,    0,    0,  121,  121,  121,    0,  121,  121,  121,
  121,  121,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   48,   55,    0,   55,   55,
   49,    0,   50,   51,   52,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   54,   55,
   56,   57,   58,   59,   60,   61,    0,    0,    0,    0,
   48,   62,    0,    0,   65,   49,    0,   50,   51,   52,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   53,   54,   55,    0,   57,   58,   59,   60,
   61,    0,    0,    0,    0,   48,    0,    0,    0,    0,
   49,    0,   50,   51,   52,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   54,   55,
    0,   57,   58,   59,   60,   61,    0,    0,    0,    0,
  122,    0,    0,    0,    0,  122,    0,  122,  122,  122,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  122,  122,  122,    0,  122,  122,  122,  122,
  122,    0,    0,    0,    0,   55,    0,    0,    0,    0,
   55,    0,   55,   55,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   55,   55,   55,
    0,   55,   55,   55,   55,   55,  111,    0,    0,  112,
   48,    0,    0,    0,    0,   49,    0,   50,   51,   52,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   53,   54,   55,    0,   57,   58,   59,   60,
   61,    0,    0,    0,    0,    0,    0,    0,  154,  155,
    0,    0,  112,    0,    0,    0,    0,    0,    0,    0,
  159,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  174,    0,  176,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  199,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  199,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  199,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   60,   93,   62,   37,   39,   44,   40,   41,   42,   43,
   44,   45,   67,   47,  192,  193,   43,   61,   45,  198,
  272,   46,   46,  275,   33,   59,   60,   61,   62,   67,
   37,   40,  257,  147,   33,   42,   44,  123,   37,   67,
   47,   40,   41,   42,   43,   44,   45,   91,   47,  287,
   67,   59,  290,  231,   67,  110,  235,  288,  289,   93,
   59,   60,   61,   62,  242,  243,   91,   91,  182,   33,
  258,  290,  110,   37,  293,  260,   40,   41,   42,   43,
   44,   45,  110,   47,  121,  122,  125,  130,  131,  123,
  204,  125,  126,  110,   93,   59,   60,  110,   62,  262,
  123,  124,  125,  126,   33,  263,   91,  265,  259,   58,
  224,   40,   41,  125,   43,   44,   45,  126,  291,   91,
  289,  284,  285,  286,  123,  290,  125,  126,  291,   93,
   59,   60,  246,   62,   93,  290,  278,   59,  281,   33,
  123,  123,  123,  257,  261,  123,   40,   41,  283,   43,
   44,   45,   58,  188,  189,  280,  290,  123,  288,  123,
   58,  125,  126,  265,   93,   59,   60,  263,   62,   40,
   40,   40,   61,  276,   33,   59,   80,  277,  290,   91,
  125,   40,   41,  123,   43,   44,   45,  282,   40,  125,
   41,   59,   58,  290,  123,   44,  125,  126,  290,   93,
   59,   60,  290,   62,   33,  288,  123,  279,  266,   59,
  262,   40,   59,  273,  274,  119,  120,  121,  122,  123,
  124,  125,  126,  127,  128,  129,  130,  131,  262,  123,
   91,  125,  126,  267,   93,  269,  270,  271,  272,  273,
  274,  275,  276,  277,   41,   41,  123,   93,  290,   93,
  284,  285,  286,   93,  288,  289,  290,  291,  292,  288,
  125,  270,  271,  262,  123,  125,  125,  126,  267,   58,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  288,
  289,  290,  123,  294,  264,  284,  285,  286,   93,  288,
  289,  290,  291,  292,  123,  125,  288,  126,  262,   41,
   93,  123,   93,  267,  290,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  268,  125,   59,  123,  265,   41,
  284,  285,  286,   41,  288,  289,  290,  291,  292,   40,
  125,  262,  264,  262,   41,  125,  123,  125,  267,  125,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  125,
  265,  125,   93,  263,   59,  284,  285,  286,   93,  288,
  289,  290,  291,  292,  290,   62,  119,  180,  262,  120,
  108,   17,  215,  267,   25,  269,  270,  271,  272,  273,
  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,
  284,  285,  286,   33,  288,  289,  290,  291,  292,   -1,
   40,   41,   -1,  262,   44,   -1,   -1,   -1,  267,   -1,
  269,  270,  271,  272,  273,  274,  275,  276,  277,   59,
   60,   -1,   62,   -1,   -1,  284,  285,  286,   33,  288,
  289,  290,  291,  292,   -1,   40,   41,   -1,   -1,   44,
   -1,  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   59,   60,   -1,   62,   -1,  288,
  289,  290,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  123,   -1,  125,  126,   -1,   93,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
  125,  126,   -1,   93,   59,   60,   -1,   62,   -1,   -1,
   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  123,   -1,  125,  126,   -1,   93,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
  125,  126,   -1,   93,   59,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  262,  123,   -1,  125,  126,  267,   93,  269,
  270,  271,  272,  273,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,
  290,  291,  292,   -1,   -1,   -1,   -1,  262,  123,   -1,
  125,  126,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,  284,
  285,  286,   -1,  288,  289,  290,  291,  292,   -1,   -1,
   -1,   -1,  262,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,
  290,  291,  292,   -1,   -1,   -1,   -1,  262,   -1,   -1,
   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,  284,
  285,  286,   -1,  288,  289,  290,  291,  292,   -1,   -1,
   -1,   -1,  262,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   33,  288,  289,
  290,  291,  292,   -1,   40,   41,   -1,  262,   44,   -1,
   -1,   -1,  267,   -1,  269,  270,  271,  272,   -1,   -1,
  275,  276,  277,   59,   -1,   -1,   -1,   -1,   -1,  284,
  285,  286,   33,  288,  289,  290,  291,  292,   -1,   40,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,
  126,   -1,   93,   59,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  123,   -1,  125,  126,   -1,   93,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,
  126,   -1,   93,   59,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  123,   -1,  125,  126,   -1,   93,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   -1,   -1,  262,  123,   -1,  125,
  126,  267,   93,  269,  270,  271,  272,   -1,   -1,  275,
  276,  277,   59,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,   33,  288,  289,  290,  291,  292,   -1,   40,   -1,
   -1,  262,  123,   -1,  125,  126,  267,   -1,  269,  270,
  271,  272,   -1,   -1,  275,  276,  277,   59,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   33,  288,  289,  290,
  291,  292,   -1,   40,   -1,   -1,  262,   -1,   -1,   -1,
   -1,  267,   -1,  269,  270,  271,  123,   -1,   -1,  126,
  276,  277,   59,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,   33,  288,  289,  290,  291,  292,   -1,   40,   41,
   -1,  262,   44,   -1,   -1,   -1,  267,   -1,  269,  270,
  271,  123,   -1,  125,  126,  276,  277,   59,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   33,  288,  289,  290,
  291,  292,   -1,   40,   -1,   -1,  262,   -1,   -1,   -1,
   -1,  267,   -1,  269,  270,  271,  123,   -1,  125,  126,
  276,   93,   59,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,   33,  288,  289,  290,  291,  292,   -1,   40,   -1,
   -1,  262,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,
  271,  123,   -1,  125,  126,  276,   -1,   59,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,
  291,  292,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  262,  123,   -1,  125,  126,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,
  287,  288,  289,  290,  291,  292,   -1,   -1,   -1,   -1,
  262,  123,   -1,   -1,  126,  267,   -1,  269,  270,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,  291,
  292,   -1,   -1,   -1,   -1,  262,   -1,   -1,   -1,   -1,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,
   -1,  288,  289,  290,  291,  292,   -1,   -1,   -1,   -1,
  262,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,  291,
  292,   -1,   -1,   -1,   -1,  262,   -1,   -1,   -1,   -1,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,
   -1,  288,  289,  290,  291,  292,   64,   -1,   -1,   67,
  262,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,  291,
  292,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  106,  107,
   -1,   -1,  110,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  118,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  133,   -1,  135,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  179,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  198,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  235,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=294;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'","'^'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'","'~'",null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"GAME_DF","CARD_DF","CHARACTER_DF",
"GAME_NM","PLAYER_C","PLAYER","SKILL","DEALER","METHOD","MAX_ROUND","IF","ELSE",
"WHILE","TRUE","FALSE","OP_EQ","OP_LE","OP_GE","OP_NE","OP_LOR","OP_LAND",
"INIT","ROUND_END","ROUND_BEGIN","ROUND","TURN","DYING","DECLR_INT","DECLR_STR",
"DECLR_BOOL","VOID","INTEGER","STRING","ID","CARD","FOREACH","ROUNDSUMMARY",
"IN",
};
final static String yyrule[] = {
"$accept : input",
"$$1 :",
"$$2 :",
"input : $$1 game_df card_df character_df $$2 init_block round_block dying_block",
"game_df : GAME_DF '{' game_df_content '}'",
"game_df_content : GAME_NM ':' STRING ';' PLAYER_C ':' INTEGER ';' MAX_ROUND ':' INTEGER ';'",
"card_df : CARD_DF '[' cards_df_content ']'",
"cards_df_content : card_df_content cards_df_content",
"cards_df_content : card_df_content",
"$$3 :",
"$$4 :",
"card_df_content : CARD ID '{' $$3 variable_list METHOD '(' PLAYER DEALER ')' '{' $$4 AUGED_STATEMENT_LIST '}' '}'",
"character_df : CHARACTER_DF '[' characters_df_content ']'",
"characters_df_content : characters_df_content character_df_content",
"characters_df_content : character_df_content",
"$$5 :",
"character_df_content : ID '{' $$5 variable_list skill_df '}'",
"variable_list : ID ':' INTEGER ';' variable_list",
"variable_list : ID ':' STRING ';' variable_list",
"variable_list :",
"skill_df : SKILL ':' '[' skill_lists ']'",
"skill_df : SKILL ':' '[' VOID ']'",
"skill_lists : skill_list skill_lists",
"skill_lists : skill_list",
"$$6 :",
"skill_list : ID '{' METHOD '(' PLAYER DEALER ')' '{' $$6 AUGED_STATEMENT_LIST '}' '}'",
"$$7 :",
"init_block : INIT '{' $$7 AUGED_STATEMENT_LIST '}'",
"round_block : ROUND '{' round_begin_block turn_block round_end_block '}'",
"$$8 :",
"round_begin_block : ROUND_BEGIN '{' $$8 AUGED_STATEMENT_LIST '}'",
"$$9 :",
"turn_block : TURN '{' $$9 AUGED_STATEMENT_LIST '}'",
"$$10 :",
"round_end_block : ROUND_END '{' $$10 AUGED_STATEMENT_LIST '}'",
"$$11 :",
"dying_block : DYING '{' $$11 AUGED_STATEMENT_LIST '}'",
"AUGED_STATEMENT_LIST : STATEMENT_LIST",
"AUGED_STATEMENT_LIST : VOID",
"STATEMENT_LIST : SelectionStatement",
"STATEMENT_LIST : STATEMENT_LIST SelectionStatement",
"STATEMENT_LIST : FieldDeclarations",
"STATEMENT_LIST : STATEMENT_LIST FieldDeclarations",
"STATEMENT_LIST : IterationStatement",
"STATEMENT_LIST : STATEMENT_LIST IterationStatement",
"STATEMENT_LIST : EmptyStatement",
"STATEMENT_LIST : STATEMENT_LIST EmptyStatement",
"STATEMENT_LIST : Expression",
"STATEMENT_LIST : STATEMENT_LIST Expression",
"STATEMENT_LIST : ForeachStatement",
"STATEMENT_LIST : STATEMENT_LIST ForeachStatement",
"ForeachStatement : FOREACH '(' TypeSpecifier ID IN ROUNDSUMMARY ')' Block",
"ForeachStatement : FOREACH '(' TypeSpecifier ID IN ID ')' Block",
"EmptyStatement : ';'",
"IterationStatement : WHILE '(' Expression ')' Block",
"SelectionStatement : IF '(' Expression ')' Block",
"SelectionStatement : IF '(' Expression ')' Block ELSE Block",
"FieldDeclarations : FieldDeclarationOptSemi",
"FieldDeclarationOptSemi : FieldDeclaration ';'",
"FieldDeclaration : FieldVariableDeclaration",
"FieldDeclaration : NonStaticInitializer",
"FieldVariableDeclaration : TypeSpecifier VariableDeclarators",
"VariableDeclarators : VariableDeclarator",
"VariableDeclarators : VariableDeclarators ',' VariableDeclarator",
"VariableDeclarator : DeclaratorName",
"VariableDeclarator : DeclaratorName '=' VariableInitializer",
"DeclaratorName : ID",
"DeclaratorName : DeclaratorName '[' INTEGER ']'",
"VariableInitializer : Expression",
"VariableInitializer : '{' ArrayInitializers '}'",
"ArrayInitializers : VariableInitializer",
"ArrayInitializers : ArrayInitializers ',' VariableInitializer",
"NonStaticInitializer : Block",
"Block : '{' STATEMENT_LIST '}'",
"Block : '{' '}'",
"TypeSpecifier : TypeName",
"TypeSpecifier : TypeName '[' INTEGER ']'",
"TypeName : PrimitiveType",
"TypeName : CARD",
"TypeName : PLAYER",
"PrimitiveType : DECLR_BOOL",
"PrimitiveType : DECLR_INT",
"PrimitiveType : DECLR_STR",
"QualifiedName : ID",
"QualifiedName : QualifiedName '.' ID",
"PrimaryExpression : QualifiedName",
"PrimaryExpression : ComplexPrimary",
"ComplexPrimary : '(' Expression ')'",
"ComplexPrimary : ComplexPrimaryNoParenthesis",
"ComplexPrimaryNoParenthesis : INTEGER",
"ComplexPrimaryNoParenthesis : STRING",
"ComplexPrimaryNoParenthesis : TRUE",
"ComplexPrimaryNoParenthesis : FALSE",
"ComplexPrimaryNoParenthesis : ArrayAccess",
"ComplexPrimaryNoParenthesis : FieldAccess",
"FieldAccess : ComplexPrimary '.' ID",
"ArrayAccess : QualifiedName '[' Expression ']'",
"ArrayAccess : ComplexPrimary '[' Expression ']'",
"UnaryExpression : LogicalUnaryExpression",
"LogicalUnaryExpression : PrimaryExpression",
"LogicalUnaryExpression : LogicalUnaryOperator UnaryExpression",
"LogicalUnaryOperator : '~'",
"LogicalUnaryOperator : '!'",
"MultiplicativeExpression : UnaryExpression",
"MultiplicativeExpression : MultiplicativeExpression '*' UnaryExpression",
"MultiplicativeExpression : MultiplicativeExpression '/' UnaryExpression",
"MultiplicativeExpression : MultiplicativeExpression '%' UnaryExpression",
"AdditiveExpression : MultiplicativeExpression",
"AdditiveExpression : AdditiveExpression '+' MultiplicativeExpression",
"AdditiveExpression : AdditiveExpression '-' MultiplicativeExpression",
"RelationalExpression : AdditiveExpression",
"RelationalExpression : RelationalExpression '<' AdditiveExpression",
"RelationalExpression : RelationalExpression '>' AdditiveExpression",
"RelationalExpression : RelationalExpression OP_LE AdditiveExpression",
"RelationalExpression : RelationalExpression OP_GE AdditiveExpression",
"EqualityExpression : RelationalExpression",
"EqualityExpression : EqualityExpression OP_EQ RelationalExpression",
"EqualityExpression : EqualityExpression OP_NE RelationalExpression",
"ConditionalAndExpression : EqualityExpression",
"ConditionalAndExpression : ConditionalAndExpression OP_LAND EqualityExpression",
"ConditionalOrExpression : ConditionalAndExpression",
"ConditionalOrExpression : ConditionalOrExpression OP_LOR ConditionalAndExpression",
"Expression : ConditionalOrExpression",
"Expression : UnaryExpression '=' Expression",
};

//#line 498 "GameWizard.y"

  /*This variable represent which scope the parser is in, so the STATEMENT_LIST in some card or character can be translated*/
  /*If in some card or character, the scope is the card or character name, otherwise, the scope is null*/
  public static String curScope = null;

  private Yylex lexer;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }

  public void checkDulDeclare (String name){
    SymbolType type = SymbolTable.lookUpSymbolType(name);
    if(type != SymbolType.UNDEFINED){
        yyerror("Variable "+name+" has been defined before! System exits!");
    }
    if(curScope !=null){
	boolean result = SymbolTable.checkCardCharacterVar(curScope, name);
	if(result){
		yyerror("Variable "+name+" has been defined before! System exits!");
	}
    }
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
    System.exit(1);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }


  static boolean interactive;

   class foo{
       public foo()
       {
           System.out.println("HAHAHA");
       }
   }

  public static void main(String args[]) throws IOException {
    System.out.println("BYACC/Java with JFlex Calculator Demo");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Expression: ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }


    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("Have a nice day");
    }
  }
//#line 821 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 121 "GameWizard.y"
{/*the global block*/SymbolTable.pushNewBlock(); SymbolTable.addKeywordsAndBuildIn();}
break;
case 2:
//#line 121 "GameWizard.y"
{curScope=null;}
break;
case 3:
//#line 122 "GameWizard.y"
{
		String methods = val_peek(2).sval+val_peek(1).sval+val_peek(0).sval;
		Util.writeGameJava(val_peek(6).sval,methods);
		
	}
break;
case 4:
//#line 128 "GameWizard.y"
{yyval.sval=val_peek(1).sval; System.out.println("game_df");}
break;
case 5:
//#line 133 "GameWizard.y"
{String s= "public static String game_name = "+val_peek(9).sval
		+";\npublic static int num_of_players = "+val_peek(5).ival
		+";\npublic static int maximum_round = "+val_peek(1).ival+";\n"; 
		yyval.sval=s; System.out.println(s);
		SymbolTable.addRecordToCurrentBlock("game_name", SymbolType.GAME_DEFINITION);
		SymbolTable.addRecordToCurrentBlock("num_of_players", SymbolType.GAME_DEFINITION);
		SymbolTable.addRecordToCurrentBlock("maximum_round", SymbolType.GAME_DEFINITION);
	}
break;
case 6:
//#line 142 "GameWizard.y"
{System.out.println("2");}
break;
case 7:
//#line 144 "GameWizard.y"
{System.out.println("1");}
break;
case 8:
//#line 145 "GameWizard.y"
{System.out.println("3");}
break;
case 9:
//#line 147 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CARD);}
break;
case 10:
//#line 147 "GameWizard.y"
{ SymbolTable.pushNewBlock();}
break;
case 11:
//#line 148 "GameWizard.y"
{System.out.println("======================================================finished card_df_content"); SymbolTable.popBlock(); Util.writeCardsJava(val_peek(13).sval.toString(),val_peek(10).obj,val_peek(2).sval); }
break;
case 12:
//#line 150 "GameWizard.y"
{System.out.println("character_df");}
break;
case 13:
//#line 152 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 14:
//#line 153 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 15:
//#line 155 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CHARACTER);}
break;
case 16:
//#line 158 "GameWizard.y"
{Util.writeCharacterJava(val_peek(5).sval,val_peek(2).obj,val_peek(1).obj); System.out.println("character_df_content");}
break;
case 17:
//#line 161 "GameWizard.y"
{
	    checkDulDeclare(val_peek(4).sval);
	    SymbolTable.addRecordToCardCharacterBlock(curScope, val_peek(4).sval, SymbolType.CARD_VARIABLE);	    
	    ArrayList<String> result= new ArrayList<String>();
            result.add("Integer"); result.add(val_peek(4).sval);result.add(String.valueOf(val_peek(2).ival));
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            
            result.addAll(x1); yyval.obj=result;  System.out.println("variable_list");
                }
break;
case 18:
//#line 171 "GameWizard.y"
{
   	    checkDulDeclare(val_peek(4).sval);
	    SymbolTable.addRecordToCardCharacterBlock(curScope, val_peek(4).sval, SymbolType.CARD_VARIABLE);	    
	    ArrayList<String> result= new ArrayList<String>();
            result.add("String"); result.add(val_peek(4).sval);result.add(val_peek(2).sval);
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            result.addAll(x1); yyval.obj=result;   System.out.println("variable_list");
                }
break;
case 19:
//#line 179 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>(); yyval.obj= result;   System.out.println("variable_list");}
break;
case 20:
//#line 184 "GameWizard.y"
{yyval.obj = val_peek(1).obj;}
break;
case 21:
//#line 188 "GameWizard.y"
{ArrayList<String> ret = new ArrayList<String>(); yyval.obj=ret;}
break;
case 22:
//#line 191 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)(val_peek(1).obj);
                                    ArrayList<String> x2 = (ArrayList<String>)(val_peek(0).obj);
                                    result.addAll(x1); result.addAll(x2); yyval.obj= result;}
break;
case 23:
//#line 195 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 24:
//#line 200 "GameWizard.y"
{checkDulDeclare(val_peek(7).sval);SymbolTable
.addRecordToCardCharacterBlock(curScope, val_peek(7).sval, SymbolType.CHARACTER_SKILL);SymbolTable.pushNewBlock();}
break;
case 25:
//#line 202 "GameWizard.y"
{
	    SymbolTable.popBlock();
	    ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(11).sval);
            result.add(val_peek(2).sval);
            yyval.obj=result;
        }
break;
case 26:
//#line 212 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 27:
//#line 213 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void init(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
		System.out.println("init_block statement");
	}
break;
case 28:
//#line 223 "GameWizard.y"
{
		String ret = "public static void round_begin(){\n"+
			val_peek(3).sval+"\n}\n"+
			"public static void turn(Player player) throws IOException{"+
			val_peek(2).sval+"\n}\n"+
			"public static void round_end() throws Exception{"+
			val_peek(1).sval+"\n}\n";
		yyval.sval = ret;
		System.out.println("round block");
	}
break;
case 29:
//#line 236 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 30:
//#line 236 "GameWizard.y"
{ SymbolTable.popBlock();yyval.sval=val_peek(1).sval; System.out.println("round_begin");}
break;
case 31:
//#line 240 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 32:
//#line 240 "GameWizard.y"
{SymbolTable.popBlock();yyval.sval=val_peek(1).sval;}
break;
case 33:
//#line 244 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 34:
//#line 244 "GameWizard.y"
{SymbolTable.popBlock(); yyval.sval=val_peek(1).sval;}
break;
case 35:
//#line 248 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 36:
//#line 249 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void dying(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
	}
break;
case 37:
//#line 257 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 38:
//#line 258 "GameWizard.y"
{yyval.sval="";}
break;
case 39:
//#line 262 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(0).sval;}
break;
case 40:
//#line 263 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 41:
//#line 264 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(0).sval;}
break;
case 42:
//#line 265 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 43:
//#line 266 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(0).sval;}
break;
case 44:
//#line 267 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 45:
//#line 268 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(0).sval;}
break;
case 46:
//#line 269 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 47:
//#line 270 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(0).sval;}
break;
case 48:
//#line 271 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 49:
//#line 272 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 50:
//#line 273 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 51:
//#line 278 "GameWizard.y"
{
		String s = "for("+val_peek(5).sval+" "+val_peek(4).sval+":roundSummary.keySet())\n"+val_peek(0).sval;
		yyval.sval=s;
	}
break;
case 52:
//#line 284 "GameWizard.y"
{
		String s = "for("+val_peek(5).sval+" "+val_peek(4).sval+":"+val_peek(2).sval+")\n"+val_peek(0).sval;
		yyval.sval=s;
	}
break;
case 53:
//#line 291 "GameWizard.y"
{yyval.sval=";";}
break;
case 54:
//#line 296 "GameWizard.y"
{System.out.println("8");String s = "while("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 55:
//#line 302 "GameWizard.y"
{System.out.println("6");String s = "if("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 56:
//#line 304 "GameWizard.y"
{System.out.println("7");String s = "if("+val_peek(4).sval+")\n"+val_peek(2).sval+";\nelse\n"+val_peek(0).sval+";"; yyval.sval=s;}
break;
case 57:
//#line 309 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 58:
//#line 313 "GameWizard.y"
{System.out.println("3");yyval.sval=val_peek(1).sval+";\n";}
break;
case 59:
//#line 318 "GameWizard.y"
{System.out.println(val_peek(0).sval);yyval.sval=val_peek(0).sval;}
break;
case 60:
//#line 319 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 61:
//#line 323 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;System.out.println(yyval.sval);}
break;
case 62:
//#line 327 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 63:
//#line 328 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 64:
//#line 332 "GameWizard.y"
{checkDulDeclare(val_peek(0).sval);System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 65:
//#line 333 "GameWizard.y"
{checkDulDeclare(val_peek(2).sval);SymbolTable.addRecordToCurrentBlock(val_peek(2).sval,SymbolType.LOCAL_VARIABLE);yyval.sval=val_peek(2).sval+'='+val_peek(0).sval;}
break;
case 66:
//#line 337 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 67:
//#line 338 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 68:
//#line 342 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 69:
//#line 343 "GameWizard.y"
{yyval.sval='{'+val_peek(1).sval+'}';}
break;
case 70:
//#line 347 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 71:
//#line 348 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 72:
//#line 352 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 73:
//#line 356 "GameWizard.y"
{yyval.sval="{"+val_peek(1).sval+"}";}
break;
case 74:
//#line 357 "GameWizard.y"
{yyval.sval="{}";}
break;
case 75:
//#line 370 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 76:
//#line 371 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 77:
//#line 376 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 78:
//#line 377 "GameWizard.y"
{yyval.sval="Card";}
break;
case 79:
//#line 378 "GameWizard.y"
{yyval.sval="Player";}
break;
case 80:
//#line 384 "GameWizard.y"
{yyval.sval="boolean ";}
break;
case 81:
//#line 385 "GameWizard.y"
{yyval.sval="int ";}
break;
case 82:
//#line 386 "GameWizard.y"
{yyval.sval="String ";}
break;
case 83:
//#line 396 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 84:
//#line 397 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 85:
//#line 401 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 86:
//#line 402 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 87:
//#line 407 "GameWizard.y"
{yyval.sval="("+val_peek(1).sval+")";}
break;
case 88:
//#line 408 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 89:
//#line 413 "GameWizard.y"
{Integer tmp = new Integer(val_peek(0).ival);yyval.sval=tmp.toString();}
break;
case 90:
//#line 414 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 91:
//#line 415 "GameWizard.y"
{yyval.sval="true";}
break;
case 92:
//#line 416 "GameWizard.y"
{yyval.sval="false";}
break;
case 93:
//#line 417 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 94:
//#line 418 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 95:
//#line 422 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'.'+val_peek(0).sval;}
break;
case 96:
//#line 426 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 97:
//#line 427 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 98:
//#line 433 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 99:
//#line 437 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 100:
//#line 438 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 101:
//#line 442 "GameWizard.y"
{yyval.sval="~";}
break;
case 102:
//#line 443 "GameWizard.y"
{yyval.sval="!";}
break;
case 103:
//#line 448 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 104:
//#line 449 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"*"+val_peek(0).sval;}
break;
case 105:
//#line 450 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"/"+val_peek(0).sval;}
break;
case 106:
//#line 451 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"%"+val_peek(0).sval;}
break;
case 107:
//#line 456 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 108:
//#line 457 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"+"+val_peek(0).sval;}
break;
case 109:
//#line 458 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"-"+val_peek(0).sval;}
break;
case 110:
//#line 463 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 111:
//#line 464 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<"+val_peek(0).sval;}
break;
case 112:
//#line 465 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">"+val_peek(0).sval;}
break;
case 113:
//#line 466 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<="+val_peek(0).sval;}
break;
case 114:
//#line 467 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">="+val_peek(0).sval;}
break;
case 115:
//#line 472 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 116:
//#line 473 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"=="+val_peek(0).sval;}
break;
case 117:
//#line 474 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"!="+val_peek(0).sval;}
break;
case 118:
//#line 478 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 119:
//#line 479 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"&&"+val_peek(0).sval;}
break;
case 120:
//#line 484 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 121:
//#line 485 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"||"+val_peek(0).sval;}
break;
case 122:
//#line 489 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 123:
//#line 490 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"="+val_peek(0).sval;}
break;
//#line 1522 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
