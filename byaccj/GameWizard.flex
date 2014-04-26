/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

GAME_DF = "define game"
GAME_NM = "game_name"
CARD_DF = "define cards"
CHARACTER_DF = "define characters"
PLAYER_C = "num_of_players"
METHOD = "method"
PLAYER = "Player"
GAME_PORT = "server_listening_port"
INTEGER = [0-9]+
STRING = \"[^\"]*\" 

/*identifier*/
ID = [a-zA-Z][_a-zA-Z0-9]*
delim = [ \t\n]
ws = {delim}+

/*used as debug*/
STATEMENT_LIST = [#].*[#]

%%


/* operators */
"+" | 
"-" | 
"*" | 
"/" | 
"^" | 
"(" | 
")" |
"{" |
"}" |
";" |
":" { return (int) yycharat(0); }

{ws}  {/*do nothing*/}

/* float */
/*{NUM}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));*/
/*         return Parser.NUM; }*/

{INTEGER}  { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
		return Parser.INTEGER;}
{STRING}   { yyparser.yylval = new ParserVal(yytext()); return Parser.STRING;}

{GAME_DF}  {return Parser.GAME_DF;}
{CARD_DF} {return Parser.CARD_DF;}
{CHARACTER_DF} {return Parser.CHARACTER_DF;}
{GAME_NM}  {return Parser.GAME_NM;}
{PLAYER_C}  {return Parser.PLAYER_C;}
{GAME_PORT}  {return Parser.GAME_PORT;}
{METHOD} {return Parser.METHOD}
{PLAYER} {return Parser.PLAYER}
{ID} { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;}

/*debug*/
{STATEMENT_LIST} {return Parser.STATEMENT_LIST;} 

/* whitespace */
[ \t]+ { }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
