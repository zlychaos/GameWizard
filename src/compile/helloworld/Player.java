package compile.helloworld;

import java.util.ArrayList;

import zhllz.gamewizard.communication.ICommunicatable;
import zhllz.gamewizard.communication.Server;
import zhllz.gamewizard.communication.ServerConnection;
import zhllz.gamewizard.communication.StrController;

public class Player implements ICommunicatable {
	
	public int id;
	public ArrayList<CardBase> handCards;
	
	public ServerConnection conn;
	public boolean online;
	
	public CharacterBase character;
	
	public Player(int id, Server server){
		
		this.id = id;
		this.handCards = new ArrayList<CardBase>();
		this.conn = server.waitForPlayer(this);;
		this.online = true;
	}
	
	public void setCharacter(CharacterBase character) {
		this.character = character;
	}
	
	@Override
	public String getResponse(String request) {
		if(StrController.QUIT.equals(request)){
			online = false;
			conn.closeConnection();
		}
		else{
			return "Not your turn..";
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Player ");
		sb.append(id);
		sb.append("( "+character.getName());
		if(!isOnline()){
			sb.append(", off-line");
		}
		sb.append(" ):{\n");
		sb.append(character.toString());
		sb.append("\n\tNumber of handCards=");
		sb.append(handCards.size());
		sb.append("\n}");
		return sb.toString();
	}

	@Override
	public void getOffLine() {
		online = false;
	}
	

	@Override
	public boolean isOnline(){
		return online;
	}

}
