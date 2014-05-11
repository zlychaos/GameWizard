package mygame.cards;

import mygame.*;

public class Defense extends CardBase {
	
	public Defense(){
		value=4;

		//value = 1;
	}
	
	@Override
	public String getName() {
		return "Attack";
	}

		@Override
	public boolean method(Player dealer){ return true; }

	
}
