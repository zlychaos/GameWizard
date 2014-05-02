package compile.helloworld.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.Player;

public class CardTwo implements ICard {

	public int value = 2;

	@Override
	public String getName() {
		return "CardTwo";
	}

	@Override
	public void method(Player dealer) {
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
