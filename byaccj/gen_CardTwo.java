public class gen_CardTwo implements ICard{
public Integer value=2;
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Card ");
		sb.append(getName());
		sb.append(":{");
sb.append("\n\tvalue=");
		sb.append(2);
		sb.append("\n}");
		return sb.toString();
	}
@Override
	public String getName() {
		return "CardTwo";
	}
@Overridepublic void method(PlayerBase dealer){
;
}