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
	import compiler.helper.*;
%}
      
%token GAME_DF
%token CARD_DF
%token CHARACTER_DF

%token METHOD
%token INIT
%token ROUND_BEGIN
%token ROUND_END
%token TURN

%token PLAYER
%token CARD
%token SKILL

%token RETURN

%token <sval> STATLIST

%token IF
%token ELSE
%token WHILE
%token TRUE
%token FALSE
%token FOREACH
%token IN
%token OP_EQ
%token OP_LE
%token OP_GE
%token OP_NE
%token OP_LOR
%token OP_LAND

%token DECLR_INT
%token DECLR_STR
%token DECLR_BOOL
%token VOID

%token <ival> INTEGER
%token <sval> STRING
%token <sval> ID
%left '-' '+'

%type <obj> value
%type <obj> json_item
%type <obj> json_content
%type <obj> json
%type <obj> config
%type <obj> config_list_content
%type <obj> config_list
%type <sval> block
%type <sval> statement_list
%type <sval> statement
%type <obj> type
%type <obj> parameter_config
%type <obj> parameter_config_list
%type <obj> built_in_function_signature
%type <obj> function_signature
%type <obj> function_definition
%type <obj> procedures_list
%type <obj> skill
%type <obj> skill_list
%type <obj> skill_list_content
%type <obj> primitive_type

%type <sval> selection_statement
%type <sval> foreach_statement
%type <sval> while_loop_statement
%type <sval> field_declaration
%type <sval> return_statement

%type <obj> Expression
%type <obj> UnaryExpression
%type <obj> ConditionalOrExpression
%type <obj> ConditionalAndExpression
%type <obj> RelationalExpression
%type <obj> MultiplicativeExpression
%type <obj> AdditiveExpression
%type <obj> UnaryExpression
%type <obj> LogicalUnaryExpression
%type <obj> PrimaryExpression
%type <sval> LogicalUnaryOperator
%type <sval> RelationalBinaryOperator
%type <obj> QuarlifiedName
%type <obj> ComplexPrimaryNoParenthesis
%type <obj> MethodCall
%type <obj> ArgumentList

%left '*' '/'
%right '^'         /* exponentiation        */
      
%%

source_code: { SymbolTable.initSymbolTable();} game_config cards_definition characters_definition procedures_list
	{
		System.out.println("source_code finished");
		ArrayList<FunctionObj> procedures = (ArrayList<FunctionObj>)$5;
		for(FunctionObj func : procedures){
			JsonItem ji = new JsonItem(JsonItemType.Function);
			ji.func = func;
			Util.game.add(ji);
		}
		Util.genGame();
		Util.genMakefile();
	}
    ;
game_config: GAME_DF { SymbolTable.current = 1; } json
    {
		System.out.println("game_config finished");
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) $3;
        Util.game = json;
    }
    ;
cards_definition: CARD_DF { SymbolTable.current = 2; } config_list
    {
		System.out.println("cards_definition finished");
        ArrayList<Config> config_list = (ArrayList<Config>) $3;
        Util.genAllCards(config_list);
    }
    ;
characters_definition: CHARACTER_DF { SymbolTable.current = 3; } config_list
    {
		System.out.println("characters_definition finished");
        ArrayList<Config> config_list = (ArrayList<Config>) $3;
        Util.genAllCharacters(config_list);
        SymbolTable.current = 4;
    }
    ;
config_list: '[' config_list_content ']' { $$ = $2; }
    ;
config_list_content
: config
    {
        ArrayList<Config> config_list = new ArrayList<Config>();
        config_list.add((Config) $1 );

        if( SymbolTable.current == 2 && SymbolTable.firstCard )
            SymbolTable.firstCard = false;
        if( SymbolTable.current == 3 && SymbolTable.firstCharacter )
            SymbolTable.firstCharacter = false;

        $$ = config_list;
    }
| config_list_content config
    {
        ArrayList<Config> config_list = (ArrayList<Config>) $1;
        config_list.add((Config) $2 );
        $$ = config_list;
    }
    ;
config: ID 
    {
        if( (SymbolTable.current == 2 && SymbolTable.putNewCardName($1)) || (SymbolTable.current == 3 && SymbolTable.putNewCharacterName($1)) ){
            SymbolTable.current_all_IDs = new HashSet<String>();
            SymbolTable.currentCardCharacterBlock = new ScopeBlock();
            if(SymbolTable.current == 3)
                SymbolTable.current_skill_IDs = new HashSet<String>();
        }
        else{
            yyerror("Attempted to make " + $1 + "card/character name failed.");
        }
    } 
json
    {
        Config c = new Config();
        c.id = $1;
        c.json = (ArrayList<JsonItem>)$3;
        $$ = c;
    }
    ;
json: '{' json_content '}' { $$ = $2; }
    ;
json_content
: json_item 
    {
        ArrayList<JsonItem> json = new ArrayList<JsonItem>();
        json.add((JsonItem) $1 );
        $$ = json;
    }
| json_content json_item
    {
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) $1;
        json.add((JsonItem) $2 );
        $$ = json;
    }
    ;
json_item
: ID ':' value ';'
    {
        JsonItem ji = null;
        AttributeObj v = (AttributeObj)$3;
        v.id = $1;
        if( (SymbolTable.current == 2 && SymbolTable.putInCard($1, true, v)) || (SymbolTable.current == 3 && SymbolTable.putInCharacter($1, true, v)) 
            || ((SymbolTable.current == 1 || SymbolTable.current == 4) && SymbolTable.putInGame($1, true, v)) ){

            ji = new JsonItem(JsonItemType.Attribute);
            ji.attr = v;
            $$ = ji;

        }
        else{
            yyerror($1 + " can not be put in current scope.");
        }
        $$ = ji;
    }
| function_definition
	{
		FunctionObj f = (FunctionObj)$1;
		JsonItem ji = new JsonItem(JsonItemType.Function);
		ji.func = f;
		$$ = ji;
	}
| skill_list
	{
		ArrayList<Skill> skills = (ArrayList<Skill>)$1;
		JsonItem ji = new JsonItem(JsonItemType.SkillList);
		ji.skills = skills;
		$$ = ji;
	}
    ;
skill_list
: SKILL '[' skill_list_content ']' { $$ = $3; }
| SKILL '[' ']' { $$ = new ArrayList<Skill>(); }
	;
skill_list_content
: skill_list_content skill
	{
		ArrayList<Skill> skills = (ArrayList<Skill>)$1;
		Skill skill = (Skill)$2;
		skills.add(skill);
		$$ = skills;
	}
| skill
	{
		ArrayList<Skill> skills = new ArrayList<Skill>();
		Skill skill = (Skill)$1;
        skills.add(skill);
        $$ = skills;
	}
	;
skill: ID
    {
        if(SymbolTable.putInSkill($1)){
            SymbolTable.newLocalBlock();
            AttributeObj dealer = new AttributeObj("dealer", Type.PLAYER);
            SymbolTable.putInLocal("dealer", dealer);
            FunctionObj skill = new FunctionObj();
            skill.return_type = Type.BOOLEAN;
            SymbolTable.curFunction = skill;
        }
        else{
            yyerror($1 + " can not be used as a skill name anymore.");
        }
    }
block
	{
		Skill skill = new Skill();
		skill.id = $1;
		skill.body = $3;
        SymbolTable.curFunction = null;
		$$ = skill;
	} 
	;
function_definition
: function_signature
    {
        FunctionObj f = (FunctionObj) $1;
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
block
	{
		FunctionObj f = (FunctionObj) $1;
		f.body = $3;
        SymbolTable.curFunction = null;
		$$ = f;
	}
	;
function_signature
: built_in_function_signature { $$ = $1; }
| type ID '(' parameter_config_list ')'
	{
		FunctionObj f = new FunctionObj();
		f.id = $2;
		f.return_type = (Type)$1;
		f.parameters = (ArrayList<AttributeObj>)$4;
		$$ = f;
	}
| type ID '(' ')'
	{
		FunctionObj f = new FunctionObj();
		f.id = $2;
		f.return_type = (Type)$1;
		f.parameters = new ArrayList<AttributeObj>();
		$$ = f;
	}
	;
built_in_function_signature
: INIT
	{
        if(SymbolTable.current != 4){
            yyerror("init can only be defined after characters");
        }
		FunctionObj f = new FunctionObj();
		f.id = "init";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		$$ = f;
	}
| METHOD
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
		$$ = f;
	}
| ROUND_BEGIN 
	{
        if(SymbolTable.current != 4){
            yyerror("round_begin can only be defined after characters");
        }
		FunctionObj f = new FunctionObj();
		f.id = "round_begin";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		$$ = f;
	}
| ROUND_END 
	{
        if(SymbolTable.current != 4){
            yyerror("round_end can only be defined after characters");
        }
		FunctionObj f = new FunctionObj();
		f.id = "round_end";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		$$ = f;
	}
| TURN 
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
		$$ = f;
	}
	; 
parameter_config_list
: parameter_config 
	{
        ArrayList<AttributeObj> parameters = new ArrayList<AttributeObj>();
        parameters.add((AttributeObj) $1 );
        $$ = parameters;
	}
| parameter_config_list ',' parameter_config
	{
        ArrayList<AttributeObj> parameters= (ArrayList<AttributeObj>) $1;
        parameters.add((AttributeObj) $3 );
        $$ = parameters;
	}
	;
parameter_config: type ID
	{
		AttributeObj a = new AttributeObj($2, (Type)$1);
		$$ = a;
	}
	;
procedures_list
: function_definition 
	{
		FunctionObj f = (FunctionObj)$1;
		ArrayList<FunctionObj> funcs = new ArrayList<FunctionObj>();
		funcs.add(f);
		$$ = funcs;
	}
| procedures_list function_definition
	{
		ArrayList<FunctionObj> funcs = (ArrayList<FunctionObj>)$1;
		FunctionObj f = (FunctionObj)$2;
		funcs.add(f);
        $$ = funcs;
	}
	;
/* ----------------------------------------------------------- */
block
: '{' statement_list '}' { SymbolTable.popLocalBlock(); $$ = $2; }
| '{' '}' { SymbolTable.popLocalBlock(); $$ = "";}
	;
statement_list
: statement_list statement { $$=$1 + "\n" + $2;}
| statement { $$=$1;}
	;
statement
: selection_statement { $$=$1;}
| field_declaration { $$=$1;}
| while_loop_statement { $$=$1;}
| Expression ';'
    {
        Expression exp = (Expression)$1;
        $$=exp.code+";";
    }
| QuarlifiedName '=' Expression ';'
    {
        QuarlifiedName qn = (QuarlifiedName)$1;
        Expression exp = (Expression)$3;
        if(qn.sr.isAttribute && qn.sr.attr.type.equals(exp.return_type)){
            $$ = qn.code + " = " + exp.code +";";
        }
        else{
           yyerror(qn.code + " can not be assigned with ( " + exp.code + " )"); 
        }
    }
| return_statement { $$=$1;}
/*
| foreach_statement { $$=$1;}
*/
	;
return_statement
: RETURN Expression ';'
    {
        Expression exp = (Expression)$2;
        if(SymbolTable.curFunction.return_type.equals(exp.return_type)){
            $$ = "return " + exp.code + ";";
        }
        else{
            yyerror("Invalid return statement in " + SymbolTable.curFunction.id);
        }
    }
| RETURN ';' 
    {
        if(SymbolTable.curFunction.return_type.equals(Type.VOID)){
            $$ = "return;";
        }
        else{
            yyerror("Invalid return statement in " + SymbolTable.curFunction.id);
        }
    }
    ;
selection_statement
: IF '(' Expression ')' {SymbolTable.newLocalBlock();} block
	{
		Expression condition = (Expression)$3;
		if(condition.return_type != Type.BOOLEAN){
			yyerror(condition.code + " does not return boolean!");
		}
		$$="if("+condition.code+"){\n"+$6+"\n}\n";
	}
| IF '(' Expression ')' {SymbolTable.newLocalBlock();} block ELSE {SymbolTable.newLocalBlock();} block
	{
		Expression condition = (Expression)$3;
        if(condition.return_type != Type.BOOLEAN){
			yyerror(condition.code + " does not return boolean!");
        }
		$$="if("+condition.code+"){\n"+$6+"\n}\nelse {\n"+$8+"\n}\n";
	}
	;
field_declaration
: type ID ';'
	{
		Type type = (Type)$1;
        AttributeObj attr = new AttributeObj($2, type);
        System.out.println("test1");
        if(!SymbolTable.putInLocal($2, attr)){
            yyerror("Can not declare " + $2 + " at this block." );
        }
        System.out.println("test2");
		$$=type.toString()+" "+$2+";";
	}
| type ID '=' Expression ';' 
	{
		Type type = (Type)$1;
		Expression exp = (Expression)$4;
        if( !exp.return_type.equals(type) ){
			yyerror($2 + " can not be assigned with (" + exp.code  + ")" );
        }
        AttributeObj attr = new AttributeObj($2, type);
        if(!SymbolTable.putInLocal($2, attr)){
            yyerror("Can not declare " + $2 + "at this block." );
        }
		$$=type.toString()+" "+$2+" = "+exp.code +";";
	}
	;
while_loop_statement: WHILE '(' Expression ')' {SymbolTable.newLocalBlock();} block
	{
		Expression condition = (Expression)$3;
        if(condition.return_type != Type.BOOLEAN){
            yyerror(condition.code + " does not return boolean!");
        }
		$$="while("+condition.code+"){\n"+$5+"\n}\n";
	}
	;
/*
foreach_statement: FOREACH '(' type ID IN ID ')' block
	{
		$$ = "foreach("+$3+$4+" in "+$6+")"+$8;
	}
	;
*/
MethodCall
: QuarlifiedName '(' ArgumentList ')'
	{
        QuarlifiedName qn = (QuarlifiedName)$1;
        if(qn.sr.isAttribute){
            yyerror(qn.code + " is not function.");
        }
        FunctionObj func = qn.sr.func;
        String method_call = qn.code+"(";
        ArrayList<Expression> args = (ArrayList<Expression>)$3;
        if(args.size() != func.parameters.size()){
            yyerror("List of parameters does not match the definiton of "+ $1);
        }
        for( int i =0;i<args.size();i++ ){
            Expression exp = args.get(i);
            AttributeObj para = func.parameters.get(i);
            if(exp.return_type != para.type){
                yyerror("List of parameters does not match the definiton of "+ $1);
            }
            if(i!=0)
                method_call = method_call + ", ";
            method_call = method_call + exp.code;
        }
        Expression func_call = new Expression();
        func_call.code = method_call + ")";
        func_call.return_type = func.return_type;
        $$ = func_call;
	}
| QuarlifiedName '(' ')'   
    {
        QuarlifiedName qn = (QuarlifiedName)$1;
        if(qn.sr.isAttribute){
            yyerror(qn.code + " is not function.");
        }
        FunctionObj func = qn.sr.func;
        String method_call = $1+"(";
        if(0 != func.parameters.size()){
            yyerror("List of parameters does not match the definiton of "+ $1);
        }
        Expression func_call = new Expression();
        func_call.code = method_call + ")";
        func_call.return_type = func.return_type;
        $$ = func_call;
    }
	;
ArgumentList
: Expression     
	{
        Expression exp = (Expression)$1;
        ArrayList<Expression> args = new ArrayList<Expression>();
        args.add(exp);
		$$ = args;
	}
| ArgumentList ',' Expression
    {
        ArrayList<Expression> args = (ArrayList<Expression>)$1;
        Expression exp = (Expression)$3;
        args.add(exp);
        $$ = args;
    }
	;
QuarlifiedName
: QuarlifiedName '.' ID
    {
        QuarlifiedName qn = (QuarlifiedName)$1;
        if(qn.sr.isAttribute){
            SymbolRecord sr = Type.dotOP(qn.sr.attr.type ,$3);
            if( sr == null ){
                yyerror($3 + " is not a attribute/function of type "+qn.sr.attr.type.toString());
            }
            if( qn.sr.attr.type.equals(Type.PLAYER) && sr.scope == SymbolTable.baseCharacterBlock ){
                qn.code = qn.code + ".character";
            }
            qn.code = qn.code + "." + $3;
            qn.sr = sr; 
        }
        else{
            yyerror("function must have parameters.");
        }
        $$=qn;
    }
| ID
    {
        QuarlifiedName qn = new QuarlifiedName();
        SymbolRecord sr = SymbolTable.accessID($1);
        if( sr == null ){
            yyerror($1 + " is unknown");
        }
        else{
            qn.code = $1;
            if(sr.scope == SymbolTable.gameBlock )
                qn.code = "Game." + $1;
            qn.sr = sr; 
        }
        $$=qn;
    }
	;
PrimaryExpression
: '(' Expression ')'
    {
        Expression exp = (Expression)$2;
        exp.code = "( " + exp.code + " )";
        $$=exp;
    }
| ComplexPrimaryNoParenthesis{$$=$1;}
	;
ComplexPrimaryNoParenthesis
: value
    {
        AttributeObj v = (AttributeObj)$1;
        Expression exp = new Expression();
        exp.code = v.value;
        exp.return_type = v.type;
        $$ = exp;
    }
| QuarlifiedName
    {
        QuarlifiedName qn = (QuarlifiedName)$1;
        if(!qn.sr.isAttribute){
            yyerror("Quarlified name " + qn.code + " reduced to ComplexPrimaryNoParenthesis error: quarlifiedname is function");
        }
        Expression exp = new Expression();
        exp.code = qn.code;
        exp.return_type = qn.sr.attr.type;
        $$ = exp;
    }
| MethodCall
    {
        $$ = $1;
    }
	;
UnaryExpression
: LogicalUnaryExpression    {$$=$1;}
	;
LogicalUnaryExpression
: PrimaryExpression {$$=$1;}
| LogicalUnaryOperator UnaryExpression
    {
        Expression exp = (Expression)$2;
        if(exp.return_type != Type.BOOLEAN){
            yyerror(exp.code + " does not return boolean!");
        }
        exp.code = $1+exp.code;
        $$ = exp;
    }
	;
MultiplicativeExpression
: UnaryExpression {$$=$1;}
| MultiplicativeExpression '*' UnaryExpression
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with *, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " * " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        $$=exp;
    }
| MultiplicativeExpression '/' UnaryExpression
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with /, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " / " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        $$=exp;
    }
| MultiplicativeExpression '%' UnaryExpression 
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with %, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " % " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        $$=exp;
    }
	;
AdditiveExpression
: MultiplicativeExpression {$$=$1;}
| AdditiveExpression '+' MultiplicativeExpression
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
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
        $$=exp;
    }
| AdditiveExpression '-' MultiplicativeExpression
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with -, because at least one of them is not integer");
        }
        String exp_code = exp1.code + " - " + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.INTEGER;
        exp.code = exp_code;
        $$=exp;
    }
	;
RelationalExpression
: AdditiveExpression    {$$=$1;}
| AdditiveExpression RelationalBinaryOperator AdditiveExpression
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
        if(!(exp1.return_type == Type.INTEGER && exp2.return_type == Type.INTEGER)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with " + $2 + ", because at least one of them is not integer");
        }
        String exp_code = exp1.code + $2 + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.BOOLEAN;
        exp.code = exp_code;
        $$=exp;
    }
	;
ConditionalAndExpression
: RelationalExpression    {$$=$1;}
| ConditionalAndExpression OP_LAND RelationalExpression
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
        if(!(exp1.return_type == Type.BOOLEAN && exp2.return_type == Type.BOOLEAN)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with &&, because at least one of them is not boolean");
        }
        String exp_code = exp1.code + "&&" + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.BOOLEAN;
        exp.code = exp_code;
        $$=exp;
    }
	;
ConditionalOrExpression
: ConditionalAndExpression  {$$=$1;}
| ConditionalOrExpression OP_LOR ConditionalAndExpression
    {
        Expression exp1 = (Expression)$1;
        Expression exp2 = (Expression)$3;
        if(!(exp1.return_type == Type.BOOLEAN && exp2.return_type == Type.BOOLEAN)){
            yyerror("( " + exp1.code + " ) and ( " + exp2.code + " ) can not be connected with ||, because at least one of them is not boolean");
        }
        String exp_code = exp1.code + "||" + exp2.code;
        Expression exp = new Expression();
        exp.return_type = Type.BOOLEAN;
        exp.code = exp_code;
        $$=exp;
    }
	;
Expression: ConditionalOrExpression {$$=$1;}
	;
type
: PLAYER { $$ = Type.PLAYER; }
| CARD { $$ = Type.CARD; }
| VOID { $$ = Type.VOID; }
| primitive_type { $$ = $1; }
	;
primitive_type
: DECLR_INT { $$ = Type.INTEGER; }
| DECLR_STR { $$ = Type.STRING; }
| DECLR_BOOL { $$ = Type.BOOLEAN; }
	;
value
: INTEGER { $$=new AttributeObj(Type.INTEGER, Integer.toString($1)); }
| STRING { $$=new AttributeObj(Type.STRING, $1); }
| TRUE { $$=new AttributeObj(Type.BOOLEAN, "true"); }
| FALSE { $$=new AttributeObj(Type.BOOLEAN, "false"); }
    ;
LogicalUnaryOperator
: '~'   {$$="~";}
| '!'   {$$="!";}
	;
RelationalBinaryOperator
: '<' {$$="<";}
| '>' {$$=">";}
| OP_EQ {$$="==";}
| OP_LE {$$="<=";}
| OP_GE {$$=">=";}
| OP_NE {$$="!=";}
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
