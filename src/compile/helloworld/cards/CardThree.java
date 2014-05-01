package compile.helloworld.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.PlayerBase;

public class CardThree implements ICard {

	public int value = 3;

	@Override
	public String getName() {
		return "CardThree";
	}

	@Override
	public void method(PlayerBase dealer) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Card ");
		sb.append(getName());
		sb.append(":{\n\tvalue=");
		sb.append(value);
		//sb.append("\n\tkey=");
		//sb.append(value);
		sb.append("\n}");
		return sb.toString();
	}

}
