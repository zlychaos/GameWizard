package compile.helloworld.characters;

import zhllz.gamewizard.basic.CharacterBase;
import zhllz.gamewizard.basic.PlayerBase;

public class No_Val extends CharacterBase {

	public int hp=3

	
	public RegularGuy(){
		skillList = new String[0];
		
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
	public boolean skill(PlayerBase p, String skillName) {
		
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
