package zhllz.gamewizard.basic;

import java.util.ArrayList;

import zhllz.gamewizard.communication.ICommunicatable;
import zhllz.gamewizard.communication.Server;
import zhllz.gamewizard.communication.ServerConnection;

public abstract class PlayerBase implements ICommunicatable {
	
	public int id;
	public ArrayList<ICard> handCards;
	
	public ServerConnection conn;
	public boolean online;
	
	public PlayerBase(int id, Server server){
		
		this.id = id;
		this.handCards = new ArrayList<ICard>();
		this.conn = server.waitForPlayer(this);;
		this.online = true;
	}
	
	@Override
	public boolean isOnline(){
		return online;
	}

}
