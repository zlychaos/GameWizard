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
CARD = "Card"
CHARACTER_DF = "define characters"
PLAYER_C = "num_of_players"
METHOD = "method"
PLAYER = "Player"
MAX_ROUND = "maximum_round"
INTEGER = -?[0-9]+
STRING = \"[^\"]*\"
SKILL = "skill"
DEALER = "dealer"
VOID = "void"
ROUNDSUMMARY = "roundSummary"

INIT = "init"
ROUND_END = "round_end"
ROUND_BEGIN = "round_begin"
ROUND = "round"
TURN = "turn"
DYING = "dying"


/*identifier*/
ID = [a-zA-Z][_a-zA-Z0-9]*
delim = [ \t\n]
ws = {delim}+

/*used as debug*/

%%


/* operators */
"+" | 
"-" | 
"*" | 
"/" | 
"%" |
"^" | 
"(" |
")" |
"{" |
"}" |
";" |
"[" |
"]" |
"=" |
">" |
"<" |
"~" |
"!" |
"." |
"," |
":" { return (int) yycharat(0); }

"=="		{return Parser.OP_EQ;}
"<="		{return Parser.OP_LE;}
">="		{return Parser.OP_GE;}
"!="		{return Parser.OP_NE;}
"||"		{return Parser.OP_LOR;}
"&&"		{return Parser.OP_LAND;}

{ws}  {/*do nothing*/}

/* float */
/*{NUM}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));*/
/*         return Parser.NUM; }*/

{INTEGER}  { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
		return Parser.INTEGER;}
{STRING}   { yyparser.yylval = new ParserVal(yytext()); return Parser.STRING;}

{CARD} {return Parser.CARD;}
{GAME_DF}  {return Parser.GAME_DF;}
{CARD_DF} {return Parser.CARD_DF;}
{CHARACTER_DF} {return Parser.CHARACTER_DF;}
{GAME_NM}  {return Parser.GAME_NM;}
{PLAYER_C}  {return Parser.PLAYER_C;}
{MAX_ROUND}  {return Parser.MAX_ROUND;}
{METHOD} {return Parser.METHOD;}
{PLAYER} {return Parser.PLAYER;}
{SKILL} {return Parser.SKILL;}
{DEALER} {return Parser.DEALER;}
{VOID} {return Parser.VOID;}

{INIT} {return Parser.INIT;}
{ROUND_END} {return Parser.ROUND_END;}
{ROUND_BEGIN} {return Parser.ROUND_BEGIN;}
{ROUND} {return Parser.ROUND;}
{TURN} {return Parser.TURN;}
{DYING} {return Parser.DYING;}

"false" {return Parser.FALSE;}
"true" {return Parser.TRUE;}
"if" {return Parser.IF;}
"else" {return Parser.ELSE;}
"while"		{return Parser.WHILE;}
"int"   {return Parser.DECLR_INT;}
"String"    {return Parser.DECLR_STR;}
"bool"  {return Parser.DECLR_BOOL;}

"foreach" {return Parser.FOREACH;}
{ROUNDSUMMARY} {return Parser.ROUNDSUMMARY;}
"in" {return Parser.IN;} 



{ID} { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;}




/*debug*/


/* whitespace */
[ \t]+ { }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
