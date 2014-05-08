package compile.tinywar;

public abstract class CardBase {
	
	public int value;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Card ");
		sb.append(getName());
		sb.append(":{\n");
		sb.append("\tvalue=");
		sb.append(value);
		sb.append("\n}");
		return sb.toString();
	}
	
	public abstract String getName();
	public abstract void method(Player dealer);

}
