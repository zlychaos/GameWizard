package zhllz.gamewizard.basic;

public abstract class CharacterBase {
	
	protected String[] skillList;

	public String getSkillList(){
		StringBuilder sb = new StringBuilder("[ ");
		boolean first = true;
		for(String skill : skillList){
			if(first){
				sb.append(skill);
				first = false;
			}
			else{
				sb.append(" ," + skill);
			}
		}
		sb.append(" ]");
		return sb.toString();
	}
	
	public abstract String getName();
	public abstract String getDiscription();
	public abstract boolean skill(PlayerBase p, String skillName);
	
}
