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
%token ROUNDBEGIN
%token ROUNDEND

%token PLAYER
%token SKILL

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

%left '*' '/'
%right '^'         /* exponentiation        */
      
%%

source_code: game_config cards_definition characters_definition {}
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
json_item: ID ':' value
    {
        AttributeObj v = (AttributeObj)$3;
        v.id = $1;
        JsonItem ji = new JsonItem(true);
        ji.attr = v;
        $$ = ji;
    }
    ;
value
: INTEGER { $$=new AttributeObj(Type.INTEGER, Integer.toString($1)); }
| STRING { $$=new AttributeObj(Type.STRING, $1); }
| TRUE { $$=new AttributeObj(Type.BOOLEAN, "true"); }
| FALSE { $$=new AttributeObj(Type.BOOLEAN, "false"); }
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
