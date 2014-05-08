package compile.tinywar.cards;

import compile.tinywar.*;

public class Dodge extends CardBase {
	
	public Dodge(){
		value = 2;
	}
	
	@Override
	public String getName() {
		return "Dodge";
	}

	@Override
	public boolean method(Player dealer) {
		return false;
	}

	
}
