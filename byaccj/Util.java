import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Util {
	public static void writeGameJava(String server_define_string,
			String init_round_body_String) {
		File outputFile = new File("./src/compile/mygame/Game.java");
		try {

			if (outputFile.exists()) {
				outputFile.delete();
			}

			String prev_server_def = 
					"package compile.mygame;\n\n"+
			"\n" + 
			"import java.util.ArrayList;\n" + 
			"import java.util.Collections;\n" + 
			"import java.util.HashMap;\n" + 
			"import java.util.LinkedList;\n" + 
			"import java.util.List;\n" + 
			"\n" + 
			"import compile.tinywar.cards.*;\n" + 
			"import compile.tinywar.characters.*;\n" + 
			"import zhllz.gamewizard.communication.Server;\n" + 
			"\n" + 
			"public class Game {\n" + 
			"	\n" + 
			"	public static Server GameServer;\n" + 
			"	public static int port = 4119;\n" + 
			"	\n" + 
			"	public static ArrayList<Player> playerList;\n" + 
			"	public static HashMap<Integer, Player> map;\n" + 
			"	public static LinkedList<CardBase> cardStack;\n" + 
			"	public static LinkedList<CardBase> droppedCardStack;\n" + 
			"	\n" + 
			"	public static int currentPlayerIndex;\n" + 
			"	\n" + 
			"	public static int roundCount=0;\n" + 
			"	\n" + 
			"	public static boolean gameover;\n" + 
			"	\n" + 
			"	public static HashMap<Integer, CardBase> roundSummary = new HashMap<Integer, CardBase>();\n" + 
			"	";
					
			String post_methods_string = "	// compiling result end\n" + 
					"	\n" + 
					"	public static void main(String[] args){\n" + 
					"		\n" + 
					"		//int init_HP = 1;\n" + 
					"		\n" + 
					"		playerList = new ArrayList<Player>();\n" + 
					"		map = new HashMap<Integer, Player>();\n" + 
					"		cardStack = new LinkedList<CardBase>();\n" + 
					"		droppedCardStack = new LinkedList<CardBase>();\n" + 
					"		\n" + 
					"		GameServer = new Server(port);\n" + 
					"		\n" + 
					"		int count = 0;\n" + 
					"		//activePlayer = 1;\n" + 
					"		while(count++ < num_of_players){\n" + 
					"			\n" + 
					"			Player p = new Player(count, GameServer);\n" + 
					"			\n" + 
					"			playerList.add(p);\n" + 
					"			p.conn.sendBroadcast(\"You are Player\"+count);\n" + 
					"			System.out.println(\"one player(\"+count+\") joined\");\n" + 
					"		}\n" + 
					"		\n" + 
					"		for(Player p : playerList){\n" + 
					"			map.put(p.id, p);\n" + 
					"		}\n" + 
					"		\n" + 
					"		System.out.println(\"Game Start!\");\n" + 
					"		GameServer.broadcast(game_name + \": Game Start!\");\n" + 
					"		\n" + 
					"		gameover = false;\n" + 
					"		\n" + 
					"		init();\n" + 
					"		for(Player p : playerList){\n" + 
					"			if(p.character==null){\n" + 
					"				gameover = true;\n" + 
					"				broadcast(\"Player \"+p.id+\" is not assigned a character after initialization. The game can not go on.\");\n" + 
					"			}\n" + 
					"		}\n" + 
					"		\n" + 
					"		currentPlayerIndex=0;	\n" + 
					"		\n" + 
					"		round_begin();\n" + 
					"		\n" + 
					"		while(!gameover)\n" + 
					"			nextOnlinePlayer();\n" + 
					"		\n" + 
					"		close();	\n" + 
					"		\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static void nextOnlinePlayer(){\n" + 
					"		if(currentPlayerIndex == num_of_players){\n" + 
					"			round_end();\n" + 
					"			\n" + 
					"			if(++roundCount == maximum_round){\n" + 
					"				gameover = true;\n" + 
					"				return ;\n" + 
					"			}\n" + 
					"			roundSummary.clear();\n" + 
					"			round_begin();\n" + 
					"		}\n" + 
					"		\n" + 
					"		currentPlayerIndex = (currentPlayerIndex+1)%num_of_players;\n" + 
					"		currentPlayerIndex = currentPlayerIndex==0?num_of_players:currentPlayerIndex;\n" + 
					"		\n" + 
					"		GameServer.broadcast(\"Now Turn: Player\"+currentPlayerIndex);\n" + 
					"		\n" + 
					"		Player playerInTurn = map.get(currentPlayerIndex);\n" + 
					"		if(playerInTurn.isOnline())\n" + 
					"			turn(playerInTurn);\n" + 
					"		\n" + 
					"	}\n" + 
					"	\n" + 
					"\n" + 
					"	public static void shuffle(List<?> list){\n" + 
					"		Collections.shuffle(list);\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static void sendToOnePlayer(Player player, String msg){\n" + 
					"		player.conn.sendBroadcast(msg);\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static void broadcast(String msg){\n" + 
					"		GameServer.broadcast(msg);\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static void close() {\n" + 
					"		GameServer.broadcast(\"Game Over!\");\n" + 
					"		GameServer.closeServer();\n" + 
					"	}\n" + 
					"	\n" + 
					"\n" + 
					"	public static int waitForChoice(Player player, String promt, int range){\n" + 
					"		player.conn.sendBroadcast(\"#If you really do not want to make this choice, type \\\"cancel\\\".#\\n\"+promt);\n" + 
					"		String input = null;\n" + 
					"		while(player.isOnline() && range>0){\n" + 
					"			try{\n" + 
					"				input = player.conn.waitForInput(\"Please choose from [\" + 1 + \"~\" + range + \"]\");\n" + 
					"				if(\"cancel\".equals(input)){\n" + 
					"					return -1;\n" + 
					"				}\n" + 
					"				int choice = Integer.parseInt(input);\n" + 
					"				if(choice <= range && choice > 0)\n" + 
					"					return choice;\n" + 
					"			} catch ( NumberFormatException e ){\n" + 
					"				player.conn.sendBroadcast(\"Please type a number, choosing from [\" + 1 + \"~\" + range + \"]\");\n" + 
					"			}\n" + 
					"			\n" + 
					"		}\n" + 
					"		return -1;\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static boolean waitForSkill(Player player){\n" + 
					"		String promt = \"Skill List( choose with the indexes ): \" + player.character.getSkillList();\n" + 
					"		int range = player.character.skillList.length;\n" + 
					"		int choice = waitForChoice(player, promt, range);\n" + 
					"		if(choice != -1){\n" + 
					"			return player.character.skill(player, player.character.skillList[choice-1]);\n" + 
					"		}\n" + 
					"		return false;\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static Player waitForTarget(Player player){\n" + 
					"		String promt = \"List of players( choose with the indexes ): \" + PlayersInfo();\n" + 
					"		int range = playerList.size();\n" + 
					"		int choice = waitForChoice(player, promt, range);\n" + 
					"		if(choice != -1)\n" + 
					"			return playerList.get(choice-1);\n" + 
					"		return null;\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static CardBase putCard(Player player) {\n" + 
					"		String promt = \"Your hand cards( choose with the indexes ): \" + HandCardInfo(player);\n" + 
					"		int range = player.handCards.size();\n" + 
					"		int choice = waitForChoice(player, promt, range);\n" + 
					"		if(choice != -1){\n" + 
					"			CardBase c = player.handCards.remove(choice-1);\n" + 
					"			roundSummary.put(player.id, c);\n" + 
					"			return c;\n" + 
					"		}\n" + 
					"		return null;\n" + 
					"		\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static void drawCard(Player p, int num) {\n" + 
					"		\n" + 
					"		for(int i=0;i<num;i++){\n" + 
					"\n" + 
					"			if(cardStack.isEmpty()){\n" + 
					"				if(droppedCardStack.isEmpty()){\n" + 
					"					gameover = true;\n" + 
					"					GameServer.broadcast(\"Sorry, the card stack is empty.. The game can not carry on\");\n" + 
					"				}\n" + 
					"				else{\n" + 
					"					LinkedList<CardBase> tmp = cardStack;\n" + 
					"					cardStack = droppedCardStack;\n" + 
					"					droppedCardStack = tmp;\n" + 
					"					Collections.shuffle(cardStack);\n" + 
					"				}		\n" + 
					"			}\n" + 
					"			p.handCards.add(cardStack.poll());\n" + 
					"			\n" + 
					"		}\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static String PlayersInfo(){\n" + 
					"		StringBuilder sb = new StringBuilder(\"\\n--------------\");\n" + 
					"		int i = 1;\n" + 
					"		for(Player p : playerList){\n" + 
					"			sb.append(\"\\n\"+i+\")\\n\"+p.toString());\n" + 
					"			sb.append(\"\\n--------------\");\n" + 
					"			i++;\n" + 
					"		}\n" + 
					"		return sb.toString();\n" + 
					"	}\n" + 
					"	\n" + 
					"	public static String HandCardInfo(Player player){\n" + 
					"		StringBuilder sb = new StringBuilder(\"\\n--------------\");\n" + 
					"		int i = 1;\n" + 
					"		for(CardBase card : player.handCards){\n" + 
					"			sb.append(\"\\n\"+i+\")\\n\");\n" + 
					"			sb.append(card.toString());\n" + 
					"			sb.append(\"\\n--------------\");\n" + 
					"			i++;\n" + 
					"		}\n" + 
					"		return sb.toString();\n" + 
					"	}\n" + 
					"\n" + 
					"	\n" + 
					"	public static String GameGeneralInfo(){\n" + 
					"		StringBuilder sb = new StringBuilder();\n" + 
					"		int length = game_name.length();\n" + 
					"		sb.append('+');\n" + 
					"		for(int i=0;i<length;i++)\n" + 
					"			sb.append('-');\n" + 
					"		sb.append(\"+\\n|\");\n" + 
					"		sb.append(game_name);\n" + 
					"		sb.append(\"|\\n+\");\n" + 
					"		for(int i=0;i<length;i++)\n" + 
					"			sb.append('-');\n" + 
					"		sb.append(\"+\");\n" + 
					"		sb.append(\"\\nThe player in turn: Player \" + currentPlayerIndex);\n" + 
					"		sb.append(\"\\nList of players: \");\n" + 
					"		sb.append(PlayersInfo());\n" + 
					"		return sb.toString();\n" + 
					"	}\n" + 
					"	\n" + 
					"}";

			outputFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

			out.write(prev_server_def);
			out.write(server_define_string);
			out.write(init_round_body_String);
			out.write(post_methods_string);

			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int card_ind=0;

	public static void writeCardsJava(String card_name, Object variable_list,
			String method_content) {
		
		
		if(card_ind==0){
			card_ind++;
			
			File cardBaseFile = new File("./src/compile/mygame/CardBase.java");
			try {
				if (cardBaseFile.exists()) {
					cardBaseFile.delete();
				}
				cardBaseFile.createNewFile();
				BufferedWriter outBase = new BufferedWriter(new FileWriter(cardBaseFile));
				
				outBase.write("package compile.tinywar;\n" + 
						"\n" + 
						"public abstract class CardBase {");
				
				List<String> var_list = (List<String>) variable_list;
				for (int i = 0; i < var_list.size(); i = i + 3) {
					outBase.write("public " + var_list.get(i) + " "
							+ var_list.get(i + 1) + ";\n");
				}
				
				
				String toStr_part1 = "	@Override\n"
						+ "	public String toString() {\n"
						+ "		StringBuilder sb = new StringBuilder(\"Card \");\n"
						+ "		sb.append(getName());\n" + "		sb.append(\":{\");\n";

				String toStr_part2 = "		sb.append(\"\\n}\");\n"
						+ "		return sb.toString();\n" + "	}\n";

				StringBuffer toStr_mid = new StringBuffer();
				for (int i = 0; i < var_list.size(); i = i + 3) {
					toStr_mid.append("sb.append(\"\\n\\t" + var_list.get(i + 1)
							+ "=\");\n" + "		sb.append(" + var_list.get(i + 1)
							+ ");\n");
				}

				outBase.write(toStr_part1 + toStr_mid.toString() + toStr_part2);
				
				outBase.write("public abstract String getName();\n" + 
						"	public abstract boolean method(Player dealer);\n" + 
						"\n" + 
						"}");
				
				outBase.flush();
				outBase.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		File outputFile = new File("./src/compile/mygame/cards/" + card_name
				+ ".java");
		try {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			outputFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

			out.write("package compile.mygame.cards;\n" + "\n");

			out.write("public class " + card_name + " implements CardBase{\n");
			
			out.write("public "+card_name+"(){\n");

			List<String> var_list = (List<String>) variable_list;
			for (int i = 0; i < var_list.size(); i = i + 3) {
				out.write( var_list.get(i + 1) + "=" + var_list.get(i + 2)
						+ ";\n");
			}
			
			out.write("}\n");

			

			String getNameMethod = "@Override\n"
					+ "	public String getName() {\n" + "		return \""
					+ card_name + "\";\n" + "	}";
			out.write(getNameMethod);

			out.write("\n@Override\n");
			out.write("public void method(Player dealer){\n");
			out.write(method_content);
			out.write("\n} \n}");

			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static int character_ind=0;

	public static void writeCharacterJava(String character_name,
			Object variable_list, Object skill_list) {
		
		if(character_ind==0){
			character_ind++;
			
			File characterBaseFile = new File("./src/compile/mygame/CharacterBase.java");
			try {
				if (characterBaseFile.exists()) {
					characterBaseFile.delete();
				}
				characterBaseFile.createNewFile();
				BufferedWriter outBase = new BufferedWriter(new FileWriter(characterBaseFile));
				
				outBase.write("package compile.mygame;\n" + 
						"\n" + 
						"public abstract class CharacterBase {\n");
				
				List<String> var_list = (List<String>) variable_list;

				for (int i = 0; i < var_list.size(); i = i + 3) {
					outBase.write("public " + var_list.get(i) + " "
							+ var_list.get(i + 1)+";\n");
				}
				
				outBase.write("	public String[] skillList;\n" + 
						"\n" + 
						"	public String getSkillList(){\n" + 
						"		StringBuilder sb = new StringBuilder(\"[ \");\n" + 
						"		int i = 1;\n" + 
						"		for(String skill : skillList){\n" + 
						"			if(i!=1){\n" + 
						"				sb.append(\", \");\n" + 
						"			}\n" + 
						"			sb.append(\"(\"+i+\"): \" + skill);\n" + 
						"			i++;\n" + 
						"		}\n" + 
						"		sb.append(\" ]\");\n" + 
						"		return sb.toString();\n" + 
						"	}\n");
				
				String toStr_part1 = "@Override\n" + "	public String toString(){\n"
						+ "		StringBuilder sb = new StringBuilder();\n";

				String toStr_part2 = "return sb.toString();\n" + "	}\n";

				StringBuffer toStr_mid = new StringBuffer();
				for (int i = 0; i < var_list.size(); i = i + 3) {
					toStr_mid.append("sb.append(\"\\t" + var_list.get(i + 1)
							+ "=\");\n" + "		sb.append(" + var_list.get(i + 1)
							+ ");\n");
				}

				outBase.write(toStr_part1 + toStr_mid.toString() + toStr_part2);
				
				outBase.write("public abstract String getName();\n" + 
						"	public abstract String getDiscription();\n" + 
						"	public abstract boolean skill(Player p, String skillName);\n}\n");
				
				outBase.flush();
				outBase.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		File outputFile = new File("./src/compile/mygame/characters/"
				+ character_name + ".java");
		try {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			outputFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

			out.write("package compile.mygame.characters;\n" + "\n"
					+ "import zhllz.gamewizard.basic.CharacterBase;\n"
					+ "import zhllz.gamewizard.basic.Player;\n");

			out.write("public class " + character_name
					+ " extends CharacterBase{\n");

			List<String> var_list = (List<String>) variable_list;
			List<String> sk_list = (List<String>) skill_list;

			

			out.write("public " + character_name + "(){\n"
					+ "		skillList = new String[" + (sk_list.size() / 2)
					+ "];\n");
			for (int i = 0; i < sk_list.size(); i = i + 2) {
				out.write("skillList[" + (i / 2) + "] = \"" + sk_list.get(i)
						+ "\";\n");
			}
			
			for (int i = 0; i < var_list.size(); i = i + 3) {
				out.write(var_list.get(i + 1) + "=" + var_list.get(i + 2)
						+ ";\n");
			}
			out.write("}\n");

			out.write("\n" + "	@Override\n"
					+ "	public String getDiscription() {\n"
					+ "		return \"\";\n" + "	}\n");

			

			String skillMethod_part1 = "@Override\n"
					+ "	public boolean skill(Player p, String skillName) {\n";

			String skillMethod_part2 = "return false;\n" + "	}\n";

			StringBuffer skillMethod_mid = new StringBuffer();
			for (int i = 0; i < sk_list.size(); i = i + 2) {
				skillMethod_mid.append("if(\"" + sk_list.get(i)
						+ "\".equals(skillName)){\n" + sk_list.get(i + 1)
						+ "\n			return true;\n" + "		}\n");
			}
			out.write(skillMethod_part1 + skillMethod_mid.toString()
					+ skillMethod_part2);

			String getNameMethod = "@Override\n"
					+ "	public String getName() {\n" + "		return \""
					+ character_name + "\";\n" + "	}";
			out.write(getNameMethod);

			out.write("\n}");
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
