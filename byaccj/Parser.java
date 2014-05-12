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
public final static short IS=284;
public final static short DEBUG=285;
public final static short DECLR_INT=286;
public final static short DECLR_STR=287;
public final static short DECLR_BOOL=288;
public final static short RETURN=289;
public final static short BREAK=290;
public final static short CONTINUE=291;
public final static short VOID=292;
public final static short INTEGER=293;
public final static short STRING=294;
public final static short ID=295;
public final static short CARD=296;
public final static short FOREACH=297;
public final static short ROUNDSUMMARY=298;
public final static short IN=299;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
   55,   58,    0,    1,    2,   56,   59,   59,   61,   62,
   60,   63,   57,   64,   64,   66,   65,    3,    3,    3,
   37,   37,   38,   38,   67,   39,   68,   40,   41,   69,
   42,   70,   44,   71,   43,   72,   45,   46,   46,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,   54,   53,   53,   53,   52,
   52,   52,   47,   36,    7,   73,    6,   74,    6,   23,
   25,   24,   24,   26,   35,   35,   34,   34,   33,   33,
   32,   32,   31,   31,   27,   30,   30,   28,   28,   29,
   29,   29,   22,   22,   22,   48,   48,   49,   50,   50,
   18,   18,   18,   18,   51,   51,   51,   51,   51,   51,
   51,   16,   16,   19,   19,   20,   20,   20,   20,   20,
   20,   20,   21,   21,    8,   15,   15,   17,   17,   13,
   13,   13,   13,   14,   14,   14,   12,   12,   12,   12,
   12,   11,   11,   11,   11,   10,   10,    9,    9,    5,
    5,
};
final static short yylen[] = {                            2,
    0,    0,    8,    4,   12,    4,    2,    1,    0,    0,
   15,    0,    5,    2,    1,    0,    6,    5,    5,    0,
    5,    5,    2,    1,    0,   12,    0,    5,    6,    0,
    5,    0,    5,    0,    5,    0,    5,    1,    1,    1,
    2,    1,    2,    1,    2,    1,    2,    1,    2,    1,
    2,    1,    2,    1,    2,    2,    2,    2,    2,    2,
    2,    2,    8,    1,    5,    0,    6,    0,    8,    1,
    2,    1,    1,    2,    1,    3,    1,    3,    1,    4,
    1,    3,    1,    3,    1,    3,    2,    1,    4,    1,
    1,    1,    1,    1,    1,    4,    3,    1,    1,    3,
    3,    3,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    3,    1,    1,    1,    1,    1,    1,
    1,    1,    4,    4,    1,    1,    2,    1,    1,    1,
    3,    3,    3,    1,    3,    3,    1,    3,    3,    3,
    3,    1,    3,    3,    3,    1,    3,    1,    3,    1,
    3,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   12,    2,    0,    4,    0,    0,    0,    0,    0,    0,
    0,    6,    7,    0,    0,    0,    0,    9,    0,    0,
   15,   27,    0,    0,    0,    0,   16,   13,   14,    0,
    0,    0,    3,    0,    0,    0,    0,  106,  107,   92,
  109,  105,  108,    0,    0,  118,  119,  110,  122,   94,
   95,   93,    0,    0,    0,   39,  116,  117,  103,   91,
    0,  111,    0,   64,    0,  128,  129,    0,    0,   40,
   44,    0,    0,    0,    0,    0,    0,    0,  125,  126,
    0,    0,    0,  115,  120,   90,   42,    0,   70,   72,
   73,    0,    0,   85,   46,    0,   50,  121,    0,  104,
   52,   54,   48,    0,    0,   36,    0,    0,    0,    0,
    0,    0,   60,   61,   62,   59,   57,   58,    0,   87,
    0,    0,   41,   45,   43,   47,   51,   53,   55,   49,
   56,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  127,    0,    0,    0,
   71,   79,    0,   75,    0,    0,   28,    0,   30,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   86,  114,  151,  130,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  131,  132,  133,    0,    0,  101,
  102,    0,    0,    0,    0,    0,    0,   97,   99,    0,
    0,   32,    0,    0,    0,    0,    0,    0,    0,    0,
   17,    0,    0,    0,  123,  124,    0,    0,   81,   78,
   76,   89,   96,    0,    0,    0,   34,   29,   37,    0,
   18,   19,    0,    0,    0,   65,    0,   80,    0,   83,
  100,   31,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   67,    0,   82,    0,   33,    0,    5,   10,   22,
    0,   21,   23,   68,    0,   84,   35,    0,    0,   69,
   63,    0,    0,    0,    0,   11,    0,    0,   25,    0,
    0,    0,   26,
};
final static short yydgoto[] = {                          1,
    4,    9,   46,   78,   79,   80,   81,   82,   83,   84,
   85,   86,   87,   88,   89,   90,   91,   92,   93,   94,
   95,   96,   97,   98,   99,  100,  101,  102,  103,  104,
  249,  230,  163,  164,  165,  105,  178,  259,  260,   26,
   34,  115,  214,  171,   43,  106,  107,  108,  109,  210,
  110,  111,  112,  113,    2,    7,   12,   19,   16,   17,
   36,  278,   18,   30,   31,   47,  290,   40,  211,  236,
  254,  172,  262,  280,
};
final static short yysindex[] = {                         0,
    0, -235,  -92, -220, -217,  -42, -198,    8,  -38, -221,
    0,    0, -211,    0, -200,   -1, -221,   22, -179,   51,
   -4,    0,    0, -175,    2, -159, -138,    0,    3,  -91,
    0,    0,    7, -150,   78, -158,    0,    0,    0,  -33,
 -136,   25,    0, -144,   92, -110, -158,    0,    0,    0,
    0,    0,    0,  125,  133,    0,    0,    0,    0,    0,
    0,    0,  -58,  116,  118,    0,    0,    0,    0,    0,
  138,    0,    6,    0,  211,    0,    0,  123,  122,    0,
    0,  124,  -93,  -88, -239,  -56,   74,   55,    0,    0,
  211,  -21,   93,    0,    0,    0,    0,  127,    0,    0,
    0, -105,  100,    0,    0,   67,    0,    0,  153,    0,
    0,    0,    0,   71,  -86,    0,  139, -192,  157,  -64,
  211,  211,    0,    0,    0,    0,    0,    0, -134,    0,
   45,  159,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  211,  211,  211,  211,  211,  211,  211,  211,  211,
  211,  211,  211,  211,  211,  211,    0, -126,  211,  211,
    0,    0,  -50,    0,  161,  -87,    0,  162,    0,   85,
  -78,  -33,  -52,  156,  160,  -46,  163,   95,  181,  182,
  -71,    0,    0,    0,    0,  -88, -239,  -56,  -56,  -56,
   55,   55,   55,   55,    0,    0,    0,   74,   74,    0,
    0,  132,  137,  -67,  323, -105,  146,    0,    0,   19,
  -33,    0,  119,  149,  154,  185, -158, -158,   14,  189,
    0,  158,  158,  -16,    0,    0,  191,  323,    0,    0,
    0,    0,    0,  211,  165,  -33,    0,    0,    0,  -11,
    0,    0,  244, -218,   18,    0, -119,    0,  -41,    0,
    0,    0,  173,  -33,  228,  190,  215,  194,  225,   24,
  158,    0,   -6,    0,  323,    0,  195,    0,    0,    0,
   56,    0,    0,    0,  158,    0,    0,  -33,  282,    0,
    0,  198,   62,  200,   63,    0,  285,  206,    0,  -33,
  222,  227,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  264,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   94,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   97,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  233,    0,    0,
    0,  568,  -17,  -31,  -36,  521,  399,  538,    0,    0,
    0,  470,  497,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   66,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -12,    0,  303,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   -7,  -29,  611,  696,  707,
  585,  598,  625,  667,    0,    0,    0,  425,  510,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -210, -210,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   84,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  271,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -30,  292,  -54,  -62,  -60,  772,    0,  223,
  224,  -66,   11,   10,    0,    0,    0,  129,    0,    0,
    0,    0,  -49,    0,    0,    0,    0,  257,    0, -203,
    0, -214,    0,  192,    0,  -34,    0,  131,    0,    0,
    0,    0,    0,    0,    0, -163,  -28,    0,    0,    0,
  237,  -24,  -22,  -19,    0,    0,    0,    0,  379,    0,
    0,    0,    0,    0,  367,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static int YYTABLESIZE=991;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
  126,   38,  265,  150,  146,  151,   75,  146,  215,  148,
  205,  147,  148,  250,  147,  133,  120,  134,  245,  246,
  132,    3,  146,  150,  158,   74,  150,  148,  135,  147,
    5,   77,  145,  149,  275,  146,  149,    6,   77,  158,
  204,  150,    8,  136,  147,   75,   77,  235,   10,  137,
  276,  149,   20,  138,   20,  139,  146,  274,  140,  233,
   11,  148,  234,  147,   74,   13,  179,  180,  133,  159,
  134,  281,  253,  257,   15,  150,  258,   77,  188,  189,
  190,  135,   20,  264,   75,  149,   14,  184,  146,   73,
  267,   22,   76,  148,   21,  147,  136,  156,   25,  155,
  174,  175,  137,   74,  202,  203,  138,  150,  139,   27,
  154,  140,   24,  209,  282,  152,   66,  149,   28,   29,
  153,   33,   35,   66,   32,   37,  291,   50,   73,   41,
  130,   76,   42,   48,   49,   44,   45,   51,   52,   53,
   48,   49,   66,  114,   51,   52,   53,  116,  117,  118,
  229,   60,   61,   62,  119,   77,   58,  191,  192,  193,
  194,   70,   75,   58,  121,  198,  199,   73,  200,  182,
   76,   72,  122,  229,  127,   69,  128,  129,   72,  251,
  141,   74,  143,  160,  142,  161,  241,  242,  144,  162,
  166,  167,  168,  169,   77,  170,  176,  173,  177,  183,
  213,   75,  208,   29,  206,  207,   66,  212,   66,   66,
  229,  123,  124,  216,  217,  219,  148,  149,  218,  221,
  220,  222,  223,  224,  225,  227,   48,   49,   50,  226,
   51,   52,   53,   54,  125,   55,   56,   57,  232,  146,
  146,  237,  240,   77,  148,   73,  147,  147,   76,   58,
   75,   59,   60,   61,   62,   63,   64,   65,   66,   67,
   68,   69,   70,   71,   72,   48,   49,   50,  149,   51,
   52,   53,   54,  238,   55,   56,   57,  243,  239,  244,
   73,  255,  247,  248,  256,  261,  268,   76,   58,  252,
   59,   60,   61,   62,   63,   64,   65,  266,   67,   68,
   69,   70,   71,   72,   48,   49,   50,  270,   51,   52,
   53,   54,  269,   55,   56,   57,  271,  272,  258,  277,
  279,  283,  284,  285,  286,  288,  287,   58,  289,   59,
   60,   61,   62,   63,   64,   65,   76,   67,   68,   69,
   70,   71,   72,   66,   66,   66,  292,   66,   66,   66,
   66,  293,   66,   66,   66,   77,    8,   38,   20,   20,
   88,   74,   75,   24,  131,  186,   66,  187,   66,   66,
   66,   66,   66,   66,   66,  263,   66,   66,   66,   66,
   66,   66,   48,   49,   50,  181,   51,   52,   53,   54,
  273,   55,   56,   57,  201,   23,   39,  231,    0,    0,
    0,    0,    0,    0,    0,   58,    0,   59,   60,   61,
   62,   63,   64,   65,    0,   67,   68,   69,   70,   71,
   72,   48,   49,    0,    0,   51,   52,   53,    0,    0,
    0,   56,   57,    0,    0,    0,    0,    0,    0,  134,
    0,  134,  134,  134,   58,  228,   59,    0,   76,    0,
    0,    0,    0,    0,   67,   68,   69,  134,  134,   72,
  134,    0,    0,    0,    0,  136,    0,  136,  136,  136,
   48,   49,    0,    0,   51,   52,   53,    0,    0,    0,
   56,   57,    0,  136,  136,    0,  136,    0,    0,    0,
    0,  134,    0,   58,    0,   59,    0,    0,    0,    0,
    0,    0,    0,   67,   68,   69,  112,    0,   72,   98,
  112,  112,  112,  112,  112,    0,  112,  136,    0,    0,
    0,    0,    0,  134,    0,    0,    0,    0,  112,  112,
  112,  112,    0,  113,    0,    0,    0,  113,  113,  113,
  113,  113,    0,  113,    0,    0,    0,    0,    0,  136,
  135,    0,  135,  135,  135,  113,  113,  113,  113,    0,
    0,  142,  112,    0,  142,    0,    0,    0,  135,  135,
    0,  135,    0,    0,    0,    0,    0,    0,  137,  142,
    0,  137,   48,   49,    0,    0,   51,   52,   53,  113,
    0,    0,   56,   57,  112,    0,  137,  137,    0,  137,
    0,    0,  135,    0,  130,   58,    0,   59,  130,  130,
  130,  130,  130,  142,  130,   67,   68,   69,    0,    0,
   72,  113,    0,    0,    0,  140,  130,  130,  140,  130,
  137,    0,    0,    0,  135,    0,    0,    0,  141,    0,
    0,  141,    0,  140,  140,  142,  140,    0,    0,    0,
    0,  143,    0,    0,  143,    0,  141,  141,    0,  141,
  130,    0,  137,    0,    0,  138,    0,    0,  138,  143,
  134,  134,  134,  134,  134,  134,    0,  140,    0,    0,
    0,    0,  134,  138,  138,    0,  138,    0,    0,    0,
  141,    0,  130,    0,    0,    0,  136,  136,  136,  136,
  136,  136,    0,  143,    0,    0,    0,  139,  136,  140,
  139,    0,    0,    0,    0,    0,    0,  138,    0,    0,
    0,    0,  141,    0,    0,  139,  139,    0,  139,    0,
    0,    0,    0,    0,    0,  143,  144,    0,    0,  144,
    0,  112,  112,  112,  112,  112,  112,  145,    0,  138,
  145,    0,    0,  112,  144,    0,    0,    0,    0,  139,
    0,    0,    0,    0,    0,  145,    0,    0,  113,  113,
  113,  113,  113,  113,    0,    0,    0,    0,    0,    0,
  113,  135,  135,  135,  135,  135,  135,    0,  144,    0,
    0,  139,  142,  135,    0,  142,  142,  142,    0,  145,
    0,    0,    0,    0,  142,    0,    0,    0,    0,  137,
  137,  137,  137,  137,  137,    0,    0,    0,    0,    0,
  144,  137,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  145,    0,    0,    0,    0,    0,    0,    0,  130,
  130,  130,  130,  130,  130,    0,    0,    0,    0,    0,
    0,  130,    0,    0,    0,    0,  140,  140,  140,  140,
  140,  140,  157,    0,    0,    0,    0,    0,  140,  141,
  141,  141,  141,  141,  141,    0,    0,    0,    0,    0,
    0,  141,  143,    0,    0,  143,  143,  143,    0,    0,
    0,    0,    0,    0,  143,    0,  138,  138,  138,  138,
  138,  138,    0,    0,    0,    0,    0,    0,  138,    0,
    0,    0,    0,    0,  185,  185,  185,  185,  185,  185,
  185,  185,  185,  195,  196,  197,  185,  185,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  139,  139,
  139,  139,  139,  139,    0,    0,    0,    0,    0,    0,
  139,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  144,    0,    0,
  144,  144,  144,    0,    0,    0,    0,    0,  145,  144,
    0,  145,  145,  145,    0,    0,    0,    0,    0,    0,
  145,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   59,   93,   44,   60,   41,   62,   40,   44,  172,   41,
   61,   41,   44,  228,   44,   78,   47,   78,  222,  223,
   75,  257,   59,   41,   46,   59,   44,   59,   78,   59,
  123,   44,  272,   41,   41,  275,   44,  258,   33,   46,
   91,   59,  260,   78,  284,   40,   59,  211,   91,   78,
  265,   59,  263,   78,  265,   78,   93,  261,   78,   41,
  259,   93,   44,   93,   59,   58,  121,  122,  131,   91,
  131,  275,  236,  292,  296,   93,  295,   33,  145,  146,
  147,  131,  294,  125,   40,   93,  125,  142,  125,  123,
  254,   93,  126,  125,  295,  125,  131,   43,  278,   45,
  293,  294,  131,   59,  159,  160,  131,  125,  131,   59,
   37,  131,   91,  168,  278,   42,   33,  125,  123,  295,
   47,  281,  261,   40,  123,  123,  290,  262,  123,  123,
  125,  126,  283,  260,  261,   58,  295,  264,  265,  266,
  260,  261,   59,  280,  264,  265,  266,  123,  293,   58,
  205,  286,  287,  288,  265,   33,  283,  148,  149,  150,
  151,  296,   40,  283,   40,  155,  156,  123,  295,  125,
  126,  298,   40,  228,   59,  295,   59,   40,  298,  234,
   59,   59,  276,   91,   61,   59,  217,  218,  277,  295,
   91,  125,   40,  123,   33,  282,   40,   59,  263,   41,
  279,   40,   41,  295,   44,  293,  123,  123,  125,  126,
  265,  270,  271,  266,   59,  262,  273,  274,   59,  125,
   58,   41,   41,  295,   93,  293,  260,  261,  262,   93,
  264,  265,  266,  267,  293,  269,  270,  271,   93,  276,
  277,  123,   58,   33,  276,  123,  276,  277,  126,  283,
   40,  285,  286,  287,  288,  289,  290,  291,  292,  293,
  294,  295,  296,  297,  298,  260,  261,  262,  276,  264,
  265,  266,  267,  125,  269,  270,  271,  264,  125,   91,
  123,  293,  299,   93,   41,  268,   59,  126,  283,  125,
  285,  286,  287,  288,  289,  290,  291,  125,  293,  294,
  295,  296,  297,  298,  260,  261,  262,   93,  264,  265,
  266,  267,  123,  269,  270,  271,  123,   93,  295,  125,
  265,   40,  125,  262,  125,   41,  264,  283,  123,  285,
  286,  287,  288,  289,  290,  291,  126,  293,  294,  295,
  296,  297,  298,  260,  261,  262,  125,  264,  265,  266,
  267,  125,  269,  270,  271,   33,   93,  125,  265,  263,
  295,   59,   40,   93,   73,  143,  283,  144,  285,  286,
  287,  288,  289,  290,  291,  247,  293,  294,  295,  296,
  297,  298,  260,  261,  262,  129,  264,  265,  266,  267,
  260,  269,  270,  271,  158,   17,   30,  206,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  283,   -1,  285,  286,  287,
  288,  289,  290,  291,   -1,  293,  294,  295,  296,  297,
  298,  260,  261,   -1,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   43,   44,   45,  283,  123,  285,   -1,  126,   -1,
   -1,   -1,   -1,   -1,  293,  294,  295,   59,   60,  298,
   62,   -1,   -1,   -1,   -1,   41,   -1,   43,   44,   45,
  260,  261,   -1,   -1,  264,  265,  266,   -1,   -1,   -1,
  270,  271,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,   93,   -1,  283,   -1,  285,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  293,  294,  295,   37,   -1,  298,   40,
   41,   42,   43,   44,   45,   -1,   47,   93,   -1,   -1,
   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   37,   -1,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,  125,
   41,   -1,   43,   44,   45,   59,   60,   61,   62,   -1,
   -1,   41,   93,   -1,   44,   -1,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   41,   59,
   -1,   44,  260,  261,   -1,   -1,  264,  265,  266,   93,
   -1,   -1,  270,  271,  125,   -1,   59,   60,   -1,   62,
   -1,   -1,   93,   -1,   37,  283,   -1,  285,   41,   42,
   43,   44,   45,   93,   47,  293,  294,  295,   -1,   -1,
  298,  125,   -1,   -1,   -1,   41,   59,   60,   44,   62,
   93,   -1,   -1,   -1,  125,   -1,   -1,   -1,   41,   -1,
   -1,   44,   -1,   59,   60,  125,   62,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   44,   -1,   59,   60,   -1,   62,
   93,   -1,  125,   -1,   -1,   41,   -1,   -1,   44,   59,
  272,  273,  274,  275,  276,  277,   -1,   93,   -1,   -1,
   -1,   -1,  284,   59,   60,   -1,   62,   -1,   -1,   -1,
   93,   -1,  125,   -1,   -1,   -1,  272,  273,  274,  275,
  276,  277,   -1,   93,   -1,   -1,   -1,   41,  284,  125,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,  125,   -1,   -1,   59,   60,   -1,   62,   -1,
   -1,   -1,   -1,   -1,   -1,  125,   41,   -1,   -1,   44,
   -1,  272,  273,  274,  275,  276,  277,   41,   -1,  125,
   44,   -1,   -1,  284,   59,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   59,   -1,   -1,  272,  273,
  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,
  284,  272,  273,  274,  275,  276,  277,   -1,   93,   -1,
   -1,  125,  272,  284,   -1,  275,  276,  277,   -1,   93,
   -1,   -1,   -1,   -1,  284,   -1,   -1,   -1,   -1,  272,
  273,  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,
  125,  284,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,
  273,  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,
   -1,  284,   -1,   -1,   -1,   -1,  272,  273,  274,  275,
  276,  277,   91,   -1,   -1,   -1,   -1,   -1,  284,  272,
  273,  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,
   -1,  284,  272,   -1,   -1,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,   -1,  272,  273,  274,  275,
  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,  284,   -1,
   -1,   -1,   -1,   -1,  143,  144,  145,  146,  147,  148,
  149,  150,  151,  152,  153,  154,  155,  156,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,
  274,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,
  284,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,   -1,   -1,
  275,  276,  277,   -1,   -1,   -1,   -1,   -1,  272,  284,
   -1,  275,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,
  284,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=299;
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
"INIT","ROUND_END","ROUND_BEGIN","ROUND","TURN","DYING","IS","DEBUG",
"DECLR_INT","DECLR_STR","DECLR_BOOL","RETURN","BREAK","CONTINUE","VOID",
"INTEGER","STRING","ID","CARD","FOREACH","ROUNDSUMMARY","IN",
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
"$$5 :",
"character_df : CHARACTER_DF $$5 '[' characters_df_content ']'",
"characters_df_content : characters_df_content character_df_content",
"characters_df_content : character_df_content",
"$$6 :",
"character_df_content : ID '{' $$6 variable_list skill_df '}'",
"variable_list : ID ':' INTEGER ';' variable_list",
"variable_list : ID ':' STRING ';' variable_list",
"variable_list :",
"skill_df : SKILL ':' '[' skill_lists ']'",
"skill_df : SKILL ':' '[' VOID ']'",
"skill_lists : skill_list skill_lists",
"skill_lists : skill_list",
"$$7 :",
"skill_list : ID '{' METHOD '(' PLAYER DEALER ')' '{' $$7 AUGED_STATEMENT_LIST '}' '}'",
"$$8 :",
"init_block : INIT '{' $$8 AUGED_STATEMENT_LIST '}'",
"round_block : ROUND '{' round_begin_block turn_block round_end_block '}'",
"$$9 :",
"round_begin_block : ROUND_BEGIN '{' $$9 AUGED_STATEMENT_LIST '}'",
"$$10 :",
"turn_block : TURN '{' $$10 AUGED_STATEMENT_LIST '}'",
"$$11 :",
"round_end_block : ROUND_END '{' $$11 AUGED_STATEMENT_LIST '}'",
"$$12 :",
"dying_block : DYING '{' $$12 AUGED_STATEMENT_LIST '}'",
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
"STATEMENT_LIST : ExpressionStatement",
"STATEMENT_LIST : STATEMENT_LIST ExpressionStatement",
"STATEMENT_LIST : ForeachStatement",
"STATEMENT_LIST : STATEMENT_LIST ForeachStatement",
"STATEMENT_LIST : ReturnStatement",
"STATEMENT_LIST : STATEMENT_LIST ReturnStatement",
"STATEMENT_LIST : JumpStatement",
"STATEMENT_LIST : STATEMENT_LIST JumpStatement",
"ExpressionStatement : Expression ';'",
"JumpStatement : BREAK ';'",
"JumpStatement : CONTINUE ';'",
"JumpStatement : RETURN ';'",
"ReturnStatement : RETURN TRUE",
"ReturnStatement : RETURN FALSE",
"ReturnStatement : RETURN INTEGER",
"ForeachStatement : FOREACH '(' TypeSpecifier ID IN QualifiedName ')' Block",
"EmptyStatement : ';'",
"IterationStatement : WHILE '(' Expression ')' Block",
"$$13 :",
"SelectionStatement : IF '(' Expression ')' Block $$13",
"$$14 :",
"SelectionStatement : IF '(' Expression ')' Block ELSE Block $$14",
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
"QualifiedName : QualifiedName '.' KeyName",
"QualifiedName : ID",
"QualifiedName : KeyName",
"KeyName : METHOD",
"KeyName : GAME_NM",
"KeyName : PLAYER_C",
"KeyName : MAX_ROUND",
"KeyName : DEALER",
"KeyName : DYING",
"KeyName : ROUNDSUMMARY",
"PrimaryExpression : QualifiedName",
"PrimaryExpression : ComplexPrimary",
"ComplexPrimary : '(' Expression ')'",
"ComplexPrimary : ComplexPrimaryNoParenthesis",
"ComplexPrimaryNoParenthesis : INTEGER",
"ComplexPrimaryNoParenthesis : STRING",
"ComplexPrimaryNoParenthesis : TRUE",
"ComplexPrimaryNoParenthesis : FALSE",
"ComplexPrimaryNoParenthesis : ArrayAccess",
"ComplexPrimaryNoParenthesis : MethodCall",
"ComplexPrimaryNoParenthesis : DEBUG",
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
"EqualityExpression : EqualityExpression IS RelationalExpression",
"ConditionalAndExpression : EqualityExpression",
"ConditionalAndExpression : ConditionalAndExpression OP_LAND EqualityExpression",
"ConditionalOrExpression : ConditionalAndExpression",
"ConditionalOrExpression : ConditionalOrExpression OP_LOR ConditionalAndExpression",
"Expression : ConditionalOrExpression",
"Expression : UnaryExpression '=' Expression",
};

//#line 625 "GameWizard.y"

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


  public Object pre_list = null;

  public void yyerror (String error) {
    System.err.println ("Error: " + error);
    System.exit(1);
  }


	public String genCardDownCast(String typeName, String varName, String postfix){
		List<String> nameList = SymbolTable.
			giveMeNameOfSomesTypeFromGloBlock(SymbolType.CARD);
		StringBuffer ret = new StringBuffer();
		ret.append(typeName+" tmp = null;\n");
		for(String name : nameList){
			ret.append("if( "+varName+" instanceof "+name+")\n\t");
			ret.append("tmp = (("+name+")"+varName+")."+postfix+";\n");
		}
		return ret.toString();
	}

  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }


  public static void main(String args[]) throws IOException {
    System.out.println("BYACC/Java with JFlex compiler for GameWizzard");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Expression: ");
	    yyparser = new Parser(new InputStreamReader(System.in));
    }


    yyparser.yyparse();
    
    System.out.println("Compiling Succeed!\nHave a nice day");
  }
//#line 759 "Parser.java"
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
//#line 132 "GameWizard.y"
{/*the global block*/SymbolTable.pushNewBlock(); SymbolTable.addKeywordsAndBuildIn();}
break;
case 2:
//#line 132 "GameWizard.y"
{curScope=null;}
break;
case 3:
//#line 133 "GameWizard.y"
{
		String methods = val_peek(2).sval+val_peek(1).sval+val_peek(0).sval;
		Util.writeGameJava(val_peek(6).sval,methods);
		
	}
break;
case 4:
//#line 139 "GameWizard.y"
{yyval.sval=val_peek(1).sval; System.out.println("game_df");}
break;
case 5:
//#line 144 "GameWizard.y"
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
//#line 153 "GameWizard.y"
{System.out.println("2");}
break;
case 7:
//#line 155 "GameWizard.y"
{System.out.println("1");}
break;
case 8:
//#line 156 "GameWizard.y"
{System.out.println("3");}
break;
case 9:
//#line 158 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CARD);}
break;
case 10:
//#line 158 "GameWizard.y"
{ SymbolTable.pushNewBlock();}
break;
case 11:
//#line 160 "GameWizard.y"
{
	if(!Util.checkVarListSame(pre_list, val_peek(10).obj)){
		yyerror("Variables in all the cards must be the same!");	
	}
	pre_list = val_peek(10).obj;
	System.out.println("======================================================finished card_df_content"); 
	SymbolTable.popBlock(); 
	Util.writeCardsJava(val_peek(13).sval.toString(),val_peek(10).obj,val_peek(2).sval); }
break;
case 12:
//#line 169 "GameWizard.y"
{pre_list=null;}
break;
case 13:
//#line 169 "GameWizard.y"
{System.out.println("=========================character_df");}
break;
case 14:
//#line 171 "GameWizard.y"
{System.out.println("=========================characters_df_content");}
break;
case 15:
//#line 172 "GameWizard.y"
{System.out.println("=========================characters_df_content");}
break;
case 16:
//#line 174 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CHARACTER);}
break;
case 17:
//#line 178 "GameWizard.y"
{
	
	if(!Util.checkVarListSame(pre_list, val_peek(2).obj)){
		yyerror("Variables in all the characters must be the same!");	
	}
	pre_list = val_peek(2).obj;
	Util.writeCharacterJava(val_peek(5).sval,val_peek(2).obj,val_peek(1).obj); 
	System.out.println("character_df_content");}
break;
case 18:
//#line 188 "GameWizard.y"
{
	    checkDulDeclare(val_peek(4).sval);
	    SymbolTable.addRecordToCardCharacterBlock(curScope, val_peek(4).sval, SymbolType.CARD_VARIABLE);	    
	    ArrayList<String> result= new ArrayList<String>();
            result.add("Integer"); result.add(val_peek(4).sval);result.add(String.valueOf(val_peek(2).ival));
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            
            result.addAll(x1); yyval.obj=result;  System.out.println("variable_list");
                }
break;
case 19:
//#line 198 "GameWizard.y"
{
   	    checkDulDeclare(val_peek(4).sval);
	    SymbolTable.addRecordToCardCharacterBlock(curScope, val_peek(4).sval, SymbolType.CARD_VARIABLE);	    
	    ArrayList<String> result= new ArrayList<String>();
            result.add("String"); result.add(val_peek(4).sval);result.add(val_peek(2).sval);
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            result.addAll(x1); yyval.obj=result;   System.out.println("variable_list");
                }
break;
case 20:
//#line 206 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>(); yyval.obj= result;   System.out.println("variable_list");}
break;
case 21:
//#line 211 "GameWizard.y"
{yyval.obj = val_peek(1).obj;}
break;
case 22:
//#line 215 "GameWizard.y"
{ArrayList<String> ret = new ArrayList<String>(); yyval.obj=ret;}
break;
case 23:
//#line 218 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)(val_peek(1).obj);
                                    ArrayList<String> x2 = (ArrayList<String>)(val_peek(0).obj);
                                    result.addAll(x1); result.addAll(x2); yyval.obj= result;}
break;
case 24:
//#line 222 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 25:
//#line 227 "GameWizard.y"
{checkDulDeclare(val_peek(7).sval);SymbolTable
.addRecordToCardCharacterBlock(curScope, val_peek(7).sval, SymbolType.CHARACTER_SKILL);SymbolTable.pushNewBlock();}
break;
case 26:
//#line 229 "GameWizard.y"
{
	    SymbolTable.popBlock();
	    ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(11).sval);
            result.add(val_peek(2).sval);
            yyval.obj=result;
        }
break;
case 27:
//#line 239 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 28:
//#line 240 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void init(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
		System.out.println("init_block statement");
	}
break;
case 29:
//#line 250 "GameWizard.y"
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
case 30:
//#line 263 "GameWizard.y"
{SymbolTable.pushNewBlock(); System.out.println("before round begin");}
break;
case 31:
//#line 263 "GameWizard.y"
{ SymbolTable.popBlock();yyval.sval=val_peek(1).sval; System.out.println("round_begin");}
break;
case 32:
//#line 267 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 33:
//#line 267 "GameWizard.y"
{SymbolTable.popBlock();yyval.sval=val_peek(1).sval;}
break;
case 34:
//#line 271 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 35:
//#line 271 "GameWizard.y"
{SymbolTable.popBlock(); yyval.sval=val_peek(1).sval;}
break;
case 36:
//#line 275 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 37:
//#line 276 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void dying(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
	}
break;
case 38:
//#line 284 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 39:
//#line 285 "GameWizard.y"
{yyval.sval="";}
break;
case 40:
//#line 289 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(0).sval;}
break;
case 41:
//#line 290 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 42:
//#line 291 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(0).sval;}
break;
case 43:
//#line 292 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 44:
//#line 293 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(0).sval;}
break;
case 45:
//#line 294 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 46:
//#line 295 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(0).sval;}
break;
case 47:
//#line 296 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 48:
//#line 297 "GameWizard.y"
{System.out.println("expression statement");yyval.sval=val_peek(0).sval;}
break;
case 49:
//#line 298 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 50:
//#line 299 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 51:
//#line 300 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 52:
//#line 301 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 53:
//#line 302 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 54:
//#line 303 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 55:
//#line 304 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 56:
//#line 309 "GameWizard.y"
{yyval.sval=val_peek(1).sval+";";}
break;
case 57:
//#line 314 "GameWizard.y"
{yyval.sval="break;";}
break;
case 58:
//#line 315 "GameWizard.y"
{yyval.sval="continue;";}
break;
case 59:
//#line 316 "GameWizard.y"
{yyval.sval="return;";}
break;
case 60:
//#line 321 "GameWizard.y"
{yyval.sval="return true";}
break;
case 61:
//#line 322 "GameWizard.y"
{yyval.sval="return false";}
break;
case 62:
//#line 323 "GameWizard.y"
{yyval.sval="return "+val_peek(0).ival;}
break;
case 63:
//#line 335 "GameWizard.y"
{
		String s = "for("+val_peek(5).sval+" "+val_peek(4).sval+":"+val_peek(2).sval+")\n"+val_peek(0).sval;
		yyval.sval=s;
	}
break;
case 64:
//#line 342 "GameWizard.y"
{yyval.sval=";\n";}
break;
case 65:
//#line 347 "GameWizard.y"
{System.out.println("8");String s = "while("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 66:
//#line 353 "GameWizard.y"
{System.out.println("6");String s = "if("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 67:
//#line 354 "GameWizard.y"
{System.out.println("selection statement");}
break;
case 68:
//#line 356 "GameWizard.y"
{System.out.println("7");String s = "if("+val_peek(4).sval+")\n"+val_peek(2).sval+"\nelse\n"+val_peek(0).sval+""; yyval.sval=s;}
break;
case 69:
//#line 357 "GameWizard.y"
{System.out.println("selection statement");}
break;
case 70:
//#line 362 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 71:
//#line 366 "GameWizard.y"
{System.out.println("3");yyval.sval=val_peek(1).sval+";\n";}
break;
case 72:
//#line 371 "GameWizard.y"
{System.out.println(val_peek(0).sval);yyval.sval=val_peek(0).sval;}
break;
case 73:
//#line 372 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 74:
//#line 377 "GameWizard.y"
{
	System.out.println("FieldVariableDeclaration");
	String[] arr = val_peek(0).sval.split("=");
	String var = arr[0];
	String val = arr[1];

	checkDulDeclare(var);
	if(val_peek(1).sval.equals("CardBase ")){
		yyval.sval=val_peek(1).sval+var+" = "+val;
		SymbolTable.addRecordToCurrentBlock(var,SymbolType.LOCAL_CARD_DECLARE);
	}else{
		SymbolTable.addRecordToCurrentBlock(var,SymbolType.LOCAL_VARIABLE);
		if(val.indexOf('.')!=-1){
			if(val.matches("[^a-zA-Z0-9.]")){
				yyerror("Syntax Error, ID.ID must be the only element on the right side of =");
			}
			int i = val.indexOf(".");
			String indicator = val.substring(0,i);
			String postfix = val.substring(i+1,val.length());
			SymbolType type;
			type = SymbolTable.lookUpSymbolType(indicator);
			/*if(type==SymbolType.LOCAL_CARD_DECLARE){*/
			/*	$$=genCardDownCast($1,indicator,postfix)+$1+var+" = tmp";*/
			/*}else*/
				yyval.sval=val_peek(1).sval+val_peek(0).sval;
		}
		else
			yyval.sval=val_peek(1).sval+val_peek(0).sval;
	}
  }
break;
case 75:
//#line 410 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 76:
//#line 411 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 77:
//#line 415 "GameWizard.y"
{checkDulDeclare(val_peek(0).sval);System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 78:
//#line 416 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'='+val_peek(0).sval;}
break;
case 79:
//#line 420 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 80:
//#line 421 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 81:
//#line 425 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 82:
//#line 426 "GameWizard.y"
{yyval.sval='{'+val_peek(1).sval+'}';}
break;
case 83:
//#line 430 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 84:
//#line 431 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 85:
//#line 435 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 86:
//#line 439 "GameWizard.y"
{yyval.sval="{\n"+val_peek(1).sval+"}\n";}
break;
case 87:
//#line 440 "GameWizard.y"
{yyval.sval="{\n}\n";}
break;
case 88:
//#line 453 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 89:
//#line 454 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 90:
//#line 459 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 91:
//#line 460 "GameWizard.y"
{yyval.sval="CardBase ";}
break;
case 92:
//#line 461 "GameWizard.y"
{yyval.sval="Player ";}
break;
case 93:
//#line 467 "GameWizard.y"
{yyval.sval="Boolean ";}
break;
case 94:
//#line 468 "GameWizard.y"
{yyval.sval="Integer ";}
break;
case 95:
//#line 469 "GameWizard.y"
{yyval.sval="String ";}
break;
case 96:
//#line 474 "GameWizard.y"
{yyval.sval=val_peek(3).sval+"("+val_peek(1).sval+")";}
break;
case 97:
//#line 475 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"()";}
break;
case 98:
//#line 479 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 99:
//#line 484 "GameWizard.y"
{
		SymbolType type = SymbolTable.lookUpSymbolType(val_peek(0).sval);
		if(type==SymbolType.CARD || type==SymbolType.CHARACTER){
			yyval.sval="new "+val_peek(0).sval+"()";	
		}else{
			yyval.sval=val_peek(0).sval;
		}
	}
break;
case 100:
//#line 492 "GameWizard.y"
{yyval.sval=val_peek(2).sval+","+val_peek(0).sval;}
break;
case 101:
//#line 497 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 102:
//#line 498 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 103:
//#line 500 "GameWizard.y"
{
		SymbolType type = SymbolTable.lookUpSymbolType(val_peek(0).sval);
                if(type==SymbolType.GAME_JAVA){
                        yyval.sval="Game."+val_peek(0).sval;
                }else{
			yyval.sval=val_peek(0).sval;
		}
	}
break;
case 104:
//#line 508 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 105:
//#line 513 "GameWizard.y"
{yyval.sval="method";}
break;
case 106:
//#line 514 "GameWizard.y"
{yyval.sval="Game.game_name";}
break;
case 107:
//#line 515 "GameWizard.y"
{yyval.sval="Game.num_of_players";}
break;
case 108:
//#line 516 "GameWizard.y"
{yyval.sval="Game.maximum_round";}
break;
case 109:
//#line 517 "GameWizard.y"
{yyval.sval="dealer";}
break;
case 110:
//#line 518 "GameWizard.y"
{yyval.sval="Game.dying";}
break;
case 111:
//#line 519 "GameWizard.y"
{yyval.sval="Game.roundSummary";}
break;
case 112:
//#line 524 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 113:
//#line 525 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 114:
//#line 530 "GameWizard.y"
{yyval.sval="("+val_peek(1).sval+")";}
break;
case 115:
//#line 531 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 116:
//#line 536 "GameWizard.y"
{Integer tmp = new Integer(val_peek(0).ival);yyval.sval=tmp.toString();}
break;
case 117:
//#line 537 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 118:
//#line 538 "GameWizard.y"
{yyval.sval="true";}
break;
case 119:
//#line 539 "GameWizard.y"
{yyval.sval="false";}
break;
case 120:
//#line 540 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 121:
//#line 541 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 122:
//#line 542 "GameWizard.y"
{System.out.println("debuging");}
break;
case 123:
//#line 551 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 124:
//#line 552 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 125:
//#line 558 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 126:
//#line 562 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 127:
//#line 563 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 128:
//#line 567 "GameWizard.y"
{yyval.sval="~";}
break;
case 129:
//#line 568 "GameWizard.y"
{yyval.sval="!";}
break;
case 130:
//#line 573 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 131:
//#line 574 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"*"+val_peek(0).sval;}
break;
case 132:
//#line 575 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"/"+val_peek(0).sval;}
break;
case 133:
//#line 576 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"%"+val_peek(0).sval;}
break;
case 134:
//#line 581 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 135:
//#line 582 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"+"+val_peek(0).sval;}
break;
case 136:
//#line 583 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"-"+val_peek(0).sval;}
break;
case 137:
//#line 588 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 138:
//#line 589 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<"+val_peek(0).sval;}
break;
case 139:
//#line 590 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">"+val_peek(0).sval;}
break;
case 140:
//#line 591 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<="+val_peek(0).sval;}
break;
case 141:
//#line 592 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">="+val_peek(0).sval;}
break;
case 142:
//#line 597 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 143:
//#line 598 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"=="+val_peek(0).sval;}
break;
case 144:
//#line 599 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"!="+val_peek(0).sval;}
break;
case 145:
//#line 600 "GameWizard.y"
{yyval.sval=val_peek(2).sval+" instanceof "+val_peek(0).sval;}
break;
case 146:
//#line 604 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 147:
//#line 605 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"&&"+val_peek(0).sval;}
break;
case 148:
//#line 610 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 149:
//#line 611 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"||"+val_peek(0).sval;}
break;
case 150:
//#line 616 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 151:
//#line 617 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"="+val_peek(0).sval;}
break;
//#line 1626 "Parser.java"
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
