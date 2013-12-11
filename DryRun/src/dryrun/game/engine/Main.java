package dryrun.game.engine;



import dryrun.game.sounds.*;
import dryrun.game.mechanics.Game;


public class Main {
	public static int i=0;

	public static void main(String[] args) {
		SEngine.init();
		//SEngine.getSoundSystem().backgroundMusic("src", "b.ogg", false);
		Engine.init();
		LoadTex.init();
		Game.initGame();
		Game.startGame();
		SEngine.getSoundSystem().cleanup();
		Engine.kill();
	}

}
