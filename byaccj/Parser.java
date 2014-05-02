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
public final static short INTEGER=281;
public final static short STRING=282;
public final static short ID=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,   41,   43,   43,   44,   42,   45,   45,
   46,    3,    3,    3,    4,   39,   39,   39,   40,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    5,   38,
    8,    7,    7,   25,   27,   26,   26,   28,   37,   37,
   36,   36,   35,   35,   34,   34,   33,   33,   29,   32,
   32,   30,   30,   31,   24,   24,   24,   19,   19,   17,
   17,   20,   20,   21,   21,   21,   21,   21,   21,   23,
   22,   22,    9,   16,   16,   18,   18,   14,   14,   14,
   14,   15,   15,   15,   13,   13,   13,   13,   13,   12,
   12,   12,   11,   11,   10,   10,    6,    6,
};
final static short yylen[] = {                            2,
    3,    4,   12,    4,    2,    1,   12,    4,    2,    1,
    5,    5,    5,    0,    5,    2,    1,    1,   11,    1,
    2,    1,    2,    1,    2,    1,    2,    1,    2,    1,
    5,    5,    7,    1,    2,    1,    1,    2,    1,    3,
    1,    3,    1,    4,    1,    3,    1,    3,    1,    3,
    2,    1,    4,    1,    1,    1,    1,    1,    3,    1,
    1,    3,    1,    1,    1,    1,    1,    1,    1,    3,
    4,    4,    1,    1,    2,    1,    1,    1,    3,    3,
    3,    1,    3,    3,    1,    3,    3,    3,    3,    1,
    3,    3,    1,    3,    1,    3,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    2,    0,    0,    0,    0,    0,    0,    4,
    5,    0,    0,   10,    0,    0,    0,    0,    8,    9,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   11,    0,   12,   13,    0,
    0,    0,    0,    0,   18,    0,    0,    0,    0,    0,
   15,   16,    0,    0,    0,   66,   67,   56,   57,   55,
   64,   65,   58,    0,   30,    0,   76,   77,    0,   28,
   20,   24,    0,    0,    0,    0,    0,    0,    0,   73,
   74,    0,    0,    0,   63,   68,   69,   54,   22,    0,
   34,   36,   37,    0,    0,   49,   26,    0,    3,    0,
    0,   51,    0,    0,    0,   29,   21,   25,   23,   27,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   75,    0,    0,    0,    0,   35,
   43,    0,   39,    0,    0,    0,    0,    0,   50,   62,
    7,   98,   78,    0,    0,    0,    0,    0,    0,    0,
    0,   79,   80,   81,    0,    0,    0,   59,    0,   70,
    0,    0,    0,    0,    0,    0,    0,   71,   72,    0,
    0,   45,   42,   40,   53,    0,    0,   31,   44,    0,
   47,    0,    0,   46,    0,    0,   33,   48,    0,    0,
   19,
};
final static short yydgoto[] = {                          2,
    3,    8,   27,   40,   79,   80,   81,   82,   83,   84,
   85,   86,   87,   88,   89,   90,   91,   92,   93,   94,
   95,   96,   97,   98,   99,  100,  101,  102,  103,  104,
  105,  106,  190,  183,  142,  143,  144,  107,   56,   57,
    6,   11,   15,   16,   23,   24,
};
final static short yysindex[] = {                      -256,
  -93,    0, -191, -189,  -15, -168,   45,  -14, -176,   26,
    0, -164,    0,   -1,   31, -176, -152,   73, -150,    0,
    0,   23,  -91,    0, -113,   91, -115, -150,    0,    0,
   95, -225,  119,  -98, -114,  107,  109,  -92,  111,   48,
  118, -150, -150,  -86,   88,    0,  -85,    0,    0,  139,
  -56,  124,   60,   62,    0,  101,  -56,  -84,  918,  -77,
    0,    0,  136,  156,  158,    0,    0,    0,    0,    0,
    0,    0,    0,   79,    0,  -18,    0,    0,  768,    0,
    0,    0,  140,  -76,  -74, -224,  -44,  105,    9,    0,
    0,  -18,  -25,  -23,    0,    0,    0,    0,    0,  149,
    0,    0,    0,  -72,  121,    0,    0,  169,    0,  -18,
  -18,    0,  875,  172,   89,    0,    0,    0,    0,    0,
  -18,  -18,  -18,  -18,  -18,  -18,  -18,  -18,  -18,  -18,
  -18,  -18,  -18,  -18,    0,  -18,  -68,  -18,  -67,    0,
    0,  -41,    0,  173,  -63,  -42,  182,  190,    0,    0,
    0,    0,    0,  -74, -224,  -44,  -44,    9,    9,    9,
    9,    0,    0,    0,  105,  105,  142,    0,  162,    0,
  -49,  382,  -72,  165,  -31,  128,  128,    0,    0,  167,
  382,    0,    0,    0,    0,  220,   -6,    0,    0,  -39,
    0,  143,  128,    0,  382,  918,    0,    0,  881,  145,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  174,    0,    0,    3,    0,
    0,    0,    0,    0,    0,    0,    0,   24,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -178, -178,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  193,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   37,  857,  820,  552,  497,   61,  166,    0,
    0,    0,  -33,    2,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    5,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -27,    0,  231,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  840,  803,  514,  535,  417,  434,  457,
  474,    0,    0,    0,   96,  131,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  898,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,   -9,    0,  -61,  -38,  -73,  -55,  259,    0,
  171,  175,  -36,  -24,  -13,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -54,    0,    0,    0,    0,    0,
    0, -140,    0, -126,    0,  122,    0,  -48,  237,    0,
    0,    0,  280,    0,    0,  274,
};
final static int YYTABLESIZE=1201;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         60,
    1,   29,   55,   60,  195,  117,   60,   60,   60,   60,
   60,   60,  113,   60,   78,  128,   41,  129,   34,  172,
  137,   76,  139,  118,  119,   60,   60,   60,   60,    4,
  120,   41,   48,   49,   61,  187,  188,  114,   61,  117,
  116,   61,   61,   61,   61,   61,   61,  124,   61,  171,
  125,  134,  197,  133,  191,   36,   37,  118,  119,   60,
   61,   61,   61,   61,  120,  136,    5,  138,  198,   78,
    7,  147,  148,   78,  116,    9,   78,   78,   78,   78,
   78,   78,  152,   78,   14,  194,   14,  156,  157,   60,
   10,   60,   60,   82,   61,   78,   78,  167,   78,  169,
   82,   82,   12,   82,   82,   82,   14,   77,  165,  166,
   13,   78,  158,  159,  160,  161,   17,   18,   76,   82,
   82,   19,   82,   20,   61,  117,   61,   61,   84,   78,
   22,   25,   26,  182,  199,   84,   84,   75,   84,   84,
   84,  132,  182,  118,  119,   28,  130,   31,   32,   33,
  120,  131,   35,   82,   84,   84,  182,   84,   38,   78,
  116,   78,   78,   83,   39,   42,   41,   43,   45,   44,
   83,   83,   46,   83,   83,   83,   47,   50,   51,   53,
   52,   58,   59,   82,   60,   82,   82,  108,   84,   83,
   83,   22,   83,   61,  109,  110,   63,  111,   85,  122,
  121,   74,  123,  112,   77,   85,   85,  140,  146,   85,
  141,  145,  150,  151,  168,  170,  173,  174,   84,  175,
   84,   84,  176,   83,   85,   85,   54,   85,  126,  127,
  177,  180,  186,   60,  178,   60,   60,   60,   60,   60,
   60,   60,   60,   60,   60,   60,   60,   60,   60,   60,
   74,   66,   67,   83,  179,   83,   83,  185,   85,  189,
  192,  193,   71,   72,   73,  196,    6,   14,   61,  201,
   61,   61,   61,   61,   61,   61,   61,   61,   61,   61,
   61,   61,   61,   61,   61,   17,   14,   52,   85,   38,
   85,   85,  154,   62,  184,   21,   30,  155,    0,    0,
    0,    0,    0,   78,    0,   78,   78,   78,   78,   78,
   78,   78,   78,   78,   78,   78,   78,   78,   78,   78,
    0,    0,    0,    0,    0,    0,    0,   82,    0,   82,
   82,   82,   82,   82,   82,   82,   82,   82,   82,   82,
   82,   82,   82,   82,    0,   64,    0,   65,   66,   67,
  135,    0,    0,    0,    0,    0,   68,   69,   70,   71,
   72,   73,   84,    0,   84,   84,   84,   84,   84,   84,
   84,   84,   84,   84,   84,   84,   84,   84,   84,    0,
  153,  153,  153,  153,  153,  153,  153,  153,  162,  163,
  164,  153,  153,    0,    0,    0,    0,   83,    0,   83,
   83,   83,   83,   83,   83,   83,   83,   83,   83,   83,
   83,   83,   83,   83,   78,    0,    0,    0,    0,    0,
    0,   76,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   85,    0,   85,   85,   85,   85,   85,   85,
   85,   85,   85,   85,   85,   85,   85,   85,   85,   88,
    0,    0,    0,    0,    0,    0,   88,   88,    0,    0,
   88,    0,    0,    0,    0,    0,   89,    0,    0,    0,
    0,    0,    0,   89,   89,   88,   88,   89,   88,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   86,
    0,    0,   89,   89,    0,   89,   86,   86,    0,    0,
   86,    0,    0,    0,  181,    0,   87,   77,    0,   88,
    0,    0,    0,   87,   87,   86,   86,   87,   86,    0,
    0,    0,    0,    0,    0,    0,   89,    0,    0,   90,
    0,    0,   87,   87,    0,   87,   90,   90,    0,   88,
   90,   88,   88,    0,    0,    0,   91,    0,    0,   86,
    0,    0,    0,   91,   91,   90,   89,   91,   89,   89,
    0,    0,    0,    0,    0,    0,   87,   92,    0,    0,
    0,    0,   91,    0,   92,   92,    0,    0,   92,   86,
    0,   86,   86,    0,   93,    0,    0,    0,    0,   90,
    0,   93,   93,   92,    0,   93,   87,    0,   87,   87,
    0,    0,    0,    0,    0,    0,   91,    0,    0,    0,
   93,    0,    0,    0,    0,    0,    0,    0,    0,   90,
    0,   90,   90,    0,    0,    0,    0,   92,    0,    0,
    0,    0,    0,    0,    0,    0,   91,    0,   91,   91,
    0,    0,    0,    0,   93,    0,    0,    0,    0,    0,
    0,   66,   67,    0,    0,    0,    0,   92,    0,   92,
   92,    0,   71,   72,   73,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   93,    0,   93,   93,    0,    0,
    0,    0,    0,   88,    0,   88,   88,   88,   88,   88,
   88,   88,   88,   88,   88,   88,   88,   88,   88,   88,
   89,    0,   89,   89,   89,   89,   89,   89,   89,   89,
   89,   89,   89,   89,   89,   89,   89,    0,    0,    0,
    0,    0,    0,   86,    0,   86,   86,   86,   86,   86,
   86,   86,   86,   86,   86,   86,   86,   86,   86,   86,
   87,    0,   87,   87,   87,   87,   87,   87,   87,   87,
   87,   87,   87,   87,   87,   87,   87,    0,    0,    0,
    0,    0,    0,   90,    0,   90,   90,   90,   90,    0,
    0,   90,   90,   90,   90,   90,   90,   90,   90,   90,
   91,    0,   91,   91,   91,   91,    0,    0,   91,   91,
   91,   91,   91,   91,   91,   91,   91,    0,    0,    0,
   78,   92,    0,   92,   92,   92,   92,   76,    0,   92,
   92,   92,   92,   92,   92,   92,   92,   92,   93,    0,
   93,   93,   93,    0,    0,    0,   75,   93,   93,   93,
   93,   93,   93,   93,   93,   94,    0,    0,    0,    0,
    0,    0,   94,   94,    0,    0,   94,    0,    0,    0,
    0,    0,   95,    0,    0,    0,    0,    0,    0,   95,
   95,   94,    0,   95,    0,    0,    0,    0,    0,    0,
    0,    0,   96,    0,    0,    0,    0,    0,   95,   96,
   96,    0,    0,   96,    0,    0,    0,    0,    0,   97,
   74,    0,  115,   77,    0,   94,   97,   97,   96,    0,
   97,    0,    0,    0,    0,    0,    0,   78,    0,    0,
    0,    0,   95,   78,   76,   97,    0,    0,    0,    0,
   76,    0,    0,    0,    0,   94,    0,   94,   94,    0,
   32,    0,   96,   75,    0,    0,    0,   32,    0,   75,
    0,    0,   95,    0,   95,   95,    0,    0,    0,   97,
   78,    0,    0,    0,    0,    0,   32,   76,    0,    0,
    0,    0,   96,    0,   96,   96,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   75,    0,    0,   97,
    0,   97,   97,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   74,    0,  149,
   77,    0,    0,   74,    0,  200,   77,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   32,    0,   32,   32,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   64,    0,   65,   66,   67,    0,
   74,    0,    0,   77,    0,   68,   69,   70,   71,   72,
   73,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   94,
    0,   94,   94,   94,    0,    0,    0,    0,   94,   94,
   94,   94,   94,   94,   94,   94,   95,    0,   95,   95,
   95,    0,    0,    0,    0,   95,    0,   95,   95,   95,
   95,   95,   95,    0,    0,    0,   96,    0,   96,   96,
   96,    0,    0,    0,    0,   96,    0,   96,   96,   96,
   96,   96,   96,   97,    0,   97,   97,   97,    0,    0,
    0,    0,    0,    0,   97,   97,   97,   97,   97,   97,
    0,   64,    0,   65,   66,   67,    0,   64,    0,   65,
   66,   67,   68,   69,   70,   71,   72,   73,   68,   69,
   70,   71,   72,   73,   32,    0,   32,   32,   32,    0,
    0,    0,    0,    0,    0,   32,   32,   32,   32,   32,
   32,    0,    0,    0,   64,    0,   65,   66,   67,    0,
    0,    0,    0,    0,    0,   68,   69,   70,   71,   72,
   73,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  257,   93,   59,   37,   44,   79,   40,   41,   42,   43,
   44,   45,   74,   47,   33,   60,   44,   62,   28,   61,
   46,   40,   46,   79,   79,   59,   60,   61,   62,  123,
   79,   59,   42,   43,   33,  176,  177,   76,   37,  113,
   79,   40,   41,   42,   43,   44,   45,  272,   47,   91,
  275,   43,  193,   45,  181,  281,  282,  113,  113,   93,
   59,   60,   61,   62,  113,   91,  258,   91,  195,   33,
  260,  110,  111,   37,  113,   91,   40,   41,   42,   43,
   44,   45,  121,   47,  263,  125,  265,  124,  125,  123,
  259,  125,  126,   33,   93,   59,   60,  136,   62,  138,
   40,   41,   58,   43,   44,   45,  283,  126,  133,  134,
  125,   33,  126,  127,  128,  129,   91,  282,   40,   59,
   60,  123,   62,   93,  123,  199,  125,  126,   33,   93,
  283,   59,  283,  172,  196,   40,   41,   59,   43,   44,
   45,   37,  181,  199,  199,  123,   42,  261,   58,  265,
  199,   47,   58,   93,   59,   60,  195,   62,   40,  123,
  199,  125,  126,   33,  263,   59,  281,   59,   58,  262,
   40,   41,  125,   43,   44,   45,   59,  264,   91,   41,
  266,   58,  123,  123,  123,  125,  126,  265,   93,   59,
   60,  283,   62,   93,   59,   40,  281,   40,   33,  276,
   61,  123,  277,  125,  126,   40,   41,   59,   40,   44,
  283,   91,   41,  125,  283,  283,   44,  281,  123,  262,
  125,  126,   41,   93,   59,   60,  283,   62,  273,  274,
   41,  281,  264,  267,   93,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  281,  282,  283,
  123,  270,  271,  123,   93,  125,  126,   93,   93,   93,
   41,  268,  281,  282,  283,  123,   93,  265,  267,  125,
  269,  270,  271,  272,  273,  274,  275,  276,  277,  278,
  279,  280,  281,  282,  283,   93,  263,  283,  123,   59,
  125,  126,  122,   57,  173,   16,   23,  123,   -1,   -1,
   -1,   -1,   -1,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,  277,  278,  279,  280,  281,  282,  283,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  280,  281,  282,  283,   -1,  267,   -1,  269,  270,  271,
   92,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,
  282,  283,  267,   -1,  269,  270,  271,  272,  273,  274,
  275,  276,  277,  278,  279,  280,  281,  282,  283,   -1,
  122,  123,  124,  125,  126,  127,  128,  129,  130,  131,
  132,  133,  134,   -1,   -1,   -1,   -1,  267,   -1,  269,
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
"GAME_NM","PLAYER_C","PLAYER","SKILL","DEALER","METHOD","MAX_ROUND","IF","ELSE",
"WHILE","TRUE","FALSE","OP_EQ","OP_LE","OP_GE","OP_NE","OP_LOR","OP_LAND",
"DECLR_INT","DECLR_STR","DECLR_BOOL","INTEGER","STRING","ID",
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
"skill_lists : ';'",
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

//#line 385 "GameWizard.y"

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
//#line 665 "Parser.java"
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
//#line 99 "GameWizard.y"
{Util.writeGameJava(val_peek(2).sval,"");}
break;
case 2:
//#line 101 "GameWizard.y"
{yyval.sval=val_peek(1).sval; System.out.println("game_df");}
break;
case 3:
//#line 105 "GameWizard.y"
{String s= "public static String name = "+val_peek(9).sval+";\npublic static int num_of_players = "+val_peek(5).ival+";\npublic static int maximum_round = "+val_peek(1).ival+";\n"; yyval.sval=s; System.out.println(s);}
break;
case 4:
//#line 107 "GameWizard.y"
{System.out.println("2");}
break;
case 5:
//#line 109 "GameWizard.y"
{System.out.println("1");}
break;
case 6:
//#line 110 "GameWizard.y"
{System.out.println("3");}
break;
case 7:
//#line 113 "GameWizard.y"
{System.out.println("5");Util.writeCardsJava(val_peek(11).sval.toString(),val_peek(9).obj,val_peek(2).sval); }
break;
case 8:
//#line 116 "GameWizard.y"
{System.out.println("character_df");}
break;
case 9:
//#line 118 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 10:
//#line 119 "GameWizard.y"
{System.out.println("characters_df_content");}
break;
case 11:
//#line 124 "GameWizard.y"
{Util.writeCharacterJava(val_peek(4).sval,val_peek(2).obj,val_peek(1).sval); System.out.println("character_df_content");}
break;
case 12:
//#line 127 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add("Integer"); result.add(val_peek(4).sval);result.add(String.valueOf(val_peek(2).ival));
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            
            result.addAll(x1); yyval.obj=result;
                }
break;
case 13:
//#line 134 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add("String"); result.add(val_peek(4).sval);result.add(val_peek(2).sval);
            ArrayList<String> x1 = (ArrayList<String>)(val_peek(0).obj);
            result.addAll(x1); yyval.obj=result;
                }
break;
case 14:
//#line 139 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>(); yyval.obj= result;}
break;
case 15:
//#line 144 "GameWizard.y"
{yyval.sval = val_peek(1).obj.toString();}
break;
case 16:
//#line 147 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)(val_peek(1).obj);
                                    ArrayList<String> x2 = (ArrayList<String>)(val_peek(0).obj);
                                    result.addAll(x1); result.addAll(x2); yyval.obj= result;}
break;
case 17:
//#line 151 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 18:
//#line 152 "GameWizard.y"
{ArrayList<String> result = new ArrayList<String>(); yyval.obj=result;}
break;
case 19:
//#line 158 "GameWizard.y"
{ArrayList<String> result= new ArrayList<String>();
            result.add(val_peek(10).sval);
            result.add(val_peek(2).sval);
            yyval.obj=result;
        }
break;
case 20:
//#line 167 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(0).sval;}
break;
case 21:
//#line 168 "GameWizard.y"
{System.out.println("selection");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 22:
//#line 169 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(0).sval;}
break;
case 23:
//#line 170 "GameWizard.y"
{System.out.println("declare");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 24:
//#line 171 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(0).sval;}
break;
case 25:
//#line 172 "GameWizard.y"
{System.out.println("iteration");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 26:
//#line 173 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(0).sval;}
break;
case 27:
//#line 174 "GameWizard.y"
{System.out.println("empty");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 28:
//#line 175 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(0).sval;}
break;
case 29:
//#line 176 "GameWizard.y"
{System.out.println("expression");yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 30:
//#line 180 "GameWizard.y"
{yyval.sval=";";}
break;
case 31:
//#line 185 "GameWizard.y"
{System.out.println("8");String s = "while("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 32:
//#line 191 "GameWizard.y"
{System.out.println("6");String s = "if("+val_peek(2).sval+")\n"+val_peek(0).sval; yyval.sval=s;}
break;
case 33:
//#line 193 "GameWizard.y"
{System.out.println("7");String s = "if("+val_peek(4).sval+")\n"+val_peek(2).sval+";\nelse\n"+val_peek(0).sval+";"; yyval.sval=s;}
break;
case 34:
//#line 198 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 35:
//#line 202 "GameWizard.y"
{System.out.println("3");yyval.sval=val_peek(1).sval+";\n";}
break;
case 36:
//#line 207 "GameWizard.y"
{System.out.println(val_peek(0).sval);yyval.sval=val_peek(0).sval;}
break;
case 37:
//#line 208 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 38:
//#line 212 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;System.out.println(yyval.sval);}
break;
case 39:
//#line 216 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 40:
//#line 217 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 41:
//#line 221 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 42:
//#line 222 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'='+val_peek(0).sval;}
break;
case 43:
//#line 226 "GameWizard.y"
{System.out.println("1");yyval.sval=val_peek(0).sval;}
break;
case 44:
//#line 227 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 45:
//#line 231 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 46:
//#line 232 "GameWizard.y"
{yyval.sval='{'+val_peek(1).sval+'}';}
break;
case 47:
//#line 236 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 48:
//#line 237 "GameWizard.y"
{yyval.sval=val_peek(2).sval+','+val_peek(0).sval;}
break;
case 49:
//#line 241 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 50:
//#line 245 "GameWizard.y"
{yyval.sval="{"+val_peek(1).sval+"}";}
break;
case 51:
//#line 246 "GameWizard.y"
{yyval.sval="{}";}
break;
case 52:
//#line 259 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 53:
//#line 260 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).ival+']';}
break;
case 54:
//#line 265 "GameWizard.y"
{System.out.println("0");yyval.sval=val_peek(0).sval;}
break;
case 55:
//#line 271 "GameWizard.y"
{yyval.sval="boolean ";}
break;
case 56:
//#line 272 "GameWizard.y"
{yyval.sval="int ";}
break;
case 57:
//#line 273 "GameWizard.y"
{yyval.sval="String ";}
break;
case 58:
//#line 283 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 59:
//#line 284 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"."+val_peek(0).sval;}
break;
case 60:
//#line 288 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 61:
//#line 289 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 62:
//#line 294 "GameWizard.y"
{yyval.sval="("+val_peek(1).sval+")";}
break;
case 63:
//#line 295 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 64:
//#line 300 "GameWizard.y"
{Integer tmp = new Integer(val_peek(0).ival);yyval.sval=tmp.toString();}
break;
case 65:
//#line 301 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 66:
//#line 302 "GameWizard.y"
{yyval.sval="true";}
break;
case 67:
//#line 303 "GameWizard.y"
{yyval.sval="false";}
break;
case 68:
//#line 304 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 69:
//#line 305 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 70:
//#line 309 "GameWizard.y"
{yyval.sval=val_peek(2).sval+'.'+val_peek(0).sval;}
break;
case 71:
//#line 313 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 72:
//#line 314 "GameWizard.y"
{yyval.sval=val_peek(3).sval+'['+val_peek(1).sval+']';}
break;
case 73:
//#line 320 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 74:
//#line 324 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 75:
//#line 325 "GameWizard.y"
{yyval.sval=val_peek(1).sval+val_peek(0).sval;}
break;
case 76:
//#line 329 "GameWizard.y"
{yyval.sval="~";}
break;
case 77:
//#line 330 "GameWizard.y"
{yyval.sval="!";}
break;
case 78:
//#line 335 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 79:
//#line 336 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"*"+val_peek(0).sval;}
break;
case 80:
//#line 337 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"/"+val_peek(0).sval;}
break;
case 81:
//#line 338 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"%"+val_peek(0).sval;}
break;
case 82:
//#line 343 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 83:
//#line 344 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"+"+val_peek(0).sval;}
break;
case 84:
//#line 345 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"-"+val_peek(0).sval;}
break;
case 85:
//#line 350 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 86:
//#line 351 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<"+val_peek(0).sval;}
break;
case 87:
//#line 352 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">"+val_peek(0).sval;}
break;
case 88:
//#line 353 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"<="+val_peek(0).sval;}
break;
case 89:
//#line 354 "GameWizard.y"
{yyval.sval=val_peek(2).sval+">="+val_peek(0).sval;}
break;
case 90:
//#line 359 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 91:
//#line 360 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"=="+val_peek(0).sval;}
break;
case 92:
//#line 361 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"!="+val_peek(0).sval;}
break;
case 93:
//#line 365 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 94:
//#line 366 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"&&"+val_peek(0).sval;}
break;
case 95:
//#line 371 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 96:
//#line 372 "GameWizard.y"
{yyval.sval=val_peek(2).sval+"||"+val_peek(0).sval;}
break;
case 97:
//#line 376 "GameWizard.y"
{yyval.sval=val_peek(0).sval;}
break;
case 98:
//#line 377 "GameWizard.y"
{yyval.sval= val_peek(2).sval+"="+val_peek(0).sval;}
break;
//#line 1222 "Parser.java"
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
