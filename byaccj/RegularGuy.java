public class RegularGuy extends CharacterBase{
public Integer HP=1;
public RegularGuy(){
		skillList = new String[0];
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
return false;
	}
@Override
	public String getName() {
		return "RegularGuy";
	}
}