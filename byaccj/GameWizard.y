/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * This is a modified version of the example from                          *
 *   http://www.lincom-asg.com/~rjamison/byacc/                            *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%{
  import java.io.*;
%}
      
%token GAME_DF
%token CARD_DF
%token CHARACTER_DF
%token GAME_NM
%token PLAYER_C
%token PLAYER
%token SKILL

%token METHOD
%token GAME_PORT
%token IF
%token ELSE
%token WHILE
%token TRUE
%token FALSE
%token OP_EQ
%token OP_LE
%token OP_GE
%token OP_NE
%token OP_LOR
%token OP_LAND




%token <ival> INTEGER
%token <sval> STRING
%token <sval> ID
%type <sval> variable_list
%type <sval> skill_df
%type <sval> STATEMENT_LIST
%type <sval> TFExpression
%type <sval> Expression
%type <sval> Statement
%type <sval> SelectionStatement
%type <sval> IterationStatement
%type <sval> UnaryExpression
%type <sval> AssignmentExpression
%type <sval> ConditionalOrExpression
%type <sval> ConditionalAndExpression
%type <sval> EqualityExpression
%type <sval> RelationalExpression
%type <sval> MultiplicativeExpression
%type <sval> AdditiveExpression
%type <sval> UnaryExpression
%type <sval> LogicalUnaryExpression
%type <sval> PrimaryExpression
%type <sval> LogicalUnaryOperator
%type <sval> QualifiedName
%type <sval> ComplexPrimary
%type <sval> ComplexPrimaryNoParenthesis
%type <sval> ArrayAccess
%type <sval> FieldAccess

%left '-' '+'

%left '*' '/'
%right '^'         /* exponentiation        */
      
%%
input: game_df card_df character_df {}
     ;
game_df : GAME_DF '{' game_df_content '}'  {}
	;
game_df_content : GAME_NM ':' STRING ';'
                  PLAYER_C ':' INTEGER ';'
                  GAME_PORT ':' INTEGER ';'  {Util.writeGameJava($3,$7,$11);System.out.println("4");}
		;
card_df : CARD_DF '[' cards_df_content ']' {System.out.println("2");}
	;
cards_df_content : cards_df_content card_df_content  {System.out.println("1");}
               | card_df_content {System.out.println("3");}
		;
card_df_content: ID '{' METHOD '{' STATEMENT_LIST  '}' '}'
			{System.out.println("5");Util.writeCardsJava($1,$5); }
		;

character_df :  CHARACTER_DF '[' characters_df_content ']' {}
             ;
characters_df_content : characters_df_content character_df_content {}
			| character_df_content {}
			;
character_df_content : ID '{'
			variable_list
			skill_df
			'}'             {Util.writeCharacterJava($1,$3,$4);}
		; 
variable_list : ID ':' INTEGER ';' variable_list	
			{String s = "public Integer "+$1+"="+$3+";\n"+$5; $$=s;}
		| ID ':' STRING	';' variable_list	
			{String s = "public String "+$1+"="+$3+";\n"+$5; $$=s;}
		|		    {String s = ""; $$= s;}
		;

skill_df : SKILL ':' '['
		ID '{'
		METHOD '(' PLAYER ID ')' '{' STATEMENT_LIST '}'
		'}'
	   ']'                 {$$ = $12;}
	;

STATEMENT_LIST
:   SelectionStatement  {System.out.println("7");$$=$1;}
|   IterationStatement  {System.out.println("8");$$=$1;}
;


IterationStatement
:   WHILE '(' Expression ')' Statement
    {System.out.println("8");String s = "while("+$3+")"+$5; $$=s;}
;


SelectionStatement
:   IF '(' Expression ')' Statement
    {System.out.println("6");String s = "if("+$3+")"+$5; $$=s;}
    |IF '(' Expression ')' Statement ELSE Statement
    {System.out.println("7");String s = "if("+$3+")\n"+$5+";\nelse\n"+$7+";"; $$=s;}
;

TFExpression
:   TRUE    {System.out.println("6");String s ="true"; $$=s;}
;

Statement
:   FALSE  {String s ="false"; $$=s;}
;

AssignmentExpression
:   FALSE  {String s ="false"; $$=s;}
;




QualifiedName
: ID    {$$=$1;}
| QualifiedName '.' ID  {$$=$1+"."+$3;}
;

PrimaryExpression
: QualifiedName {$$=$1;}
| ComplexPrimary    {$$=$1;}
;


ComplexPrimary
: '(' Expression ')'    {$$="("+$2+")";}
| ComplexPrimaryNoParenthesis{$$=$1;}
;


ComplexPrimaryNoParenthesis
: INTEGER   {Integer tmp = new Integer($1);$$=tmp.toString();}
| STRING    {$$=$1;}
| TRUE      {$$="true";}
| FALSE     {$$="false";}
| ArrayAccess   {$$=$1;}
| FieldAccess   {$$=$1;}
;

FieldAccess
: ComplexPrimary '.' ID {$$=$1+'.'+$3;}
;

ArrayAccess
: QualifiedName '[' Expression ']'  {$$=$1+'['+$3+']';}
| ComplexPrimary '[' Expression ']' {$$=$1+'['+$3+']';}
;



UnaryExpression
: LogicalUnaryExpression    {$$=$1;}
;

LogicalUnaryExpression
: PrimaryExpression {$$=$1;}
| LogicalUnaryOperator UnaryExpression  {$$=$1+$2;}
;

LogicalUnaryOperator
: '~'   {$$="~";}
| '!'   {$$="!";}
;


MultiplicativeExpression
: UnaryExpression {$$=$1;}
| MultiplicativeExpression '*' UnaryExpression  {$$=$1+"*"+$3;}
| MultiplicativeExpression '/' UnaryExpression  {$$=$1+"/"+$3;}
| MultiplicativeExpression '%' UnaryExpression  {$$=$1+"%"+$3;}
;


AdditiveExpression
: MultiplicativeExpression {$$=$1;}
| AdditiveExpression '+' MultiplicativeExpression  {$$=$1+"+"+$3;}
| AdditiveExpression '-' MultiplicativeExpression  {$$=$1+"-"+$3;}
;


RelationalExpression
: AdditiveExpression    {$$=$1;}
| RelationalExpression '<' AdditiveExpression {$$=$1+"<"+$3;}
| RelationalExpression '>' AdditiveExpression {$$=$1+">"+$3;}
| RelationalExpression OP_LE AdditiveExpression {$$=$1+"<="+$3;}
| RelationalExpression OP_GE AdditiveExpression {$$=$1+">="+$3;}
;


EqualityExpression
: RelationalExpression  {$$=$1;}
| EqualityExpression OP_EQ RelationalExpression  {$$=$1+"=="+$3;}
| EqualityExpression OP_NE RelationalExpression  {$$=$1+"!="+$3;}
;

ConditionalAndExpression
: EqualityExpression    {$$=$1;}
| ConditionalAndExpression OP_LAND EqualityExpression  {$$=$1+"&&"+$3;}
;


ConditionalOrExpression
:ConditionalAndExpression  {$$=$1;}
| ConditionalOrExpression OP_LOR ConditionalAndExpression {$$=$1+"||"+$3;}
;

Expression
:ConditionalOrExpression {$$=$1;}
|UnaryExpression '=' AssignmentExpression {$$= $1+"="+$3;}
|TFExpression   {$$=$1;}
;





%%

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
