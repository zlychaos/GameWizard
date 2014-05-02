package mygame.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.Player;

public class Attack implements ICard {
	
	public int  value=3
	@Override
	public void method(Player dealer) {
dosomethinghere
	}


	@Override
	public String getName() {
		return "Attack";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Card ");
		sb.append(getName());
		sb.append(":{\n");
		sb.append("\tvalue=");
		sb.append(value);
		sb.append("\n")
;
		sb.append("\n}");
		return sb.toString();
	}
	
}
