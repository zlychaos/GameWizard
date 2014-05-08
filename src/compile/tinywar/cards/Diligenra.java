package compile.tinywar.cards;

import compile.tinywar.*;

public class Diligenra extends CardBase {
	
	public Diligenra(){
		value = 4;
	}
	
	@Override
	public String getName() {
		return "Diligenra";
	}

	@Override
	public void method(Player dealer) {
		Game.drawCard(dealer, 2);
	}

	
}
