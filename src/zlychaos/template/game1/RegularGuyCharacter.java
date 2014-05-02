package zlychaos.template.game1;

import zhllz.gamewizard.basic.CharacterBase;
import zhllz.gamewizard.basic.Player;

public class RegularGuyCharacter extends CharacterBase {

	public int HP = 3;
	
	public RegularGuyCharacter(){
		skillList = new String[2];
		skillList[0] = "Skill1";
		skillList[1] = "Skill2";
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
	public boolean skill(Player p, String skillName) {
		if("Skill1".equals(skillName)){
			this.HP = this.HP + 1 - 1;
			return true;
		}
		else if("Skill2".equals(skillName)){
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
