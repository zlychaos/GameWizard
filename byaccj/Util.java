import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Util {
	public static void writeGameJava(String server_define_string,
			String init_round_body_String) {
		File outputFile = new File("Game.java");
		try {

			if (outputFile.exists()) {
				outputFile.delete();
			}

			String prev_server_def = "import java.io.IOException;\n"
					+ "import java.util.ArrayList;\n"
					+ "import java.util.Collections;\n"
					+ "import java.util.HashMap;\n"
					+ "import java.util.LinkedList;\n"
					+ "import java.util.List;\n"
					+ "\n"
					+ "import compile.helloworld.cards.*;\n"
					+ "import compile.helloworld.characters.*;\n"
					+ "import zhllz.gamewizard.basic.ICard;\n"
					+ "import zhllz.gamewizard.communication.Server;\n"
					+ "\n"
					+ "public class Game {\n"
					+ "	\n"
					+ "	public static Server GameServer;\n"
					+ "	public static int port = 4119;\n"
					+ "	\n"
					+ "	public static ArrayList<Player> playerList;\n"
					+ "	public static HashMap<Integer, Player> map;\n"
					+ "	public static LinkedList<ICard> cardStack;\n"
					+ "	public static LinkedList<ICard> droppedCardStack;\n"
					+ "	\n"
					+ "	public static int currentPlayerIndex;\n"
					+ "	\n"
					+ "	public static int roundCount=0;\n"
					+ "	\n"
					+ "	public static boolean gameover;\n"
					+ "	\n"
					+ "	public static HashMap<Integer, ICard> roundSummary = new HashMap<Integer, ICard>();\n";

			String post_methods_string = "public static void shuffle(List<?> list){\n"
					+ "		Collections.shuffle(list);\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static void sendToOnePlayer(Player player, String msg) throws IOException{\n"
					+ "		player.conn.sendBroadcast(msg);\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static void broadcast(String msg) throws IOException{\n"
					+ "		GameServer.broadcast(msg);\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static void close() throws IOException{\n"
					+ "		GameServer.broadcast(\"Game Over!\");\n"
					+ "		GameServer.closeServer();\n"
					+ "	}\n"
					+ "	\n"
					+ "	\n"
					+ "	public static void main(String[] args) throws Exception{\n"
					+ "		\n"
					+ "		//int init_HP = 1;\n"
					+ "		\n"
					+ "		playerList = new ArrayList<Player>();\n"
					+ "		map = new HashMap<Integer, Player>();\n"
					+ "		cardStack = new LinkedList<ICard>();\n"
					+ "		droppedCardStack = new LinkedList<ICard>();\n"
					+ "		\n"
					+ "		GameServer = new Server(port);\n"
					+ "		\n"
					+ "		int count = 0;\n"
					+ "		//activePlayer = 1;\n"
					+ "		while(count++ < num_of_players){\n"
					+ "			\n"
					+ "			Player p = new Player(count, GameServer);\n"
					+ "			\n"
					+ "			playerList.add(p);\n"
					+ "			p.conn.sendBroadcast(\"You are Player\"+count);\n"
					+ "			System.out.println(\"one player(\"+count+\") joined\");\n"
					+ "		}\n"
					+ "		\n"
					+ "		for(Player p : playerList){\n"
					+ "			map.put(p.id, p);\n"
					+ "		}\n"
					+ "		\n"
					+ "		System.out.println(\"Game Start!\");\n"
					+ "		GameServer.broadcast(name + \": Game Start!\");\n"
					+ "		\n"
					+ "		init();\n"
					+ "		\n"
					+ "		currentPlayerIndex=0;	\n"
					+ "		\n"
					+ "		round_begin();\n"
					+ "		gameover = false;\n"
					+ "		while(!gameover)\n"
					+ "			nextOnlinePlayer();\n"
					+ "		\n"
					+ "		close();	\n"
					+ "		\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static ICard putCard(Player player) throws IOException {\n"
					+ "		player.conn.sendBroadcast(\"Your hand cards: \" + HandCardInfo(player));\n"
					+ "		\n"
					+ "		String input = null;\n"
					+ "		ICard c = null;\n"
					+ "		while(player.handCards.size() > 0 && player.isOnline()){\n"
					+ "			try{\n"
					+ "				input = player.conn.waitForInput(\"Please deal a card( type the index of card [\" + 1 + \"~\" + player.handCards.size() + \"])\");\n"
					+ "				int card_index = Integer.parseInt(input);\n"
					+ "				if(card_index <= player.handCards.size() && card_index > 0){\n"
					+ "					c = player.handCards.remove(card_index-1);\n"
					+ "					\n"
					+ "					roundSummary.put(player.id, c);\n"
					+ "					\n"
					+ "					return c;\n"
					+ "				}\n"
					+ "			} catch ( NumberFormatException e ){\n"
					+ "				player.conn.sendBroadcast(\"Please type the index of card [\" + 1 + \"~\" + player.handCards.size() + \"]\");\n"
					+ "			}\n"
					+ "		}\n"
					+ "		return null;\n"
					+ "		\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static void drawCard(Player p, int num) throws IOException{\n"
					+ "		\n"
					+ "		for(int i=0;i<num;i++){\n"
					+ "\n"
					+ "			if(cardStack.isEmpty()){\n"
					+ "				if(droppedCardStack.isEmpty()){\n"
					+ "					gameover = true;\n"
					+ "					GameServer.broadcast(\"Sorry, the card stack is empty.. The game can not carry on\");\n"
					+ "				}\n"
					+ "				else{\n"
					+ "					LinkedList<ICard> tmp = cardStack;\n"
					+ "					cardStack = droppedCardStack;\n"
					+ "					droppedCardStack = tmp;\n"
					+ "					Collections.shuffle(cardStack);\n"
					+ "				}		\n"
					+ "			}\n"
					+ "			p.handCards.add(cardStack.poll());\n"
					+ "			\n"
					+ "		}\n"
					+ "	}\n"
					+ "\n"
					+ "	\n"
					+ "	public static void nextOnlinePlayer() throws Exception{\n"
					+ "		if(currentPlayerIndex == num_of_players){\n"
					+ "			round_end();\n"
					+ "			\n"
					+ "			if(++roundCount == maximum_round){\n"
					+ "				gameover = true;\n"
					+ "				return ;\n"
					+ "			}\n"
					+ "			round_begin();\n"
					+ "		}\n"
					+ "		\n"
					+ "		currentPlayerIndex = (currentPlayerIndex+1)%num_of_players;\n"
					+ "		currentPlayerIndex = currentPlayerIndex==0?num_of_players:currentPlayerIndex;\n"
					+ "		\n"
					+ "		GameServer.broadcast(\"Now Turn: Player\"+currentPlayerIndex);\n"
					+ "		\n"
					+ "		Player playerInTurn = map.get(currentPlayerIndex);\n"
					+ "		if(playerInTurn.isOnline())\n"
					+ "			turn(playerInTurn);\n"
					+ "		\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static String PlayersInfo(){\n"
					+ "		StringBuilder sb = new StringBuilder(\"\\n--------------\");\n"
					+ "		for(Player p : playerList){\n"
					+ "			sb.append(\"\\n\"+p.toString());\n"
					+ "			sb.append(\"\\n--------------\");\n"
					+ "		}\n"
					+ "		return sb.toString();\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static String HandCardInfo(Player player){\n"
					+ "		StringBuilder sb = new StringBuilder(\"\\n--------------\");\n"
					+ "		int i = 1;\n"
					+ "		for(ICard card : player.handCards){\n"
					+ "			sb.append(\"\\n(\"+i+\")\");\n"
					+ "			sb.append(card.toString());\n"
					+ "			sb.append(\"\\n--------------\");\n"
					+ "			i++;\n"
					+ "		}\n"
					+ "		return sb.toString();\n"
					+ "	}\n"
					+ "	\n"
					+ "	public static String GameGeneralInfo(){\n"
					+ "		StringBuilder sb = new StringBuilder();\n"
					+ "		int length = name.length();\n"
					+ "		sb.append('+');\n"
					+ "		for(int i=0;i<length;i++)\n"
					+ "			sb.append('-');\n"
					+ "		sb.append(\"+\\n|\");\n"
					+ "		sb.append(name);\n"
					+ "		sb.append(\"|\\n+\");\n"
					+ "		for(int i=0;i<length;i++)\n"
					+ "			sb.append('-');\n"
					+ "		sb.append(\"+\");\n"
					+ "		sb.append(\"\\nThe player in turn: Player \" + currentPlayerIndex);\n"
					+ "		sb.append(\"\\nList of players: \");\n"
					+ "		sb.append(PlayersInfo());\n"
					+ "		return sb.toString();\n" + "	}\n" + "	\n" + "}\n";

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

	public static void writeCardsJava(String card_name, Object variable_list,
			String method_content) {
		File outputFile = new File( card_name + ".java");
		try {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			outputFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			out.write("public class "  + card_name
					+ " implements ICard{\n");

			List<String> var_list = (List<String>) variable_list;
			for (int i = 0; i < var_list.size(); i = i + 3) {
				out.write("public " + var_list.get(i) + " "
						+ var_list.get(i + 1) + "=" + var_list.get(i + 2)
						+ ";\n");
			}
			
			String toStr_part1 = "	@Override\n" + 
					"	public String toString() {\n" + 
					"		StringBuilder sb = new StringBuilder(\"Card \");\n" + 
					"		sb.append(getName());\n" + 
					"		sb.append(\":{\");\n";
			
			String toStr_part2 = "		sb.append(\"\\n}\");\n" + 
					"		return sb.toString();\n" + 
					"	}\n";
			
			StringBuffer toStr_mid = new StringBuffer();
			for (int i = 0; i < var_list.size(); i = i + 3) {
				toStr_mid.append("sb.append(\"\\n\\t"+var_list.get(i+1)+"=\");\n" + 
						"		sb.append("+var_list.get(i+1)+");\n");
			}
			
			out.write(toStr_part1+toStr_mid.toString()+toStr_part2);
			

			String getNameMethod = "@Override\n" + "	public String getName() {\n"
					+ "		return \"" + card_name + "\";\n" + "	}";
			out.write(getNameMethod);

			out.write("\n@Override");
			out.write("public void method(PlayerBase dealer){\n");
			out.write(method_content);
			out.write("\n} \n}");


			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void writeCharacterJava(String character_name,
			Object variable_list, Object skill_list) {
		File outputFile = new File( character_name + ".java");
		try {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			outputFile.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			out.write("public class " + character_name
					+ " extends CharacterBase{\n");
			
			
			List<String> var_list = (List<String>) variable_list;
			List<String> sk_list = (List<String>) skill_list;
			
			for (int i = 0; i < var_list.size(); i = i + 3) {
				out.write("public " + var_list.get(i) + " "
						+ var_list.get(i + 1) + "=" + var_list.get(i + 2)
						+ ";\n");
			}
			
			out.write("public "+character_name+"(){\n" + 
					"		skillList = new String["+(sk_list.size()/2)+"];\n");
			for (int i = 0; i < sk_list.size(); i = i + 2) {
				out.write("skillList["+(i/2)+"] = \""+sk_list.get(i)+"\";\n");
			}
			out.write("}\n");
			
			
			String toStr_part1 = "@Override\n" + 
					"	public String toString(){\n" + 
					"		StringBuilder sb = new StringBuilder();\n";
			
			String toStr_part2 = "return sb.toString();\n" + 
					"	}\n";
			
			StringBuffer toStr_mid = new StringBuffer();
			for (int i = 0; i < var_list.size(); i = i + 3) {
				toStr_mid.append("sb.append(\"\\t"+var_list.get(i+1)+"=\");\n" + 
						"		sb.append("+var_list.get(i+1)+");\n");
			}
			
			out.write(toStr_part1+toStr_mid.toString()+toStr_part2);
			
			
			String skillMethod_part1= "@Override\n" + 
					"	public boolean skill(PlayerBase p, String skillName) {\n";
			
			String skillMethod_part2= "return false;\n" + 
					"	}\n";
			
			StringBuffer skillMethod_mid = new StringBuffer();
			for (int i = 0; i < sk_list.size(); i = i + 2) {
				skillMethod_mid.append("if(\""+sk_list.get(i)+"\".equals(skillName)){\n" + 
						  sk_list.get(i+1) + 
						"\n			return true;\n" + 
						"		}\n");
			}
			out.write(skillMethod_part1+skillMethod_mid.toString()+skillMethod_part2);
			
			
			
			
			String getNameMethod = "@Override\n" + "	public String getName() {\n"
					+ "		return \"" + character_name + "\";\n" + "	}";
			out.write(getNameMethod);
			
			

			
			out.write("\n}");
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
