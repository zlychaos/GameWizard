package compile.helloworld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import compile.helloworld.cards.*;
import compile.helloworld.characters.*;

import zhllz.gamewizard.basic.ICard;import zhllz.gamewizard.basic.Player;
import zhllz.gamewizard.communication.Server;

public class Game {
	
	public static Server GameServer;
	public static int port = 4119;
	
	public static ArrayList<Player> playerList;
	public static HashMap<Integer, Player> map;
	public static LinkedList<ICard> cardStack;
	public static LinkedList<ICard> droppedCardStack;
	
	public static int currentPlayerIndex;
	
	public static int roundCount=0;
	
	public static boolean gameover;
	
	public static HashMap<Integer, ICard> roundSummary = new HashMap<Integer, ICard>();
public static String game_name = "Hello World";
public static int num_of_players = 2;
public static int maximum_round = 2;
public static void init(){
Integer i=0;
while(i<10)
{
cardStack.add(new CardOne());
cardStack.add(new CardTwo());
cardStack.add(new CardThree());
i=i+1;
}
shuffle(cardStack);
for(Player  player:playerList)
{
player.setCharacter(new RegularGuy());
}

}
public static void round_begin(){

}
public static void turn(Player player) throws IOException{drawCard(player,1);
sendToOnePlayer(player,GameGeneralInfo());
ICard card = putCard(player);
droppedCardStack.add(card);

}
public static void round_end() throws Exception{Integer max=0;
Integer maxPlayer=-1;
String ret="";
for(Integer  player_id:roundSummary.keySet())
{
ICard card = roundSummary.get(player_id);
Integer  tmp = null;
if( card instanceof CardOne)
	tmp = ((CardOne)card).value;
if( card instanceof CardTwo)
	tmp = ((CardTwo)card).value;
if( card instanceof CardThree)
	tmp = ((CardThree)card).value;
Integer value = tmp;
ret=ret+"Player "+player_id+" : "+value+", ";
if(value>max)
{
max=value;
maxPlayer=player_id;
}
}
broadcast("Result:\n"+ret);
if(maxPlayer==-1)
{
broadcast("No winner this turn");
}

else
{
broadcast("Player "+maxPlayer+" win!");
}

}
public static void dying(){

}
public static void shuffle(List<?> list){
		Collections.shuffle(list);
	}
	
	public static void sendToOnePlayer(Player player, String msg) throws IOException{
		player.conn.sendBroadcast(msg);
	}
	
	public static void broadcast(String msg) throws IOException{
		GameServer.broadcast(msg);
	}
	
	public static void close() throws IOException{
		GameServer.broadcast("Game Over!");
		GameServer.closeServer();
	}
	
	
	public static void main(String[] args) throws Exception{
		
		//int init_HP = 1;
		
		playerList = new ArrayList<Player>();
		map = new HashMap<Integer, Player>();
		cardStack = new LinkedList<ICard>();
		droppedCardStack = new LinkedList<ICard>();
		
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
		
		init();
		
		currentPlayerIndex=0;	
		
		round_begin();
		gameover = false;
		while(!gameover)
			nextOnlinePlayer();
		
		close();	
		
	}
	
	public static ICard putCard(Player player) throws IOException {
		player.conn.sendBroadcast("Your hand cards: " + HandCardInfo(player));
		
		String input = null;
		ICard c = null;
		while(player.handCards.size() > 0 && player.isOnline()){
			try{
				input = player.conn.waitForInput("Please deal a card( type the index of card [" + 1 + "~" + player.handCards.size() + "])");
				int card_index = Integer.parseInt(input);
				if(card_index <= player.handCards.size() && card_index > 0){
					c = player.handCards.remove(card_index-1);
					
					roundSummary.put(player.id, c);
					
					return c;
				}
			} catch ( NumberFormatException e ){
				player.conn.sendBroadcast("Please type the index of card [" + 1 + "~" + player.handCards.size() + "]");
			}
		}
		return null;
		
	}
	
	public static void drawCard(Player p, int num) throws IOException{
		
		for(int i=0;i<num;i++){

			if(cardStack.isEmpty()){
				if(droppedCardStack.isEmpty()){
					gameover = true;
					GameServer.broadcast("Sorry, the card stack is empty.. The game can not carry on");
				}
				else{
					LinkedList<ICard> tmp = cardStack;
					cardStack = droppedCardStack;
					droppedCardStack = tmp;
					Collections.shuffle(cardStack);
				}		
			}
			p.handCards.add(cardStack.poll());
			
		}
	}

	
	public static void nextOnlinePlayer() throws Exception{
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
	
	public static String PlayersInfo(){
		StringBuilder sb = new StringBuilder("\n--------------");
		for(Player p : playerList){
			sb.append("\n"+p.toString());
			sb.append("\n--------------");
		}
		return sb.toString();
	}
	
	public static String HandCardInfo(Player player){
		StringBuilder sb = new StringBuilder("\n--------------");
		int i = 1;
		for(ICard card : player.handCards){
			sb.append("\n("+i+")");
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
