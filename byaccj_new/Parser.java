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
	import compiler.helper.*;
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
public final static short METHOD=260;
public final static short INIT=261;
public final static short ROUND_BEGIN=262;
public final static short ROUND_END=263;
public final static short TURN=264;
public final static short PLAYER=265;
public final static short CARD=266;
public final static short SKILL=267;
public final static short RETURN=268;
public final static short STATLIST=269;
public final static short IF=270;
public final static short ELSE=271;
public final static short WHILE=272;
public final static short TRUE=273;
public final static short FALSE=274;
public final static short FOREACH=275;
public final static short IN=276;
public final static short OP_EQ=277;
public final static short OP_LE=278;
public final static short OP_GE=279;
public final static short OP_NE=280;
public final static short OP_LOR=281;
public final static short OP_LAND=282;
public final static short DECLR_INT=283;
public final static short DECLR_STR=284;
public final static short DECLR_BOOL=285;
public final static short VOID=286;
public final static short INTEGER=287;
public final static short STRING=288;
public final static short ID=289;
public final static short foreach_statement=290;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
   42,    0,   45,   41,   46,   43,   47,   44,    7,    6,
    6,   48,    5,    4,    3,    3,    2,    2,    2,   19,
   19,   20,   20,   49,   18,   50,   16,   15,   15,   15,
   14,   14,   14,   14,   14,   13,   13,   12,   17,   17,
    8,    8,    9,    9,   10,   10,   10,   10,   10,   10,
   25,   25,   51,   22,   52,   53,   22,   24,   24,   54,
   23,   39,   39,   40,   40,   37,   37,   34,   34,   38,
   38,   38,   27,   33,   33,   31,   31,   31,   31,   32,
   32,   32,   30,   30,   29,   29,   28,   28,   26,   11,
   11,   11,   11,   21,   21,   21,    1,    1,    1,    1,
   35,   35,   36,   36,   36,   36,   36,   36,
};
final static short yylen[] = {                            2,
    0,    5,    0,    3,    0,    3,    0,    3,    3,    1,
    2,    0,    3,    3,    1,    2,    4,    1,    1,    4,
    3,    2,    1,    0,    3,    0,    3,    1,    5,    4,
    1,    1,    1,    1,    1,    1,    3,    2,    1,    2,
    3,    2,    2,    1,    1,    1,    1,    2,    4,    1,
    3,    2,    0,    6,    0,    0,    9,    3,    5,    0,
    6,    4,    3,    1,    3,    3,    1,    3,    1,    1,
    1,    1,    1,    1,    2,    1,    3,    3,    3,    1,
    3,    3,    1,    3,    1,    3,    1,    3,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         1,
    0,    0,    3,    0,    0,    5,    0,    0,    4,    0,
    7,    0,   32,   31,   33,   34,   35,   90,   91,    0,
   94,   95,   96,   92,    0,   15,    0,    0,   28,   26,
   18,   19,   93,    0,    6,    0,   39,    0,    0,    0,
   14,   16,    0,    0,   12,   10,    0,    8,   40,   24,
   21,   23,    0,   99,  100,   97,   98,    0,    0,    0,
   27,    0,    9,   11,    0,   20,   22,   17,   30,    0,
   36,    0,    0,    0,    0,   67,   42,    0,  101,  102,
   70,    0,   44,    0,   45,   47,   46,   50,    0,   76,
    0,    0,   85,    0,    0,   73,   74,    0,    0,   69,
   72,   13,   25,   38,   29,    0,   52,    0,    0,    0,
    0,    0,   41,   43,    0,   48,    0,    0,    0,    0,
    0,  105,  106,  107,  108,    0,    0,  103,  104,    0,
   75,    0,    0,    0,   37,   51,    0,    0,   68,   58,
    0,    0,   86,   77,   78,   79,    0,    0,    0,   63,
   64,    0,    0,   66,   53,   60,    0,   62,    0,   49,
    0,    0,    0,   59,   65,   54,    0,   61,   56,    0,
   57,
};
final static short yydgoto[] = {                          1,
   81,   26,   27,    9,   46,   47,   35,   61,   82,   83,
   28,   71,   72,   29,   30,   31,   38,   52,   32,   53,
   33,   85,   86,   87,   88,   89,   90,   91,   92,   93,
   94,   95,   96,   97,   98,  130,  109,  100,  101,  152,
    4,    2,    7,   12,    5,   10,   36,   62,   65,   44,
  161,  162,  170,  163,
};
final static short yysindex[] = {                         0,
    0, -243,    0, -222,  -78,    0, -197, -102,    0,  -24,
    0,  -69,    0,    0,    0,    0,    0,    0,    0,  -21,
    0,    0,    0,    0,   19,    0, -117, -208,    0,    0,
    0,    0,    0, -206,    0,  -24,    0,  -69,  -90, -134,
    0,    0,   45,  -29,    0,    0,  -89,    0,    0,    0,
    0,    0,  -87,    0,    0,    0,    0,   27,  -40,  -33,
    0,  -78,    0,    0,  -29,    0,    0,    0,    0, -189,
    0,  -18,   -5,   62,   74,    0,    0,   15,    0,    0,
    0,   -8,    0, -173,    0,    0,    0,    0,   64,    0,
 -154, -153,    0,    9,   46,    0,    0,   15,  -27,    0,
    0,    0,    0,    0,    0, -110,    0,   72,  -30,   15,
   15,   91,    0,    0,  -37,    0,   15,   15,   15,   15,
   15,    0,    0,    0,    0,   15,   15,    0,    0,   15,
    0,   -2,   15, -152,    0,    0,   95,   97,    0,    0,
   15, -153,    0,    0,    0,    0,    9,    9,   23,    0,
    0,    8,   92,    0,    0,    0,  111,    0,   15,    0,
  -29,  -29,  -29,    0,    0,    0, -100,    0,    0,  -29,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  177,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    6,  -39,    0,   28,   54,    0,    0,    0,   37,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   16,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -32,    0,    0,    0,    0,   60,   66,   71,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
  138,  152,    0,  118,  139,    0,  149,  -28,    0,  106,
  -42,   98,    0,    0,    0,    3,    0,  145,    0,    0,
    0,    0,    0,    0,    0,   79,  -77,    0,   84,   85,
  -97,   75,    0,    0,    0,    0,  -49,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static int YYTABLESIZE=353;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         80,
   69,   87,   51,   63,   87,   66,   78,   41,   88,  132,
   99,   88,  132,    3,   37,  134,   70,   84,  134,   87,
  131,  140,  105,  141,   80,  106,   88,   80,  147,  148,
   80,   78,   99,  133,   78,    6,  103,   78,  150,   84,
   49,  144,  145,  146,    8,  121,   89,   80,  158,   89,
  119,  159,   71,  107,   78,  120,   71,   71,   71,   71,
   71,   11,   71,   70,   89,  127,   34,  126,   80,   39,
   80,   80,   80,   71,   71,   71,   40,   71,   71,   71,
   43,   71,   45,   71,   59,   68,   80,   80,  127,   80,
  126,   77,   79,   60,   83,   71,   71,   83,   71,  104,
   82,  110,   82,   82,   82,  128,   81,  129,   81,   81,
   81,   84,   83,  111,   84,  115,  113,   79,   82,   82,
   79,   82,  116,   79,   81,   81,  117,   81,  118,   84,
  136,  139,  166,  167,  168,  155,  154,  156,   54,   55,
   79,  171,   13,   14,   15,   16,   17,   18,   19,   20,
  160,  108,   56,   57,   18,   19,  112,   13,   14,   15,
   16,   17,   18,   19,   20,   21,   22,   23,   24,  164,
  169,   25,   21,   22,   23,   24,    2,   58,   42,  102,
   21,   22,   23,   24,   48,   64,   25,  114,  137,  138,
   13,   14,   15,   16,   17,   18,   19,   67,   50,   45,
  142,   50,  143,  135,  149,    0,    0,    0,    0,    0,
  151,  153,    0,   21,   22,   23,   24,    0,    0,  157,
    0,    0,    0,    0,   18,   19,    0,    0,    0,    0,
    0,   18,   19,    0,   73,    0,   74,  165,   75,   54,
   55,   87,   21,   22,   23,   24,    0,    0,   88,   21,
   22,   23,   24,   56,   57,   76,   18,   19,    0,   73,
    0,   74,    0,   75,   54,   55,    0,   54,   55,    0,
   54,   55,    0,    0,   21,   22,   23,   24,   56,   57,
   76,   56,   57,   76,   56,   57,   76,   54,   55,    0,
    0,    0,   71,   71,   71,   71,   71,   71,    0,    0,
    0,   56,   57,   76,   80,   80,   80,   80,   80,   80,
    0,    0,    0,   71,   71,   71,   71,   71,   71,    0,
    0,    0,  122,  123,  124,  125,    0,    0,    0,    0,
    0,    0,    0,    0,   83,   83,   82,   82,   82,   82,
   82,   82,   81,   81,   81,   81,   81,   81,    0,    0,
    0,   84,   84,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   41,   93,   93,   44,   93,   40,  125,   41,   40,
   60,   44,   40,  257,   12,   46,   59,   60,   46,   59,
   98,   59,   41,   61,   33,   44,   59,   33,  126,  127,
   33,   40,   82,   61,   40,  258,   65,   40,   41,   82,
   38,  119,  120,  121,  123,   37,   41,   33,   41,   44,
   42,   44,   37,   59,   40,   47,   41,   42,   43,   44,
   45,  259,   47,  106,   59,   43,   91,   45,   41,   91,
   43,   44,   45,   37,   59,   60,   58,   62,   42,   43,
  289,   45,  289,   47,   40,   59,   59,   60,   43,   62,
   45,  125,  126,  123,   41,   59,   60,   44,   62,  289,
   41,   40,   43,   44,   45,   60,   41,   62,   43,   44,
   45,   41,   59,   40,   44,  289,  125,  126,   59,   60,
  126,   62,   59,  126,   59,   60,  281,   62,  282,   59,
   59,   41,  161,  162,  163,   41,  289,   41,  273,  274,
  126,  170,  260,  261,  262,  263,  264,  265,  266,  267,
   59,   73,  287,  288,  265,  266,   78,  260,  261,  262,
  263,  264,  265,  266,  267,  283,  284,  285,  286,   59,
  271,  289,  283,  284,  285,  286,    0,   40,   27,   62,
  283,  284,  285,  286,   36,   47,  289,   82,  110,  111,
  260,  261,  262,  263,  264,  265,  266,   53,  289,  289,
  117,  289,  118,  106,  130,   -1,   -1,   -1,   -1,   -1,
  132,  133,   -1,  283,  284,  285,  286,   -1,   -1,  141,
   -1,   -1,   -1,   -1,  265,  266,   -1,   -1,   -1,   -1,
   -1,  265,  266,   -1,  268,   -1,  270,  159,  272,  273,
  274,  281,  283,  284,  285,  286,   -1,   -1,  281,  283,
  284,  285,  286,  287,  288,  289,  265,  266,   -1,  268,
   -1,  270,   -1,  272,  273,  274,   -1,  273,  274,   -1,
  273,  274,   -1,   -1,  283,  284,  285,  286,  287,  288,
  289,  287,  288,  289,  287,  288,  289,  273,  274,   -1,
   -1,   -1,  277,  278,  279,  280,  281,  282,   -1,   -1,
   -1,  287,  288,  289,  277,  278,  279,  280,  281,  282,
   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,   -1,
   -1,   -1,  277,  278,  279,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  281,  282,  277,  278,  279,  280,
  281,  282,  277,  278,  279,  280,  281,  282,   -1,   -1,
   -1,  281,  282,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=290;
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
"METHOD","INIT","ROUND_BEGIN","ROUND_END","TURN","PLAYER","CARD","SKILL",
"RETURN","STATLIST","IF","ELSE","WHILE","TRUE","FALSE","FOREACH","IN","OP_EQ",
"OP_LE","OP_GE","OP_NE","OP_LOR","OP_LAND","DECLR_INT","DECLR_STR","DECLR_BOOL",
"VOID","INTEGER","STRING","ID","foreach_statement",
};
final static String yyrule[] = {
"$accept : source_code",
"$$1 :",
"source_code : $$1 game_config cards_definition characters_definition procedures_list",
"$$2 :",
"game_config : GAME_DF $$2 json",
"$$3 :",
"cards_definition : CARD_DF $$3 config_list",
"$$4 :",
"characters_definition : CHARACTER_DF $$4 config_list",
"config_list : '[' config_list_content ']'",
"config_list_content : config",
"config_list_content : config_list_content config",
"$$5 :",
"config : ID $$5 json",
"json : '{' json_content '}'",
"json_content : json_item",
"json_content : json_content json_item",
"json_item : ID ':' value ';'",
"json_item : function_definition",
"json_item : skill_list",
"skill_list : SKILL '[' skill_list_content ']'",
"skill_list : SKILL '[' ']'",
"skill_list_content : skill_list_content skill",
"skill_list_content : skill",
"$$6 :",
"skill : ID $$6 block",
"$$7 :",
"function_definition : function_signature $$7 block",
"function_signature : built_in_function_signature",
"function_signature : type ID '(' parameter_config_list ')'",
"function_signature : type ID '(' ')'",
"built_in_function_signature : INIT",
"built_in_function_signature : METHOD",
"built_in_function_signature : ROUND_BEGIN",
"built_in_function_signature : ROUND_END",
"built_in_function_signature : TURN",
"parameter_config_list : parameter_config",
"parameter_config_list : parameter_config_list ',' parameter_config",
"parameter_config : type ID",
"procedures_list : function_definition",
"procedures_list : procedures_list function_definition",
"block : '{' statement_list '}'",
"block : '{' '}'",
"statement_list : statement_list statement",
"statement_list : statement",
"statement : selection_statement",
"statement : field_declaration",
"statement : while_loop_statement",
"statement : Expression ';'",
"statement : QuarlifiedName '=' Expression ';'",
"statement : return_statement",
"return_statement : RETURN Expression ';'",
"return_statement : RETURN ';'",
"$$8 :",
"selection_statement : IF '(' Expression ')' $$8 block",
"$$9 :",
"$$10 :",
"selection_statement : IF '(' Expression ')' $$9 block ELSE $$10 block",
"field_declaration : type ID ';'",
"field_declaration : type ID '=' Expression ';'",
"$$11 :",
"while_loop_statement : WHILE '(' Expression ')' $$11 block",
"MethodCall : QuarlifiedName '(' ArgumentList ')'",
"MethodCall : QuarlifiedName '(' ')'",
"ArgumentList : Expression",
"ArgumentList : ArgumentList ',' Expression",
"QuarlifiedName : QuarlifiedName '.' ID",
"QuarlifiedName : ID",
"PrimaryExpression : '(' Expression ')'",
"PrimaryExpression : ComplexPrimaryNoParenthesis",
"ComplexPrimaryNoParenthesis : value",
"ComplexPrimaryNoParenthesis : QuarlifiedName",
"ComplexPrimaryNoParenthesis : MethodCall",
"UnaryExpression : LogicalUnaryExpression",
"LogicalUnaryExpression : PrimaryExpression",
"LogicalUnaryExpression : LogicalUnaryOperator UnaryExpression",
"MultiplicativeExpression : UnaryExpression",
"MultiplicativeExpression : MultiplicativeExpression '*' UnaryExpression",
"MultiplicativeExpression : MultiplicativeExpression '/' UnaryExpression",
"MultiplicativeExpression : MultiplicativeExpression '%' UnaryExpression",
"AdditiveExpression : MultiplicativeExpression",
"AdditiveExpression : AdditiveExpression '+' MultiplicativeExpression",
"AdditiveExpression : AdditiveExpression '-' MultiplicativeExpression",
"RelationalExpression : AdditiveExpression",
"RelationalExpression : AdditiveExpression RelationalBinaryOperator AdditiveExpression",
"ConditionalAndExpression : RelationalExpression",
"ConditionalAndExpression : ConditionalAndExpression OP_LAND RelationalExpression",
"ConditionalOrExpression : ConditionalAndExpression",
"ConditionalOrExpression : ConditionalOrExpression OP_LOR ConditionalAndExpression",
"Expression : ConditionalOrExpression",
"type : PLAYER",
"type : CARD",
"type : VOID",
"type : primitive_type",
"primitive_type : DECLR_INT",
"primitive_type : DECLR_STR",
"primitive_type : DECLR_BOOL",
"value : INTEGER",
"value : STRING",
"value : TRUE",
"value : FALSE",
"LogicalUnaryOperator : '~'",
"LogicalUnaryOperator : '!'",
"RelationalBinaryOperator : '<'",
"RelationalBinaryOperator : '>'",
"RelationalBinaryOperator : OP_EQ",
"RelationalBinaryOperator : OP_LE",
"RelationalBinaryOperator : OP_GE",
"RelationalBinaryOperator : OP_NE",
};

//#line 837 "GameWizard.y"

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
//#line 508 "Parser.java"
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
//#line 112 "GameWizard.y"
{ SymbolTable.initSymbolTable();}
break;
case 2:
//#line 113 "GameWizard.y"
{
		System.out.println("source_code finished");
		ArrayList<FunctionObj> procedures = (ArrayList<FunctionObj>)val_peek(0).obj;
		for(FunctionObj func : procedures){
			JsonItem ji = new JsonItem(JsonItemType.Function);
			ji.func = func;
			Util.game.add(ji);
		}
		Util.genGame();
		Util.genMakefile();
	}
break;
case 3:
//#line 125 "GameWizard.y"
{ SymbolTable.current = 1; }
break;
case 4:
//#line 126 "GameWizard.y"
{
		System.out.println("game_config finished");
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) val_peek(0).obj;
        Util.game = json;
    }
break;
case 5:
//#line 132 "GameWizard.y"
{ SymbolTable.current = 2; }
break;
case 6:
//#line 133 "GameWizard.y"
{
		System.out.println("cards_definition finished");
        ArrayList<Config> config_list = (ArrayList<Config>) val_peek(0).obj;
        Util.genAllCards(config_list);
    }
break;
case 7:
//#line 139 "GameWizard.y"
{ SymbolTable.current = 3; }
break;
case 8:
//#line 140 "GameWizard.y"
{
		System.out.println("characters_definition finished");
        ArrayList<Config> config_list = (ArrayList<Config>) val_peek(0).obj;
        Util.genAllCharacters(config_list);
        SymbolTable.current = 4;
    }
break;
case 9:
//#line 147 "GameWizard.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 10:
//#line 151 "GameWizard.y"
{
        ArrayList<Config> config_list = new ArrayList<Config>();
        config_list.add((Config) val_peek(0).obj );

        if( SymbolTable.current == 2 && SymbolTable.firstCard )
            SymbolTable.firstCard = false;
        if( SymbolTable.current == 3 && SymbolTable.firstCharacter )
            SymbolTable.firstCharacter = false;

        yyval.obj = config_list;
    }
break;
case 11:
//#line 163 "GameWizard.y"
{
        ArrayList<Config> config_list = (ArrayList<Config>) val_peek(1).obj;
        config_list.add((Config) val_peek(0).obj );
        yyval.obj = config_list;
    }
break;
case 12:
//#line 170 "GameWizard.y"
{
        if( (SymbolTable.current == 2 && SymbolTable.putNewCardName(val_peek(0).sval)) || (SymbolTable.current == 3 && SymbolTable.putNewCharacterName(val_peek(0).sval)) ){
            SymbolTable.current_all_IDs = new HashSet<String>();
            SymbolTable.currentCardCharacterBlock = new ScopeBlock();
            if(SymbolTable.current == 3)
                SymbolTable.current_skill_IDs = new HashSet<String>();
        }
        else{
            yyerror("Attempted to make " + val_peek(0).sval + "card/character name failed.");
        }
    }
break;
case 13:
//#line 182 "GameWizard.y"
{
        Config c = new Config();
        c.id = val_peek(2).sval;
        c.json = (ArrayList<JsonItem>)val_peek(0).obj;
        yyval.obj = c;
    }
break;
case 14:
//#line 189 "GameWizard.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 15:
//#line 193 "GameWizard.y"
{
        ArrayList<JsonItem> json = new ArrayList<JsonItem>();
        json.add((JsonItem) val_peek(0).obj );
        yyval.obj = json;
    }
break;
case 16:
//#line 199 "GameWizard.y"
{
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) val_peek(1).obj;
        json.add((JsonItem) val_peek(0).obj );
        yyval.obj = json;
    }
break;
case 17:
//#line 207 "GameWizard.y"
{
        JsonItem ji = null;
        AttributeObj v = (AttributeObj)val_peek(1).obj;
        v.id = val_peek(3).sval;
        if( (SymbolTable.current == 2 && SymbolTable.putInCard(val_peek(3).sval, true, v)) || (SymbolTable.current == 3 && SymbolTable.putInCharacter(val_peek(3).sval, true, v)) 
            || ((SymbolTable.current == 1 || SymbolTable.current == 4) && SymbolTable.putInGame(val_peek(3).sval, true, v)) ){

            ji = new JsonItem(JsonItemType.Attribute);
            ji.attr = v;
            yyval.obj = ji;

        }
        else{
            yyerror(val_peek(3).sval + " can not be put in current scope.");
        }
        yyval.obj = ji;
    }
break;
case 18:
//#line 225 "GameWizard.y"
{
		FunctionObj f = (FunctionObj)val_peek(0).obj;
		JsonItem ji = new JsonItem(JsonItemType.Function);
		ji.func = f;
		yyval.obj = ji;
	}
break;
case 19:
//#line 232 "GameWizard.y"
{
		ArrayList<Skill> skills = (ArrayList<Skill>)val_peek(0).obj;
		JsonItem ji = new JsonItem(JsonItemType.SkillList);
		ji.skills = skills;
		yyval.obj = ji;
	}
break;
case 20:
//#line 240 "GameWizard.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 21:
//#line 241 "GameWizard.y"
{ yyval.obj = new ArrayList<Skill>(); }
break;
case 22:
//#line 245 "GameWizard.y"
{
		ArrayList<Skill> skills = (ArrayList<Skill>)val_peek(1).obj;
		Skill skill = (Skill)val_peek(0).obj;
		skills.add(skill);
		yyval.obj = skills;
	}
break;
case 23:
//#line 252 "GameWizard.y"
{
		ArrayList<Skill> skills = new ArrayList<Skill>();
		Skill skill = (Skill)val_peek(0).obj;
        skills.add(skill);
        yyval.obj = skills;
	}
break;
case 24:
//#line 260 "GameWizard.y"
{
        if(SymbolTable.putInSkill(val_peek(0).sval)){
            SymbolTable.newLocalBlock();
            AttributeObj dealer = new AttributeObj("dealer", Type.PLAYER);
            SymbolTable.putInLocal("dealer", dealer);
            FunctionObj skill = new FunctionObj();
            skill.return_type = Type.BOOLEAN;
            SymbolTable.curFunction = skill;
        }
        else{
            yyerror(val_peek(0).sval + " can not be used as a skill name anymore.");
        }
    }
break;
case 25:
//#line 274 "GameWizard.y"
{
		Skill skill = new Skill();
		skill.id = val_peek(2).sval;
		skill.body = val_peek(0).sval;
        SymbolTable.curFunction = null;
		yyval.obj = skill;
	}
break;
case 26:
//#line 284 "GameWizard.y"
{
        FunctionObj f = (FunctionObj) val_peek(0).obj;
        System.out.println("translated function signature: " + f.id);
        if( (SymbolTable.current == 2 && SymbolTable.putInCard(f.id, false, f)) || (SymbolTable.current == 3 && SymbolTable.putInCharacter(f.id, false, f)) 
            || ((SymbolTable.current == 1 ||SymbolTable.current == 4 ) && SymbolTable.putInGame(f.id, false, f)) ){
            SymbolTable.curFunction = f;
            SymbolTable.newLocalBlock();
            for(AttributeObj para : f.parameters){
                SymbolTable.putInLocal(para.id, para);
            }
     
        }
    }
break;
case 27:
//#line 298 "GameWizard.y"
{
		FunctionObj f = (FunctionObj) val_peek(2).obj;
		f.body = val_peek(0).sval;
        SymbolTable.curFunction = null;
		yyval.obj = f;
	}
break;
case 28:
//#line 306 "GameWizard.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 29:
//#line 308 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = val_peek(3).sval;
		f.return_type = (Type)val_peek(4).obj;
		f.parameters = (ArrayList<AttributeObj>)val_peek(1).obj;
		yyval.obj = f;
	}
break;
case 30:
//#line 316 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = val_peek(2).sval;
		f.return_type = (Type)val_peek(3).obj;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 31:
//#line 326 "GameWizard.y"
{
        if(SymbolTable.current != 4){
            yyerror("init can only be defined after characters");
        }
		FunctionObj f = new FunctionObj();
		f.id = "init";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 32:
//#line 337 "GameWizard.y"
{
        if(SymbolTable.current != 2 ){
            yyerror("method can only be defined for cards");
        }
		FunctionObj f = new FunctionObj();
		f.id = "method";
		f.return_type = Type.BOOLEAN;
		f.parameters = new ArrayList<AttributeObj>();
		AttributeObj para = new AttributeObj("dealer", Type.PLAYER);
		f.parameters.add(para);
		yyval.obj = f;
	}
break;
case 33:
//#line 350 "GameWizard.y"
{
        if(SymbolTable.current != 4){
            yyerror("round_begin can only be defined after characters");
        }
		FunctionObj f = new FunctionObj();
		f.id = "round_begin";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 34:
//#line 361 "GameWizard.y"
{
        if(SymbolTable.current != 4){
            yyerror("round_end can only be defined after characters");
        }
		FunctionObj f = new FunctionObj();
		f.id = "round_end";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 35:
//#line 372 "GameWizard.y"
{
        if(SymbolTable.current != 4){
            yyerror("turn can only be defined after characters");
        }
		FunctionObj f = new FunctionObj();
		f.id = "turn";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		AttributeObj para = new AttributeObj("player", Type.PLAYER);
		f.parameters.add(para);
		yyval.obj = f;
	}
break;
case 36:
//#line 387 "GameWizard.y"
{
        ArrayList<AttributeObj> parameters = new ArrayList<AttributeObj>();
        parameters.add((AttributeObj) val_peek(0).obj );
        yyval.obj = parameters;
	}
break;
case 37:
//#line 393 "GameWizard.y"
{
        ArrayList<AttributeObj> parameters= (ArrayList<AttributeObj>) val_peek(2).obj;
        parameters.add((AttributeObj) val_peek(0).obj );
        yyval.obj = parameters;
	}
break;
case 38:
//#line 400 "GameWizard.y"
{
		AttributeObj a = new AttributeObj(val_peek(0).sval, (Type)val_peek(1).obj);
		yyval.obj = a;
	}
break;
case 39:
//#line 407 "GameWizard.y"
{
		FunctionObj f = (FunctionObj)val_peek(0).obj;
		ArrayList<FunctionObj> funcs = new ArrayList<FunctionObj>();
		funcs.add(f);
		yyval.obj = funcs;
	}
break;
case 40:
//#line 414 "GameWizard.y"
{
		ArrayList<FunctionObj> funcs = (ArrayList<FunctionObj>)val_peek(1).obj;
		FunctionObj f = (FunctionObj)val_peek(0).obj;
		funcs.add(f);
        yyval.obj = funcs;
	}
break;
case 41:
//#line 423 "GameWizard.y"
{ SymbolTable.popLocalBlock(); yyval.sval = val_peek(1).sval; }
break;
case 42:
//#line 424 "GameWizard.y"
{ SymbolTable.popLocalBlock(); yyval.sval = "";}
break;
case 43:
//#line 427 "GameWizard.y"
{ yyval.sval=val_peek(1).sval + "\n" + val_peek(0).sval;}
break;
case 44:
//#line 428 "GameWizard.y"
{ yyval.sval=val_peek(0).sval;}
break;
case 45:
//#line 431 "GameWizard.y"
{ yyval.sval=val_peek(0).sval;}
break;
case 46:
//#line 432 "GameWizard.y"
{ yyval.sval=val_peek(0).sval;}
break;
case 47:
//#line 433 "GameWizard.y"
{ yyval.sval=val_peek(0).sval;}
break;
case 48:
//#line 435 "GameWizard.y"
{
        Expression exp = (Expression)val_peek(1).obj;
        yyval.sval=exp.code+";";
    }
break;
case 49:
//#line 440 "GameWizard.y"
{
        QuarlifiedName qn = (QuarlifiedName)val_peek(3).obj;
        Expression exp = (Expression)val_peek(1).obj;
        if(qn.sr.isAttribute && qn.sr.attr.type.equals(exp.return_type)){
            yyval.sval = qn.code + " = " + exp.code +";";
        }
        else{
           yyerror(qn.code + " can not be assigned with ( " + exp.code + " )"); 
        }
    }
break;
case 50:
//#line 450 "GameWizard.y"
{ yyval.sval=val_peek(0).sval;}
break;
case 51:
//#line 457 "GameWizard.y"
{
        Expression exp = (Expression)val_peek(1).obj;
        if(SymbolTable.curFunction.return_type.equals(exp.return_type)){
            yyval.sval = "return " + exp.code + ";";
        }
        else{
            yyerror("Invalid return statement in " + SymbolTable.curFunction.id);
        }
    }
break;
case 52:
//#line 467 "GameWizard.y"
{
        if(SymbolTable.curFunction.return_type.equals(Type.VOID)){
            yyval.sval = "return;";
        }
        else{
            yyerror("Invalid return statement in " + SymbolTable.curFunction.id);
        }
    }
break;
case 53:
//#line 477 "GameWizard.y"
{SymbolTable.newLocalBlock();}
break;
case 54:
//#line 478 "GameWizard.y"
{
		Expression condition = (Expression)val_peek(3).obj;
		if(condition.return_type != Type.BOOLEAN){
			yyerror(condition.code + " does not return boolean!");
		}
		yyval.sval="if("+condition.code+"){\n"+val_peek(0).sval+"\n}\n";
	}
break;
case 55:
//#line 485 "GameWizard.y"
{SymbolTable.newLocalBlock();}
break;
case 56:
//#line 485 "GameWizard.y"
{SymbolTable.newLocalBlock();}
break;
case 57:
//#line 486 "GameWizard.y"
{
		Expression condition = (Expression)val_peek(6).obj;
        if(condition.return_type != Type.BOOLEAN){
			yyerror(condition.code + " does not return boolean!");
        }
		yyval.sval="if("+condition.code+"){\n"+val_peek(3).sval+"\n}\nelse {\n"+val_peek(1).sval+"\n}\n";
	}
break;
case 58:
//#line 496 "GameWizard.y"
{
		Type type = (Type)val_peek(2).obj;
        AttributeObj attr = new AttributeObj(val_peek(1).sval, type);
        System.out.println("test1");
        if(!SymbolTable.putInLocal(val_peek(1).sval, attr)){
            yyerror("Can not declare " + val_peek(1).sval + " at this block." );
        }
        System.out.println("test2");
		yyval.sval=type.toString()+" "+val_peek(1).sval+";";
	}
break;
case 59:
//#line 507 "GameWizard.y"
{
		Type type = (Type)val_peek(4).obj;
		Expression exp = (Expression)val_peek(1).obj;
        if( !exp.return_type.equals(type) ){
			yyerror(val_peek(3).sval + " can not be assigned with (" + exp.code  + ")" );
        }
        AttributeObj attr = new AttributeObj(val_peek(3).sval, type);
        if(!SymbolTable.putInLocal(val_peek(3).sval, attr)){
            yyerror("Can not declare " + val_peek(3).sval + "at this block." );
        }
		yyval.sval=type.toString()+" "+val_peek(3).sval+" = "+exp.code +";";
	}
break;
case 60:
//#line 520 "GameWizard.y"
{SymbolTable.newLocalBlock();}
break;
case 61:
//#line 521 "GameWizard.y"
{
		Expression condition = (Expression)val_peek(3).obj;
        if(condition.return_type != Type.BOOLEAN){
            yyerror(condition.code + " does not return boolean!");
        }
		yyval.sval="while("+condition.code+"){\n"+val_peek(1).sval+"\n}\n";
	}
break;
case 62:
//#line 538 "GameWizard.y"
{
        QuarlifiedName qn = (QuarlifiedName)val_peek(3).obj;
        if(qn.sr.isAttribute){
            yyerror(qn.code + " is not function.");
        }
        FunctionObj func = qn.sr.func;
        String method_call = qn.code+"(";
        ArrayList<Expression> args = (ArrayList<Expression>)val_peek(1).obj;
        if(args.size() != func.parameters.size()){
            yyerror("List of parameters does not match the definiton of "+ val_peek(3).obj);
        }
        for( int i =0;i<args.size();i++ ){
            Expression exp = args.get(i);
            AttributeObj para = func.parameters.get(i);
            if(exp.return_type != para.type){
                yyerror("List of parameters does not match the definiton of "+ val_peek(3).obj);
            }
            if(i!=0)
                method_call = method_call + ", ";
            method_call = method_call + exp.code;
        }
        Expression func_call = new Expression();
        func_call.code = method_call + ")";
        func_call.return_type = func.return_type;
        yyval.obj = func_call;
	}
break;
case 63:
//#line 565 "GameWizard.y"
{
        QuarlifiedName qn = (QuarlifiedName)val_peek(2).obj;
        if(qn.sr.isAttribute){
            yyerror(qn.code + " is not function.");
        }
        FunctionObj func = qn.sr.func;
        String method_call = val_peek(2).obj+"(";
        if(0 != func.parameters.size()){
            yyerror("List of parameters does not match the definiton of "+ val_peek(2).obj);
        }
        Expression func_call = new Expression();
        func_call.code = method_call + ")";
        func_call.return_type = func.return_type;
        yyval.obj = func_call;
    }
break;
case 64:
//#line 583 "GameWizard.y"
{
        Expression exp = (Expression)val_peek(0).obj;
        ArrayList<Expression> args = new ArrayList<Expression>();
        args.add(exp);
		yyval.obj = args;
	}
break;
case 65:
//#line 590 "GameWizard.y"
{
        ArrayList<Expression> args = (ArrayList<Expression>)val_peek(2).obj;
        Expression exp = (Expression)val_peek(0).obj;
        args.add(exp);
        yyval.obj = args;
    }
break;
case 66:
//#line 599 "GameWizard.y"
{
        QuarlifiedName qn = (QuarlifiedName)val_peek(2).obj;
        if(qn.sr.isAttribute){
            SymbolRecord sr = Type.dotOP(qn.sr.attr.type ,val_peek(0).sval);
            if( sr == null ){
                yyerror(val_peek(0).sval + " is not a attribute/function of type "+qn.sr.attr.type.toString());
            }
            if( qn.sr.attr.type.equals(Type.PLAYER) && sr.scope == SymbolTable.baseCharacterBlock ){
                qn.code = qn.code + ".character";
            }
            qn.code = qn.code + "." + val_peek(0).sval;
            qn.sr = sr; 
        }
        else{
            yyerror("function must have parameters.");
        }
        yyval.obj=qn;
    }
break;
case 67:
//#line 618 "GameWizard.y"
{
        QuarlifiedName qn = new QuarlifiedName();
        SymbolRecord sr = SymbolTable.accessID(val_peek(0).sval);
        if( sr == null ){
            yyerror(val_peek(0).sval + " is unknown");
        }
        else{
            qn.code = val_peek(0).sval;
            if(sr.scope == SymbolTable.gameBlock )
                qn.code = "Game." + val_peek(0).sval;
            qn.sr = sr; 
        }
        yyval.obj=qn;
    }
break;
case 68:
//#line 635 "GameWizard.y"
{
        Expression exp = (Expression)val_peek(1).obj;
        exp.code = "( " + exp.code + " )";
        yyval.obj=exp;
    }
break;
case 69:
//#line 640 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 70:
//#line 644 "GameWizard.y"
{
        AttributeObj v = (AttributeObj)val_peek(0).obj;
        Expression exp = new Expression();
        exp.code = v.value;
        exp.return_type = v.type;
        yyval.obj = exp;
    }
break;
case 71:
//#line 652 "GameWizard.y"
{
        QuarlifiedName qn = (QuarlifiedName)val_peek(0).obj;
        if(!qn.sr.isAttribute){
            yyerror("Quarlified name " + qn.code + " reduced to ComplexPrimaryNoParenthesis error: quarlifiedname is function");
        }
        Expression exp = new Expression();
        exp.code = qn.code;
        exp.return_type = qn.sr.attr.type;
        yyval.obj = exp;
    }
break;
case 72:
//#line 663 "GameWizard.y"
{
        yyval.obj = val_peek(0).obj;
    }
break;
case 73:
//#line 668 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 74:
//#line 671 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 75:
//#line 673 "GameWizard.y"
{
        Expression exp = (Expression)val_peek(0).obj;
        if(exp.return_type != Type.BOOLEAN){
            yyerror(exp.code + " does not return boolean!");
        }
        exp.code = val_peek(1).sval+exp.code;
        yyval.obj = exp;
    }
break;
case 76:
//#line 683 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 77:
//#line 685 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with *, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " * " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 78:
//#line 698 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with /, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " / " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 79:
//#line 711 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with %, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " % " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 80:
//#line 725 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 81:
//#line 727 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER
            || exp1.return_type == Type.STRING )){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with +");
        }
        String exp_code = exp1.code + " + " + exp2.code;
        Expression exp = new Expression();
        if(exp1.return_type == Type.STRING)
            exp.return_type = Type.STRING;
        else
            exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 82:
//#line 744 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with -, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " - " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 83:
//#line 758 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 84:
//#line 760 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with " + val_peek(1).sval + ", because at least one of them is not integer");
        }
        String exp_code = exp1.code + val_peek(1).sval + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.BOOLEAN;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 85:
//#line 774 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 86:
//#line 776 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.BOOLEAN && exp2.return_type == Type.BOOLEAN)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with &&, because at least one of them is not boolean");
        }
        String exp_code = exp1.code + "&&" + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.BOOLEAN;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 87:
//#line 790 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 88:
//#line 792 "GameWizard.y"
{
        Expression exp1 = (Expression)val_peek(2).obj;
        Expression exp2 = (Expression)val_peek(0).obj;
        if(!(exp1.return_type == Type.BOOLEAN && exp2.return_type == Type.BOOLEAN)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with ||, because at least one of them is not boolean");
        }
        String exp_code = exp1.code + "||" + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.BOOLEAN;
        exp.code = exp_code;
        yyval.obj=exp;
    }
break;
case 89:
//#line 805 "GameWizard.y"
{yyval.obj=val_peek(0).obj;}
break;
case 90:
//#line 808 "GameWizard.y"
{ yyval.obj = Type.PLAYER; }
break;
case 91:
//#line 809 "GameWizard.y"
{ yyval.obj = Type.CARD; }
break;
case 92:
//#line 810 "GameWizard.y"
{ yyval.obj = Type.VOID; }
break;
case 93:
//#line 811 "GameWizard.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 94:
//#line 814 "GameWizard.y"
{ yyval.obj = Type.INTEGER; }
break;
case 95:
//#line 815 "GameWizard.y"
{ yyval.obj = Type.STRING; }
break;
case 96:
//#line 816 "GameWizard.y"
{ yyval.obj = Type.BOOLEAN; }
break;
case 97:
//#line 819 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.INTEGER, Integer.toString(val_peek(0).ival)); }
break;
case 98:
//#line 820 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.STRING, val_peek(0).sval); }
break;
case 99:
//#line 821 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.BOOLEAN, "true"); }
break;
case 100:
//#line 822 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.BOOLEAN, "false"); }
break;
case 101:
//#line 825 "GameWizard.y"
{yyval.sval="~";}
break;
case 102:
//#line 826 "GameWizard.y"
{yyval.sval="!";}
break;
case 103:
//#line 829 "GameWizard.y"
{yyval.sval="<";}
break;
case 104:
//#line 830 "GameWizard.y"
{yyval.sval=">";}
break;
case 105:
//#line 831 "GameWizard.y"
{yyval.sval="==";}
break;
case 106:
//#line 832 "GameWizard.y"
{yyval.sval="<=";}
break;
case 107:
//#line 833 "GameWizard.y"
{yyval.sval=">=";}
break;
case 108:
//#line 834 "GameWizard.y"
{yyval.sval="!=";}
break;
//#line 1566 "Parser.java"
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
