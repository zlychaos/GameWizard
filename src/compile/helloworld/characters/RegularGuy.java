package compile.helloworld.characters;

import compile.helloworld.*;

public class RegularGuy extends CharacterBase {
	
	public RegularGuy(){
		HP = 3;
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
	
	

}
