package compile.helloworld.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.PlayerBase;

public class Attack implements ICard {
	
	public int value=3

	
	@Override
	public String getName() {
		return "Attack";
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
