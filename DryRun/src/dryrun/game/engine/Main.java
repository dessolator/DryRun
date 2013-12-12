package dryrun.game.engine;




import dryrun.game.engine.sounds.SEngine;
import dryrun.game.mechanics.Game;

//all "ze" magic happens here :))))) 
public class Main {
	public static int i=0;

	public static void main(String[] args) {
		SEngine.init();
		Engine.init();
		SEngine.getSoundSystem().quickPlay(false, "splashsound.ogg", false, 0, 0, 0, 0, 0);
		LoadTex.init();
		Game.initGame();
		Game.startGame();
		SEngine.dispose();
		Engine.kill();
	}

}
