package dryrun.game.common;

import dryrun.game.mechanics.Checkpoint;

public class PlayerBot extends Player {
	private static int botcnt=1;
	private Ai myAi;

	public PlayerBot(String carType, float x, float y, float dimx, float dimy, Checkpoint[] allChkPnt) {
		super("Bot"+botcnt++, carType, x, y, dimx, dimy);
		myAi=new Ai(allChkPnt);
	}
	
	//void render(){myAi.getCommand;}

}
