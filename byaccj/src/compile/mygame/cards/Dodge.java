package compile.mygame.cards;
import compile.mygame.*;
import compile.mygame.characters.*;
public class Dodge extends CardBase{
public Dodge(){
value=2;
}
@Override
	public String getName() {
		return "Dodge";
	}
@Override
public boolean method(Player dealer){
return true;

} 
}