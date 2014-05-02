package compile.helloworld.characters;

import zhllz.gamewizard.basic.CharacterBase;
import zhllz.gamewizard.basic.Player;

public class RegularGuy extends CharacterBase {

	public int HP = 3;
	
	public RegularGuy(){
		skillList = new String[0];
	}
	
	@Override
	public String getName() {
		return "Regular Guy";
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
		sb.append("\tHP=");
		sb.append(HP);
		//sb.append("\n\tkey=");
		//sb.append(value);
		return sb.toString();
	}

}
