package compile.helloworld.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.PlayerBase;

public class Defense implements ICard {
	
	public int value=4

	
	@Override
	public String getName() {
		return "Defense";
	}

	@Override
	public void method(PlayerBase dealer) {
		###
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Card ");
		sb.append(getName());
		sb.append(":{\n");
		sb.append("\tvalue=");
		sb.append(value);
		sb.append("\n");
		sb.append("}");
		return sb.toString();
	}
	
}
