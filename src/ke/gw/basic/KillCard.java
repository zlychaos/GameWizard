package ke.gw.basic;



import ke.gw.game.Game1;
import zhllz.gamewizard.basic.Player;


public class KillCard implements Card{
	public String name;
	
	public void method(Player dealer) throws Exception {
		Player target = Game1.chooseTarget(dealer);
		String cname = target.conn.waitForInput("Please put dodge");
		if(cname==null || !cname.equals("dodge")){
			target.HP--;
		}
	}
	
	public KillCard(){
		this.name = "kill";
	}
}
