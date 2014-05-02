package zlychaos.template.game1;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.Player;

public class Card implements ICard {
	
	public int value;
	
	public Card(int value){
		this.value = value;
	}

	@Override
	public String getName() {
		return Integer.toString(value);
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
