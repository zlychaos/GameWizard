package ke.gw.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import zhllz.gamewizard.communication.*;
import zhllz.gamewizard.basic.*;

public class Game {
	private static ArrayList<Player> list_players;
	private static HashMap<Integer, Player> map;
	
	private static LinkedList<Card> cardStack;
	private static Server GameServer;
	private static int activePlayer;
	private static int num_player;
	private static int round; 
	private static int roundCount=0;
	
	private static HashMap<Integer, Card> retEachRound;
	
	public static void main(String[] args) throws Exception{
		int port = 4119;
		num_player = 2;
		int init_HP = 1;
		round = 1;
		
		list_players = new ArrayList<Player>();
		map = new HashMap<Integer, Player>();
		cardStack = new LinkedList<Card>();
		
		initCardStack();
		
		GameServer = new Server(port);
		
		int count = 0;
		//activePlayer = 1;
		while(count++ < num_player){
			ServerConnection sconn = GameServer.waitForPlayer();
			
			Player p = new Player(count, init_HP, sconn);
			
			list_players.add(p);
			sconn.sendBroadcast("You are Player"+count);
			System.out.println("one player("+count+") joined");
		}
		
		for(Player p : list_players){
			map.put(p.id, p);
		}
		
		System.out.println("Game Start!");
		broadCastEveryOne("Game Start!");
		
		for(Player p : list_players){
			new Thread(new WorkForOnePlayer(p)).start();
		}
		
		Thread.sleep(200);
		
		activePlayer=0;
		nextOnlinePlayer();
		retEachRound = new HashMap<Integer, Card>();
	}
	
	public static void broadCastEveryOne(String msg) throws Exception{
		for(Player p : list_players){
			if(p.isOnline)
				p.conn.sendBroadcast(msg);
		}
	}
	
	public static void closeOnePlayer(Player p) throws Exception{
		p.conn.closeMyself();
	}
	
	public static void closeAllPlayers() throws Exception{
		for(Player p : list_players){
			p.conn.closeMyself();
		}
	}
	
	public static void closeGame() throws IOException{
		System.out.println("Game Over!!");
		//System.out.println(GameServer.waiting_server);
		GameServer.closeServer();
	}
	
	public static void checkForCloseGame() throws IOException{
		System.out.println("current # of players: "+list_players.size());
		boolean shouldEnd = true;
		for(Player p:list_players){
			if(p.isOnline){
				shouldEnd = false;
				break;
			}
		}
		if(shouldEnd){
			closeGame();
		}
	}
	
	public static void closeForce() throws Exception{
		for(Player p : list_players){
			if(p.isOnline){
				System.out.println(p.id+" "+"sent Over");
				p.conn.sendBroadcast("GameOver");
				//p.conn.closeMyself();
			}
		}
		closeGame();
	}
	
	public static void nextOnlinePlayer() throws Exception{
		if(activePlayer == num_player){
			//do round end here
			winCond();
			retEachRound.clear();
			if(++roundCount == round){
				closeForce();
				return ;
			}
			
			//do round start here
			
		}
		
		int num = num_player;
		activePlayer = (activePlayer+1)%num;
		activePlayer = activePlayer==0?num:activePlayer;
		
		for(int i=0; i<=num; i++){
			if(!map.get(activePlayer).isOnline){
				activePlayer = (activePlayer+1)%num;
				activePlayer = activePlayer==0?num:activePlayer;
			}else{
				break;
			}
		}
		
		
		broadCastEveryOne("Now Turn: Player"+activePlayer);
		
		Player playerInTurn = map.get(activePlayer);
		//do card distribution here
		playerInTurn.handCards.add(cardStack.poll());
		sendHandCardsInfo(playerInTurn);
		
		
	}
	
	public static String sendHandCardsInfo(Player p) throws IOException{
		if(!p.isOnline) return "";
		String others = "Other Players handCard num:\n";
		for(Player player : list_players){
			if(player.id != p.id){
				others+="Player"+player.id+": "+player.handCards.size()+"\n";
			}
		}
		String ret = "Your handCards: ";
		for(int i=0; i<p.handCards.size(); i++){
			Card c = p.handCards.get(i);
			int pos = i+1;
			ret += c.value+"("+pos+"), ";
		}
		p.conn.sendBroadcast(others+ret);
		return ret;
	}
	
	public static void initCardStack(){
		for(int i=0; i<10; i++){
			cardStack.add(new Card(1));
			cardStack.add(new Card(2));
			cardStack.add(new Card(3));
		}
		Collections.shuffle(cardStack);
	}
	
	public static boolean isValid(Player p, String input) {
		if("end".equals(input)||"quit".equals(input)||"skill".equals(input)){
			return true;
		}
	    try { 
	        Integer.parseInt(input); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }

	    int cardnum = Integer.parseInt(input);
	    if(cardnum>0 && cardnum<=p.handCards.size()){
	    	return true;
	    }
	    return false;
	}
	
	static class WorkForOnePlayer implements Runnable{
		Player player;
		public WorkForOnePlayer(Player p){
			this.player=p;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
				while(true){
					String player_input = player.conn.waitForInput("Type input: (num/skill/end/quit)");
					if(!isValid(this.player, player_input)){
						player.conn.sendBroadcast("inValid input!");
						continue;
					}
					if(player_input.equals("quit")){
						this.player.isOnline = false;
						if(this.player.id==activePlayer)
							nextOnlinePlayer();
						break;
					}
					if(player.id != activePlayer){
						
						player.conn.sendBroadcast("not your turn!");
					}else{
						//do the relevant action. 
						if("end".equals(player_input)){
							nextOnlinePlayer();
						}else{
							if("skill".equals(player_input)){
								player.conn.sendBroadcast("Player"+player.id+": "+player_input);
							}else{
								int cardnum = Integer.parseInt(player_input);
								putCard(this.player, cardnum);
								sendHandCardsInfo(this.player);
							}
						}
					}
					
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					this.player.isOnline = false;
					broadCastEveryOne("Player"+this.player.id+" has quit!");
					player.conn.closeMyself();
					checkForCloseGame();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void putCard(Player p, int num) throws Exception{
		Card c = p.handCards.remove(num-1);
		//broadCastEveryOne("Move: Player"+p.id+" puts "+c.value);
		retEachRound.put(p.id, c);
	}
	
	public static void winCond() throws Exception{
		int max=0;
		int maxID = 1;
		String ret = "";
		for(int id : retEachRound.keySet()){
			ret += "Player"+id+":"+retEachRound.get(id).value+", ";
			if(retEachRound.get(id).value>max){
				max = retEachRound.get(id).value;
				maxID = id;
			}
		}
		boolean should_even = true;
		for(int id:retEachRound.keySet()){
			if(retEachRound.get(id).value!=max){
				should_even = false;
				break;
			}
		}
		broadCastEveryOne("Result:\n "+ret);
		if(should_even){
			broadCastEveryOne("This Round is even");
		}else 
			broadCastEveryOne("Player"+maxID+" win!");
		
	}
}
