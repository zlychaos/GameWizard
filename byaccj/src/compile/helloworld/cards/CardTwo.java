package compile.helloworld.cards;

import zhllz.gamewizard.basic.ICard;
import zhllz.gamewizard.basic.Player;
public class CardTwo implements ICard{
public Integer value=2;
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
		return "CardTwo";
	}
@Override
public void method(Player dealer){

} 
}