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
GAME_NM = "name"
PLAYER_C = "num_of_players"
GAME_PORT = "server_listening_port"
INTEGER = [0-9]+
STRING = \"[^\"]*\" 
NL  = \n | \r | \r\n

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
":"  { return (int) yycharat(0); }


/* newline */
/*{NL}   { return Parser.NL; }*/

/* float */
/*{NUM}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));*/
/*         return Parser.NUM; }*/

{INTEGER}  { yyparser.yylval = new ParserVal(Integer.parseInteger(yytext()));
		return Parser.INTEGER;}
{STRING}   { yyparser.yylval = new ParserVal(yytext()); return Parser.STRING;}

{GAME_DF}  {return Parser.GAME_DF;}
{GAME_NM}  {return Parser.GAME_NM;}
{PLAYER_C}  {return Parser.PLAYER_C;}
{GAME_PORT}  {return Parser.GAME_PORT;}


/* whitespace */
[ \t]+ { }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
