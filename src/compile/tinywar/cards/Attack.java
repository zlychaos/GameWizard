package compile.tinywar.cards;

import compile.tinywar.*;

public class Attack extends CardBase {
	
	public Attack(){
		value = 1;
	}
	
	@Override
	public String getName() {
		return "Attack";
	}

	@Override
	public void method(Player dealer) {
		Player target = Game.waitForTarget(dealer);
		Game.sendToOnePlayer(target, "You are attacked by Player " + dealer.id + ", please put a Dodge, otherwise you will lose one HP point.");
		CardBase c = Game.putCard(target);		
		while( c!=null && !(c instanceof Dodge) ){
			target.handCards.add(c);
			c = Game.putCard(target);
		}
		
		if(c == null){  
			target.character.HP = target.character.HP-1;
			if(target.character.HP == 0){
				Game.dying();
			}
		}
		else{
			Game.droppedCardStack.add(c);
		}


	}

	
}
