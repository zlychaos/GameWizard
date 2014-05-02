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
public final static short STATLIST=268;
public final static short IF=269;
public final static short ELSE=270;
public final static short WHILE=271;
public final static short TRUE=272;
public final static short FALSE=273;
public final static short OP_EQ=274;
public final static short OP_LE=275;
public final static short OP_GE=276;
public final static short OP_NE=277;
public final static short OP_LOR=278;
public final static short OP_LAND=279;
public final static short DECLR_INT=280;
public final static short DECLR_STR=281;
public final static short DECLR_BOOL=282;
public final static short VOID=283;
public final static short INTEGER=284;
public final static short STRING=285;
public final static short ID=286;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,   20,   21,   22,    7,    6,    6,    5,    4,    3,
    3,    2,    2,    2,   18,   18,   19,   19,   17,    1,
    1,    1,    1,   15,   14,   14,   14,   13,   13,   13,
   13,   13,   12,   12,   11,   10,   10,   10,   10,   10,
   10,    8,    9,   16,   16,
};
final static short yylen[] = {                            2,
    4,    2,    2,    2,    3,    1,    3,    2,    3,    1,
    3,    3,    1,    1,    4,    3,    2,    1,    2,    1,
    1,    1,    1,    2,    1,    5,    4,    1,    1,    1,
    1,    1,    1,    3,    2,    1,    1,    1,    1,    1,
    1,    3,    1,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    2,    0,    0,   29,   28,   30,
   31,   32,   36,   37,    0,   39,   40,   41,   38,    0,
   10,    0,    0,   25,    0,   13,   14,    0,    3,    0,
    0,    0,    0,    0,    9,    0,    0,   24,    0,    6,
    0,    4,   44,    0,    0,   16,   18,    0,   22,   23,
   20,   21,   12,   11,    0,   43,    0,    8,    5,    0,
   45,   19,   15,   17,   27,    0,   33,    0,   42,    7,
   35,   26,    0,   34,
};
final static short yydgoto[] = {                          2,
   53,   21,   22,    5,   40,   41,   29,   38,   57,   23,
   67,   68,   24,   25,   26,   44,   47,   27,   48,    3,
    7,   31,
};
final static short yysindex[] = {                      -235,
  -81,    0, -226, -255,    0,  -48, -211,    0,    0,    0,
    0,    0,    0,    0,  -42,    0,    0,    0,    0,   -8,
    0,  -56, -231,    0,  -72,    0,    0, -230,    0,  -48,
 -247,  -92, -232, -255,    0,   14, -210,    0,  -81,    0,
  -55,    0,    0, -247,  -72,    0,    0,  -91,    0,    0,
    0,    0,    0,    0,  -41,    0,  -68,    0,    0, -230,
    0,    0,    0,    0,    0, -227,    0,  -21,    0,    0,
    0,    0, -236,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   60,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   27,    0,   23,    3,    0,   34,   20,    0,  -34,
   -6,    0,    0,    0,   -7,    0,   18,    0,    0,    0,
    0,    0,
};
final static int YYTABLESIZE=242;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         65,
   46,   63,   34,   60,    8,    9,   10,   11,   12,   13,
   14,   15,    8,    9,   10,   11,   12,   13,   14,   72,
   66,    1,   73,   43,   16,   17,   18,   19,   13,   14,
   20,    6,   16,   17,   18,   19,   61,   59,   66,   49,
   50,    4,   28,   16,   17,   18,   19,   30,   32,   33,
   37,   51,   52,   55,   36,   39,   69,   56,   71,    1,
   54,   58,   70,   42,   62,   64,   74,    0,   35,    0,
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
    0,    0,    0,   45,   45,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   13,   14,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   16,   17,
   18,   19,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   93,   93,   59,   59,  260,  261,  262,  263,  264,  265,
  266,  267,  260,  261,  262,  263,  264,  265,  266,   41,
   55,  257,   44,   31,  280,  281,  282,  283,  265,  266,
  286,  258,  280,  281,  282,  283,   44,   93,   73,  272,
  273,  123,   91,  280,  281,  282,  283,  259,   91,   58,
  123,  284,  285,   40,  286,  286,  125,  268,  286,    0,
   34,   39,   60,   30,   45,   48,   73,   -1,  125,   -1,
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
   -1,   -1,   -1,  286,  286,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  265,  266,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  280,  281,
  282,  283,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=286;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'","'^'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"GAME_DF","CARD_DF","CHARACTER_DF",
"METHOD","INIT","ROUND_BEGIN","ROUND_END","TURN","PLAYER","CARD","SKILL",
"STATLIST","IF","ELSE","WHILE","TRUE","FALSE","OP_EQ","OP_LE","OP_GE","OP_NE",
"OP_LOR","OP_LAND","DECLR_INT","DECLR_STR","DECLR_BOOL","VOID","INTEGER",
"STRING","ID",
};
final static String yyrule[] = {
"$accept : source_code",
"source_code : game_config cards_definition characters_definition procedures_list",
"game_config : GAME_DF json",
"cards_definition : CARD_DF config_list",
"characters_definition : CHARACTER_DF config_list",
"config_list : '[' config_list_content ']'",
"config_list_content : config",
"config_list_content : config_list_content ';' config",
"config : ID json",
"json : '{' json_content '}'",
"json_content : json_item",
"json_content : json_content ';' json_item",
"json_item : ID ':' value",
"json_item : function_definition",
"json_item : skill_list",
"skill_list : SKILL '[' skill_list_content ']'",
"skill_list : SKILL '[' ']'",
"skill_list_content : skill_list_content skill",
"skill_list_content : skill",
"skill : ID block",
"value : INTEGER",
"value : STRING",
"value : TRUE",
"value : FALSE",
"function_definition : function_signature block",
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
"type : PLAYER",
"type : CARD",
"type : VOID",
"type : DECLR_INT",
"type : DECLR_STR",
"type : DECLR_BOOL",
"block : '{' statement_list '}'",
"statement_list : STATLIST",
"procedures_list : function_definition",
"procedures_list : procedures_list function_definition",
};

//#line 334 "GameWizard.y"

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
//#line 371 "Parser.java"
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
//#line 84 "GameWizard.y"
{
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
case 2:
//#line 96 "GameWizard.y"
{
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) val_peek(0).obj;
        Util.game = json;
    }
break;
case 3:
//#line 102 "GameWizard.y"
{
        ArrayList<Config> config_list = (ArrayList<Config>) val_peek(0).obj;
        Util.genAllCards(config_list);
    }
break;
case 4:
//#line 108 "GameWizard.y"
{
        ArrayList<Config> config_list = (ArrayList<Config>) val_peek(0).obj;
        Util.genAllCharacters(config_list);
    }
break;
case 5:
//#line 113 "GameWizard.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 6:
//#line 117 "GameWizard.y"
{
        ArrayList<Config> config_list = new ArrayList<Config>();
        config_list.add((Config) val_peek(0).obj );
        yyval.obj = config_list;
    }
break;
case 7:
//#line 123 "GameWizard.y"
{
        ArrayList<Config> config_list = (ArrayList<Config>) val_peek(2).obj;
        config_list.add((Config) val_peek(0).obj );
        yyval.obj = config_list;
    }
break;
case 8:
//#line 130 "GameWizard.y"
{
        Config c = new Config();
        c.id = val_peek(1).sval;
        c.json = (ArrayList<JsonItem>)val_peek(0).obj;
        yyval.obj = c;
    }
break;
case 9:
//#line 137 "GameWizard.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 10:
//#line 141 "GameWizard.y"
{
        ArrayList<JsonItem> json = new ArrayList<JsonItem>();
        json.add((JsonItem) val_peek(0).obj );
        yyval.obj = json;
    }
break;
case 11:
//#line 147 "GameWizard.y"
{
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) val_peek(2).obj;
        json.add((JsonItem) val_peek(0).obj );
        yyval.obj = json;
    }
break;
case 12:
//#line 155 "GameWizard.y"
{
        AttributeObj v = (AttributeObj)val_peek(0).obj;
        v.id = val_peek(2).sval;
        JsonItem ji = new JsonItem(JsonItemType.Attribute);
        ji.attr = v;
        yyval.obj = ji;
    }
break;
case 13:
//#line 163 "GameWizard.y"
{
		FunctionObj f = (FunctionObj)val_peek(0).obj;
		JsonItem ji = new JsonItem(JsonItemType.Function);
		ji.func = f;
		yyval.obj = ji;
	}
break;
case 14:
//#line 170 "GameWizard.y"
{
		ArrayList<Skill> skills = (ArrayList<Skill>)val_peek(0).obj;
		JsonItem ji = new JsonItem(JsonItemType.SkillList);
		ji.skills = skills;
		yyval.obj = ji;
	}
break;
case 15:
//#line 178 "GameWizard.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 16:
//#line 179 "GameWizard.y"
{ yyval.obj = new ArrayList<Skill>(); }
break;
case 17:
//#line 183 "GameWizard.y"
{
		ArrayList<Skill> skills = (ArrayList<Skill>)val_peek(1).obj;
		Skill skill = (Skill)val_peek(0).obj;
		skills.add(skill);
		yyval.obj = skills;
	}
break;
case 18:
//#line 190 "GameWizard.y"
{
		ArrayList<Skill> skills = new ArrayList<Skill>();
		Skill skill = (Skill)val_peek(0).obj;
        skills.add(skill);
        yyval.obj = skills;
	}
break;
case 19:
//#line 198 "GameWizard.y"
{
		Skill skill = new Skill();
		skill.id = val_peek(1).sval;
		skill.body = val_peek(0).sval;
		yyval.obj = skill;
	}
break;
case 20:
//#line 206 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.INTEGER, Integer.toString(val_peek(0).ival)); }
break;
case 21:
//#line 207 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.STRING, val_peek(0).sval); }
break;
case 22:
//#line 208 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.BOOLEAN, "true"); }
break;
case 23:
//#line 209 "GameWizard.y"
{ yyval.obj=new AttributeObj(Type.BOOLEAN, "false"); }
break;
case 24:
//#line 213 "GameWizard.y"
{
		FunctionObj f = (FunctionObj) val_peek(1).obj;
		f.body = val_peek(0).sval;
		yyval.obj = f;
	}
break;
case 25:
//#line 220 "GameWizard.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 26:
//#line 222 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = val_peek(3).sval;
		f.return_type = (Type)val_peek(4).obj;
		f.parameters = (ArrayList<AttributeObj>)val_peek(1).obj;
		yyval.obj = f;
	}
break;
case 27:
//#line 230 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = val_peek(2).sval;
		f.return_type = (Type)val_peek(3).obj;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 28:
//#line 240 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = "init";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 29:
//#line 248 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = "method";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		AttributeObj para = new AttributeObj("dealer", Type.PLAYER);
		f.parameters.add(para);
		yyval.obj = f;
	}
break;
case 30:
//#line 258 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = "round_begin";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 31:
//#line 266 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = "round_end";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		yyval.obj = f;
	}
break;
case 32:
//#line 274 "GameWizard.y"
{
		FunctionObj f = new FunctionObj();
		f.id = "turn";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		AttributeObj para = new AttributeObj("player", Type.PLAYER);
		f.parameters.add(para);
		yyval.obj = f;
	}
break;
case 33:
//#line 286 "GameWizard.y"
{
        ArrayList<AttributeObj> parameters = new ArrayList<AttributeObj>();
        parameters.add((AttributeObj) val_peek(0).obj );
        yyval.obj = parameters;
	}
break;
case 34:
//#line 292 "GameWizard.y"
{
        ArrayList<AttributeObj> parameters= (ArrayList<AttributeObj>) val_peek(2).obj;
        parameters.add((AttributeObj) val_peek(0).obj );
        yyval.obj = parameters;
	}
break;
case 35:
//#line 299 "GameWizard.y"
{
		AttributeObj a = new AttributeObj(val_peek(0).sval, (Type)val_peek(1).obj);
		yyval.obj = a;
	}
break;
case 36:
//#line 305 "GameWizard.y"
{ yyval.obj = Type.PLAYER; }
break;
case 37:
//#line 306 "GameWizard.y"
{ yyval.obj = Type.CARD; }
break;
case 38:
//#line 307 "GameWizard.y"
{ yyval.obj = Type.VOID; }
break;
case 39:
//#line 308 "GameWizard.y"
{ yyval.obj = Type.INTEGER; }
break;
case 40:
//#line 309 "GameWizard.y"
{ yyval.obj = Type.STRING; }
break;
case 41:
//#line 310 "GameWizard.y"
{ yyval.obj = Type.BOOLEAN; }
break;
case 42:
//#line 312 "GameWizard.y"
{  yyval.sval = val_peek(1).sval; }
break;
case 43:
//#line 314 "GameWizard.y"
{ yyval.sval = val_peek(0).sval;}
break;
case 44:
//#line 318 "GameWizard.y"
{
		FunctionObj f = (FunctionObj)val_peek(0).obj;
		ArrayList<FunctionObj> funcs = new ArrayList<FunctionObj>();
		funcs.add(f);
		yyval.obj = funcs;
	}
break;
case 45:
//#line 325 "GameWizard.y"
{
		ArrayList<FunctionObj> funcs = (ArrayList<FunctionObj>)val_peek(1).obj;
		FunctionObj f = (FunctionObj)val_peek(0).obj;
		funcs.add(f);
        yyval.obj = funcs;
	}
break;
//#line 841 "Parser.java"
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
