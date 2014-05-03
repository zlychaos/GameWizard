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
   52,   55,    0,    1,    2,   53,   56,   56,   58,   59,
   57,   54,   60,   60,   62,   61,    3,    3,    3,   38,
   38,   39,   39,   63,   40,   64,   41,   42,   65,   43,
   66,   45,   67,   44,   68,   46,   47,   47,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
   48,   48,   37,    7,    6,    6,   24,   26,   25,   25,
   27,   36,   36,   35,   35,   34,   34,   33,   33,   32,
   32,   28,   31,   31,   29,   29,   30,   30,   30,   23,
   23,   23,   49,   49,   50,   51,   51,   18,   18,   18,
   16,   16,   19,   20,   20,   20,   20,   20,   20,   22,
   21,   21,    8,   15,   15,   17,   17,   13,   13,   13,
   13,   14,   14,   14,   12,   12,   12,   12,   12,   11,
   11,   11,   10,   10,    9,    9,    5,    5,
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
    1,    1,    4,    3,    1,    1,    3,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    3,
    4,    4,    1,    1,    2,    1,    1,    1,    3,    3,
    3,    1,    3,    3,    1,    3,    3,    3,    3,    1,
    3,    3,    1,    3,    1,    3,    1,    3,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    2,    0,    4,    0,    0,    0,    0,    0,    0,
    0,    6,    7,    0,    0,   14,    0,    0,    0,    9,
   15,   12,   13,   26,    0,    0,    0,    0,    0,    0,
    0,    0,    3,    0,    0,    0,    0,   79,    0,    0,
   96,   97,   81,   82,   80,   38,   94,   95,   89,   78,
    0,   90,    0,   53,  106,  107,    0,   47,   39,   43,
    0,    0,    0,    0,    0,    0,    0,  103,  104,    0,
    0,    0,   93,   98,   77,   41,    0,   57,   59,   60,
    0,    0,   72,   45,    0,   49,   99,    0,    0,    0,
   35,    0,    0,    0,    0,    0,    0,    0,    0,   74,
    0,   48,   40,   44,   42,   46,   50,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  105,    0,    0,    0,   58,   66,    0,   62,    0,
    0,   27,    0,   29,    0,    0,    0,    0,    0,    0,
    0,    0,   16,    0,    0,    0,   73,  128,  108,    0,
    0,    0,    0,    0,    0,    0,    0,  109,  110,  111,
    0,    0,    0,   88,    0,    0,    0,    0,    0,   84,
   86,    0,    0,   31,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  101,  102,    0,    0,   68,
   65,   63,   76,   83,    0,    0,    0,   33,   28,   36,
    0,   17,   18,    0,    0,    0,    0,    0,    0,   54,
    0,   67,    0,   70,   87,   30,    0,    0,    0,    0,
   21,    0,   20,   22,    0,    0,    0,   69,    0,   32,
    0,    5,   10,    0,   56,    0,    0,   71,   34,    0,
    0,   52,   51,    0,    0,    0,    0,   11,    0,   24,
    0,    0,    0,   25,
};
final static short yydgoto[] = {                          1,
    4,    9,   46,   67,   68,   69,   70,   71,   72,   73,
   74,   75,   76,   77,   78,   79,   80,   81,   82,   83,
   84,    0,   85,   86,   87,   88,   89,   90,   91,   92,
   93,  223,  201,  138,  139,  140,   94,  106,  217,  218,
   28,   36,  100,  186,  146,   43,   95,   96,   97,   98,
  182,    2,    7,   12,   19,   16,   17,   38,  250,   25,
   26,   39,  261,   40,  183,  207,  228,  147,
};
final static short yysindex[] = {                         0,
    0, -240,  -93, -222, -212,  -32, -187,   19,  -18, -208,
   13,    0, -198,    0, -182,   17, -208, -171, -166,   67,
    6,    0,    0,   10,  -91,    0,   12, -142, -120,    0,
    0,    0,    0,    0,   21, -138,   84, -144, -144,  974,
 -133,   26,    0, -135,   96, -108, -105,    0,  119,  121,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  124,    0, 1009,    0,    0,    0, 1149,    0,    0,    0,
  109, -104, -109, -235,  -59,   64,  -21,    0,    0,  189,
  -26,   80,    0,    0,    0,    0,  114,    0,    0,    0,
 -116,   85,    0,    0,   52,    0,    0,  138,   56, -102,
    0,  122, -203,  144,  130,   57,  189,  189, -148,    0,
 1044,    0,    0,    0,    0,    0,    0,  189,  189,  189,
  189,  189,  189,  189,  189,  189,  189,  189,  189,  189,
  189,    0,  189, -101,  189,    0,    0,  -40,    0,  146,
  -97,    0,   -8,    0,   69,  -86,  974,  -72,  137,  147,
  -57,  112,    0,  166,  167,  -81,    0,    0,    0, -109,
 -235,  -59,  -59,  -21,  -21,  -21,  -21,    0,    0,    0,
   64,   64,  118,    0,  120,  -76,  177, -116,  123,    0,
    0,   -3,  974,    0,   94,   93,   95,  161, -144, -144,
  -43, -237,  100,  100,  -70,    0,    0,  132,  177,    0,
    0,    0,    0,    0,  189,  101,  974,    0,    0,    0,
  -61,    0,    0,  187,  152,  108,  153,  -41,  -20,    0,
 -238,    0,  -31,    0,    0,    0,  125,  974,  188,  131,
    0,   -4,    0,    0,  100,  225,  229,    0,  177,    0,
  158,    0,    0,  244,    0,  100,  100,    0,    0,  974,
   27,    0,    0,  171,   33,  173,  260,    0,  179,    0,
  974,  180,  191,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  224,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   61,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  194,    0,    0,    0,
   37, 1079,  895,  825,  536,   72,  361,    0,    0,    0,
  -33,    2,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   30,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -25,    0,  272,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  930,
  860,  755,  790,  396,  431,  466,  501,    0,    0,    0,
  107,  142,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -192, -192,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  239, 1114,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -34,  270,  728,  -44,  -36,  478,    0,  216,
  217,  -19,  -42,   -2,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -35,    0,    0,    0,    0,  227,    0,
 -178,    0, -181,    0,  160,    0,  -13,    0,  133,    0,
    0,    0,    0,    0,    0,    0, -141,  -11,    0,    0,
    0,    0,    0,    0,    0,  323,    0,    0,    0,    0,
  325,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1442;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         91,
  125,   32,  126,   91,   47,  187,   85,   91,   91,   91,
   91,   91,  239,   91,  219,  220,    3,  224,   64,  134,
  177,  131,  113,  130,   66,   91,   91,   91,   91,    5,
  114,  115,  180,   64,   92,    6,  121,  204,   92,  122,
  205,  206,   92,   92,   92,   92,   92,    8,   92,  215,
  176,  236,  216,  116,  237,  117,  245,  248,   10,   91,
   92,   92,   92,   92,  133,  227,  113,  252,  253,  108,
   19,   11,   19,  108,  114,  115,   13,  108,  108,  108,
  108,  108,   15,  108,  149,  150,  241,  171,  172,   91,
   20,   91,   91,  238,   92,  108,  108,  116,  108,  117,
  129,  162,  163,   18,  112,  127,   14,   21,  254,   22,
  128,   27,  112,   48,  112,  112,  112,   65,   24,  262,
  164,  165,  166,  167,   92,   29,   92,   92,   30,  108,
  112,  112,   31,  112,   34,   53,   54,   55,   35,  114,
   37,   44,   60,   41,   42,   45,   99,  114,  101,  114,
  114,  114,  102,  103,  212,  213,  104,  105,  107,  108,
  108,  108,  108,  109,  112,  114,  114,  120,  114,  118,
  135,  119,  136,  137,  113,  141,  142,  143,  144,  145,
  148,  153,  113,  151,  113,  113,  113,  152,  174,  178,
  179,  184,  185,  188,  112,  189,  112,  112,   24,  114,
  113,  113,  192,  113,  191,  190,  193,  194,  195,   66,
  196,  198,  197,  123,  124,  203,  208,  209,  211,  210,
  214,   66,   63,  221,  222,  226,  229,  230,   91,  114,
  232,  114,  114,   91,  113,   91,   91,   91,   91,   91,
   91,   91,   91,   91,  231,  233,  242,  235,  216,  240,
   91,   91,   91,  243,   91,   91,   91,   91,   91,   91,
  244,   51,   52,   92,  113,  246,  113,  113,   92,  247,
   92,   92,   92,   92,   92,   92,   92,   92,   92,   57,
   58,   59,  249,  251,   62,   92,   92,   92,  255,   92,
   92,   92,   92,   92,   92,  256,  257,  258,  108,  199,
  259,  260,   65,  108,  263,  108,  108,  108,  108,  108,
  108,  108,  108,  108,   65,  264,    8,   19,   37,   75,
  108,  108,  108,   19,  108,  108,  108,  108,  108,  108,
   61,   23,  111,  112,  160,  156,  161,  202,  112,   23,
  112,  112,  112,  112,  112,  112,  112,  112,  112,   33,
  234,    0,    0,    0,    0,  112,  112,  112,    0,  112,
  112,  112,  112,  112,  112,    0,    0,    0,  114,    0,
    0,    0,    0,  114,    0,  114,  114,  114,  114,  114,
  114,  114,  114,  114,    0,    0,    0,    0,    0,    0,
  114,  114,  114,  115,  114,  114,  114,  114,  114,  114,
    0,  115,    0,  113,  115,    0,    0,    0,  113,    0,
  113,  113,  113,  113,  113,  113,  113,  113,  113,  115,
  115,    0,  115,    0,    0,  113,  113,  113,  118,  113,
  113,  113,  113,  113,  113,    0,  118,    0,    0,  118,
    0,    0,    0,    0,    0,    0,   51,   52,    0,    0,
    0,    0,    0,  115,  118,  118,    0,  118,   51,   52,
    0,    0,    0,  119,   57,   58,   59,    0,    0,   62,
    0,  119,    0,    0,  119,    0,   57,   58,   59,    0,
    0,   62,    0,  115,    0,  115,  115,    0,  118,  119,
  119,    0,  119,    0,    0,    0,    0,    0,  116,    0,
    0,    0,    0,    0,    0,    0,  116,    0,    0,  116,
    0,    0,    0,    0,    0,    0,    0,    0,  118,    0,
  118,  118,    0,  119,  116,  116,    0,  116,    0,    0,
    0,    0,    0,  117,    0,    0,    0,    0,    0,    0,
    0,  117,    0,    0,  117,    0,    0,    0,    0,    0,
    0,    0,    0,  119,    0,  119,  119,  132,  116,  117,
  117,    0,  117,    0,    0,    0,    0,    0,  120,    0,
    0,    0,    0,    0,    0,    0,  120,    0,    0,  120,
    0,    0,    0,    0,    0,    0,    0,    0,  116,    0,
  116,  116,    0,  117,  120,    0,  159,  159,  159,  159,
  159,  159,  159,  159,  168,  169,  170,  159,  159,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  115,  117,    0,  117,  117,  115,  120,  115,
  115,  115,  115,  115,  115,  115,  115,  115,    0,    0,
    0,    0,    0,    0,  115,  115,  115,    0,  115,  115,
  115,  115,  115,  115,    0,    0,    0,  118,  120,    0,
  120,  120,  118,    0,  118,  118,  118,  118,  118,  118,
  118,  118,  118,    0,    0,    0,    0,    0,    0,  118,
  118,  118,    0,  118,  118,  118,  118,  118,  118,    0,
    0,    0,  119,    0,    0,    0,    0,  119,    0,  119,
  119,  119,  119,  119,  119,  119,  119,  119,    0,    0,
    0,    0,    0,    0,  119,  119,  119,    0,  119,  119,
  119,  119,  119,  119,    0,    0,    0,  116,    0,    0,
    0,    0,  116,    0,  116,  116,  116,  116,  116,  116,
  116,  116,  116,    0,    0,    0,    0,    0,    0,  116,
  116,  116,    0,  116,  116,  116,  116,  116,  116,    0,
    0,    0,  117,    0,    0,    0,    0,  117,    0,  117,
  117,  117,  117,  117,  117,  117,  117,  117,    0,    0,
    0,    0,    0,    0,  117,  117,  117,  121,  117,  117,
  117,  117,  117,  117,  112,  121,    0,  120,  121,    0,
    0,    0,  120,    0,  120,  120,  120,  120,    0,    0,
  120,  120,  120,  121,    0,    0,    0,    0,    0,  120,
  120,  120,  122,  120,  120,  120,  120,  120,  120,    0,
  122,    0,    0,  122,  154,  155,    0,    0,  112,    0,
    0,    0,    0,    0,    0,  158,    0,  121,  122,    0,
    0,    0,    0,    0,    0,    0,    0,  123,    0,    0,
  173,    0,  175,    0,    0,  123,    0,    0,  123,    0,
  181,    0,    0,    0,    0,    0,    0,  121,    0,  121,
  121,    0,  122,  123,    0,    0,    0,    0,    0,    0,
    0,    0,  124,    0,    0,    0,    0,    0,    0,    0,
  124,    0,    0,  124,  200,    0,    0,    0,    0,    0,
    0,    0,  122,    0,  122,  122,    0,  123,  124,    0,
    0,    0,    0,    0,    0,    0,  200,  125,    0,    0,
    0,    0,  225,    0,    0,  125,    0,    0,  125,    0,
    0,    0,    0,    0,    0,    0,    0,  123,    0,  123,
  123,    0,  124,  125,    0,    0,    0,    0,    0,    0,
    0,    0,  126,    0,    0,    0,  200,    0,    0,    0,
  126,    0,    0,  126,    0,    0,    0,    0,    0,    0,
    0,    0,  124,    0,  124,  124,    0,  125,  126,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   66,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  121,  125,    0,  125,
  125,  121,  126,  121,  121,  121,  121,    0,    0,  121,
  121,  121,   64,    0,    0,    0,    0,    0,  121,  121,
  121,   66,  121,  121,  121,  121,  121,  121,    0,    0,
    0,  122,  126,    0,  126,  126,  122,    0,  122,  122,
  122,  122,    0,    0,  122,  122,  122,   64,    0,    0,
    0,    0,    0,  122,  122,  122,   66,  122,  122,  122,
  122,  122,  122,    0,    0,    0,  123,    0,    0,    0,
    0,  123,    0,  123,  123,  123,   63,    0,    0,   65,
  123,  123,   64,    0,    0,    0,    0,    0,  123,  123,
  123,  127,  123,  123,  123,  123,  123,  123,    0,  127,
    0,  124,  127,    0,    0,    0,  124,    0,  124,  124,
  124,   63,    0,  110,   65,  124,  124,  127,    0,    0,
    0,    0,    0,  124,  124,  124,   55,  124,  124,  124,
  124,  124,  124,    0,    0,    0,  125,    0,    0,    0,
    0,  125,    0,  125,  125,  125,   63,    0,  157,   65,
  125,  127,   55,    0,    0,    0,    0,    0,  125,  125,
  125,   66,  125,  125,  125,  125,  125,  125,    0,    0,
    0,  126,    0,    0,    0,    0,  126,    0,  126,  126,
  126,  127,    0,  127,  127,  126,    0,   64,    0,    0,
    0,    0,    0,  126,  126,  126,    0,  126,  126,  126,
  126,  126,  126,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   48,   55,    0,   55,   55,
   49,    0,   50,   51,   52,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   54,   55,
   56,   57,   58,   59,   60,   61,   62,    0,    0,    0,
   48,   63,    0,    0,   65,   49,    0,   50,   51,   52,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   53,   54,   55,    0,   57,   58,   59,   60,
   61,   62,    0,    0,    0,   48,    0,    0,    0,    0,
   49,    0,   50,   51,   52,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   54,   55,
    0,   57,   58,   59,   60,   61,   62,    0,    0,    0,
  127,    0,    0,    0,    0,  127,    0,  127,  127,  127,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  127,  127,  127,    0,  127,  127,  127,  127,
  127,  127,    0,    0,    0,   55,    0,    0,    0,    0,
   55,    0,   55,   55,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   55,   55,   55,
    0,   55,   55,   55,   55,   55,   55,    0,    0,    0,
   48,    0,    0,    0,    0,   49,    0,   50,   51,   52,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   53,   54,   55,    0,   57,   58,   59,   60,
   61,   62,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   60,   93,   62,   37,   39,  147,   40,   41,   42,   43,
   44,   45,   44,   47,  193,  194,  257,  199,   44,   46,
   61,   43,   67,   45,   33,   59,   60,   61,   62,  123,
   67,   67,   41,   59,   33,  258,  272,   41,   37,  275,
   44,  183,   41,   42,   43,   44,   45,  260,   47,  287,
   91,  290,  290,   67,  293,   67,  235,  239,   91,   93,
   59,   60,   61,   62,   91,  207,  111,  246,  247,   33,
  263,  259,  265,   37,  111,  111,   58,   41,   42,   43,
   44,   45,  291,   47,  288,  289,  228,  130,  131,  123,
  289,  125,  126,  125,   93,   59,   60,  111,   62,  111,
   37,  121,  122,   91,   33,   42,  125,  290,  250,   93,
   47,  278,   41,  262,   43,   44,   45,  126,  290,  261,
  123,  124,  125,  126,  123,   59,  125,  126,  123,   93,
   59,   60,  123,   62,  123,  284,  285,  286,  281,   33,
  261,   58,  291,  123,  283,  290,  280,   41,  123,   43,
   44,   45,  288,   58,  189,  190,  265,  263,   40,  123,
   40,  125,  126,   40,   93,   59,   60,  277,   62,   61,
   91,  276,   59,  290,   33,   91,  125,   40,  123,  282,
   59,  125,   41,   40,   43,   44,   45,   58,  290,   44,
  288,  123,  279,  266,  123,   59,  125,  126,  290,   93,
   59,   60,   91,   62,  262,   59,   41,   41,  290,   33,
   93,  288,   93,  273,  274,   93,  123,  125,   58,  125,
  264,   33,  123,  294,   93,  125,  288,   41,  262,  123,
  123,  125,  126,  267,   93,  269,  270,  271,  272,  273,
  274,  275,  276,  277,   93,   93,   59,  268,  290,  125,
  284,  285,  286,  123,  288,  289,  290,  291,  292,  293,
  265,  270,  271,  262,  123,   41,  125,  126,  267,   41,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  288,
  289,  290,  125,   40,  293,  284,  285,  286,  262,  288,
  289,  290,  291,  292,  293,  125,  264,  125,  262,  123,
   41,  123,  126,  267,  125,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  126,  125,   93,  265,  125,  290,
  284,  285,  286,  263,  288,  289,  290,  291,  292,  293,
   59,   93,   63,  262,  119,  109,  120,  178,  267,   17,
  269,  270,  271,  272,  273,  274,  275,  276,  277,   25,
  218,   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,
  289,  290,  291,  292,  293,   -1,   -1,   -1,  262,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,
  284,  285,  286,   33,  288,  289,  290,  291,  292,  293,
   -1,   41,   -1,  262,   44,   -1,   -1,   -1,  267,   -1,
  269,  270,  271,  272,  273,  274,  275,  276,  277,   59,
   60,   -1,   62,   -1,   -1,  284,  285,  286,   33,  288,
  289,  290,  291,  292,  293,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,  270,  271,   -1,   -1,
   -1,   -1,   -1,   93,   59,   60,   -1,   62,  270,  271,
   -1,   -1,   -1,   33,  288,  289,  290,   -1,   -1,  293,
   -1,   41,   -1,   -1,   44,   -1,  288,  289,  290,   -1,
   -1,  293,   -1,  123,   -1,  125,  126,   -1,   93,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
  125,  126,   -1,   93,   59,   60,   -1,   62,   -1,   -1,
   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  123,   -1,  125,  126,   80,   93,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,
  125,  126,   -1,   93,   59,   -1,  119,  120,  121,  122,
  123,  124,  125,  126,  127,  128,  129,  130,  131,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  262,  123,   -1,  125,  126,  267,   93,  269,
  270,  271,  272,  273,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,
  290,  291,  292,  293,   -1,   -1,   -1,  262,  123,   -1,
  125,  126,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,  284,
  285,  286,   -1,  288,  289,  290,  291,  292,  293,   -1,
   -1,   -1,  262,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,
  290,  291,  292,  293,   -1,   -1,   -1,  262,   -1,   -1,
   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,  284,
  285,  286,   -1,  288,  289,  290,  291,  292,  293,   -1,
   -1,   -1,  262,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   33,  288,  289,
  290,  291,  292,  293,   67,   41,   -1,  262,   44,   -1,
   -1,   -1,  267,   -1,  269,  270,  271,  272,   -1,   -1,
  275,  276,  277,   59,   -1,   -1,   -1,   -1,   -1,  284,
  285,  286,   33,  288,  289,  290,  291,  292,  293,   -1,
   41,   -1,   -1,   44,  107,  108,   -1,   -1,  111,   -1,
   -1,   -1,   -1,   -1,   -1,  118,   -1,   93,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
  133,   -1,  135,   -1,   -1,   41,   -1,   -1,   44,   -1,
  143,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,
  126,   -1,   93,   59,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,  177,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  123,   -1,  125,  126,   -1,   93,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  199,   33,   -1,   -1,
   -1,   -1,  205,   -1,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,
  126,   -1,   93,   59,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,  239,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  123,   -1,  125,  126,   -1,   93,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  262,  123,   -1,  125,
  126,  267,   93,  269,  270,  271,  272,   -1,   -1,  275,
  276,  277,   59,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,   33,  288,  289,  290,  291,  292,  293,   -1,   -1,
   -1,  262,  123,   -1,  125,  126,  267,   -1,  269,  270,
  271,  272,   -1,   -1,  275,  276,  277,   59,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   33,  288,  289,  290,
  291,  292,  293,   -1,   -1,   -1,  262,   -1,   -1,   -1,
   -1,  267,   -1,  269,  270,  271,  123,   -1,   -1,  126,
  276,  277,   59,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,   33,  288,  289,  290,  291,  292,  293,   -1,   41,
   -1,  262,   44,   -1,   -1,   -1,  267,   -1,  269,  270,
  271,  123,   -1,  125,  126,  276,  277,   59,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   33,  288,  289,  290,
  291,  292,  293,   -1,   -1,   -1,  262,   -1,   -1,   -1,
   -1,  267,   -1,  269,  270,  271,  123,   -1,  125,  126,
  276,   93,   59,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,   33,  288,  289,  290,  291,  292,  293,   -1,   -1,
   -1,  262,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,
  271,  123,   -1,  125,  126,  276,   -1,   59,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,
  291,  292,  293,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  262,  123,   -1,  125,  126,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,
  287,  288,  289,  290,  291,  292,  293,   -1,   -1,   -1,
  262,  123,   -1,   -1,  126,  267,   -1,  269,  270,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,  291,
  292,  293,   -1,   -1,   -1,  262,   -1,   -1,   -1,   -1,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,
   -1,  288,  289,  290,  291,  292,  293,   -1,   -1,   -1,
  262,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,  291,
  292,  293,   -1,   -1,   -1,  262,   -1,   -1,   -1,   -1,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,
   -1,  288,  289,  290,  291,  292,  293,   -1,   -1,   -1,
  262,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,  291,
  292,  293,
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
"MethodCall : MethodAccess '(' ArgumentList ')'",
"MethodCall : MethodAccess '(' ')'",
"MethodAccess : QualifiedName",
"ArgumentList : Expression",
"ArgumentList : ArgumentList ',' Expression",
"QualifiedName : QualifiedName '.' ID",
"QualifiedName : ID",
"QualifiedName : ROUNDSUMMARY",
"PrimaryExpression : QualifiedName",
"PrimaryExpression : ComplexPrimary",
"ComplexPrimary : ComplexPrimaryNoParenthesis",
"ComplexPrimaryNoParenthesis : INTEGER",
"ComplexPrimaryNoParenthesis : STRING",
"ComplexPrimaryNoParenthesis : TRUE",
"ComplexPrimaryNoParenthesis : FALSE",
"ComplexPrimaryNoParenthesis : ArrayAccess",
"ComplexPrimaryNoParenthesis : MethodCall",
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

//#line 522 "GameWizard.y"

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
//#line 803 "Parser.java"
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
//#line 124 "GameWizard.y"
{/*the global block*/SymbolTable.pushNewBlock(); SymbolTable.addKeywordsAndBuildIn();}
break;
case 2:
//#line 124 "GameWizard.y"
{curScope=null;}
break;
case 3:
//#line 125 "GameWizard.y"
{
		String methods = val_peek(2).sval+val_peek(1).sval+val_peek(0).sval;
		Util.writeGameJava(val_peek(6).sval,methods);
		
	}
break;
case 4:
//#line 131 "GameWizard.y"
{yyval.sval=val_peek(1).sval; System.out.println("game_df");}
break;
case 5:
//#line 136 "GameWizard.y"
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
//#line 145 "GameWizard.y"
{System.out.println("2");}
break;
case 7:
//#line 147 "GameWizard.y"
{System.out.println("1");}
break;
case 8:
//#line 148 "GameWizard.y"
{System.out.println("3");}
break;
case 9:
//#line 150 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CARD);}
break;
case 10:
//#line 150 "GameWizard.y"
{ SymbolTable.pushNewBlock();}
break;
case 11:
//#line 151 "GameWizard.y"
{System.out.println("======================================================finished card_df_content"); SymbolTable.popBlock(); Util.writeCardsJava(val_peek(13).sval.toString(),val_peek(10).obj,val_peek(2).sval); }
break;
case 12:
//#line 153 "GameWizard.y"
{System.out.println("character_df");}
break;
case 13:
//#line 155 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 14:
//#line 156 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 15:
//#line 158 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CHARACTER);}
break;
case 16:
//#line 161 "GameWizard.y"
{Util.writeCharacterJava(val_peek(5).sval,val_peek(2).obj,val_peek(1).obj); System.out.println("character_df_content");}
break;
case 17:
//#line 164 "GameWizard.y"
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
//#line 174 "GameWizard.y"
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
//#line 182 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>(); yyval.obj= result;   System.out.println("variable_list");}
break;
case 20:
//#line 187 "GameWizard.y"
{yyval.obj = val_peek(1).obj;}
break;
case 21:
//#line 191 "GameWizard.y"
{ArrayList<String> ret = new ArrayList<String>(); yyval.obj=ret;}
break;
case 22:
//#line 194 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)(val_peek(1).obj);
                                    ArrayList<String> x2 = (ArrayList<String>)(val_peek(0).obj);
                                    result.addAll(x1); result.addAll(x2); yyval.obj= result;}
break;
case 23:
//#line 198 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 24:
//#line 203 "GameWizard.y"
{checkDulDeclare(val_peek(7).sval);SymbolTable
.addRecordToCardCharacterBlock(curScope, val_peek(7).sval, SymbolType.CHARACTER_SKILL);SymbolTable.pushNewBlock();}
break;
case 25:
//#line 205 "GameWizard.y"
{
	    SymbolTable.popBlock();
	    ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(11).sval);
            result.add(val_peek(2).sval);
            yyval.obj=result;
        }
break;
case 26:
//#line 215 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 27:
//#line 216 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void init(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
		System.out.println("init_block statement");
	}
break;
case 28:
//#line 226 "GameWizard.y"
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
//#line 239 "GameWizard.y"
{SymbolTable.pushNewBlock(); System.out.println("before round begin");}
break;
case 30:
//#line 239 "GameWizard.y"
{ SymbolTable.popBlock();yyval.sval=val_peek(1).sval; System.out.println("round_begin");}
break;
case 31:
//#line 243 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 32:
//#line 243 "GameWizard.y"
{SymbolTable.popBlock();yyval.sval=val_peek(1).sval;}
break;
case 33:
//#line 247 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 34:
//#line 247 "GameWizard.y"
{SymbolTable.popBlock(); yyval.sval=val_peek(1).sval;}
break;
case 35:
//#line 251 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 36:
//#line 252 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void dying(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
	}
break;
case 37:
//#line 260 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 38:
//#line 261 "GameWizard.y"
{yyval.sval="";}
break;
case 39:
//#line 265 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(0).sval;}
break;
case 40:
//#line 266 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 41:
//#line 267 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(0).sval;}
break;
case 42:
//#line 268 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 43:
//#line 269 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(0).sval;}
break;
case 44:
//#line 270 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 45:
//#line 271 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(0).sval;}
break;
case 46:
//#line 272 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 47:
//#line 273 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(0).sval;}
break;
case 48:
//#line 274 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 49:
//#line 275 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 50:
//#line 276 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 51:
//#line 281 "GameWizard.y"
{
		String s = "for("+val_peek(5).sval+" "+val_peek(4).sval+":roundSummary.keySet())\n"+val_peek(0).sval;
		yyval.sval=s;
	}
break;
case 52:
//#line 287 "GameWizard.y"
{
		String s = "for("+val_peek(5).sval+" "+val_peek(4).sval+":"+val_peek(2).sval+")\n"+val_peek(0).sval;
		yyval.sval=s;
	}
break;
case 53:
//#line 294 "GameWizard.y"
{yyval.sval=";";}
break;
case 54:
//#line 299 "GameWizard.y"
{System.out.println("8");String s = "while("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 55:
//#line 305 "GameWizard.y"
{System.out.println("6");String s = "if("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 56:
//#line 307 "GameWizard.y"
{System.out.println("7");String s = "if("+val_peek(4).sval+")\n"+val_peek(2).sval+";\nelse\n"+val_peek(0).sval+";"; yyval.sval=s;}
break;
case 57:
//#line 312 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 58:
//#line 316 "GameWizard.y"
{System.out.println("3");yyval.sval=val_peek(1).sval+";\n";}
break;
case 59:
//#line 321 "GameWizard.y"
{System.out.println(val_peek(0).sval);yyval.sval=val_peek(0).sval;}
break;
case 60:
//#line 322 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 61:
//#line 327 "GameWizard.y"
{
	if(val_peek(1).sval.equals("ICard ")){
		String var = val_peek(0).sval.split("=")[0];
		yyval.sval=val_peek(1).sval+val_peek(0).sval+";\n"+var+"=("+var+".getName())"+var;
	}else{
		yyval.sval=val_peek(1).sval+val_peek(0).sval;
	}
  }
break;
case 62:
//#line 338 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 63:
//#line 339 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 64:
//#line 343 "GameWizard.y"
{checkDulDeclare(val_peek(0).sval);System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 65:
//#line 344 "GameWizard.y"
{checkDulDeclare(val_peek(2).sval);SymbolTable.addRecordToCurrentBlock(val_peek(2).sval,SymbolType.LOCAL_VARIABLE);yyval.sval=val_peek(2).sval+'='+val_peek(0).sval;}
break;
case 66:
//#line 348 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 67:
//#line 349 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 68:
//#line 353 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 69:
//#line 354 "GameWizard.y"
{yyval.sval='{'+val_peek(1).sval+'}';}
break;
case 70:
//#line 358 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 71:
//#line 359 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 72:
//#line 363 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 73:
//#line 367 "GameWizard.y"
{yyval.sval="{"+val_peek(1).sval+"}";}
break;
case 74:
//#line 368 "GameWizard.y"
{yyval.sval="{}";}
break;
case 75:
//#line 381 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 76:
//#line 382 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 77:
//#line 387 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 78:
//#line 388 "GameWizard.y"
{yyval.sval="ICard ";}
break;
case 79:
//#line 389 "GameWizard.y"
{yyval.sval="Player ";}
break;
case 80:
//#line 395 "GameWizard.y"
{yyval.sval="boolean ";}
break;
case 81:
//#line 396 "GameWizard.y"
{yyval.sval="int ";}
break;
case 82:
//#line 397 "GameWizard.y"
{yyval.sval="String ";}
break;
case 83:
//#line 402 "GameWizard.y"
{yyval.sval=val_peek(3).sval+"("+val_peek(1).sval+")";}
break;
case 84:
//#line 403 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"()";}
break;
case 85:
//#line 407 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 86:
//#line 411 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 87:
//#line 412 "GameWizard.y"
{yyval.sval=val_peek(2).sval+","+val_peek(0).sval;}
break;
case 88:
//#line 417 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 89:
//#line 418 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 90:
//#line 419 "GameWizard.y"
{yyval.sval="roundSummary";}
break;
case 91:
//#line 423 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 92:
//#line 424 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 93:
//#line 430 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 94:
//#line 435 "GameWizard.y"
{Integer tmp = new Integer(val_peek(0).ival);yyval.sval=tmp.toString();}
break;
case 95:
//#line 436 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 96:
//#line 437 "GameWizard.y"
{yyval.sval="true";}
break;
case 97:
//#line 438 "GameWizard.y"
{yyval.sval="false";}
break;
case 98:
//#line 439 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 99:
//#line 440 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 100:
//#line 445 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 101:
//#line 449 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 102:
//#line 450 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 103:
//#line 456 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 104:
//#line 460 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 105:
//#line 461 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 106:
//#line 465 "GameWizard.y"
{yyval.sval="~";}
break;
case 107:
//#line 466 "GameWizard.y"
{yyval.sval="!";}
break;
case 108:
//#line 471 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 109:
//#line 472 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"*"+val_peek(0).sval;}
break;
case 110:
//#line 473 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"/"+val_peek(0).sval;}
break;
case 111:
//#line 474 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"%"+val_peek(0).sval;}
break;
case 112:
//#line 479 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 113:
//#line 480 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"+"+val_peek(0).sval;}
break;
case 114:
//#line 481 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"-"+val_peek(0).sval;}
break;
case 115:
//#line 486 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 116:
//#line 487 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<"+val_peek(0).sval;}
break;
case 117:
//#line 488 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">"+val_peek(0).sval;}
break;
case 118:
//#line 489 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<="+val_peek(0).sval;}
break;
case 119:
//#line 490 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">="+val_peek(0).sval;}
break;
case 120:
//#line 495 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 121:
//#line 496 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"=="+val_peek(0).sval;}
break;
case 122:
//#line 497 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"!="+val_peek(0).sval;}
break;
case 123:
//#line 501 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 124:
//#line 502 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"&&"+val_peek(0).sval;}
break;
case 125:
//#line 507 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 126:
//#line 508 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"||"+val_peek(0).sval;}
break;
case 127:
//#line 513 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 128:
//#line 514 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"="+val_peek(0).sval;}
break;
//#line 1531 "Parser.java"
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
