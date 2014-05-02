package mygame.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.Player;

public class Defense implements ICard {
	
	public int  value=4
	@Override
	public void method(Player dealer){ }

	@Override
	public String getName() {
		return "Defense";
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
