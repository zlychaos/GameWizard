package compile.tinywar.characters;

import compile.tinywar.*;

public class Gambler extends CharacterBase{
	public Gambler(){
		HP = 3;
		skillList = new String[1];
		skillList[0] = "Gamble";
	}
	
	@Override
	public String getName() {
		return "Gambler";
	}
	
	@Override
	public String getDiscription() {
		return "";
	}

	@Override
	public boolean skill(Player dealer, String skillName) {
		if("Gamble".equals(skillName)){
			int i = 0;
			for(CardBase card : dealer.handCards){
				//dealer.handCards.remove(card);
				Game.droppedCardStack.add(card);
				i = i+1;
			}
			dealer.handCards.clear();
			Game.drawCard(dealer, i);
			return true;
		}
		return false;
	}	
}
