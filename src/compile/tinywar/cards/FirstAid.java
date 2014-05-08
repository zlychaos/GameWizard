package compile.tinywar.cards;

import compile.tinywar.*;

public class FirstAid extends CardBase {
	
	public FirstAid(){
		value = 3;
	}
	
	@Override
	public String getName() {
		return "FirstAid";
	}

	@Override
	public void method(Player dealer) {
		dealer.character.HP = dealer.character.HP + 1;
	}

	
}
