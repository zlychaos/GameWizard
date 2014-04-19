package zhllz.gamewizard.basic;

import java.util.ArrayList;

import zhllz.gamewizard.communication.ServerConnection;


public class Player {
	public int id;
	public int HP;
	public ArrayList<Card> handCards;
	public ServerConnection conn;
	
	public boolean isOnline;
	
	public Player(int id, int HP, ServerConnection conn){
		this.id = id;
		this.HP = HP;
		this.handCards = new ArrayList<Card>();
		this.conn = conn;
		
		this.isOnline = true;
	}
}
