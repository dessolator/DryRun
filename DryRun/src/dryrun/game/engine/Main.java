package dryrun.game.engine;




import dryrun.game.engine.sounds.*;
import dryrun.game.mechanics.Game;

//all happends here :))))) 
public class Main {
	public static int i=0;

	public static void main(String[] args) {
	//	SEngine.init();
//		SEngine.getSoundSystem().quickPlay(false, "splashsound.ogg", false, 0, 0, 0, 0, 0);
		Engine.init();
		LoadTex.init();
		Game.initGame();
		Game.startGame();
	//	SEngine.getSoundSystem().cleanup();
		Engine.kill();
	}

}
