package mygame.characters;

import zhllz.gamewizard.basic.CharacterBase;
import zhllz.gamewizard.basic.Player;

public class No_Val extends CharacterBase {

	public int  hp=3

	
	public RegularGuy(){
		skillList = new String[1];
				skillList[0] = "mercy";

	}
	
	@Override
	public String getName() {
		return "No_Val";
	}
	
	@Override
	public String getDiscription() {
		return "";
	}

	@Override
	public boolean skill(Player p, String skillName) {
				if("mercy".equals(skillName){
dosomethingherereturn true;
		}

		return false;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\thp=");
		sb.append(hp);
		sb.append("\n");
		return sb.toString();
	}

}
