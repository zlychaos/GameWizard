package mygame.characters;

import zhllz.gamewizard.basic.CharacterBase;
import zhllz.gamewizard.basic.Player;

public class King extends CharacterBase {

	public int  hp=5
	public String name="my king"

	
	public RegularGuy(){
		skillList = new String[0];
		
	}
	
	@Override
	public String getName() {
		return "King";
	}
	
	@Override
	public String getDiscription() {
		return "";
	}

	@Override
	public boolean skill(Player p, String skillName) {
		
		return false;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\thp=");
		sb.append(hp);
		sb.append("\n");sb.append("\tname=");
		sb.append(name);
		sb.append("\n");
		return sb.toString();
	}

}
