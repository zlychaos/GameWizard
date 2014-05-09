package compile.mygame.characters;

import compile.mygame.*;
import compile.mygame.cards.*;
public class Gambler extends CharacterBase{
public Gambler(){
		skillList = new String[1];
skillList[0] = "Gamble";
HP=3;
}

	@Override
	public String getDiscription() {
		return "";
	}
@Override
	public boolean skill(Player p, String skillName) {
if("Gamble".equals(skillName)){
Integer i=0;
for(ICard  card:dealer.handCards)
{
Game.droppedCardStack.add(card);
i=i+1;
}
dealer.handCards.clear();
Game.drawCard(dealer,i);
return true;
		}
return false;
	}
@Override
	public String getName() {
		return "Gambler";
	}
}