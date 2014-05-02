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
  import java.util.*;
  import java.util.ArrayList;
%}
      
%token GAME_DF
%token CARD_DF
%token CHARACTER_DF
%token GAME_NM
%token PLAYER_C
%token PLAYER
%token SKILL
%token DEALER
%token METHOD
%token MAX_ROUND
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
%token INIT
%token ROUND_END
%token ROUND_BEGIN
%token ROUND
%token TURN
%token DYING

%token DECLR_INT
%token DECLR_STR
%token DECLR_BOOL

%token VOID


%token <ival> INTEGER
%token <sval> STRING
%token <sval> ID

%type <sval> game_df
%type <sval> game_df_content
%type <obj> variable_list
%type <sval> STATEMENT_LIST
%type <sval> Expression
%type <sval> SelectionStatement
%type <sval> IterationStatement
%type <sval> UnaryExpression
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
%type <sval> PrimitiveType
%type <sval> FieldDeclarations
%type <sval> FieldDeclaration
%type <sval> FieldDeclarationOptSemi
%type <sval> FieldVariableDeclaration
%type <sval> NonStaticInitializer
%type <sval> TypeSpecifier
%type <sval> TypeName
%type <sval> Block
%type <sval> NonStaticInitializer
%type <sval> ArrayInitializers
%type <sval> VariableInitializer
%type <sval> DeclaratorName
%type <sval> VariableDeclarator
%type <sval> VariableDeclarators
%type <sval> EmptyStatement
%type <obj> skill_df
%type <obj> skill_lists
%type <obj> skill_list
%type <sval> init_block
%type <sval> round_block
%type <sval> round_begin_block
%type <sval> round_end_block
%type <sval> turn_block
%type <sval> dying_block


%left '-' '+'

%left '*' '/'
%right '^'         /* exponentiation        */
      
%%
input: game_df card_df character_df init_block round_block dying_block
	{
		String methods = $4+$5+$6;
		Util.writeGameJava($1,methods);
	}
     ;
game_df : GAME_DF '{' game_df_content '}'  {$$=$3; System.out.println("game_df");}
	;
game_df_content : GAME_NM ':' STRING ';'
                  PLAYER_C ':' INTEGER ';'
                  MAX_ROUND ':' INTEGER ';'  {String s= "public static String name = "+$3+";\npublic static int num_of_players = "+$7+";\npublic static int maximum_round = "+$11+";\n"; $$=s; System.out.println(s);}
		;
card_df : CARD_DF '[' cards_df_content ']' {System.out.println("2");}
	;
cards_df_content : card_df_content cards_df_content  {System.out.println("1");}
               | card_df_content {System.out.println("3");}
		;
card_df_content: ID '{' variable_list METHOD '(' PLAYER DEALER ')' '{' STATEMENT_LIST  '}' '}'
			{System.out.println("5");Util.writeCardsJava($1.toString(),$3,$10); }
	| ID '{' variable_list METHOD '(' PLAYER DEALER ')' '{' VOID  '}' '}'
	         {System.out.println("5");Util.writeCardsJava($1.toString(),$3,""); }       ;

character_df :  CHARACTER_DF '[' characters_df_content ']' {System.out.println("character_df");}
             ;
characters_df_content : characters_df_content character_df_content {System.out.println("characters_df_content");}
			| character_df_content {System.out.println("characters_df_content");}
			;
character_df_content : ID '{'
			variable_list
			skill_df
			'}'             {Util.writeCharacterJava($1,$3,$4); System.out.println("character_df_content");}
		; 
variable_list : ID ':' INTEGER ';' variable_list	
        {ArrayList<String> result= new ArrayList<String>();
            result.add("Integer"); result.add($1);result.add(String.valueOf($3));
            ArrayList<String> x1 = (ArrayList<String>)($5);
            
            result.addAll(x1); $$=result;  System.out.println("variable_list");
                }
		| ID ':' STRING	';' variable_list
        {ArrayList<String> result= new ArrayList<String>();
            result.add("String"); result.add($1);result.add($3);
            ArrayList<String> x1 = (ArrayList<String>)($5);
            result.addAll(x1); $$=result;   System.out.println("variable_list");
                }
		|		    {ArrayList<String> result= new ArrayList<String>(); $$= result;   System.out.println("variable_list");}
		;

skill_df : SKILL ':' '['
        skill_lists
	   ']'                 {$$ = $4;}
	|
	  SKILL ':' '['
	  VOID
	   ']'		{ArrayList<String> ret = new ArrayList<String>(); $$=ret;}
	;

skill_lists: skill_list skill_lists {ArrayList<String> result= new ArrayList<String>();
                                    ArrayList<String> x1 = (ArrayList<String>)($1);
                                    ArrayList<String> x2 = (ArrayList<String>)($2);
                                    result.addAll(x1); result.addAll(x2); $$= result;}
        |skill_list {$$=$1;}
	;


skill_list:
        ID '{' METHOD '(' PLAYER DEALER ')' '{' STATEMENT_LIST '}' '}'
        {ArrayList<String> result= new ArrayList<String>();
            result.add($1);
            result.add($9);
            $$=result;
        }
	|
        ID '{' METHOD '(' PLAYER DEALER ')' '{' VOID '}' '}'
        {ArrayList<String> result= new ArrayList<String>();
            result.add($1);
            result.add("");
            $$=result;
        }
	;

init_block:
	INIT '{' STATEMENT_LIST '}' 
	{
		String ret = "public static void init(){\n"+$3+"\n}\n";
		$$=ret;
	}
	|
	INIT '{' VOID '}' 
	{
		String ret = "public static void init(){}\n";
	}
	;

round_block:
	ROUND '{' round_begin_block turn_block round_end_block '}' 
	{
		String ret = "public static void round_begin(){\n"+
			$3+"\n}\n"+
			"public static void turn(Player player) throws IOException{"+
			$4+"\n}\n"+
			"public static void round_end() throws Exception{"+
			$5+"\n}\n";
		$$ = ret;
	}
	;

round_begin_block:
	ROUND_BEGIN '{' STATEMENT_LIST '}' {$$=$3;}
	|
	ROUND_BEGIN '{' VOID '}' {$$="";}
	;

turn_block:
	TURN '{' STATEMENT_LIST '}' {$$=$3;}
	|
	TURN '{' VOID '}' {$$="";}
	;

round_end_block:
	ROUND_end '{' STATEMENT_LIST '}' {$$=$3;}
	|
	ROUND_end '{' VOID '}' {$$="";}
	;

dying_block:
	DYING '{' STATEMENT_LIST '}' 
	{
		String ret = "public static void dying(){\n"+$3+"\n}\n";
		$$=ret;
	}
	|
	DYING '{' VOID '}'
	{
		String ret = "public static void dying(){}\n";
		$$=ret;
	}
	;


STATEMENT_LIST
:   SelectionStatement  {System.out.println("selection");$$=$1;}
|   STATEMENT_LIST SelectionStatement  {System.out.println("selection");$$=$1+$2;}
|   FieldDeclarations   {System.out.println("declare");$$=$1;}
|   STATEMENT_LIST FieldDeclarations   {System.out.println("declare");$$=$1+$2;}
|   IterationStatement  {System.out.println("iteration");$$=$1;}
|   STATEMENT_LIST IterationStatement  {System.out.println("iteration");$$=$1+$2;}
|   EmptyStatement  {System.out.println("empty");$$=$1;}
|   STATEMENT_LIST EmptyStatement   {System.out.println("empty");$$=$1+$2;}
|   Expression  {System.out.println("expression");$$=$1;}
|   STATEMENT_LIST Expression  {System.out.println("expression");$$=$1+$2;}
;

EmptyStatement
:   ';' {$$=";";}
;

IterationStatement
:   WHILE '(' Expression ')' Block
    {System.out.println("8");String s = "while("+$3+")\n"+$5; $$=s;}
;


SelectionStatement
:   IF '(' Expression ')' Block
    {System.out.println("6");String s = "if("+$3+")\n"+$5; $$=s;}
    |IF '(' Expression ')' Block ELSE Block
    {System.out.println("7");String s = "if("+$3+")\n"+$5+";\nelse\n"+$7+";"; $$=s;}
;


FieldDeclarations
: FieldDeclarationOptSemi   {$$=$1;}
;

FieldDeclarationOptSemi
: FieldDeclaration ';'   {System.out.println("3");$$=$1+";\n";}
;


FieldDeclaration
: FieldVariableDeclaration  {System.out.println($1);$$=$1;}
| NonStaticInitializer  {$$=$1;}
;

FieldVariableDeclaration
: TypeSpecifier VariableDeclarators {$$=$1+$2;System.out.println($$);}
;

VariableDeclarators
: VariableDeclarator    {System.out.println("1");$$=$1;}
| VariableDeclarators ',' VariableDeclarator    {$$=$1+','+$3;}
;

VariableDeclarator
: DeclaratorName    {System.out.println("1");$$=$1;}
| DeclaratorName '=' VariableInitializer    {$$=$1+'='+$3;}
;

DeclaratorName
: ID    {System.out.println("1");$$=$1;}
| DeclaratorName '[' INTEGER ']'    {$$=$1+'['+$3+']';}
;

VariableInitializer
: Expression    {$$=$1;}
| '{' ArrayInitializers '}' {$$='{'+$2+'}';}
;

ArrayInitializers
: VariableInitializer   {$$=$1;}
| ArrayInitializers ',' VariableInitializer {$$=$1+','+$3;}
;

NonStaticInitializer
: Block {$$=$1;}
;

Block
: '{' STATEMENT_LIST '}' {$$="{"+$2+"}";}
| '{' '}'   {$$="{}";}
;










TypeSpecifier
: TypeName  {System.out.println("0");$$=$1;}
| TypeName '[' INTEGER ']'  {$$=$1+'['+$3+']';}
;


TypeName
: PrimitiveType {System.out.println("0");$$=$1;}
;



PrimitiveType
: DECLR_BOOL    {$$="boolean ";}
| DECLR_INT     {$$="int ";}
| DECLR_STR     {$$="String ";}
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
|UnaryExpression '=' Expression {$$= $1+"="+$3;}
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
