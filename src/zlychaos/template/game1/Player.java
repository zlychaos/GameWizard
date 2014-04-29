package zlychaos.template.game1;

import zhllz.gamewizard.basic.PlayerBase;
import zhllz.gamewizard.communication.Server;

public class Player extends PlayerBase{
	//public int HP;
	
	public Player(int id, Server server){
		super(id, server);
		//this.HP = HP;
	}
	
	@Override
	public String getResponse(String request) {
		if(MsgType.QUIT.equals(request)){
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
	
	

}
