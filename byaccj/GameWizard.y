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
%token GAME_NM
%token PLAYER_C
%token PLAYER
%token STATEMENT_LIST

%token <sval> ID
%token METHOD

%token GAME_PORT
%token <ival> INTEGER
%token <sval> STRING


%left '-' '+'
%left '*' '/'
%right '^'         /* exponentiation        */
      
%%
input: game_df card_df    {}
     ;
game_df : GAME_DF '{' game_df_content '}'  {}
	;
game_df_content : GAME_NM ':' STRING ';'
                  PLAYER_C ':' INTEGER ';'
                  GAME_PORT ':' INTEGER ';'  {Util.writeGameJava($3,$7,$11);}
		;
card_df : CARD_DF '[' cards_df_content ']' {}
	;
cards_df_content : cards_df_content card_df_content  {}
               | card_df_content {}
		;
card_df_content: METHOD '(' PLAYER ID ')' '{' STEATEMENT_LIST '}'
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
