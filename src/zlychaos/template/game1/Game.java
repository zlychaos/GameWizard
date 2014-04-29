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
	
	private String name = "Whose number is bigger";
	private int num_of_players = 2;
	
	private ArrayList<Player> playerList;
	private HashMap<Integer, Player> map;
	private LinkedList<ICard> cardStack;
	private LinkedList<ICard> droppedCardStack;
	
	private int currentPlayerIndex;
	
	private int round; 
	private int roundCount=0;
	
	private boolean gameover;
	
	private HashMap<Integer, ICard> retEachRound;
	
	public ICard putCard(Player p) throws IOException {
		String input = p.conn.waitForInput("Please deal a card");
		int card_index = Integer.parseInt(input);
		ICard c = p.handCards.remove(card_index-1);
		return c;
	}
	
	public void drawCard(Player p, int num) throws IOException{
		
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
	
	public void init(){
		for(int i=0; i<10; i++){
			cardStack.add(new Card(1));
			cardStack.add(new Card(2));
			cardStack.add(new Card(3));
		}
		Collections.shuffle(cardStack);
		
		retEachRound = new HashMap<Integer, ICard>();
	}
	
	public void round_begin(){
		retEachRound.clear();
	}
	
	public void round_end() throws Exception{
		winCond();
	}
	
	public void playerTurn(Player player) throws IOException{
		drawCard(player,1);
		player.conn.sendBroadcast(PlayersInfo());
		player.conn.sendBroadcast(HandCardInfo(player));
		
		ICard c = putCard(player);
		retEachRound.put(player.id, c);
		droppedCardStack.add(c);
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
		droppedCardStack = new LinkedList<ICard>();
		
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
		GameServer.broadcast(name + ": Game Start!");
		
		init();
		
		currentPlayerIndex=0;	
		
		round_begin();
		gameover = false;
		while(!gameover)
			nextOnlinePlayer();
		
		close();	
		
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
	
	public  void nextOnlinePlayer() throws Exception{
		if(currentPlayerIndex == num_of_players){
			round_end();
			
			if(++roundCount == round){
				gameover = true;
				return ;
			}
			round_begin();
		}
		
		currentPlayerIndex = (currentPlayerIndex+1)%num_of_players;
		currentPlayerIndex = currentPlayerIndex==0?num_of_players:currentPlayerIndex;
		
		for(int i=0; i<=num_of_players; i++){
			if(!map.get(currentPlayerIndex).isOnline()){
				currentPlayerIndex = (currentPlayerIndex+1)%num_of_players;
				currentPlayerIndex = currentPlayerIndex==0?num_of_players:currentPlayerIndex;
			}else{
				break;
			}
		}
		
		
		GameServer.broadcast("Now Turn: Player"+currentPlayerIndex);
		
		Player playerInTurn = map.get(currentPlayerIndex);
		//do card distribution here
		playerTurn(playerInTurn);
		
	}
	
	// This info is for the everyone
	public String PlayersInfo(){
		StringBuilder sb = new StringBuilder("\n--------------\n");
		for(Player p : playerList){
			sb.append(p.toString());
			sb.append("\n--------------");
		}
		return sb.toString();
	}
	
	public String HandCardInfo(Player player){
		StringBuilder sb = new StringBuilder("\n--------------\n");
		int i = 1;
		for(ICard card : player.handCards){
			sb.append("("+i+")");
			sb.append(card.toString());
			sb.append("\n--------------");
			i++;
		}
		return sb.toString();
	}
	
	public String GameGeneralInfo(){
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("\nThe player in turn: Player " + currentPlayerIndex);
		return sb.toString();
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		Game game = new Game();
		game.gamestart();
	}

}
