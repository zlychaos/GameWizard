# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

JFLEX  = bin/jflex 
BYACCJ = ./yacc.macosx -v -J
JAVAC  = javac
JFLAGS = -g

.SUFFIXES: .java .class

CLASSES = \
    compiler/helper/PrimaryType.java \
    compiler/helper/Type.java \
	compiler/helper/Skill.java \
	compiler/helper/JsonItemType.java \
    compiler/helper/AttributeObj.java \
    compiler/helper/FunctionObj.java \
    compiler/helper/Expression.java \
    compiler/helper/QuarlifiedName.java \
    compiler/helper/JsonItem.java \
    compiler/helper/Config.java \
    compiler/helper/ScopeBlock.java \
    compiler/helper/SymbolRecord.java \
    compiler/helper/SymbolTable.java \
    compiler/helper/Util.java

all: Parser.class

classes: $(CLASSES:.java=.class)

.java.class:
	$(JAVAC) $(JFLAGS) $*.java

# targets:


run: Parser.class
	java -cp compiler/helper/:. Parser

build: clean Parser.class

clean:
	rm -f *~ *.class Parser.java Yylex.java Game.java gen_*.java .*.swp
	rm -f compiler/helper/*.class
	rm -rf target/mygame/cards/
	rm -rf target/mygame/characters/
	rm -f target/mygame/CardBase.java
	rm -f target/mygame/CharacterBase.java
	rm -f target/mygame/Game.java
	rm -f target/Makefile

Parser.class: Yylex.java Parser.java classes
	$(JAVAC) Parser.java

Yylex.java: GameWizard.flex
	$(JFLEX) GameWizard.flex

Parser.java: GameWizard.y
	$(BYACCJ) GameWizard.y
