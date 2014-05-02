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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
   48,   51,    0,    1,    2,   49,   52,   52,   54,   55,
   53,   50,   56,   56,   58,   57,    3,    3,    3,   38,
   38,   39,   39,   59,   40,   60,   41,   42,   61,   43,
   62,   45,   63,   44,   64,   46,   47,   47,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,   37,    7,
    6,    6,   24,   26,   25,   25,   27,   36,   36,   35,
   35,   34,   34,   33,   33,   32,   32,   28,   31,   31,
   29,   29,   30,   23,   23,   23,   18,   18,   16,   16,
   19,   19,   20,   20,   20,   20,   20,   20,   22,   21,
   21,    8,   15,   15,   17,   17,   13,   13,   13,   13,
   14,   14,   14,   12,   12,   12,   12,   12,   11,   11,
   11,   10,   10,    9,    9,    5,    5,
};
final static short yylen[] = {                            2,
    0,    0,    8,    4,   12,    4,    2,    1,    0,    0,
   15,    4,    2,    1,    0,    6,    5,    5,    0,    5,
    5,    2,    1,    0,   12,    0,    5,    6,    0,    5,
    0,    5,    0,    5,    0,    5,    1,    1,    1,    2,
    1,    2,    1,    2,    1,    2,    1,    2,    1,    5,
    5,    7,    1,    2,    1,    1,    2,    1,    3,    1,
    3,    1,    4,    1,    3,    1,    3,    1,    3,    2,
    1,    4,    1,    1,    1,    1,    1,    3,    1,    1,
    3,    1,    1,    1,    1,    1,    1,    1,    3,    4,
    4,    1,    1,    2,    1,    1,    1,    3,    3,    3,
    1,    3,    3,    1,    3,    3,    3,    3,    1,    3,
    3,    1,    3,    1,    3,    1,    3,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    2,    0,    4,    0,    0,    0,    0,    0,    0,
    0,    6,    7,    0,    0,   14,    0,    0,    0,    9,
   15,   12,   13,   26,    0,    0,    0,    0,    0,    0,
    0,    0,    3,    0,    0,    0,    0,    0,    0,   85,
   86,   75,   76,   74,   38,   83,   84,   77,    0,   49,
    0,   95,   96,    0,   47,   39,   43,    0,    0,    0,
    0,    0,    0,    0,   92,   93,    0,    0,    0,   82,
   87,   88,   73,   41,    0,   53,   55,   56,    0,    0,
   68,   45,    0,    0,    0,   35,    0,    0,    0,    0,
    0,    0,    0,   70,    0,    0,   48,   40,   44,   42,
   46,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   94,    0,    0,    0,    0,
   54,   62,    0,   58,    0,    0,   27,   29,    0,    0,
    0,    0,    0,    0,    0,    0,   16,    0,    0,   69,
   81,  117,   97,    0,    0,    0,    0,    0,    0,    0,
    0,   98,   99,  100,    0,    0,    0,   78,    0,   89,
    0,    0,    0,    0,    0,   31,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   90,   91,    0,    0,
   64,   61,   59,   72,    0,    0,   33,   28,   36,    0,
   17,   18,    0,    0,    0,    0,    0,    0,   50,   63,
    0,   66,   30,    0,    0,    0,    0,   21,    0,   20,
   22,    0,   65,    0,   32,    0,    5,   10,    0,   52,
   67,   34,    0,    0,    0,    0,    0,    0,   11,    0,
   24,    0,    0,    0,   25,
};
final static short yydgoto[] = {                          1,
    4,    9,   46,   64,   65,   66,   67,   68,   69,   70,
   71,   72,   73,   74,   75,   76,   77,   78,   79,   80,
   81,   82,   83,   84,   85,   86,   87,   88,   89,   90,
   91,  211,  192,  133,  134,  135,   92,  101,  206,  207,
   28,   36,   95,  178,  140,   43,   93,    2,    7,   12,
   19,   16,   17,   38,  233,   25,   26,   39,  242,   40,
  175,  196,  215,  141,
};
final static short yysindex[] = {                         0,
    0, -225,  -68, -200, -192,  -18, -184,   18,  -42, -206,
   -5,    0, -191,    0, -190,   14, -206, -182, -167,   65,
    8,    0,    0,    9,  -92,    0,   10, -147, -126,    0,
    0,    0,    0,    0,   15, -141,   85, -144, -144,  905,
 -133,   25,    0, -139,   92, -114, -111,  113,  117,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  937,    0,
   86,    0,    0,  993,    0,    0,    0,   98, -115, -113,
 -239,  -57,   75,    5,    0,    0,   86,  -26,  -25,    0,
    0,    0,    0,    0,  106,    0,    0,    0, -123,   78,
    0,    0,   41,   47, -110,    0,  112, -264,  133,  116,
   52,   86,   86,    0,  945,  140,    0,    0,    0,    0,
    0,   86,   86,   86,   86,   86,   86,   86,   86,   86,
   86,   86,   86,   86,   86,    0,   86, -108,   86, -107,
    0,    0,  -39,    0,  141, -100,    0,    0,   67,  -88,
  905,  -74,  142,  143,  -63,  109,    0,  163,  164,    0,
    0,    0,    0, -113, -239,  -57,  -57,    5,    5,    5,
    5,    0,    0,    0,   75,   75,  114,    0,  115,    0,
  -82,   70, -123,  120,  905,    0,   95,   84,   90,  162,
 -144, -144,  -41, -253,  101,  101,    0,    0,  132,   70,
    0,    0,    0,    0,  102,  905,    0,    0,    0,  -62,
    0,    0,  190,  152,  110,  153,  -55,  -20,    0,    0,
  -38,    0,    0,  122,  905,  191,  126,    0,  -11,    0,
    0,  101,    0,   70,    0,  134,    0,    0,  222,    0,
    0,    0,  905,    3,  139,    4,  145,  225,    0,  144,
    0,  905,  155,  156,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  189,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   19,   20,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  160,    0,    0,    0,   37,  486,  842,
  769,  511,   61,  170,    0,    0,    0,  -33,    2,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   -1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -21,    0,  235,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  867,  811,  549,  643,  355,  424,  449,
  474,    0,    0,    0,   96,  135,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -194, -194,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  204,  982,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -37,  239,  367,  -51,  -49,  533,    0,  186,
  187,  -75,  -36,   -4,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -48,    0,    0,    0,    0,    0,    0,
 -155,    0, -171,    0,  127,    0,  -46,    0,  108,    0,
    0,    0,    0,    0,    0,    0, -124,    0,    0,    0,
    0,  285,    0,    0,    0,    0,  278,    0,    0,    0,
    0,    0,    0,    0,
};
final static int YYTABLESIZE=1283;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         79,
   32,   47,  119,   79,  120,  224,   79,   79,   79,   79,
   79,   79,  108,   79,  109,  110,  179,  111,  212,  128,
  130,  172,   60,  143,  144,   79,   79,   79,   79,  208,
  209,    3,  115,  204,   80,  116,  205,   60,   80,  156,
  157,   80,   80,   80,   80,   80,   80,  125,   80,  124,
  195,  171,  231,  108,    5,  109,  110,    6,  111,   79,
   80,   80,   80,   80,  127,  129,  230,    8,   19,   97,
   19,  214,   10,   97,   11,   13,   97,   97,   97,   97,
   97,   97,   14,   97,   15,   18,  223,  165,  166,   79,
  226,   79,   79,  101,   80,   97,   97,   20,   97,   21,
  101,  101,   63,  101,  101,  101,   22,   24,  235,   61,
   27,  123,  158,  159,  160,  161,  121,  243,   63,  101,
  101,  122,  101,   29,   80,   61,   80,   80,  103,   97,
   30,   31,   34,   35,   37,  103,  103,   41,  103,  103,
  103,   42,   44,  201,  202,   45,   94,   96,   97,   98,
   99,  100,  102,  101,  103,  103,  103,  103,  112,   97,
  113,   97,   97,  114,  131,  137,  132,  102,  136,  138,
  142,  139,  145,  146,  102,  102,  147,  102,  102,  102,
  151,  168,  170,  101,  173,  101,  101,  174,  103,  176,
  177,  180,  190,  102,  102,   62,  102,   24,  183,  184,
  181,  182,  104,  185,  186,  189,  187,  188,  198,  104,
  104,   62,  194,  104,  199,  117,  118,  197,  103,  200,
  103,  103,  203,   59,  210,  216,  213,  102,  104,  104,
  217,  104,  219,   79,  205,   79,   79,   79,   79,   79,
   79,   79,   79,   79,  218,  220,  225,  222,  228,  227,
   79,   79,   79,  229,   79,   79,   79,  102,  232,  102,
  102,  234,  104,  237,  236,  240,  241,  238,   80,  239,
   80,   80,   80,   80,   80,   80,   80,   80,   80,  244,
  245,    8,   19,   19,   37,   80,   80,   80,   71,   80,
   80,   80,  104,   57,  104,  104,   23,  105,  154,  193,
  155,   23,   33,   97,    0,   97,   97,   97,   97,   97,
   97,   97,   97,   97,  221,    0,    0,    0,    0,    0,
   97,   97,   97,    0,   97,   97,   97,  101,    0,  101,
  101,  101,  101,  101,  101,  101,  101,  101,    0,   50,
   51,    0,    0,    0,  101,  101,  101,    0,  101,  101,
  101,    0,    0,    0,    0,   50,   51,   56,   57,   58,
    0,    0,  103,    0,  103,  103,  103,  103,  103,  103,
  103,  103,  103,   56,   57,   58,    0,    0,    0,  103,
  103,  103,    0,  103,  103,  103,    0,  107,    0,    0,
    0,    0,    0,    0,  107,  107,    0,    0,  107,    0,
    0,  102,    0,  102,  102,  102,  102,  102,  102,  102,
  102,  102,    0,  107,  107,    0,  107,    0,  102,  102,
  102,    0,  102,  102,  102,    0,    0,  106,    0,    0,
  107,    0,    0,    0,    0,    0,  104,    0,  104,  104,
  104,  104,  104,  104,  104,  104,  104,  107,    0,    0,
    0,    0,    0,  104,  104,  104,  108,  104,  104,  104,
    0,    0,    0,  108,  108,    0,    0,  108,  148,  149,
    0,  107,    0,    0,    0,    0,    0,  107,  152,  107,
  107,  105,  108,  108,    0,  108,    0,    0,  105,  105,
    0,    0,  105,  167,    0,  169,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  106,  105,  105,    0,
  105,    0,    0,  106,  106,    0,  108,  106,  116,    0,
    0,    0,    0,    0,    0,  116,  116,    0,    0,  116,
    0,    0,  106,  106,    0,  106,    0,    0,  191,    0,
    0,  105,    0,  109,  116,    0,  108,    0,  108,  108,
  109,  109,    0,    0,  109,    0,  191,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  106,    0,    0,  109,
    0,  105,    0,  105,  105,    0,    0,    0,  116,    0,
    0,  110,    0,    0,    0,    0,    0,    0,  110,  110,
  191,    0,  110,    0,    0,    0,  106,    0,  106,  106,
    0,    0,    0,  109,    0,    0,    0,  110,  116,  126,
  116,  116,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  107,    0,  107,  107,  107,  107,  107,  107,  107,
  107,  107,    0,  109,    0,  109,  109,    0,  107,  107,
  107,  110,  107,  107,  107,  153,  153,  153,  153,  153,
  153,  153,  153,  162,  163,  164,  153,  153,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  110,    0,  110,  110,  111,    0,    0,    0,    0,
    0,    0,  111,  111,    0,    0,  111,    0,    0,    0,
  108,    0,  108,  108,  108,  108,  108,  108,  108,  108,
  108,  111,    0,    0,    0,    0,    0,  108,  108,  108,
    0,  108,  108,  108,    0,  105,    0,  105,  105,  105,
  105,  105,  105,  105,  105,  105,    0,    0,    0,    0,
    0,    0,  105,  105,  105,  111,  105,  105,  105,    0,
  106,    0,  106,  106,  106,  106,  106,  106,  106,  106,
  106,    0,  116,    0,  116,  116,  116,  106,  106,  106,
    0,  106,  106,  106,    0,  111,    0,  111,  111,  116,
  116,  116,    0,  116,  116,  116,    0,  109,    0,  109,
  109,  109,  109,    0,    0,  109,  109,  109,    0,    0,
    0,    0,    0,    0,  109,  109,  109,    0,  109,  109,
  109,  112,    0,    0,    0,    0,    0,    0,  112,  112,
    0,    0,  112,    0,    0,  110,    0,  110,  110,  110,
  110,    0,    0,  110,  110,  110,    0,  112,    0,    0,
    0,    0,  110,  110,  110,    0,  110,  110,  110,    0,
    0,    0,    0,  113,    0,    0,    0,    0,    0,    0,
  113,  113,    0,    0,  113,    0,    0,    0,    0,    0,
    0,  112,    0,    0,    0,    0,    0,    0,    0,  113,
    0,    0,    0,    0,  114,    0,    0,    0,    0,    0,
    0,  114,  114,    0,    0,  114,    0,    0,    0,    0,
    0,  112,    0,  112,  112,    0,    0,    0,    0,  115,
  114,    0,    0,  113,    0,    0,  115,  115,    0,  111,
  115,  111,  111,  111,  111,    0,    0,  111,  111,  111,
    0,    0,    0,    0,    0,  115,  111,  111,  111,    0,
  111,  111,  111,  113,  114,  113,  113,   63,    0,    0,
    0,    0,    0,    0,   61,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  115,
    0,    0,    0,   60,  114,    0,  114,  114,    0,   63,
    0,    0,    0,    0,    0,    0,   61,   63,    0,    0,
    0,    0,    0,    0,   61,    0,    0,    0,    0,  115,
    0,  115,  115,    0,    0,   60,    0,    0,    0,    0,
    0,    0,    0,   60,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   51,    0,    0,    0,    0,    0,
    0,   51,    0,    0,    0,   63,    0,   59,    0,    0,
   62,    0,   61,    0,    0,  112,    0,  112,  112,  112,
   51,    0,    0,    0,  112,  112,    0,    0,    0,    0,
    0,   60,  112,  112,  112,    0,  112,  112,  112,   59,
    0,  104,   62,    0,    0,    0,    0,   59,    0,  150,
   62,    0,    0,    0,    0,    0,    0,  113,    0,  113,
  113,  113,    0,    0,    0,    0,  113,  113,    0,    0,
    0,    0,    0,    0,  113,  113,  113,    0,  113,  113,
  113,    0,    0,    0,   51,    0,   51,   51,  114,    0,
  114,  114,  114,    0,    0,   59,    0,  114,   62,    0,
    0,    0,    0,    0,    0,  114,  114,  114,    0,  114,
  114,  114,    0,  115,    0,  115,  115,  115,    0,    0,
    0,    0,  115,    0,    0,    0,    0,    0,    0,    0,
  115,  115,  115,    0,  115,  115,  115,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   48,    0,   49,   50,   51,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   52,   53,
   54,   55,   56,   57,   58,    0,    0,    0,    0,    0,
    0,    0,    0,   48,    0,   49,   50,   51,    0,    0,
    0,   48,    0,   49,   50,   51,    0,    0,    0,    0,
   52,   53,   54,    0,   56,   57,   58,    0,   52,   53,
   54,    0,   56,   57,   58,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   51,    0,
   51,   51,   51,    0,    0,    0,    0,    0,    0,   48,
    0,   49,   50,   51,    0,   51,   51,   51,    0,   51,
   51,   51,    0,    0,    0,    0,   52,   53,   54,    0,
   56,   57,   58,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   93,   39,   60,   37,   62,   44,   40,   41,   42,   43,
   44,   45,   64,   47,   64,   64,  141,   64,  190,   46,
   46,   61,   44,  288,  289,   59,   60,   61,   62,  185,
  186,  257,  272,  287,   33,  275,  290,   59,   37,  115,
  116,   40,   41,   42,   43,   44,   45,   43,   47,   45,
  175,   91,  224,  105,  123,  105,  105,  258,  105,   93,
   59,   60,   61,   62,   91,   91,  222,  260,  263,   33,
  265,  196,   91,   37,  259,   58,   40,   41,   42,   43,
   44,   45,  125,   47,  291,   91,  125,  124,  125,  123,
  215,  125,  126,   33,   93,   59,   60,  289,   62,  290,
   40,   41,   33,   43,   44,   45,   93,  290,  233,   40,
  278,   37,  117,  118,  119,  120,   42,  242,   33,   59,
   60,   47,   62,   59,  123,   40,  125,  126,   33,   93,
  123,  123,  123,  281,  261,   40,   41,  123,   43,   44,
   45,  283,   58,  181,  182,  290,  280,  123,  288,   58,
  265,  263,   40,   93,   59,   60,   40,   62,   61,  123,
  276,  125,  126,  277,   59,  125,  290,   33,   91,  123,
   59,  282,   40,   58,   40,   41,  125,   43,   44,   45,
   41,  290,  290,  123,   44,  125,  126,  288,   93,  123,
  279,  266,  123,   59,   60,  126,   62,  290,  262,   91,
   59,   59,   33,   41,   41,  288,   93,   93,  125,   40,
   41,  126,   93,   44,  125,  273,  274,  123,  123,   58,
  125,  126,  264,  123,   93,  288,  125,   93,   59,   60,
   41,   62,  123,  267,  290,  269,  270,  271,  272,  273,
  274,  275,  276,  277,   93,   93,  125,  268,  123,   59,
  284,  285,  286,  265,  288,  289,  290,  123,  125,  125,
  126,   40,   93,  125,  262,   41,  123,  264,  267,  125,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  125,
  125,   93,  263,  265,  125,  284,  285,  286,  290,  288,
  289,  290,  123,   59,  125,  126,   93,   59,  113,  173,
  114,   17,   25,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  207,   -1,   -1,   -1,   -1,   -1,
  284,  285,  286,   -1,  288,  289,  290,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,   -1,  270,
  271,   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,
  290,   -1,   -1,   -1,   -1,  270,  271,  288,  289,  290,
   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  288,  289,  290,   -1,   -1,   -1,  284,
  285,  286,   -1,  288,  289,  290,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,   44,   -1,
   -1,  267,   -1,  269,  270,  271,  272,  273,  274,  275,
  276,  277,   -1,   59,   60,   -1,   62,   -1,  284,  285,
  286,   -1,  288,  289,  290,   -1,   -1,   61,   -1,   -1,
   64,   -1,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,
  271,  272,  273,  274,  275,  276,  277,   93,   -1,   -1,
   -1,   -1,   -1,  284,  285,  286,   33,  288,  289,  290,
   -1,   -1,   -1,   40,   41,   -1,   -1,   44,  102,  103,
   -1,  105,   -1,   -1,   -1,   -1,   -1,  123,  112,  125,
  126,   33,   59,   60,   -1,   62,   -1,   -1,   40,   41,
   -1,   -1,   44,  127,   -1,  129,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   33,   59,   60,   -1,
   62,   -1,   -1,   40,   41,   -1,   93,   44,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,   44,
   -1,   -1,   59,   60,   -1,   62,   -1,   -1,  172,   -1,
   -1,   93,   -1,   33,   59,   -1,  123,   -1,  125,  126,
   40,   41,   -1,   -1,   44,   -1,  190,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   59,
   -1,  123,   -1,  125,  126,   -1,   -1,   -1,   93,   -1,
   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   41,
  224,   -1,   44,   -1,   -1,   -1,  123,   -1,  125,  126,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   59,  123,   77,
  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  267,   -1,  269,  270,  271,  272,  273,  274,  275,
  276,  277,   -1,  123,   -1,  125,  126,   -1,  284,  285,
  286,   93,  288,  289,  290,  113,  114,  115,  116,  117,
  118,  119,  120,  121,  122,  123,  124,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  123,   -1,  125,  126,   33,   -1,   -1,   -1,   -1,
   -1,   -1,   40,   41,   -1,   -1,   44,   -1,   -1,   -1,
  267,   -1,  269,  270,  271,  272,  273,  274,  275,  276,
  277,   59,   -1,   -1,   -1,   -1,   -1,  284,  285,  286,
   -1,  288,  289,  290,   -1,  267,   -1,  269,  270,  271,
  272,  273,  274,  275,  276,  277,   -1,   -1,   -1,   -1,
   -1,   -1,  284,  285,  286,   93,  288,  289,  290,   -1,
  267,   -1,  269,  270,  271,  272,  273,  274,  275,  276,
  277,   -1,  267,   -1,  269,  270,  271,  284,  285,  286,
   -1,  288,  289,  290,   -1,  123,   -1,  125,  126,  284,
  285,  286,   -1,  288,  289,  290,   -1,  267,   -1,  269,
  270,  271,  272,   -1,   -1,  275,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,
  290,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   41,
   -1,   -1,   44,   -1,   -1,  267,   -1,  269,  270,  271,
  272,   -1,   -1,  275,  276,  277,   -1,   59,   -1,   -1,
   -1,   -1,  284,  285,  286,   -1,  288,  289,  290,   -1,
   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,
   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   -1,  123,   -1,  125,  126,   -1,   -1,   -1,   -1,   33,
   59,   -1,   -1,   93,   -1,   -1,   40,   41,   -1,  267,
   44,  269,  270,  271,  272,   -1,   -1,  275,  276,  277,
   -1,   -1,   -1,   -1,   -1,   59,  284,  285,  286,   -1,
  288,  289,  290,  123,   93,  125,  126,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   59,  123,   -1,  125,  126,   -1,   33,
   -1,   -1,   -1,   -1,   -1,   -1,   40,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,  123,
   -1,  125,  126,   -1,   -1,   59,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   33,   -1,  123,   -1,   -1,
  126,   -1,   40,   -1,   -1,  267,   -1,  269,  270,  271,
   59,   -1,   -1,   -1,  276,  277,   -1,   -1,   -1,   -1,
   -1,   59,  284,  285,  286,   -1,  288,  289,  290,  123,
   -1,  125,  126,   -1,   -1,   -1,   -1,  123,   -1,  125,
  126,   -1,   -1,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,   -1,   -1,   -1,   -1,  276,  277,   -1,   -1,
   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,  289,
  290,   -1,   -1,   -1,  123,   -1,  125,  126,  267,   -1,
  269,  270,  271,   -1,   -1,  123,   -1,  276,  126,   -1,
   -1,   -1,   -1,   -1,   -1,  284,  285,  286,   -1,  288,
  289,  290,   -1,  267,   -1,  269,  270,  271,   -1,   -1,
   -1,   -1,  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  284,  285,  286,   -1,  288,  289,  290,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  284,  285,
  286,  287,  288,  289,  290,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,   -1,   -1,
   -1,  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,
  284,  285,  286,   -1,  288,  289,  290,   -1,  284,  285,
  286,   -1,  288,  289,  290,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,   -1,
  269,  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,  267,
   -1,  269,  270,  271,   -1,  284,  285,  286,   -1,  288,
  289,  290,   -1,   -1,   -1,   -1,  284,  285,  286,   -1,
  288,  289,  290,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=291;
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
"DECLR_BOOL","VOID","INTEGER","STRING","ID","CARD",
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

//#line 476 "GameWizard.y"

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
//#line 748 "Parser.java"
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
//#line 117 "GameWizard.y"
{/*the global block*/SymbolTable.pushNewBlock(); SymbolTable.addKeywordsAndBuildIn();}
break;
case 2:
//#line 117 "GameWizard.y"
{curScope=null;}
break;
case 3:
//#line 118 "GameWizard.y"
{
		String methods = val_peek(2).sval+val_peek(1).sval+val_peek(0).sval;
		Util.writeGameJava(val_peek(6).sval,methods);
		
	}
break;
case 4:
//#line 124 "GameWizard.y"
{yyval.sval=val_peek(1).sval; System.out.println("game_df");}
break;
case 5:
//#line 129 "GameWizard.y"
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
//#line 138 "GameWizard.y"
{System.out.println("2");}
break;
case 7:
//#line 140 "GameWizard.y"
{System.out.println("1");}
break;
case 8:
//#line 141 "GameWizard.y"
{System.out.println("3");}
break;
case 9:
//#line 143 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CARD);}
break;
case 10:
//#line 143 "GameWizard.y"
{ SymbolTable.pushNewBlock();}
break;
case 11:
//#line 144 "GameWizard.y"
{System.out.println("======================================================finished card_df_content"); SymbolTable.popBlock(); Util.writeCardsJava(val_peek(13).sval.toString(),val_peek(10).obj,val_peek(2).sval); }
break;
case 12:
//#line 146 "GameWizard.y"
{System.out.println("character_df");}
break;
case 13:
//#line 148 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 14:
//#line 149 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 15:
//#line 151 "GameWizard.y"
{curScope=val_peek(1).sval; checkDulDeclare(val_peek(1).sval); SymbolTable.addRecordToCurrentBlock(val_peek(1).sval, SymbolType.CHARACTER);}
break;
case 16:
//#line 154 "GameWizard.y"
{Util.writeCharacterJava(val_peek(5).sval,val_peek(2).obj,val_peek(1).obj); System.out.println("character_df_content");}
break;
case 17:
//#line 157 "GameWizard.y"
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
//#line 167 "GameWizard.y"
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
//#line 175 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>(); yyval.obj= result;   System.out.println("variable_list");}
break;
case 20:
//#line 180 "GameWizard.y"
{yyval.obj = val_peek(1).obj;}
break;
case 21:
//#line 184 "GameWizard.y"
{ArrayList<String> ret = new ArrayList<String>(); yyval.obj=ret;}
break;
case 22:
//#line 187 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)(val_peek(1).obj);
                                    ArrayList<String> x2 = (ArrayList<String>)(val_peek(0).obj);
                                    result.addAll(x1); result.addAll(x2); yyval.obj= result;}
break;
case 23:
//#line 191 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 24:
//#line 196 "GameWizard.y"
{checkDulDeclare(val_peek(7).sval);SymbolTable
.addRecordToCardCharacterBlock(curScope, val_peek(7).sval, SymbolType.CHARACTER_SKILL);SymbolTable.pushNewBlock();}
break;
case 25:
//#line 198 "GameWizard.y"
{
	    SymbolTable.popBlock();
	    ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(11).sval);
            result.add(val_peek(2).sval);
            yyval.obj=result;
        }
break;
case 26:
//#line 208 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 27:
//#line 209 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void init(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
		System.out.println("init_block statement");
	}
break;
case 28:
//#line 219 "GameWizard.y"
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
//#line 232 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 30:
//#line 232 "GameWizard.y"
{ SymbolTable.popBlock();yyval.sval=val_peek(1).sval; System.out.println("round_begin");}
break;
case 31:
//#line 236 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 32:
//#line 236 "GameWizard.y"
{SymbolTable.popBlock();yyval.sval=val_peek(1).sval;}
break;
case 33:
//#line 240 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 34:
//#line 240 "GameWizard.y"
{SymbolTable.popBlock(); yyval.sval=val_peek(1).sval;}
break;
case 35:
//#line 244 "GameWizard.y"
{SymbolTable.pushNewBlock();}
break;
case 36:
//#line 245 "GameWizard.y"
{
		SymbolTable.popBlock();
		String ret = "public static void dying(){\n"+val_peek(1).sval+"\n}\n";
		yyval.sval=ret;
	}
break;
case 37:
//#line 253 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 38:
//#line 254 "GameWizard.y"
{yyval.sval="";}
break;
case 39:
//#line 258 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(0).sval;}
break;
case 40:
//#line 259 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 41:
//#line 260 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(0).sval;}
break;
case 42:
//#line 261 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 43:
//#line 262 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(0).sval;}
break;
case 44:
//#line 263 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 45:
//#line 264 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(0).sval;}
break;
case 46:
//#line 265 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 47:
//#line 266 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(0).sval;}
break;
case 48:
//#line 267 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 49:
//#line 271 "GameWizard.y"
{yyval.sval=";";}
break;
case 50:
//#line 276 "GameWizard.y"
{System.out.println("8");String s = "while("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 51:
//#line 282 "GameWizard.y"
{System.out.println("6");String s = "if("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 52:
//#line 284 "GameWizard.y"
{System.out.println("7");String s = "if("+val_peek(4).sval+")\n"+val_peek(2).sval+";\nelse\n"+val_peek(0).sval+";"; yyval.sval=s;}
break;
case 53:
//#line 289 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 54:
//#line 293 "GameWizard.y"
{System.out.println("3");yyval.sval=val_peek(1).sval+";\n";}
break;
case 55:
//#line 298 "GameWizard.y"
{System.out.println(val_peek(0).sval);yyval.sval=val_peek(0).sval;}
break;
case 56:
//#line 299 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 57:
//#line 303 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;System.out.println(yyval.sval);}
break;
case 58:
//#line 307 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 59:
//#line 308 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 60:
//#line 312 "GameWizard.y"
{checkDulDeclare(val_peek(0).sval);System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 61:
//#line 313 "GameWizard.y"
{checkDulDeclare(val_peek(2).sval);SymbolTable.addRecordToCurrentBlock(val_peek(2).sval,SymbolType.LOCAL_VARIABLE);yyval.sval=val_peek(2).sval+'='+val_peek(0).sval;}
break;
case 62:
//#line 317 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 63:
//#line 318 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 64:
//#line 322 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 65:
//#line 323 "GameWizard.y"
{yyval.sval='{'+val_peek(1).sval+'}';}
break;
case 66:
//#line 327 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 67:
//#line 328 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 68:
//#line 332 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 69:
//#line 336 "GameWizard.y"
{yyval.sval="{"+val_peek(1).sval+"}";}
break;
case 70:
//#line 337 "GameWizard.y"
{yyval.sval="{}";}
break;
case 71:
//#line 350 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 72:
//#line 351 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 73:
//#line 356 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 74:
//#line 362 "GameWizard.y"
{yyval.sval="boolean ";}
break;
case 75:
//#line 363 "GameWizard.y"
{yyval.sval="int ";}
break;
case 76:
//#line 364 "GameWizard.y"
{yyval.sval="String ";}
break;
case 77:
//#line 374 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 78:
//#line 375 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 79:
//#line 379 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 80:
//#line 380 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 81:
//#line 385 "GameWizard.y"
{yyval.sval="("+val_peek(1).sval+")";}
break;
case 82:
//#line 386 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 83:
//#line 391 "GameWizard.y"
{Integer tmp = new Integer(val_peek(0).ival);yyval.sval=tmp.toString();}
break;
case 84:
//#line 392 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 85:
//#line 393 "GameWizard.y"
{yyval.sval="true";}
break;
case 86:
//#line 394 "GameWizard.y"
{yyval.sval="false";}
break;
case 87:
//#line 395 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 88:
//#line 396 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 89:
//#line 400 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'.'+val_peek(0).sval;}
break;
case 90:
//#line 404 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 91:
//#line 405 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 92:
//#line 411 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 93:
//#line 415 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 94:
//#line 416 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 95:
//#line 420 "GameWizard.y"
{yyval.sval="~";}
break;
case 96:
//#line 421 "GameWizard.y"
{yyval.sval="!";}
break;
case 97:
//#line 426 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 98:
//#line 427 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"*"+val_peek(0).sval;}
break;
case 99:
//#line 428 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"/"+val_peek(0).sval;}
break;
case 100:
//#line 429 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"%"+val_peek(0).sval;}
break;
case 101:
//#line 434 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 102:
//#line 435 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"+"+val_peek(0).sval;}
break;
case 103:
//#line 436 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"-"+val_peek(0).sval;}
break;
case 104:
//#line 441 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 105:
//#line 442 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<"+val_peek(0).sval;}
break;
case 106:
//#line 443 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">"+val_peek(0).sval;}
break;
case 107:
//#line 444 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<="+val_peek(0).sval;}
break;
case 108:
//#line 445 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">="+val_peek(0).sval;}
break;
case 109:
//#line 450 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 110:
//#line 451 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"=="+val_peek(0).sval;}
break;
case 111:
//#line 452 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"!="+val_peek(0).sval;}
break;
case 112:
//#line 456 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 113:
//#line 457 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"&&"+val_peek(0).sval;}
break;
case 114:
//#line 462 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 115:
//#line 463 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"||"+val_peek(0).sval;}
break;
case 116:
//#line 467 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 117:
//#line 468 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"="+val_peek(0).sval;}
break;
//#line 1419 "Parser.java"
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
