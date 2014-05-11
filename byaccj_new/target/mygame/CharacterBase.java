package mygame;

public abstract class CharacterBase {

		public int hp;	public String name;
	
	public String[] skillList;

	public String getSkillList(){
		StringBuilder sb = new StringBuilder("[ ");
		int i = 1;
		for(String skill : skillList){
			if(i!=1){
				sb.append(", ");
			}
			sb.append("("+i+"): " + skill);
			i++;
		}
		sb.append(" ]");
		return sb.toString();
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
sb.append("\thp=");
		sb.append(hp);
		sb.append("\n");
sb.append("\tname=");
		sb.append(name);
		sb.append("\n");

		//sb.append("\tHP=");
		//sb.append(HP);
		return sb.toString();
	}
	
	public abstract String getName();
	public abstract String getDiscription();
	public abstract boolean skill(Player p, String skillName);
	
}
