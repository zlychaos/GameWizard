package compile.helloworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import compile.helloworld.cards.*;
import compile.helloworld.characters.*;
import zhllz.gamewizard.communication.Server;

public class Game {
	
	public static Server GameServer;
	public static int port = 4119;
	
	public static ArrayList<Player> playerList;
	public static HashMap<Integer, Player> map;
	public static LinkedList<CardBase> cardStack;
	public static LinkedList<CardBase> droppedCardStack;
	
	public static int currentPlayerIndex;
	
	public static int roundCount=0;
	
	public static boolean gameover;
	
	public static HashMap<Integer, CardBase> roundSummary = new HashMap<Integer, CardBase>();
	

	// compiling result
	public static String game_name = "Hello World";
	public static int num_of_players = 2;
	public static int maximum_round = 2; 
		
	public static void init(){
		for(int i=0; i<10; i++){
			cardStack.add(new CardOne());
			cardStack.add(new CardTwo());
			cardStack.add(new CardThree());
		}
		shuffle(cardStack);
		
		for(Player p : playerList)
			p.setCharacter(new RegularGuy());
		
	}
	
	public static void round_begin(){
		roundSummary.clear();
	}
	
	public static void round_end() {
		int max=0;
		int maxPlayer = -1;
		String ret = "";
		for(int player_id : roundSummary.keySet()){
			
			int value = roundSummary.get(player_id).value;
			
			ret += "Player "+player_id+" : "+value+", ";
			if(value>max){
				max = value;
				maxPlayer = player_id;
			}
		}
		broadcast("Result:\n "+ret);
		if( maxPlayer==-1){
			broadcast("No winner this turn");
		}
		else{
			broadcast("Player " + maxPlayer +" win!");
		}
	}
	
	public static void turn(Player player){
		
		drawCard(player,1);
		sendToOnePlayer(player, GameGeneralInfo());
		CardBase c = putCard(player);
		droppedCardStack.add(c);
		
	}
	// compiling result end
	
public static void main(String[] args){
		
		//int init_HP = 1;
		
		playerList = new ArrayList<Player>();
		map = new HashMap<Integer, Player>();
		cardStack = new LinkedList<CardBase>();
		droppedCardStack = new LinkedList<CardBase>();
		
		GameServer = new Server(port);
		
		int count = 0;
		//activePlayer = 1;
		while(count++ < num_of_players){
			
			Player p = new Player(count, GameServer);
			
			playerList.add(p);
			p.conn.sendBroadcast("You are Player"+count);
			System.out.println("one player("+count+") joined");
		}
		
		for(Player p : playerList){
			map.put(p.id, p);
		}
		
		System.out.println("Game Start!");
		GameServer.broadcast(game_name + ": Game Start!");
		
		gameover = false;
		
		init();
		for(Player p : playerList){
			if(p.character==null){
				gameover = true;
				broadcast("Player "+p.id+" is not assigned a character after initialization. The game can not go on.");
			}
		}
		
		currentPlayerIndex=0;	
		
		round_begin();
		
		while(!gameover)
			nextOnlinePlayer();
		
		close();	
		
	}
	
	public static void nextOnlinePlayer(){
		if(currentPlayerIndex == num_of_players){
			round_end();
			
			if(++roundCount == maximum_round){
				gameover = true;
				return ;
			}
			round_begin();
		}
		
		currentPlayerIndex = (currentPlayerIndex+1)%num_of_players;
		currentPlayerIndex = currentPlayerIndex==0?num_of_players:currentPlayerIndex;
		
		GameServer.broadcast("Now Turn: Player"+currentPlayerIndex);
		
		Player playerInTurn = map.get(currentPlayerIndex);
		if(playerInTurn.isOnline())
			turn(playerInTurn);
		
	}
	

	public static void shuffle(List<?> list){
		Collections.shuffle(list);
	}
	
	public static void sendToOnePlayer(Player player, String msg){
		player.conn.sendBroadcast(msg);
	}
	
	public static void broadcast(String msg){
		GameServer.broadcast(msg);
	}
	
	public static void close() {
		GameServer.broadcast("Game Over!");
		GameServer.closeServer();
	}
	
	public static int waitForChoice(Player player, String promt, int range){
		player.conn.sendBroadcast("#If you really do not want to make this choice, type \"cancel\".#\n"+promt);
		String input = null;
		while(player.isOnline() && range>0){
			try{
				input = player.conn.waitForInput("Please choose from [" + 1 + "~" + range + "]");
				if("cancel".equals(input)){
					return -1;
				}
				int choice = Integer.parseInt(input);
				if(choice <= range && choice > 0)
					return choice;
			} catch ( NumberFormatException e ){
				player.conn.sendBroadcast("Please type a number, choosing from [" + 1 + "~" + range + "]");
			}
			
		}
		return -1;
	}
	
	public static Player waitForTarget(Player player){
		String promt = "List of players( choose with the indexes ): " + PlayersInfo();
		int range = playerList.size();
		int choice = waitForChoice(player, promt, range);
		if(choice != -1)
			return playerList.get(choice-1);
		return null;
	}
	
	public static CardBase putCard(Player player) {
		String promt = "Your hand cards( choose with the indexes ): " + HandCardInfo(player);
		int range = player.handCards.size();
		int choice = waitForChoice(player, promt, range);
		if(choice != -1){
			CardBase c = player.handCards.remove(choice-1);
			roundSummary.put(player.id, c);
			return c;
		}
		return null;
		
	}
	
	public static void drawCard(Player p, int num) {
		
		for(int i=0;i<num;i++){

			if(cardStack.isEmpty()){
				if(droppedCardStack.isEmpty()){
					gameover = true;
					GameServer.broadcast("Sorry, the card stack is empty.. The game can not carry on");
				}
				else{
					LinkedList<CardBase> tmp = cardStack;
					cardStack = droppedCardStack;
					droppedCardStack = tmp;
					Collections.shuffle(cardStack);
				}		
			}
			p.handCards.add(cardStack.poll());
			
		}
	}
	
	public static String PlayersInfo(){
		StringBuilder sb = new StringBuilder("\n--------------");
		int i = 1;
		for(Player p : playerList){
			sb.append("\n"+i+")\n"+p.toString());
			sb.append("\n--------------");
			i++;
		}
		return sb.toString();
	}
	
	public static String HandCardInfo(Player player){
		StringBuilder sb = new StringBuilder("\n--------------");
		int i = 1;
		for(CardBase card : player.handCards){
			sb.append("\n"+i+")\n");
			sb.append(card.toString());
			sb.append("\n--------------");
			i++;
		}
		return sb.toString();
	}

	
	public static String GameGeneralInfo(){
		StringBuilder sb = new StringBuilder();
		int length = game_name.length();
		sb.append('+');
		for(int i=0;i<length;i++)
			sb.append('-');
		sb.append("+\n|");
		sb.append(game_name);
		sb.append("|\n+");
		for(int i=0;i<length;i++)
			sb.append('-');
		sb.append("+");
		sb.append("\nThe player in turn: Player " + currentPlayerIndex);
		sb.append("\nList of players: ");
		sb.append(PlayersInfo());
		return sb.toString();
	}
	
}
