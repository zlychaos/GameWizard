package mygame.characters;

import mygame.*;

public class No_Val extends CharacterBase{
	public No_Val(){
				hp=3;
		name="sdfas";

		skillList = new String[1];
				skillList[0] = "mercy";

		//skillList[0] = "Heal";
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
	public boolean skill(Player dealer, String skillName) {
				if("mercy".equals(skillName)){
int b = 0;
String strg = "sdf";
if(0==b){
b = 1;
}

		}

		return false;
	}	
}
