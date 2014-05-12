package compile.mygame.cards;
import compile.mygame.*;
import compile.mygame.characters.*;
public class Attack extends CardBase{
public Attack(){
value=1;
}
@Override
	public String getName() {
		return "Attack";
	}
@Override
public boolean method(Player dealer){
Player target=Game.waitForTarget(dealer);
nullGame.sendToOnePlayer(target,"You are attacked by Player "+dealer.id+", please put a Dodge, otherwise you will lose one HP point.");CardBase c = Game.putCard(target);
while(c!=null&&!(c instanceof Dodge))
{
target.handCards.add(c);c=Game.putCard(target);}
nullreturn true;

} 
}