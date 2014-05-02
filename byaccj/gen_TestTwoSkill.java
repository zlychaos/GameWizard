public class gen_TestTwoSkill extends CharacterBase{
public Integer HP=3;
public gen_TestTwoSkill(){
		skillList = new String[2];
skillList[0] = "mercy";
skillList[1] = "gangLie";
}
@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
sb.append("\tHP=");
		sb.append(HP);
return sb.toString();
	}
@Override
	public boolean skill(PlayerBase p, String skillName) {
if("mercy".equals(skillName)){
if(true)
{false;}
			return true;
		}
if("gangLie".equals(skillName)){
if(false)
{true;}
			return true;
		}
return false;
	}
@Override
	public String getName() {
		return "TestTwoSkill";
	}
}