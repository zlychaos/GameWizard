GameWizard
==========

Compiler for GameWizard, a language for creating RPG card games.

How to use
----------
The repo gets messy overtime. To try out, go to byacc_new folder, and run 
'''
make
java Parser < source_code_file_in_GameWizard
'''
There are four example source code file under byacc_new folder, starting with "source_".

And then we can start the game we created with GameWizard by going under byacc_new/target folder, and run 
'''
make game
'''
to start a game server, and run 
'''
make client
'''
to start a client process for a player to connect to the game server and join the game.


