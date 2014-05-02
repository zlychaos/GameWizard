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
public final static short GAME_PORT=266;
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
public final static short DECLR_INT=278;
public final static short DECLR_STR=279;
public final static short DECLR_BOOL=280;
public final static short INTEGER=281;
public final static short STRING=282;
public final static short ID=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,   39,   42,   40,   43,   43,   44,   41,   45,   45,
   46,    1,    1,    1,    2,   37,   37,   38,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,   36,    6,
    5,    5,   23,   25,   24,   24,   26,   35,   35,   34,
   34,   33,   33,   32,   32,   31,   31,   27,   30,   30,
   28,   28,   29,   22,   22,   22,   17,   17,   15,   15,
   18,   18,   19,   19,   19,   19,   19,   19,   21,   20,
   20,    7,   14,   14,   16,   16,   12,   12,   12,   12,
   13,   13,   13,   11,   11,   11,   11,   11,   10,   10,
   10,    9,    9,    8,    8,    4,    4,
};
final static short yylen[] = {                            2,
    3,    4,   12,    4,    2,    1,   12,    4,    2,    1,
    5,    5,    5,    0,    5,    2,    1,   11,    1,    2,
    1,    2,    1,    2,    1,    2,    1,    2,    1,    5,
    5,    7,    1,    2,    1,    1,    2,    1,    3,    1,
    3,    1,    4,    1,    3,    1,    3,    1,    3,    2,
    1,    4,    1,    1,    1,    1,    1,    3,    1,    1,
    3,    1,    1,    1,    1,    1,    1,    1,    3,    4,
    4,    1,    1,    2,    1,    1,    1,    3,    3,    3,
    1,    3,    3,    1,    3,    3,    3,    3,    1,    3,
    3,    1,    3,    1,    3,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    2,    0,    0,    0,    0,    0,    0,    4,
    5,    0,    0,   10,    0,    0,    0,    0,    8,    9,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   11,    0,   12,   13,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   15,
   16,    0,    0,    0,   65,   66,   55,   56,   54,   63,
   64,   57,    0,   29,    0,   75,   76,    0,   27,   19,
   23,    0,    0,    0,    0,    0,    0,    0,   72,   73,
    0,    0,    0,   62,   67,   68,   53,   21,    0,   33,
   35,   36,    0,    0,   48,   25,    0,    3,    0,    0,
   50,    0,    0,    0,   28,   20,   24,   22,   26,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   74,    0,    0,    0,    0,   34,   42,
    0,   38,    0,    0,    0,    0,    0,   49,   61,    7,
   97,   77,    0,    0,    0,    0,    0,    0,    0,    0,
   78,   79,   80,    0,    0,    0,   58,    0,   69,    0,
    0,    0,    0,    0,    0,    0,   70,   71,    0,    0,
   44,   41,   39,   52,    0,    0,   30,   43,    0,   46,
    0,    0,   45,    0,    0,   32,   47,    0,    0,   18,
};
final static short yydgoto[] = {                          2,
   27,   40,   78,   79,   80,   81,   82,   83,   84,   85,
   86,   87,   88,   89,   90,   91,   92,   93,   94,   95,
   96,   97,   98,   99,  100,  101,  102,  103,  104,  105,
  189,  182,  141,  142,  143,  106,   55,   56,    3,    6,
   11,    8,   15,   16,   23,   24,
};
final static short yysindex[] = {                      -238,
  -70,    0, -182, -189,   -4, -170,   33,  -22, -176,   20,
    0, -165,    0,   -5,   29, -176, -152,   65, -151,    0,
    0,   10,  -91,    0, -115,   90, -116, -151,    0,    0,
   92, -172,  113, -104, -114,  106,  107,  -94,  111,   45,
  114, -151, -151,  -87,   87,    0,  -86,    0,    0,  138,
 -102,  124,   60,   62,   95, -102,  -85,  918,  -68,    0,
    0,  135,  155,  158,    0,    0,    0,    0,    0,    0,
    0,    0,   79,    0,  -18,    0,    0,  768,    0,    0,
    0,  139,  -75,  -74, -187,  -59,  105,  -13,    0,    0,
  -18,  -25,  -23,    0,    0,    0,    0,    0,  149,    0,
    0,    0,  -67,  118,    0,    0,  171,    0,  -18,  -18,
    0,  875,  172,   93,    0,    0,    0,    0,    0,  -18,
  -18,  -18,  -18,  -18,  -18,  -18,  -18,  -18,  -18,  -18,
  -18,  -18,  -18,    0,  -18,  -66,  -18,  -63,    0,    0,
  -43,    0,  168,  -58,  -35,  188,  189,    0,    0,    0,
    0,    0,  -74, -187,  -59,  -59,  -13,  -13,  -13,  -13,
    0,    0,    0,  105,  105,  140,    0,  142,    0,  -50,
  382,  -67,  162,  -32,  128,  128,    0,    0,  165,  382,
    0,    0,    0,    0,  219,   -6,    0,    0,  -39,    0,
  143,  128,    0,  382,  918,    0,    0,  881,  136,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  174,    0,    0,    3,    0,
    0,    0,    0,    0,    0,    0,    0,    7,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -196, -196,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  193,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   37,  857,  820,  552,  497,   61,  166,    0,    0,
    0,  -33,    2,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    4,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -7,    0,  229,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  840,  803,  514,  535,  417,  434,  457,  474,
    0,    0,    0,   96,  131,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  898,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    8,    0,  -60,  -37,  -72,  -54,  260,    0,  169,  173,
  -10,  -17,  -71,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -53,    0,    0,    0,    0,    0,    0, -159,
    0, -160,    0,  121,    0,  -47,  238,    0,    0,    0,
    0,    0,  280,    0,    0,  274,
};
final static int YYTABLESIZE=1201;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         59,
  127,   29,  128,   59,  194,  116,   59,   59,   59,   59,
   59,   59,  112,   59,   77,  186,  187,  171,    1,  190,
  136,   75,  138,  117,  118,   59,   59,   59,   59,  133,
  119,  132,  196,  197,   60,   34,   40,  113,   60,  116,
  115,   60,   60,   60,   60,   60,   60,  170,   60,   48,
   49,   40,    4,  157,  158,  159,  160,  117,  118,   59,
   60,   60,   60,   60,  119,  135,   14,  137,   14,   77,
    7,  146,  147,   77,  115,    5,   77,   77,   77,   77,
   77,   77,  151,   77,  123,  193,    9,  124,   10,   59,
   12,   59,   59,   81,   60,   77,   77,  166,   77,  168,
   81,   81,   13,   81,   81,   81,   14,   76,   36,   37,
   17,   77,  155,  156,  164,  165,   18,   19,   75,   81,
   81,   20,   81,   25,   60,  116,   60,   60,   83,   77,
   22,   26,   28,  181,  198,   83,   83,   74,   83,   83,
   83,  131,  181,  117,  118,   31,  129,   32,   33,   35,
  119,  130,   38,   81,   83,   83,  181,   83,   39,   77,
  115,   77,   77,   82,   42,   43,   41,   44,   45,   46,
   82,   82,   47,   82,   82,   82,   50,   51,   53,   52,
   54,   57,   58,   81,   59,   81,   81,   60,   83,   82,
   82,   22,   82,  108,  109,   62,  107,  110,   84,  120,
  121,   73,  122,  111,   76,   84,   84,  139,  144,   84,
  145,  172,  149,  125,  126,  140,  167,  150,   83,  169,
   83,   83,  173,   82,   84,   84,  174,   84,  175,  176,
  179,  185,  177,   59,  178,   59,   59,   59,   59,   59,
   59,   59,   59,   59,   59,   59,   59,   59,   59,   59,
   73,   65,   66,   82,  184,   82,   82,  188,   84,  191,
  200,  192,   70,   71,   72,  195,    6,   14,   60,   14,
   60,   60,   60,   60,   60,   60,   60,   60,   60,   60,
   60,   60,   60,   60,   60,   17,   51,   37,   84,  153,
   84,   84,  183,   61,  154,   21,   30,    0,    0,    0,
    0,    0,    0,   77,    0,   77,   77,   77,   77,   77,
   77,   77,   77,   77,   77,   77,   77,   77,   77,   77,
    0,    0,    0,    0,    0,    0,    0,   81,    0,   81,
   81,   81,   81,   81,   81,   81,   81,   81,   81,   81,
   81,   81,   81,   81,    0,   63,    0,   64,   65,   66,
  134,    0,    0,    0,    0,    0,   67,   68,   69,   70,
   71,   72,   83,    0,   83,   83,   83,   83,   83,   83,
   83,   83,   83,   83,   83,   83,   83,   83,   83,    0,
  152,  152,  152,  152,  152,  152,  152,  152,  161,  162,
  163,  152,  152,    0,    0,    0,    0,   82,    0,   82,
   82,   82,   82,   82,   82,   82,   82,   82,   82,   82,
   82,   82,   82,   82,   77,    0,    0,    0,    0,    0,
    0,   75,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   84,    0,   84,   84,   84,   84,   84,   84,
   84,   84,   84,   84,   84,   84,   84,   84,   84,   87,
    0,    0,    0,    0,    0,    0,   87,   87,    0,    0,
   87,    0,    0,    0,    0,    0,   88,    0,    0,    0,
    0,    0,    0,   88,   88,   87,   87,   88,   87,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   85,
    0,    0,   88,   88,    0,   88,   85,   85,    0,    0,
   85,    0,    0,    0,  180,    0,   86,   76,    0,   87,
    0,    0,    0,   86,   86,   85,   85,   86,   85,    0,
    0,    0,    0,    0,    0,    0,   88,    0,    0,   89,
    0,    0,   86,   86,    0,   86,   89,   89,    0,   87,
   89,   87,   87,    0,    0,    0,   90,    0,    0,   85,
    0,    0,    0,   90,   90,   89,   88,   90,   88,   88,
    0,    0,    0,    0,    0,    0,   86,   91,    0,    0,
    0,    0,   90,    0,   91,   91,    0,    0,   91,   85,
    0,   85,   85,    0,   92,    0,    0,    0,    0,   89,
    0,   92,   92,   91,    0,   92,   86,    0,   86,   86,
    0,    0,    0,    0,    0,    0,   90,    0,    0,    0,
   92,    0,    0,    0,    0,    0,    0,    0,    0,   89,
    0,   89,   89,    0,    0,    0,    0,   91,    0,    0,
    0,    0,    0,    0,    0,    0,   90,    0,   90,   90,
    0,    0,    0,    0,   92,    0,    0,    0,    0,    0,
    0,   65,   66,    0,    0,    0,    0,   91,    0,   91,
   91,    0,   70,   71,   72,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   92,    0,   92,   92,    0,    0,
    0,    0,    0,   87,    0,   87,   87,   87,   87,   87,
   87,   87,   87,   87,   87,   87,   87,   87,   87,   87,
   88,    0,   88,   88,   88,   88,   88,   88,   88,   88,
   88,   88,   88,   88,   88,   88,   88,    0,    0,    0,
    0,    0,    0,   85,    0,   85,   85,   85,   85,   85,
   85,   85,   85,   85,   85,   85,   85,   85,   85,   85,
   86,    0,   86,   86,   86,   86,   86,   86,   86,   86,
   86,   86,   86,   86,   86,   86,   86,    0,    0,    0,
    0,    0,    0,   89,    0,   89,   89,   89,   89,    0,
    0,   89,   89,   89,   89,   89,   89,   89,   89,   89,
   90,    0,   90,   90,   90,   90,    0,    0,   90,   90,
   90,   90,   90,   90,   90,   90,   90,    0,    0,    0,
   77,   91,    0,   91,   91,   91,   91,   75,    0,   91,
   91,   91,   91,   91,   91,   91,   91,   91,   92,    0,
   92,   92,   92,    0,    0,    0,   74,   92,   92,   92,
   92,   92,   92,   92,   92,   93,    0,    0,    0,    0,
    0,    0,   93,   93,    0,    0,   93,    0,    0,    0,
    0,    0,   94,    0,    0,    0,    0,    0,    0,   94,
   94,   93,    0,   94,    0,    0,    0,    0,    0,    0,
    0,    0,   95,    0,    0,    0,    0,    0,   94,   95,
   95,    0,    0,   95,    0,    0,    0,    0,    0,   96,
   73,    0,  114,   76,    0,   93,   96,   96,   95,    0,
   96,    0,    0,    0,    0,    0,    0,   77,    0,    0,
    0,    0,   94,   77,   75,   96,    0,    0,    0,    0,
   75,    0,    0,    0,    0,   93,    0,   93,   93,    0,
   31,    0,   95,   74,    0,    0,    0,   31,    0,   74,
    0,    0,   94,    0,   94,   94,    0,    0,    0,   96,
   77,    0,    0,    0,    0,    0,   31,   75,    0,    0,
    0,    0,   95,    0,   95,   95,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   74,    0,    0,   96,
    0,   96,   96,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   73,    0,  148,
   76,    0,    0,   73,    0,  199,   76,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   31,    0,   31,   31,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   63,    0,   64,   65,   66,    0,
   73,    0,    0,   76,    0,   67,   68,   69,   70,   71,
   72,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   93,
    0,   93,   93,   93,    0,    0,    0,    0,   93,   93,
   93,   93,   93,   93,   93,   93,   94,    0,   94,   94,
   94,    0,    0,    0,    0,   94,    0,   94,   94,   94,
   94,   94,   94,    0,    0,    0,   95,    0,   95,   95,
   95,    0,    0,    0,    0,   95,    0,   95,   95,   95,
   95,   95,   95,   96,    0,   96,   96,   96,    0,    0,
    0,    0,    0,    0,   96,   96,   96,   96,   96,   96,
    0,   63,    0,   64,   65,   66,    0,   63,    0,   64,
   65,   66,   67,   68,   69,   70,   71,   72,   67,   68,
   69,   70,   71,   72,   31,    0,   31,   31,   31,    0,
    0,    0,    0,    0,    0,   31,   31,   31,   31,   31,
   31,    0,    0,    0,   63,    0,   64,   65,   66,    0,
    0,    0,    0,    0,    0,   67,   68,   69,   70,   71,
   72,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   60,   93,   62,   37,   44,   78,   40,   41,   42,   43,
   44,   45,   73,   47,   33,  175,  176,   61,  257,  180,
   46,   40,   46,   78,   78,   59,   60,   61,   62,   43,
   78,   45,  192,  194,   33,   28,   44,   75,   37,  112,
   78,   40,   41,   42,   43,   44,   45,   91,   47,   42,
   43,   59,  123,  125,  126,  127,  128,  112,  112,   93,
   59,   60,   61,   62,  112,   91,  263,   91,  265,   33,
  260,  109,  110,   37,  112,  258,   40,   41,   42,   43,
   44,   45,  120,   47,  272,  125,   91,  275,  259,  123,
   58,  125,  126,   33,   93,   59,   60,  135,   62,  137,
   40,   41,  125,   43,   44,   45,  283,  126,  281,  282,
   91,   33,  123,  124,  132,  133,  282,  123,   40,   59,
   60,   93,   62,   59,  123,  198,  125,  126,   33,   93,
  283,  283,  123,  171,  195,   40,   41,   59,   43,   44,
   45,   37,  180,  198,  198,  261,   42,   58,  265,   58,
  198,   47,   40,   93,   59,   60,  194,   62,  263,  123,
  198,  125,  126,   33,   59,   59,  281,  262,   58,  125,
   40,   41,   59,   43,   44,   45,  264,   91,   41,  266,
  283,   58,  123,  123,  123,  125,  126,   93,   93,   59,
   60,  283,   62,   59,   40,  281,  265,   40,   33,   61,
  276,  123,  277,  125,  126,   40,   41,   59,   91,   44,
   40,   44,   41,  273,  274,  283,  283,  125,  123,  283,
  125,  126,  281,   93,   59,   60,  262,   62,   41,   41,
  281,  264,   93,  267,   93,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  281,  282,  283,
  123,  270,  271,  123,   93,  125,  126,   93,   93,   41,
  125,  268,  281,  282,  283,  123,   93,  265,  267,  263,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  278,
  279,  280,  281,  282,  283,   93,  283,   59,  123,  121,
  125,  126,  172,   56,  122,   16,   23,   -1,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  281,  282,  283,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  281,  282,  283,   -1,  267,   -1,  269,  270,  271,
   91,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,
  282,  283,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,  281,  282,  283,   -1,
  121,  122,  123,  124,  125,  126,  127,  128,  129,  130,
  131,  132,  133,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  281,  282,  283,   33,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,  281,  282,  283,   33,
   -1,   -1,   -1,   -1,   -1,   -1,   40,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   41,   59,   60,   44,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,
   -1,   -1,   59,   60,   -1,   62,   40,   41,   -1,   -1,
   44,   -1,   -1,   -1,  123,   -1,   33,  126,   -1,   93,
   -1,   -1,   -1,   40,   41,   59,   60,   44,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   33,
   -1,   -1,   59,   60,   -1,   62,   40,   41,   -1,  123,
   44,  125,  126,   -1,   -1,   -1,   33,   -1,   -1,   93,
   -1,   -1,   -1,   40,   41,   59,  123,   44,  125,  126,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   33,   -1,   -1,
   -1,   -1,   59,   -1,   40,   41,   -1,   -1,   44,  123,
   -1,  125,  126,   -1,   33,   -1,   -1,   -1,   -1,   93,
   -1,   40,   41,   59,   -1,   44,  123,   -1,  125,  126,
   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,
   59,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,
   -1,  125,  126,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,  126,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,  270,  271,   -1,   -1,   -1,   -1,  123,   -1,  125,
  126,   -1,  281,  282,  283,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  123,   -1,  125,  126,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  281,  282,  283,
  267,   -1,  269,  270,  271,  272,  273,  274,  275,  276,
  277,  278,  279,  280,  281,  282,  283,   -1,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  281,  282,  283,
  267,   -1,  269,  270,  271,  272,  273,  274,  275,  276,
  277,  278,  279,  280,  281,  282,  283,   -1,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,   -1,
   -1,  275,  276,  277,  278,  279,  280,  281,  282,  283,
  267,   -1,  269,  270,  271,  272,   -1,   -1,  275,  276,
  277,  278,  279,  280,  281,  282,  283,   -1,   -1,   -1,
   33,  267,   -1,  269,  270,  271,  272,   40,   -1,  275,
  276,  277,  278,  279,  280,  281,  282,  283,  267,   -1,
  269,  270,  271,   -1,   -1,   -1,   59,  276,  277,  278,
  279,  280,  281,  282,  283,   33,   -1,   -1,   -1,   -1,
   -1,   -1,   40,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   41,   59,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   59,   40,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   33,
  123,   -1,  125,  126,   -1,   93,   40,   41,   59,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   93,   33,   40,   59,   -1,   -1,   -1,   -1,
   40,   -1,   -1,   -1,   -1,  123,   -1,  125,  126,   -1,
   33,   -1,   93,   59,   -1,   -1,   -1,   40,   -1,   59,
   -1,   -1,  123,   -1,  125,  126,   -1,   -1,   -1,   93,
   33,   -1,   -1,   -1,   -1,   -1,   59,   40,   -1,   -1,
   -1,   -1,  123,   -1,  125,  126,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   -1,   -1,  123,
   -1,  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,
  126,   -1,   -1,  123,   -1,  125,  126,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  123,   -1,  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  267,   -1,  269,  270,  271,   -1,
  123,   -1,   -1,  126,   -1,  278,  279,  280,  281,  282,
  283,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,
   -1,  269,  270,  271,   -1,   -1,   -1,   -1,  276,  277,
  278,  279,  280,  281,  282,  283,  267,   -1,  269,  270,
  271,   -1,   -1,   -1,   -1,  276,   -1,  278,  279,  280,
  281,  282,  283,   -1,   -1,   -1,  267,   -1,  269,  270,
  271,   -1,   -1,   -1,   -1,  276,   -1,  278,  279,  280,
  281,  282,  283,  267,   -1,  269,  270,  271,   -1,   -1,
   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,
   -1,  267,   -1,  269,  270,  271,   -1,  267,   -1,  269,
  270,  271,  278,  279,  280,  281,  282,  283,  278,  279,
  280,  281,  282,  283,  267,   -1,  269,  270,  271,   -1,
   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,   -1,   -1,   -1,  267,   -1,  269,  270,  271,   -1,
   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=283;
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
"GAME_NM","PLAYER_C","PLAYER","SKILL","DEALER","METHOD","GAME_PORT","IF","ELSE",
"WHILE","TRUE","FALSE","OP_EQ","OP_LE","OP_GE","OP_NE","OP_LOR","OP_LAND",
"DECLR_INT","DECLR_STR","DECLR_BOOL","INTEGER","STRING","ID",
};
final static String yyrule[] = {
"$accept : input",
"input : game_df card_df character_df",
"game_df : GAME_DF '{' game_df_content '}'",
"game_df_content : GAME_NM ':' STRING ';' PLAYER_C ':' INTEGER ';' GAME_PORT ':' INTEGER ';'",
"card_df : CARD_DF '[' cards_df_content ']'",
"cards_df_content : card_df_content cards_df_content",
"cards_df_content : card_df_content",
"card_df_content : ID '{' variable_list METHOD '(' PLAYER DEALER ')' '{' STATEMENT_LIST '}' '}'",
"character_df : CHARACTER_DF '[' characters_df_content ']'",
"characters_df_content : characters_df_content character_df_content",
"characters_df_content : character_df_content",
"character_df_content : ID '{' variable_list skill_df '}'",
"variable_list : ID ':' INTEGER ';' variable_list",
"variable_list : ID ':' STRING ';' variable_list",
"variable_list :",
"skill_df : SKILL ':' '[' skill_lists ']'",
"skill_lists : skill_list skill_lists",
"skill_lists : skill_list",
"skill_list : ID '{' METHOD '(' PLAYER DEALER ')' '{' STATEMENT_LIST '}' '}'",
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

//#line 379 "GameWizard.y"

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


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
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
//#line 661 "Parser.java"
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
//#line 96 "GameWizard.y"
{}
break;
case 2:
//#line 98 "GameWizard.y"
{}
break;
case 3:
//#line 102 "GameWizard.y"
{Util.writeGameJava(val_peek(9).sval,val_peek(5).ival,val_peek(1).ival);System.out.println("4");}
break;
case 4:
//#line 104 "GameWizard.y"
{System.out.println("2");}
break;
case 5:
//#line 106 "GameWizard.y"
{System.out.println("1");}
break;
case 6:
//#line 107 "GameWizard.y"
{System.out.println("3");}
break;
case 7:
//#line 110 "GameWizard.y"
{System.out.println("5");Util.writeCardsJava(val_peek(11).sval.toString(),val_peek(2).sval); }
break;
case 8:
//#line 113 "GameWizard.y"
{}
break;
case 9:
//#line 115 "GameWizard.y"
{}
break;
case 10:
//#line 116 "GameWizard.y"
{}
break;
case 11:
//#line 121 "GameWizard.y"
{Util.writeCharacterJava(val_peek(4).sval,val_peek(2).obj.toString(),val_peek(1).sval);}
break;
case 12:
//#line 124 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add("Integer"); result.add(val_peek(4).sval);result.add(String.valueOf(val_peek(2).ival));
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            
            result.addAll(x1); yyval.obj=result;
                }
break;
case 13:
//#line 131 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add("String"); result.add(val_peek(4).sval);result.add(val_peek(2).sval);
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            result.addAll(x1); yyval.obj=result;
                }
break;
case 14:
//#line 136 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>(); yyval.obj= result;}
break;
case 15:
//#line 141 "GameWizard.y"
{yyval.sval = val_peek(1).obj.toString();}
break;
case 16:
//#line 144 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)(val_peek(1).obj);
                                    ArrayList<String> x2 = (ArrayList<String>)(val_peek(0).obj);
                                    result.addAll(x1); result.addAll(x2); yyval.obj= result;}
break;
case 17:
//#line 148 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 18:
//#line 153 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(10).sval);
            result.add(val_peek(2).sval);
            yyval.obj=result;
        }
break;
case 19:
//#line 161 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(0).sval;}
break;
case 20:
//#line 162 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 21:
//#line 163 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(0).sval;}
break;
case 22:
//#line 164 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 23:
//#line 165 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(0).sval;}
break;
case 24:
//#line 166 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 25:
//#line 167 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(0).sval;}
break;
case 26:
//#line 168 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 27:
//#line 169 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(0).sval;}
break;
case 28:
//#line 170 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 29:
//#line 174 "GameWizard.y"
{yyval.sval=";";}
break;
case 30:
//#line 179 "GameWizard.y"
{System.out.println("8");String s = "while("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 31:
//#line 185 "GameWizard.y"
{System.out.println("6");String s = "if("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 32:
//#line 187 "GameWizard.y"
{System.out.println("7");String s = "if("+val_peek(4).sval+")\n"+val_peek(2).sval+";\nelse\n"+val_peek(0).sval+";"; yyval.sval=s;}
break;
case 33:
//#line 192 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 34:
//#line 196 "GameWizard.y"
{System.out.println("3");yyval.sval=val_peek(1).sval+";\n";}
break;
case 35:
//#line 201 "GameWizard.y"
{System.out.println(val_peek(0).sval);yyval.sval=val_peek(0).sval;}
break;
case 36:
//#line 202 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 37:
//#line 206 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;System.out.println(yyval.sval);}
break;
case 38:
//#line 210 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 39:
//#line 211 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 40:
//#line 215 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 41:
//#line 216 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'='+val_peek(0).sval;}
break;
case 42:
//#line 220 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 43:
//#line 221 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 44:
//#line 225 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 45:
//#line 226 "GameWizard.y"
{yyval.sval='{'+val_peek(1).sval+'}';}
break;
case 46:
//#line 230 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 47:
//#line 231 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 48:
//#line 235 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 49:
//#line 239 "GameWizard.y"
{yyval.sval="{"+val_peek(1).sval+"}";}
break;
case 50:
//#line 240 "GameWizard.y"
{yyval.sval="{}";}
break;
case 51:
//#line 253 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 52:
//#line 254 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 53:
//#line 259 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 54:
//#line 265 "GameWizard.y"
{yyval.sval="boolean ";}
break;
case 55:
//#line 266 "GameWizard.y"
{yyval.sval="int ";}
break;
case 56:
//#line 267 "GameWizard.y"
{yyval.sval="String ";}
break;
case 57:
//#line 277 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 58:
//#line 278 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 59:
//#line 282 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 60:
//#line 283 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 61:
//#line 288 "GameWizard.y"
{yyval.sval="("+val_peek(1).sval+")";}
break;
case 62:
//#line 289 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 63:
//#line 294 "GameWizard.y"
{Integer tmp = new Integer(val_peek(0).ival);yyval.sval=tmp.toString();}
break;
case 64:
//#line 295 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 65:
//#line 296 "GameWizard.y"
{yyval.sval="true";}
break;
case 66:
//#line 297 "GameWizard.y"
{yyval.sval="false";}
break;
case 67:
//#line 298 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 68:
//#line 299 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 69:
//#line 303 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'.'+val_peek(0).sval;}
break;
case 70:
//#line 307 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 71:
//#line 308 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 72:
//#line 314 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 73:
//#line 318 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 74:
//#line 319 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 75:
//#line 323 "GameWizard.y"
{yyval.sval="~";}
break;
case 76:
//#line 324 "GameWizard.y"
{yyval.sval="!";}
break;
case 77:
//#line 329 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 78:
//#line 330 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"*"+val_peek(0).sval;}
break;
case 79:
//#line 331 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"/"+val_peek(0).sval;}
break;
case 80:
//#line 332 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"%"+val_peek(0).sval;}
break;
case 81:
//#line 337 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 82:
//#line 338 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"+"+val_peek(0).sval;}
break;
case 83:
//#line 339 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"-"+val_peek(0).sval;}
break;
case 84:
//#line 344 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 85:
//#line 345 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<"+val_peek(0).sval;}
break;
case 86:
//#line 346 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">"+val_peek(0).sval;}
break;
case 87:
//#line 347 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<="+val_peek(0).sval;}
break;
case 88:
//#line 348 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">="+val_peek(0).sval;}
break;
case 89:
//#line 353 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 90:
//#line 354 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"=="+val_peek(0).sval;}
break;
case 91:
//#line 355 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"!="+val_peek(0).sval;}
break;
case 92:
//#line 359 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 93:
//#line 360 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"&&"+val_peek(0).sval;}
break;
case 94:
//#line 365 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 95:
//#line 366 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"||"+val_peek(0).sval;}
break;
case 96:
//#line 370 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 97:
//#line 371 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"="+val_peek(0).sval;}
break;
//#line 1214 "Parser.java"
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
