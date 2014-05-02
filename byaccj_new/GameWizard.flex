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

/* keywords for language structure */
GAME_DF = "define game"
CARD_DF = "define cards"
CHARACTER_DF = "define characters"

/* built-in function names */
METHOD = "method"
INIT = "init"
ROUND_BEGIN = "round_begin"
ROUND_END = "round_end"
TURN = "turn"

PLAYER = "Player"
CARD = "Card"
VOID = "void"

INTEGER = [0-9]+
STRING = \"[^\"]*\"
SKILL = "skill"

STATLIST = "dosomethinghere"

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

{GAME_DF}  {return Parser.GAME_DF;}
{CARD_DF} {return Parser.CARD_DF;}
{CHARACTER_DF} {return Parser.CHARACTER_DF;}
{METHOD} {return Parser.METHOD;}
{PLAYER} {return Parser.PLAYER;}
{SKILL} {return Parser.SKILL;}
{INIT} {return Parser.INIT;}
{ROUND_END} {return Parser.ROUND_END;}
{ROUND_BEGIN} {return Parser.ROUND_BEGIN;}
{TURN} {return Parser.TURN;}

"false" {return Parser.FALSE;}
"true" {return Parser.TRUE;}
"if" {return Parser.IF;}
"else" {return Parser.ELSE;}
"while"		{return Parser.WHILE;}
"int"   {return Parser.DECLR_INT;}
"String"    {return Parser.DECLR_STR;}
"bool"  {return Parser.DECLR_BOOL;}
{VOID} {return Parser.VOID;}
{STATLIST} {yyparser.yylval = new ParserVal(yytext()); ;return Parser.STATLIST;}


{ID} { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;}




/*debug*/


/* whitespace */
[ \t]+ { }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
