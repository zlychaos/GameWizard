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
%token IS

%token DECLR_INT
%token DECLR_STR
%token DECLR_BOOL

%token VOID


%token <ival> INTEGER
%token <sval> STRING
%token <sval> ID
%token CARD
%token FOREACH
%token ROUNDSUMMARY
%token IN


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
%type <sval> AUGED_STATEMENT_LIST
%type <sval> ForeachStatement
%type <sval> MethodCall
%type <sval> MethodAccess
%type <sval> ArgumentList

%left '-' '+'

%left '*' '/'
%right '^'         /* exponentiation        */
      
%%
input: {/*the global block*/SymbolTable.pushNewBlock(); SymbolTable.addKeywordsAndBuildIn();} game_df card_df character_df {curScope=null;} init_block round_block dying_block
	{
		String methods = $6+$7+$8;
		Util.writeGameJava($2,methods);
		
	}
     ;
game_df : GAME_DF '{' game_df_content '}'  {$$=$3; System.out.println("game_df");}
	;
game_df_content : GAME_NM ':' STRING ';'
                  PLAYER_C ':' INTEGER ';'
                  MAX_ROUND ':' INTEGER ';'  
	{String s= "public static String game_name = "+$3
		+";\npublic static int num_of_players = "+$7
		+";\npublic static int maximum_round = "+$11+";\n"; 
		$$=s; System.out.println(s);
		SymbolTable.addRecordToCurrentBlock("game_name", SymbolType.GAME_DEFINITION);
		SymbolTable.addRecordToCurrentBlock("num_of_players", SymbolType.GAME_DEFINITION);
		SymbolTable.addRecordToCurrentBlock("maximum_round", SymbolType.GAME_DEFINITION);
	}
		;
card_df : CARD_DF '[' cards_df_content ']' {System.out.println("2");}
	;
cards_df_content : card_df_content cards_df_content  {System.out.println("1");}
               | card_df_content {System.out.println("3");}
		;
card_df_content: CARD ID '{' {curScope=$2; checkDulDeclare($2); SymbolTable.addRecordToCurrentBlock($2, SymbolType.CARD);} variable_list METHOD '(' PLAYER DEALER ')' '{' { SymbolTable.pushNewBlock();} AUGED_STATEMENT_LIST  '}' '}'
			
	{
	if(!Util.checkVarListSame(pre_list, $5)){
		yyerror("Variables in all the cards must be the same!");	
	}
	pre_list = $5;
	System.out.println("======================================================finished card_df_content"); 
	SymbolTable.popBlock(); 
	Util.writeCardsJava($2.toString(),$5,$13); }
	;
character_df :  CHARACTER_DF {pre_list=null;} '[' characters_df_content ']' {System.out.println("character_df");}
             ;
characters_df_content : characters_df_content character_df_content {System.out.println("characters_df_content");}
			| character_df_content {System.out.println("characters_df_content");}
			;
character_df_content : ID '{' {curScope=$1; checkDulDeclare($1); SymbolTable.addRecordToCurrentBlock($1, SymbolType.CHARACTER);} 
			variable_list
			skill_df
			'}'            
	{
	
	if(!Util.checkVarListSame(pre_list, $4)){
		yyerror("Variables in all the characters must be the same!");	
	}
	pre_list = $4;
	Util.writeCharacterJava($1,$4,$5); 
	System.out.println("character_df_content");}
		; 
variable_list : ID ':' INTEGER ';' variable_list	
        {
	    checkDulDeclare($1);
	    SymbolTable.addRecordToCardCharacterBlock(curScope, $1, SymbolType.CARD_VARIABLE);	    
	    ArrayList<String> result= new ArrayList<String>();
            result.add("Integer"); result.add($1);result.add(String.valueOf($3));
            ArrayList<String> x1 = (ArrayList<String>)($5);
            
            result.addAll(x1); $$=result;  System.out.println("variable_list");
                }
		| ID ':' STRING	';' variable_list
        {
   	    checkDulDeclare($1);
	    SymbolTable.addRecordToCardCharacterBlock(curScope, $1, SymbolType.CARD_VARIABLE);	    
	    ArrayList<String> result= new ArrayList<String>();
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
        ID '{' METHOD '(' PLAYER DEALER ')' '{' {checkDulDeclare($1);SymbolTable
.addRecordToCardCharacterBlock(curScope, $1, SymbolType.CHARACTER_SKILL);SymbolTable.pushNewBlock();} AUGED_STATEMENT_LIST '}' '}'
        {
	    SymbolTable.popBlock();
	    ArrayList<String> result= new ArrayList<String>();
            result.add($1);
            result.add($10);
            $$=result;
        }
	;

init_block:
	INIT '{' {SymbolTable.pushNewBlock();} AUGED_STATEMENT_LIST '}' 
	{
		SymbolTable.popBlock();
		String ret = "public static void init(){\n"+$4+"\n}\n";
		$$=ret;
		System.out.println("init_block statement");
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
		System.out.println("round block");
	}
	;

round_begin_block:
	ROUND_BEGIN '{' {SymbolTable.pushNewBlock(); System.out.println("before round begin");} AUGED_STATEMENT_LIST '}' { SymbolTable.popBlock();$$=$4; System.out.println("round_begin");}
	;

turn_block:
	TURN '{' {SymbolTable.pushNewBlock();} AUGED_STATEMENT_LIST '}' {SymbolTable.popBlock();$$=$4;}
	;

round_end_block:
	ROUND_END '{' {SymbolTable.pushNewBlock();} AUGED_STATEMENT_LIST '}' {SymbolTable.popBlock(); $$=$4;}
	;

dying_block:
	DYING '{' {SymbolTable.pushNewBlock();} AUGED_STATEMENT_LIST '}' 
	{
		SymbolTable.popBlock();
		String ret = "public static void dying(){\n"+$4+"\n}\n";
		$$=ret;
	}
	;

AUGED_STATEMENT_LIST:
STATEMENT_LIST {$$=$1;}
| VOID {$$="";}
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
|   ForeachStatement {$$=$1;}
|   STATEMENT_LIST ForeachStatement {$$=$1+$2;}
;

ForeachStatement:
	FOREACH '(' TypeSpecifier ID IN ROUNDSUMMARY ')'  Block 
        {
		String s = "for("+$3+" "+$4+":roundSummary.keySet())\n"+$8;
		$$=s;
	}
|
	FOREACH '(' TypeSpecifier ID IN ID ')' Block 
        {
		String s = "for("+$3+" "+$4+":"+$6+")\n"+$8;
		$$=s;
	}	
;

EmptyStatement
:   ';' {$$=";\n";}
;

IterationStatement
:   WHILE '(' Expression ')' Block
    {System.out.println("8");String s = "while("+$3+")\n"+$5; $$=s;}
;


SelectionStatement
:   IF '(' Expression ')' Block
    {System.out.println("6");String s = "if("+$3+")\n"+$5; $$=s;}
    |IF '(' Expression ')' Block ELSE Block
    {System.out.println("7");String s = "if("+$3+")\n"+$5+"\nelse\n"+$7+""; $$=s;}
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
: TypeSpecifier VariableDeclarators 
  {
	System.out.println("FieldVariableDeclaration");
	String[] arr = $2.split("=");
	String var = arr[0];
	String val = arr[1];

	checkDulDeclare(var);
	if($1.equals("ICard ")){
		$$=$1+var+" = "+val;
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
			//if(type==SymbolType.LOCAL_CARD_DECLARE){
			//	$$=genCardDownCast($1,indicator,postfix)+$1+var+" = tmp";
			//}else
				$$=$1+$2;
		}
		else
			$$=$1+$2;
	}
  }
;

VariableDeclarators
: VariableDeclarator    {System.out.println("1");$$=$1;}
| VariableDeclarators ',' VariableDeclarator    {$$=$1+','+$3;}
;

VariableDeclarator
: DeclaratorName    {checkDulDeclare($1);System.out.println("1");$$=$1;}
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
: '{' STATEMENT_LIST '}' {$$="{\n"+$2+"}\n";}
| '{' '}'   {$$="{\n}\n";}
;










TypeSpecifier
: TypeName  {System.out.println("0");$$=$1;}
| TypeName '[' INTEGER ']'  {$$=$1+'['+$3+']';}
;


TypeName
: PrimitiveType {System.out.println("0");$$=$1;}
| CARD {$$="ICard ";}
| PLAYER {$$="Player ";}
;



PrimitiveType
: DECLR_BOOL    {$$="Boolean ";}
| DECLR_INT     {$$="Integer ";}
| DECLR_STR     {$$="String ";}
;


MethodCall
	: MethodAccess '(' ArgumentList ')' {$$=$1+"("+$3+")";}
	| MethodAccess '(' ')'   {$$=$1+"()";}
	;

MethodAccess:
	 QualifiedName		{$$=$1;}
	;

ArgumentList
	: Expression     
	{
		SymbolType type = SymbolTable.lookUpSymbolType($1);
		if(type==SymbolType.CARD || type==SymbolType.CHARACTER){
			$$="new "+$1+"()";	
		}else{
			$$=$1;
		}
	}
	| ArgumentList ',' Expression {$$=$1+","+$3;}
	;


QualifiedName
: QualifiedName '.' ID  {$$=$1+"."+$3;}
| ID    
	{
		SymbolType type = SymbolTable.lookUpSymbolType($1);
                if(type==SymbolType.GAME_JAVA){
                        $$="Game."+$1;
                }else{
			$$=$1;
		}
	}
| ROUNDSUMMARY {$$="Game.roundSummary";}
| GAME_NM {$$="Game.game_name";}
| PLAYER_C {$$="Game.num_of_players";}
| MAX_ROUND {$$="Game.maximum_round";}
| DEALER {$$="dealer";}
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
| ArrayAccess  {$$=$1;}
| MethodCall   {$$=$1;}
;

 
FieldAccess :
	ComplexPrimary '.' ID {$$= $1+"."+$3;}
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
| EqualityExpression IS RelationalExpression    {$$=$1+" instanceof "+$3;}
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
