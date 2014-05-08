# This Makefile is for test the package, zhllz.gamewizard.communication

#
# define compiler and compiler flag variables
#

JFLAGS = -g
JC = javac

#
# Clear any default targets for building .class files from .java files; we 
# will provide our own target entry to do this in this makefile.
# make has a set of default targets for different suffixes (like .c.o) 
# Currently, clearing the default for .java.class is not necessary since 
# make does not have a definition for this target, but later versions of 
# make may, so it doesn't hurt to make sure that we clear any default 
# definitions for these
#

.SUFFIXES: .java .class

#
# CLASSES is a macro consisting of 4 words (one for each java source file)
#

CLASSES = \
	zhllz/gamewizard/communication/StrController.java \
	zhllz/gamewizard/communication/ICommunicatable.java \
	zhllz/gamewizard/communication/ServerConnection.java \
	zhllz/gamewizard/communication/Server.java \
	zhllz/gamewizard/communication/Client.java \
	compile/helloworld/cards/CardOne.java \
	compile/helloworld/cards/CardTwo.java \
	compile/helloworld/cards/CardThree.java \
	compile/helloworld/characters/RegularGuy.java \
	compile/helloworld/Player.java \
    compile/helloworld/CardBase.java \
    compile/helloworld/CharacterBase.java \
	compile/helloworld/Game.java \
	compile/tinywar/cards/Attack.java \
	compile/tinywar/cards/Dodge.java \
	compile/tinywar/cards/FirstAid.java \
	compile/tinywar/cards/Diligenra.java \
	compile/tinywar/characters/Doctor.java \
	compile/tinywar/characters/Gambler.java \
	compile/tinywar/characters/Vampire.java \
	compile/tinywar/Player.java \
    compile/tinywar/CardBase.java \
    compile/tinywar/CharacterBase.java \
	compile/tinywar/Game.java
#
# the default make target entry
#

default: classes

#
# This target entry uses Suffix Replacement within a macro: 
# $(name:string1=string2)
# 	In the words in the macro named 'name' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES 
# with the .class suffix
#

classes: $(CLASSES:.java=.class)

#
# Here is our target entry for creating .class files from .java files 
# This is a target entry that uses the suffix rule syntax:
#	DSTS:
#		rule
#  'TS' is the suffix of the target file, 'DS' is the suffix of the dependency 
#  file, and 'rule'  is the rule for building a target	
# '$*' is a built-in macro that gets the basename of the current target 
# Remember that there must be a < tab > before the command line ('rule') 
#

.java.class:
	$(JC) $(JFLAGS) -classpath ../java-json.jar:zhllz/gamewizard/communication/:compile/helloworld/:compile/helloworld/cards:compile/helloworld/characters:compile/tinywar/:compile/tinywar/cards:compile/tinywar/characters:. $*.java

server: classes
	java -cp ../java-json.jar:zhllz/gamewizard/communication/:. zhllz.gamewizard.communication.Server

helloworld: classes
	java -cp ../java-json.jar:zhllz/gamewizard/communication/:compile/helloworld/:compile/helloworld/cards:compile/helloworld/characters:. compile.helloworld.Game

tinywar: classes
	java -cp ../java-json.jar:zhllz/gamewizard/communication/:compile/tinywar/:compile/tinywar/cards:compile/tinywar/characters:. compile.tinywar.Game

client: classes
	java -cp ../java-json.jar:zhllz/gamewizard/communication/:. zhllz.gamewizard.communication.Client


#
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) zhllz/gamewizard/communication/*.class
