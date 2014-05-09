package compile.mygame.characters;

import compile.mygame.*;
import compile.mygame.cards.*;
public class Doctor extends CharacterBase{
public Doctor(){
		skillList = new String[1];
skillList[0] = "Heal";
HP=2;
}

	@Override
	public String getDiscription() {
		return "";
	}
@Override
	public boolean skill(Player dealer, String skillName) {
if("Heal".equals(skillName)){
Player target=Game.waitForTarget(dealer);
if(target==null)
{
return false;
}
target.character.HP=target.character.HP+2;
return true;
		}
return false;
	}
@Override
	public String getName() {
		return "Doctor";
	}
}