package zlychaos.template.game1;

import zhllz.gamewizard.basic.CharacterBase;
import zhllz.gamewizard.basic.PlayerBase;

public class RegularGuyCharacter extends CharacterBase {

	public int HP = 3;
	
	public RegularGuyCharacter(){
		skillList = new String[1];
		skillList[0] = "NoEffect";
	}
	
	@Override
	public String getName() {
		return "Regular Guy";
	}
	
	@Override
	public String getDiscription() {
		return "Regular Guy: The dumbest guy in this world. Its skill 'NoEffect' has no effect";
	}

	@Override
	public boolean skill(PlayerBase p, String skillName) {
		if("NoEffect".equals(skillName)){
			this.HP = this.HP + 1 - 1;
			return true;
		}
		else{
			return false;
		}
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
