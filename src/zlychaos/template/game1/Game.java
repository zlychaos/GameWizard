package zlychaos.template.game1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.communication.Server;

public class Game {
	
	private Server GameServer;
	private int port = 4119;
	private int num_of_players = 2;
	
	private ArrayList<Player> playerList;
	private HashMap<Integer, Player> map;
	private LinkedList<ICard> cardStack;
	
	private int activePlayer;
	
	private int round; 
	private int roundCount=0;
	
	private boolean gameover;
	
	private HashMap<Integer, ICard> retEachRound;
	
	public void init(){
		
	}
	
	public void close() throws IOException{
		GameServer.broadcast("Game Over!");
		GameServer.closeServer();
	}
	
	
	public void gamestart() throws Exception{
		
		int init_HP = 1;
		round = 2;
		
		playerList = new ArrayList<Player>();
		map = new HashMap<Integer, Player>();
		cardStack = new LinkedList<ICard>();
		
		GameServer = new Server(port);
		
		int count = 0;
		//activePlayer = 1;
		while(count++ < num_of_players){
			
			Player p = new Player(count, init_HP, GameServer);
			
			playerList.add(p);
			p.conn.sendBroadcast("You are Player"+count);
			System.out.println("one player("+count+") joined");
		}
		
		for(Player p : playerList){
			map.put(p.id, p);
		}
		
		System.out.println("Game Start!");
		GameServer.broadcast("Game Start!");
		
		initCardStack();
		
		activePlayer=0;
		retEachRound = new HashMap<Integer, ICard>();
		gameover = false;
		while(!gameover)
			nextOnlinePlayer();
		
		close();	
		
	}
	
	public  void nextOnlinePlayer() throws Exception{
		if(activePlayer == num_of_players){
			//do round end here
			winCond();
			retEachRound.clear();
			if(++roundCount == round){
				gameover = true;
				return ;
			}
			
		}
		
		activePlayer = (activePlayer+1)%num_of_players;
		activePlayer = activePlayer==0?num_of_players:activePlayer;
		
		for(int i=0; i<=num_of_players; i++){
			if(!map.get(activePlayer).isOnline){
				activePlayer = (activePlayer+1)%num_of_players;
				activePlayer = activePlayer==0?num_of_players:activePlayer;
			}else{
				break;
			}
		}
		
		
		GameServer.broadcast("Now Turn: Player"+activePlayer);
		
		Player playerInTurn = map.get(activePlayer);
		//do card distribution here
		playerInTurn.handCards.add(cardStack.poll());
		sendHandCardsInfo(playerInTurn);
		String input = playerInTurn.conn.waitForInput("Please deal a card");
		
		int card_index = Integer.parseInt(input);
		putCard(playerInTurn, card_index);
		
	}
	
	
	
	public  String sendHandCardsInfo(Player p) throws IOException{
		//if(!p.isOnline) return "";
		String others = "Other Players handCard num:\n";
		for(Player player : playerList){
			if(player.id != p.id){
				others+="Player "+player.id+": "+player.handCards.size()+"\n";
			}
		}
		String ret = "Your handCards: ";
		for(int i=0; i<p.handCards.size(); i++){
			Card c = (Card) p.handCards.get(i);
			if(c==null){
				System.out.println("i: "+i);
				System.out.println(p.handCards.size());
				System.out.println("-----------------");
			}
				
			int pos = i+1;
			ret += c.value+"("+pos+"), ";
		}
		p.conn.sendBroadcast(others+ret);
		return ret;
	}
	
	public  void initCardStack(){
		for(int i=0; i<10; i++){
			cardStack.add(new Card(1));
			cardStack.add(new Card(2));
			cardStack.add(new Card(3));
		}
		Collections.shuffle(cardStack);
	}
	
	public void putCard(Player p, int num) throws Exception{
		ICard c = p.handCards.remove(num-1);
		//broadCastEveryOne("Move: Player"+p.id+" puts "+c.value);
		retEachRound.put(p.id, c);
	}
	
	public void winCond() throws Exception{
		int max=0;
		int maxID = 1;
		String ret = "";
		for(int id : retEachRound.keySet()){
			
			int value = ((Card) retEachRound.get(id)).value;
			
			ret += "Player"+id+":"+value+", ";
			if(value>max){
				max = value;
				maxID = id;
			}
		}
		boolean should_even = true;
		for(int id:retEachRound.keySet()){
			
			int value = ((Card) retEachRound.get(id)).value;
			
			if(value!=max){
				should_even = false;
				break;
			}
		}
		GameServer.broadcast("Result:\n "+ret);
		if(should_even){
			GameServer.broadcast("This Round is even");
		}else 
			GameServer.broadcast("Player"+maxID+" win!");
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		Game game = new Game();
		game.gamestart();
	}

}
