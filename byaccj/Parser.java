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
public final static short DECLR_INT=278;
public final static short DECLR_STR=279;
public final static short DECLR_BOOL=280;
public final static short VOID=281;
public final static short INTEGER=282;
public final static short STRING=283;
public final static short ID=284;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,   41,   43,   43,   44,   44,   42,   45,
   45,   46,    3,    3,    3,   38,   38,   39,   39,   40,
   40,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,   37,    7,    6,    6,   24,   26,   25,   25,   27,
   36,   36,   35,   35,   34,   34,   33,   33,   32,   32,
   28,   31,   31,   29,   29,   30,   23,   23,   23,   18,
   18,   16,   16,   19,   19,   20,   20,   20,   20,   20,
   20,   22,   21,   21,    8,   15,   15,   17,   17,   13,
   13,   13,   13,   14,   14,   14,   12,   12,   12,   12,
   12,   11,   11,   11,   10,   10,    9,    9,    5,    5,
};
final static short yylen[] = {                            2,
    3,    4,   12,    4,    2,    1,   12,   12,    4,    2,
    1,    5,    5,    5,    0,    5,    5,    2,    1,   11,
   11,    1,    2,    1,    2,    1,    2,    1,    2,    1,
    2,    1,    5,    5,    7,    1,    2,    1,    1,    2,
    1,    3,    1,    3,    1,    4,    1,    3,    1,    3,
    1,    3,    2,    1,    4,    1,    1,    1,    1,    1,
    3,    1,    1,    3,    1,    1,    1,    1,    1,    1,
    1,    3,    4,    4,    1,    1,    2,    1,    1,    1,
    3,    3,    3,    1,    3,    3,    1,    3,    3,    3,
    3,    1,    3,    3,    1,    3,    1,    3,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    2,    0,    0,    0,    0,    0,    0,    4,
    5,    0,    0,   11,    0,    0,    0,    0,    9,   10,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   12,    0,   13,   14,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   17,
    0,   16,   18,    0,    0,    0,   68,   69,   58,   59,
   57,    0,   66,   67,   60,    0,   32,    0,   78,   79,
    0,   30,   22,   26,    0,    0,    0,    0,    0,    0,
    0,   75,   76,    0,    0,    0,   65,   70,   71,   56,
   24,    0,   36,   38,   39,    0,    0,   51,   28,    0,
    3,    0,    0,    0,   53,    0,    0,    0,   31,   23,
   27,   25,   29,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   77,    0,    0,
    0,    0,   37,   45,    0,   41,    0,    0,    0,    0,
    0,    8,   52,   64,    7,  100,   80,    0,    0,    0,
    0,    0,    0,    0,    0,   81,   82,   83,    0,    0,
    0,   61,    0,   72,    0,    0,    0,    0,    0,    0,
    0,   73,   74,    0,    0,   47,   44,   42,   55,    0,
    0,   33,   46,    0,   49,    0,    0,   48,    0,    0,
   35,   50,    0,    0,    0,    0,   21,   20,
};
final static short yydgoto[] = {                          2,
    3,    8,   27,   81,   82,   83,   84,   85,   86,   87,
   88,   89,   90,   91,   92,   93,   94,   95,   96,   97,
   98,   99,  100,  101,  102,  103,  104,  105,  106,  107,
  108,  194,  187,  145,  146,  147,  109,   40,   56,   57,
    6,   11,   15,   16,   23,   24,
};
final static short yysindex[] = {                      -244,
  -91,    0, -221, -219,  -24, -206,   28,  -34, -186,    9,
    0, -176,    0,  -20,   16, -186, -173,   57, -166,    0,
    0,   -4,  -88,    0, -139,   66, -132, -166,    0,    0,
   76, -210,   95, -125, -137,   88,   89, -113,   92,   26,
   93, -166, -166, -111,   68,    0, -109,    0,    0,  120,
 -250,  107,   43,   74,   45,   80, -115, -112,  776,    0,
  -87,    0,    0,  118,  139,  140,    0,    0,    0,    0,
    0,   56,    0,    0,    0,  904,    0,  -18,    0,    0,
  925,    0,    0,    0,  121,  -93,  -92, -207,  -59,   29,
   14,    0,    0,  -18,  -22,  -16,    0,    0,    0,    0,
    0,  129,    0,    0,    0,  -90,  101,    0,    0,  155,
    0,  -18,  -18,   72,    0,  945,  157,   79,    0,    0,
    0,    0,    0,  -18,  -18,  -18,  -18,  -18,  -18,  -18,
  -18,  -18,  -18,  -18,  -18,  -18,  -18,    0,  -18,  -83,
  -18,  -82,    0,    0,  -43,    0,  161,  -73,  -54,  170,
  171,    0,    0,    0,    0,    0,    0,  -92, -207,  -59,
  -59,   14,   14,   14,   14,    0,    0,    0,   29,   29,
  123,    0,  124,    0,  -69,   77,  -90,  125,  -44,  100,
  100,    0,    0,  134,   77,    0,    0,    0,    0,  188,
  -37,    0,    0,  -38,    0,  109,  100,    0,   77,  965,
    0,    0,  105,  985,  108,  110,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  162,    0,    0,   -7,    0,
    0,    0,    0,    0,    0,    0,    0,  -15,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -180, -180,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  167,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   37,  884,  836,  796,  508,   61,
  166,    0,    0,    0,  -33,    2,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -23,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -19,    0,  203,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  857,  816,  528,
  548,  414,  437,  460,  483,    0,    0,    0,   96,  131,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 1158,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    8,  -74,  769,  -62,  -61,  256,    0,  138,
  141,  -39,   -5,  -17,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -60,    0,    0,    0,    0,    0,    0,
 -164,    0, -147,    0,   91,    0,  -58,    0,  213,    0,
    0,    0,  267,    0,    0,  264,
};
final static int YYTABLESIZE=1442;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         62,
  131,  116,  132,   62,   29,  199,   62,   62,   62,   62,
   62,   62,    1,   62,   80,  191,  192,  176,  120,  121,
  122,   78,  123,  140,   43,   62,   62,   62,   62,  142,
   54,    4,  201,   55,   63,   34,    5,  195,   63,   43,
    7,   63,   63,   63,   63,   63,   63,  175,   63,   48,
   49,  202,   10,  120,  121,  122,  137,  123,  136,   62,
   63,   63,   63,   63,  127,  135,    9,  128,  139,   80,
  133,   36,   37,   80,  141,  134,   80,   80,   80,   80,
   80,   80,   15,   80,   15,   12,  198,  160,  161,   62,
   13,   62,   62,   84,   63,   80,   80,   14,   80,   17,
   84,   84,   19,   84,   84,   84,   18,   79,   20,   80,
   22,  162,  163,  164,  165,   25,   78,   26,   28,   84,
   84,   31,   84,   32,   63,  204,   63,   63,   86,   80,
  169,  170,   33,   35,   38,   86,   86,   39,   86,   86,
   86,  120,  121,  122,   41,  123,   42,   43,   44,   45,
   46,   47,   50,   84,   86,   86,   52,   86,   51,   80,
   53,   80,   80,   85,   58,   59,   60,   61,   55,   64,
   85,   85,   62,   85,   85,   85,  111,  110,  112,  113,
  114,  124,  125,   84,  126,   84,   84,  143,   86,   85,
   85,  148,   85,  144,  149,   22,  152,  154,   87,  185,
  172,  174,   79,  155,  177,   87,   87,  179,  178,   87,
  180,  181,  184,  129,  130,  182,  183,  189,   86,  190,
   86,   86,   76,   85,   87,   87,  193,   87,  196,  205,
  197,  200,  207,   62,  208,   62,   62,   62,   62,   62,
   62,   62,   62,   62,   62,   62,   62,   15,   62,   62,
   62,   67,   68,   85,    6,   85,   85,   15,   87,   19,
   54,   40,  158,   73,   74,   75,  159,  188,   63,   63,
   63,   63,   63,   63,   63,   63,   63,   63,   63,   63,
   63,   63,   21,   63,   63,   63,   30,    0,   87,    0,
   87,   87,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   80,    0,   80,   80,   80,   80,   80,
   80,   80,   80,   80,   80,   80,   80,    0,   80,   80,
   80,    0,    0,    0,    0,    0,    0,   84,    0,   84,
   84,   84,   84,   84,   84,   84,   84,   84,   84,   84,
   84,    0,   84,   84,   84,    0,   67,   68,    0,  138,
    0,    0,    0,    0,    0,    0,    0,    0,   73,   74,
   75,    0,   86,    0,   86,   86,   86,   86,   86,   86,
   86,   86,   86,   86,   86,   86,    0,   86,   86,   86,
  157,  157,  157,  157,  157,  157,  157,  157,  166,  167,
  168,  157,  157,    0,    0,    0,    0,   85,    0,   85,
   85,   85,   85,   85,   85,   85,   85,   85,   85,   85,
   85,    0,   85,   85,   85,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   87,    0,   87,   87,   87,   87,   87,   87,
   87,   87,   87,   87,   87,   87,   90,   87,   87,   87,
    0,    0,    0,   90,   90,    0,    0,   90,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   91,
    0,    0,   90,   90,    0,   90,   91,   91,    0,    0,
   91,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   88,    0,    0,   91,   91,    0,   91,   88,
   88,    0,    0,   88,    0,    0,   90,    0,    0,    0,
    0,    0,    0,    0,    0,   89,    0,    0,   88,   88,
    0,   88,   89,   89,    0,    0,   89,    0,    0,   91,
    0,    0,    0,    0,    0,    0,   90,    0,   90,   90,
   92,   89,   89,    0,   89,    0,    0,   92,   92,    0,
    0,   92,   88,    0,    0,    0,    0,    0,    0,   91,
   93,   91,   91,    0,    0,    0,   92,   93,   93,    0,
    0,   93,    0,    0,    0,   89,    0,    0,    0,    0,
   94,    0,   88,    0,   88,   88,   93,   94,   94,    0,
    0,   94,    0,    0,    0,    0,    0,    0,    0,    0,
   92,    0,    0,    0,    0,   89,   94,   89,   89,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   93,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   92,    0,   92,   92,    0,    0,    0,    0,    0,    0,
   94,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   93,    0,   93,   93,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   94,    0,   94,   94,    0,    0,    0,    0,    0,    0,
   90,    0,   90,   90,   90,   90,   90,   90,   90,   90,
   90,   90,   90,   90,    0,   90,   90,   90,    0,    0,
    0,    0,    0,   91,    0,   91,   91,   91,   91,   91,
   91,   91,   91,   91,   91,   91,   91,    0,   91,   91,
   91,    0,    0,    0,    0,    0,   88,    0,   88,   88,
   88,   88,   88,   88,   88,   88,   88,   88,   88,   88,
    0,   88,   88,   88,    0,    0,    0,    0,    0,   89,
    0,   89,   89,   89,   89,   89,   89,   89,   89,   89,
   89,   89,   89,    0,   89,   89,   89,    0,    0,    0,
    0,    0,    0,    0,   92,    0,   92,   92,   92,   92,
    0,    0,   92,   92,   92,   92,   92,   92,    0,   92,
   92,   92,    0,    0,   93,    0,   93,   93,   93,   93,
    0,    0,   93,   93,   93,   93,   93,   93,   80,   93,
   93,   93,    0,    0,   94,   78,   94,   94,   94,   94,
    0,    0,   94,   94,   94,   94,   94,   94,   95,   94,
   94,   94,    0,    0,   77,   95,   95,    0,    0,   95,
    0,    0,    0,    0,    0,    0,  117,    0,   96,  119,
    0,    0,    0,    0,   95,   96,   96,    0,    0,   96,
    0,    0,    0,    0,    0,    0,    0,    0,   97,    0,
    0,    0,    0,    0,   96,   97,   97,    0,    0,   97,
  150,  151,    0,    0,  119,    0,    0,    0,   95,   98,
    0,    0,  156,    0,   97,    0,   98,   98,   76,    0,
   98,   79,    0,    0,    0,    0,    0,  171,   96,  173,
    0,    0,    0,    0,    0,   98,   99,    0,   95,    0,
   95,   95,    0,   99,   99,    0,    0,   99,   97,    0,
    0,    0,    0,    0,    0,    0,   80,    0,   96,    0,
   96,   96,   99,   78,  186,    0,    0,    0,    0,   98,
    0,    0,    0,  186,    0,    0,    0,   80,   97,    0,
   97,   97,   77,    0,   78,    0,    0,  186,    0,    0,
    0,    0,  119,    0,    0,    0,   99,   80,    0,   98,
    0,   98,   98,   77,   78,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   80,    0,    0,
    0,    0,    0,   77,   78,    0,   99,    0,   99,   99,
    0,    0,    0,    0,    0,    0,    0,   80,    0,    0,
    0,    0,    0,   77,   78,    0,   76,    0,  115,   79,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,   77,   66,   67,   68,   76,    0,  118,
   79,    0,    0,   69,   70,   71,   72,   73,   74,   75,
    0,    0,   95,    0,   95,   95,   95,   76,    0,  153,
   79,   95,   95,   95,   95,   95,    0,   95,   95,   95,
    0,    0,   96,    0,   96,   96,   96,   76,    0,    0,
   79,   96,   96,   96,   96,   96,    0,   96,   96,   96,
    0,    0,   97,    0,   97,   97,   97,   76,    0,  206,
   79,   97,    0,   97,   97,   97,    0,   97,   97,   97,
    0,    0,    0,   98,    0,   98,   98,   98,    0,    0,
    0,    0,   98,    0,   98,   98,   98,    0,   98,   98,
   98,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   99,    0,   99,   99,   99,    0,    0,    0,    0,    0,
    0,   99,   99,   99,    0,   99,   99,   99,    0,    0,
   65,    0,   66,   67,   68,    0,    0,    0,    0,    0,
    0,   69,   70,   71,    0,   73,   74,   75,    0,    0,
   34,   65,    0,   66,   67,   68,    0,   34,    0,    0,
    0,    0,   69,   70,   71,    0,   73,   74,   75,    0,
    0,   65,    0,   66,   67,   68,   34,    0,    0,    0,
    0,    0,   69,   70,   71,    0,   73,   74,   75,    0,
    0,   65,    0,   66,   67,   68,    0,    0,    0,    0,
    0,    0,   69,   70,   71,  203,   73,   74,   75,    0,
    0,   65,    0,   66,   67,   68,    0,    0,    0,    0,
    0,    0,   69,   70,   71,    0,   73,   74,   75,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   34,    0,   34,   34,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   34,    0,   34,   34,   34,    0,
    0,    0,    0,    0,    0,   34,   34,   34,    0,   34,
   34,   34,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   60,   76,   62,   37,   93,   44,   40,   41,   42,   43,
   44,   45,  257,   47,   33,  180,  181,   61,   81,   81,
   81,   40,   81,   46,   44,   59,   60,   61,   62,   46,
  281,  123,  197,  284,   33,   28,  258,  185,   37,   59,
  260,   40,   41,   42,   43,   44,   45,   91,   47,   42,
   43,  199,  259,  116,  116,  116,   43,  116,   45,   93,
   59,   60,   61,   62,  272,   37,   91,  275,   91,   33,
   42,  282,  283,   37,   91,   47,   40,   41,   42,   43,
   44,   45,  263,   47,  265,   58,  125,  127,  128,  123,
  125,  125,  126,   33,   93,   59,   60,  284,   62,   91,
   40,   41,  123,   43,   44,   45,  283,  126,   93,   33,
  284,  129,  130,  131,  132,   59,   40,  284,  123,   59,
   60,  261,   62,   58,  123,  200,  125,  126,   33,   93,
  136,  137,  265,   58,   40,   40,   41,  263,   43,   44,
   45,  204,  204,  204,  282,  204,   59,   59,  262,   58,
  125,   59,  264,   93,   59,   60,  266,   62,   91,  123,
   41,  125,  126,   33,   58,  123,   93,  123,  284,  282,
   40,   41,   93,   43,   44,   45,   59,  265,   40,   40,
  125,   61,  276,  123,  277,  125,  126,   59,   93,   59,
   60,   91,   62,  284,   40,  284,  125,   41,   33,  123,
  284,  284,  126,  125,   44,   40,   41,  262,  282,   44,
   41,   41,  282,  273,  274,   93,   93,   93,  123,  264,
  125,  126,  123,   93,   59,   60,   93,   62,   41,  125,
  268,  123,  125,  267,  125,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  263,  282,  283,
  284,  270,  271,  123,   93,  125,  126,  265,   93,   93,
  284,   59,  125,  282,  283,  284,  126,  177,  267,   57,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  278,
  279,  280,   16,  282,  283,  284,   23,   -1,  123,   -1,
  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,   -1,  282,  283,
  284,   -1,   -1,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,   -1,  282,  283,  284,   -1,  270,  271,   -1,   94,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  282,  283,
  284,   -1,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,   -1,  282,  283,  284,
  125,  126,  127,  128,  129,  130,  131,  132,  133,  134,
  135,  136,  137,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,   -1,  282,  283,  284,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,   33,  282,  283,  284,
   -1,   -1,   -1,   40,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,
   -1,   -1,   59,   60,   -1,   62,   40,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   59,   60,   -1,   62,   40,
   41,   -1,   -1,   44,   -1,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   59,   60,
   -1,   62,   40,   41,   -1,   -1,   44,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,  126,
   33,   59,   60,   -1,   62,   -1,   -1,   40,   41,   -1,
   -1,   44,   93,   -1,   -1,   -1,   -1,   -1,   -1,  123,
   33,  125,  126,   -1,   -1,   -1,   59,   40,   41,   -1,
   -1,   44,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   33,   -1,  123,   -1,  125,  126,   59,   40,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,  123,   59,  125,  126,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  123,   -1,  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  123,   -1,  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  123,   -1,  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,
  267,   -1,  269,  270,  271,  272,  273,  274,  275,  276,
  277,  278,  279,  280,   -1,  282,  283,  284,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,   -1,  282,  283,
  284,   -1,   -1,   -1,   -1,   -1,  267,   -1,  269,  270,
  271,  272,  273,  274,  275,  276,  277,  278,  279,  280,
   -1,  282,  283,  284,   -1,   -1,   -1,   -1,   -1,  267,
   -1,  269,  270,  271,  272,  273,  274,  275,  276,  277,
  278,  279,  280,   -1,  282,  283,  284,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,
   -1,   -1,  275,  276,  277,  278,  279,  280,   -1,  282,
  283,  284,   -1,   -1,  267,   -1,  269,  270,  271,  272,
   -1,   -1,  275,  276,  277,  278,  279,  280,   33,  282,
  283,  284,   -1,   -1,  267,   40,  269,  270,  271,  272,
   -1,   -1,  275,  276,  277,  278,  279,  280,   33,  282,
  283,  284,   -1,   -1,   59,   40,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   78,   -1,   33,   81,
   -1,   -1,   -1,   -1,   59,   40,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   59,   40,   41,   -1,   -1,   44,
  112,  113,   -1,   -1,  116,   -1,   -1,   -1,   93,   33,
   -1,   -1,  124,   -1,   59,   -1,   40,   41,  123,   -1,
   44,  126,   -1,   -1,   -1,   -1,   -1,  139,   93,  141,
   -1,   -1,   -1,   -1,   -1,   59,   33,   -1,  123,   -1,
  125,  126,   -1,   40,   41,   -1,   -1,   44,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,  123,   -1,
  125,  126,   59,   40,  176,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,  185,   -1,   -1,   -1,   33,  123,   -1,
  125,  126,   59,   -1,   40,   -1,   -1,  199,   -1,   -1,
   -1,   -1,  204,   -1,   -1,   -1,   93,   33,   -1,  123,
   -1,  125,  126,   59,   40,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   59,   40,   -1,  123,   -1,  125,  126,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   59,   40,   -1,  123,   -1,  125,  126,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  267,   59,  269,  270,  271,  123,   -1,  125,
  126,   -1,   -1,  278,  279,  280,  281,  282,  283,  284,
   -1,   -1,  267,   -1,  269,  270,  271,  123,   -1,  125,
  126,  276,  277,  278,  279,  280,   -1,  282,  283,  284,
   -1,   -1,  267,   -1,  269,  270,  271,  123,   -1,   -1,
  126,  276,  277,  278,  279,  280,   -1,  282,  283,  284,
   -1,   -1,  267,   -1,  269,  270,  271,  123,   -1,  125,
  126,  276,   -1,  278,  279,  280,   -1,  282,  283,  284,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,   -1,   -1,
   -1,   -1,  276,   -1,  278,  279,  280,   -1,  282,  283,
  284,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,  278,  279,  280,   -1,  282,  283,  284,   -1,   -1,
  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,   -1,
   -1,  278,  279,  280,   -1,  282,  283,  284,   -1,   -1,
   33,  267,   -1,  269,  270,  271,   -1,   40,   -1,   -1,
   -1,   -1,  278,  279,  280,   -1,  282,  283,  284,   -1,
   -1,  267,   -1,  269,  270,  271,   59,   -1,   -1,   -1,
   -1,   -1,  278,  279,  280,   -1,  282,  283,  284,   -1,
   -1,  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,
   -1,   -1,  278,  279,  280,  281,  282,  283,  284,   -1,
   -1,  267,   -1,  269,  270,  271,   -1,   -1,   -1,   -1,
   -1,   -1,  278,  279,  280,   -1,  282,  283,  284,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  123,   -1,  125,  126,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  267,   -1,  269,  270,  271,   -1,
   -1,   -1,   -1,   -1,   -1,  278,  279,  280,   -1,  282,
  283,  284,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=284;
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
"DECLR_INT","DECLR_STR","DECLR_BOOL","VOID","INTEGER","STRING","ID",
};
final static String yyrule[] = {
"$accept : input",
"input : game_df card_df character_df",
"game_df : GAME_DF '{' game_df_content '}'",
"game_df_content : GAME_NM ':' STRING ';' PLAYER_C ':' INTEGER ';' MAX_ROUND ':' INTEGER ';'",
"card_df : CARD_DF '[' cards_df_content ']'",
"cards_df_content : card_df_content cards_df_content",
"cards_df_content : card_df_content",
"card_df_content : ID '{' variable_list METHOD '(' PLAYER DEALER ')' '{' STATEMENT_LIST '}' '}'",
"card_df_content : ID '{' variable_list METHOD '(' PLAYER DEALER ')' '{' VOID '}' '}'",
"character_df : CHARACTER_DF '[' characters_df_content ']'",
"characters_df_content : characters_df_content character_df_content",
"characters_df_content : character_df_content",
"character_df_content : ID '{' variable_list skill_df '}'",
"variable_list : ID ':' INTEGER ';' variable_list",
"variable_list : ID ':' STRING ';' variable_list",
"variable_list :",
"skill_df : SKILL ':' '[' skill_lists ']'",
"skill_df : SKILL ':' '[' VOID ']'",
"skill_lists : skill_list skill_lists",
"skill_lists : skill_list",
"skill_list : ID '{' METHOD '(' PLAYER DEALER ')' '{' STATEMENT_LIST '}' '}'",
"skill_list : ID '{' METHOD '(' PLAYER DEALER ')' '{' VOID '}' '}'",
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

//#line 399 "GameWizard.y"

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
//#line 716 "Parser.java"
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
//#line 102 "GameWizard.y"
{Util.writeGameJava(val_peek(2).sval,"");}
break;
case 2:
//#line 104 "GameWizard.y"
{yyval.sval=val_peek(1).sval; System.out.println("game_df");}
break;
case 3:
//#line 108 "GameWizard.y"
{String s= "public static String name = "+val_peek(9).sval+";\npublic static int num_of_players = "+val_peek(5).ival+";\npublic static int maximum_round = "+val_peek(1).ival+";\n"; yyval.sval=s; System.out.println(s);}
break;
case 4:
//#line 110 "GameWizard.y"
{System.out.println("2");}
break;
case 5:
//#line 112 "GameWizard.y"
{System.out.println("1");}
break;
case 6:
//#line 113 "GameWizard.y"
{System.out.println("3");}
break;
case 7:
//#line 116 "GameWizard.y"
{System.out.println("5");Util.writeCardsJava(val_peek(11).sval.toString(),val_peek(9).obj,val_peek(2).sval); }
break;
case 8:
//#line 118 "GameWizard.y"
{System.out.println("5");Util.writeCardsJava(val_peek(11).sval.toString(),val_peek(9).obj,""); }
break;
case 9:
//#line 120 "GameWizard.y"
{System.out.println("character_df");}
break;
case 10:
//#line 122 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 11:
//#line 123 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 12:
//#line 128 "GameWizard.y"
{Util.writeCharacterJava(val_peek(4).sval,val_peek(2).obj,val_peek(1).obj); System.out.println("character_df_content");}
break;
case 13:
//#line 131 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add("Integer"); result.add(val_peek(4).sval);result.add(String.valueOf(val_peek(2).ival));
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            
            result.addAll(x1); yyval.obj=result;  System.out.println("variable_list");
                }
break;
case 14:
//#line 138 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add("String"); result.add(val_peek(4).sval);result.add(val_peek(2).sval);
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            result.addAll(x1); yyval.obj=result;   System.out.println("variable_list");
                }
break;
case 15:
//#line 143 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>(); yyval.obj= result;   System.out.println("variable_list");}
break;
case 16:
//#line 148 "GameWizard.y"
{yyval.obj = val_peek(1).obj;}
break;
case 17:
//#line 152 "GameWizard.y"
{ArrayList<String> ret = new ArrayList<String>(); yyval.obj=ret;}
break;
case 18:
//#line 155 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)(val_peek(1).obj);
                                    ArrayList<String> x2 = (ArrayList<String>)(val_peek(0).obj);
                                    result.addAll(x1); result.addAll(x2); yyval.obj= result;}
break;
case 19:
//#line 159 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 20:
//#line 165 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(10).sval);
            result.add(val_peek(2).sval);
            yyval.obj=result;
        }
break;
case 21:
//#line 172 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(10).sval);
            result.add("");
            yyval.obj=result;
        }
break;
case 22:
//#line 181 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(0).sval;}
break;
case 23:
//#line 182 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 24:
//#line 183 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(0).sval;}
break;
case 25:
//#line 184 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 26:
//#line 185 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(0).sval;}
break;
case 27:
//#line 186 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 28:
//#line 187 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(0).sval;}
break;
case 29:
//#line 188 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 30:
//#line 189 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(0).sval;}
break;
case 31:
//#line 190 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 32:
//#line 194 "GameWizard.y"
{yyval.sval=";";}
break;
case 33:
//#line 199 "GameWizard.y"
{System.out.println("8");String s = "while("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 34:
//#line 205 "GameWizard.y"
{System.out.println("6");String s = "if("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 35:
//#line 207 "GameWizard.y"
{System.out.println("7");String s = "if("+val_peek(4).sval+")\n"+val_peek(2).sval+";\nelse\n"+val_peek(0).sval+";"; yyval.sval=s;}
break;
case 36:
//#line 212 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 37:
//#line 216 "GameWizard.y"
{System.out.println("3");yyval.sval=val_peek(1).sval+";\n";}
break;
case 38:
//#line 221 "GameWizard.y"
{System.out.println(val_peek(0).sval);yyval.sval=val_peek(0).sval;}
break;
case 39:
//#line 222 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 40:
//#line 226 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;System.out.println(yyval.sval);}
break;
case 41:
//#line 230 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 42:
//#line 231 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 43:
//#line 235 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 44:
//#line 236 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'='+val_peek(0).sval;}
break;
case 45:
//#line 240 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 46:
//#line 241 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 47:
//#line 245 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 48:
//#line 246 "GameWizard.y"
{yyval.sval='{'+val_peek(1).sval+'}';}
break;
case 49:
//#line 250 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 50:
//#line 251 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 51:
//#line 255 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 52:
//#line 259 "GameWizard.y"
{yyval.sval="{"+val_peek(1).sval+"}";}
break;
case 53:
//#line 260 "GameWizard.y"
{yyval.sval="{}";}
break;
case 54:
//#line 273 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 55:
//#line 274 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 56:
//#line 279 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 57:
//#line 285 "GameWizard.y"
{yyval.sval="boolean ";}
break;
case 58:
//#line 286 "GameWizard.y"
{yyval.sval="int ";}
break;
case 59:
//#line 287 "GameWizard.y"
{yyval.sval="String ";}
break;
case 60:
//#line 297 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 61:
//#line 298 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 62:
//#line 302 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 63:
//#line 303 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 64:
//#line 308 "GameWizard.y"
{yyval.sval="("+val_peek(1).sval+")";}
break;
case 65:
//#line 309 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 66:
//#line 314 "GameWizard.y"
{Integer tmp = new Integer(val_peek(0).ival);yyval.sval=tmp.toString();}
break;
case 67:
//#line 315 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 68:
//#line 316 "GameWizard.y"
{yyval.sval="true";}
break;
case 69:
//#line 317 "GameWizard.y"
{yyval.sval="false";}
break;
case 70:
//#line 318 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 71:
//#line 319 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 72:
//#line 323 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'.'+val_peek(0).sval;}
break;
case 73:
//#line 327 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 74:
//#line 328 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 75:
//#line 334 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 76:
//#line 338 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 77:
//#line 339 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 78:
//#line 343 "GameWizard.y"
{yyval.sval="~";}
break;
case 79:
//#line 344 "GameWizard.y"
{yyval.sval="!";}
break;
case 80:
//#line 349 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 81:
//#line 350 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"*"+val_peek(0).sval;}
break;
case 82:
//#line 351 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"/"+val_peek(0).sval;}
break;
case 83:
//#line 352 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"%"+val_peek(0).sval;}
break;
case 84:
//#line 357 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 85:
//#line 358 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"+"+val_peek(0).sval;}
break;
case 86:
//#line 359 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"-"+val_peek(0).sval;}
break;
case 87:
//#line 364 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 88:
//#line 365 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<"+val_peek(0).sval;}
break;
case 89:
//#line 366 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">"+val_peek(0).sval;}
break;
case 90:
//#line 367 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<="+val_peek(0).sval;}
break;
case 91:
//#line 368 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">="+val_peek(0).sval;}
break;
case 92:
//#line 373 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 93:
//#line 374 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"=="+val_peek(0).sval;}
break;
case 94:
//#line 375 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"!="+val_peek(0).sval;}
break;
case 95:
//#line 379 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 96:
//#line 380 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"&&"+val_peek(0).sval;}
break;
case 97:
//#line 385 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 98:
//#line 386 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"||"+val_peek(0).sval;}
break;
case 99:
//#line 390 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 100:
//#line 391 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"="+val_peek(0).sval;}
break;
//#line 1285 "Parser.java"
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
