package compile.mygame.characters;

import compile.mygame.*;
import compile.mygame.cards.*;
public class Vampire extends CharacterBase{
public Vampire(){
		skillList = new String[1];
skillList[0] = "Leech";
HP=3;
}

	@Override
	public String getDiscription() {
		return "";
	}
@Override
	public boolean skill(Player dealer, String skillName) {
if("Leech".equals(skillName)){
Player target=Game.waitForTarget(dealer);
if(target==null)
{
return false;
}
target.character.HP=target.character.HP-1;
if(target.character.HP==0)
{
Game.dying();
}
dealer.character.HP=dealer.character.HP+1;
return true;
		}
return false;
	}
@Override
	public String getName() {
		return "Vampire";
	}
}