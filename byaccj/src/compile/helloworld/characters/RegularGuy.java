package compile.helloworld.characters;

import zhllz.gamewizard.basic.CharacterBase;
import zhllz.gamewizard.basic.Player;
public class RegularGuy extends CharacterBase{
public Integer HP=1;
public RegularGuy(){
		skillList = new String[0];
}

	@Override
	public String getDiscription() {
		return "";
	}
@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
sb.append("\tHP=");
		sb.append(HP);
return sb.toString();
	}
@Override
	public boolean skill(Player p, String skillName) {
return false;
	}
@Override
	public String getName() {
		return "RegularGuy";
	}
}