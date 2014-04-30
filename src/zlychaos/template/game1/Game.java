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
	
	public ICard putCard(Player player) throws IOException {
		player.conn.sendBroadcast("Your hand cards: " + HandCardInfo(player));
		
		String input = null;
		ICard c = null;
		while(player.handCards.size() > 0 && player.isOnline()){
			try{
				input = player.conn.waitForInput("Please deal a card( type the index of card [" + 1 + "~" + player.handCards.size() + "])");
				int card_index = Integer.parseInt(input);
				if(card_index <= player.handCards.size() && card_index > 0){
					c = player.handCards.remove(card_index-1);
					return c;
				}
			} catch ( NumberFormatException e ){
				player.conn.sendBroadcast("Please type the index of card [" + 1 + "~" + player.handCards.size() + "]");
			}
		}
		return null;
		
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
		
		for(Player p : playerList)
			p.setCharacter(new RegularGuyCharacter());
		
		retEachRound = new HashMap<Integer, ICard>();
	}
	
	public void round_begin(){
		retEachRound.clear();
	}
	
	public void round_end() throws Exception{
		winCond();
	}
	
	public void playerTurnPrepare(Player player) throws IOException{
		drawCard(player,1);
	}
	
	public void playerTurn(Player player) throws IOException{
		playerTurnPrepare(player);
		player.conn.sendBroadcast(GameGeneralInfo());
		
		String input = player.conn.waitForInput("Use a skill( choose from "+player.character.getSkillList() + ", or put a card( Type: 'card' )");
		if("card".equals(input)){
			ICard c = putCard(player);
			retEachRound.put(player.id, c);
			droppedCardStack.add(c);
		}
		else{
			player.character.skill(player, input);
		}
	}
	
	public void close() throws IOException{
		GameServer.broadcast("Game Over!");
		GameServer.closeServer();
	}
	
	
	public void gamestart() throws Exception{
		
		//int init_HP = 1;
		round = 2;
		
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
//		boolean isTie = true;
//		for(int id:retEachRound.keySet()){
//			
//			int value = ((Card) retEachRound.get(id)).value;
//			
//			if(value!=max){
//				isTie = false;
//				break;
//			}
//		}
		GameServer.broadcast("Result:\n "+ret);
//		if(isTie){
//			GameServer.broadcast("This Round is tie");
//		}else 
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
		
		GameServer.broadcast("Now Turn: Player"+currentPlayerIndex);
		
		Player playerInTurn = map.get(currentPlayerIndex);
		if(playerInTurn.isOnline())
			playerTurn(playerInTurn);
		
	}
	
	public String PlayersInfo(){
		StringBuilder sb = new StringBuilder("\n--------------");
		for(Player p : playerList){
			sb.append("\n"+p.toString());
			sb.append("\n--------------");
		}
		return sb.toString();
	}
	
	public String HandCardInfo(Player player){
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
	
	public String GameGeneralInfo(){
		StringBuilder sb = new StringBuilder();
		int length = name.length();
		sb.append('+');
		for(int i=0;i<length;i++)
			sb.append('-');
		sb.append("+\n|");
		sb.append(name);
		sb.append("|\n+");
		for(int i=0;i<length;i++)
			sb.append('-');
		sb.append("+");
		sb.append("\nThe player in turn: Player " + currentPlayerIndex);
		sb.append("\nList of players: ");
		sb.append(PlayersInfo());
		return sb.toString();
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		Game game = new Game();
		game.gamestart();
	}

}
