package compile.helloworld.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.Player;
public class CardOne implements ICard{
public Integer value=1;
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Card ");
		sb.append(getName());
		sb.append(":{");
sb.append("\n\tvalue=");
		sb.append(value);
		sb.append("\n}");
		return sb.toString();
	}
@Override
	public String getName() {
		return "CardOne";
	}
@Override
public void method(Player dealer){

} 
}