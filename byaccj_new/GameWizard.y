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

%token <sval> STATLIST

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

%left '*' '/'
%right '^'         /* exponentiation        */
      
%%

source_code: game_config cards_definition characters_definition procedures_list
	{
		ArrayList<FunctionObj> procedures = (ArrayList<FunctionObj>)$4;
		for(FunctionObj func : procedures){
			JsonItem ji = new JsonItem(JsonItemType.Function);
			ji.func = func;
			Util.game.add(ji);
		}
		Util.genGame();
		Util.genMakefile();
	}
    ;
game_config: GAME_DF json
    {
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) $2;
        Util.game = json;
    }
    ;
cards_definition: CARD_DF config_list
    {
        ArrayList<Config> config_list = (ArrayList<Config>) $2;
        Util.genAllCards(config_list);
    }
    ;
characters_definition: CHARACTER_DF config_list
    {
        ArrayList<Config> config_list = (ArrayList<Config>) $2;
        Util.genAllCharacters(config_list);
    }
    ;
config_list: '[' config_list_content ']' { $$ = $2; }
    ;
config_list_content
: config
    {
        ArrayList<Config> config_list = new ArrayList<Config>();
        config_list.add((Config) $1 );
        $$ = config_list;
    }
| config_list_content ';' config
    {
        ArrayList<Config> config_list = (ArrayList<Config>) $1;
        config_list.add((Config) $3 );
        $$ = config_list;
    }
    ;
config: ID json
    {
        Config c = new Config();
        c.id = $1;
        c.json = (ArrayList<JsonItem>)$2;
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
| json_content ';' json_item
    {
        ArrayList<JsonItem> json = (ArrayList<JsonItem>) $1;
        json.add((JsonItem) $3 );
        $$ = json;
    }
    ;
json_item
: ID ':' value
    {
        AttributeObj v = (AttributeObj)$3;
        v.id = $1;
        JsonItem ji = new JsonItem(JsonItemType.Attribute);
        ji.attr = v;
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
skill: ID block
	{
		Skill skill = new Skill();
		skill.id = $1;
		skill.body = $2;
		$$ = skill;
	} 
	;
value
: INTEGER { $$=new AttributeObj(Type.INTEGER, Integer.toString($1)); }
| STRING { $$=new AttributeObj(Type.STRING, $1); }
| TRUE { $$=new AttributeObj(Type.BOOLEAN, "true"); }
| FALSE { $$=new AttributeObj(Type.BOOLEAN, "false"); }
    ;
function_definition
: function_signature block
	{
		FunctionObj f = (FunctionObj) $1;
		f.body = $2;
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
		FunctionObj f = new FunctionObj();
		f.id = "init";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		$$ = f;
	}
| METHOD
	{
		FunctionObj f = new FunctionObj();
		f.id = "method";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		AttributeObj para = new AttributeObj("dealer", Type.PLAYER);
		f.parameters.add(para);
		$$ = f;
	}
| ROUND_BEGIN 
	{
		FunctionObj f = new FunctionObj();
		f.id = "round_begin";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		$$ = f;
	}
| ROUND_END 
	{
		FunctionObj f = new FunctionObj();
		f.id = "round_end";
		f.return_type = Type.VOID;
		f.parameters = new ArrayList<AttributeObj>();
		$$ = f;
	}
| TURN 
	{
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
type
: PLAYER { $$ = Type.PLAYER; }
| CARD { $$ = Type.CARD; }
| VOID { $$ = Type.VOID; }
| DECLR_INT { $$ = Type.INTEGER; }
| DECLR_STR { $$ = Type.STRING; }
| DECLR_BOOL { $$ = Type.BOOLEAN; }
	;
block: '{' statement_list '}' {  $$ = $2; }
	;
statement_list: STATLIST { $$ = $1;}
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
