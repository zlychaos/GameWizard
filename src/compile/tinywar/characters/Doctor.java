package compile.tinywar.characters;

import compile.tinywar.*;

public class Doctor extends CharacterBase{
	public Doctor(){
		HP = 2;
		skillList = new String[1];
		skillList[0] = "Heal";
	}
	
	@Override
	public String getName() {
		return "Doctor";
	}
	
	@Override
	public String getDiscription() {
		return "";
	}

	@Override
	public boolean skill(Player dealer, String skillName) {
		if("Heal".equals(skillName)){
			Player target = Game.waitForTarget(dealer);
			target.character.HP = target.character.HP+2;
			return true;
		}
		return false;
	}	
}
