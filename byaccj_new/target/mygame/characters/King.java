package mygame.characters;

import mygame.*;

public class King extends CharacterBase{
	public King(){
				hp=5;
		name="my king";

		skillList = new String[0];
		
		//skillList[0] = "Heal";
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
	public boolean skill(Player dealer, String skillName) {
		
		return false;
	}	
}
