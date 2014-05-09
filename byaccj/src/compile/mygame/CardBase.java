package compile.mygame;
import compile.mygame.*;
import compile.mygame.characters.*;

public abstract class CardBase {public Integer value;
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
public abstract String getName();
	public abstract boolean method(Player dealer);

}