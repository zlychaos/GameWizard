# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

JFLEX  = bin/jflex 
BYACCJ = ./yacc.linux -J
JAVAC  = javac

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean:
	rm -f *~ *.class Parser.java Yylex.java Game.java gen_*.java

Parser.class: Yylex.java Parser.java
	$(JAVAC) Parser.java

Yylex.java: GameWizard.flex
	$(JFLEX) GameWizard.flex

Parser.java: GameWizard.y
	$(BYACCJ) GameWizard.y
