package compile.tinywar.characters;

import compile.tinywar.*;

public class Vampire extends CharacterBase{
	public Vampire(){
		HP = 3;
		skillList = new String[1];
		skillList[0] = "Leech";
	}
	
	@Override
	public String getName() {
		return "Vampire";
	}
	
	@Override
	public String getDiscription() {
		return "";
	}

	@Override
	public boolean skill(Player dealer, String skillName) {
		if("Leech".equals(skillName)){
			Player target = Game.waitForTarget(dealer);
			if(target == null){
				return false;
			}
			target.character.HP = target.character.HP-1;
			if(target.character.HP == 0){
				Game.dying();
			}
			dealer.character.HP = dealer.character.HP+1;
			return true;
		}
		return false;
	}
	
}
