%{
#include <ytab.h>
%}

Separator	[\(\)\{\}\[\]\;\,\.]
Delimiter1	[\=\>\<\!\~\?\:\+\-\*\/\&\|\^\%]
HexDigit	[0-9a-fA-F]
Digit		[0-9]
NonZeroDigit	[1-9]
Letter		[a-zA-Z_]
AnyButSlash	[^\/]
AnyButAstr	[^\*]
BLANK		[ ]
BLK		[\b]
TAB		[\t]
FF		[\f]
ESCCHR		[\\]
CR		[\r]
LF		[\n]

Escape		[\\]([r]|[n]|[b]|[f]|[t]|[\\]|[\']|[\"])
Identifier 	{Letter}({Letter}|{Digit})*

Comment1        [\/][\*]({AnyButAstr}|[\*]{AnyButSlash})*[\*][\/]
Comment2        [\/][\/].*
Comment		({Comment1}|{Comment2})

Dimension	[\[]({CR}|{LF}|{FF}|{TAB}|{BLK}|{BLANK}|{Comment})*[\]]

DecimalNum	{NonZeroDigit}{Digit}*


AnyChrChr	[^\\']
AnyStrChr	[^\\"]
Character	[\']({Escape}|{OctEscape}|{AnyChrChr})[\']
String		[\"]({Escape}|{OctEscape}|{AnyStrChr})*[\"]
Numeric  	({IntegerLiteral}|{FloatingPoint})
Literal		({Numeric}|{Character}|{String})

%%

"true"		{return TRUE;}
"false"		{return FALSE;}

{Separator}	{return yytext[0];}
{Delimiter1}	{return yytext[0];}
{Dimension}	{return OP_DIM;}

"=="		{return OP_EQ;}
"<="		{return OP_LE;}
">="		{return OP_GE;}
"!="		{return OP_NE;}
"||"		{return OP_LOR;}
"&&"		{return OP_LAND;}

"boolean"	{return BOOLEAN;}
"int"		{return INT;}
"string"	{return STRING;}

{String}	{return LITERAL;}


"break"		{return BREAK;}
"else"		{return ELSE;}

"return"	{return RETURN;}
"for"		{return FOR;}
"while"		{return WHILE;}
"continue"	{return CONTINUE;}
"if"		{return IF;}
"Start"   	{return START;}


{Identifier}	{return IDENTIFIER;}

{DecimalNum}    {return LITERAL;}
{OctalNum}      {return LITERAL;}
{HexNum}        {return LITERAL;}



{CR}   		{}
{LF}		{}
{FF}		{}
{TAB}		{}
{BLK}          {}
{BLANK}		{}

{Comment}	{}


