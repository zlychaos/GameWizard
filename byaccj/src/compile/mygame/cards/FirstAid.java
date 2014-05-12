package compile.mygame.cards;
import compile.mygame.*;
import compile.mygame.characters.*;
public class FirstAid extends CardBase{
public FirstAid(){
value=3;
}
@Override
	public String getName() {
		return "FirstAid";
	}
@Override
public boolean method(Player dealer){
dealer.character.HP=dealer.character.HP+1;return true;

} 
}