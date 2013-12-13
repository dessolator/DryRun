package dryrun.game.engine;




import dryrun.game.engine.sounds.SEngine;
import dryrun.game.mechanics.Game;

//all "ze" magic happens here :))))) 
public class Main {
	public static int i=0;

	public static void main(String[] args) {
		SEngine.init();
		Engine.init();
		//SEngine.getSoundSystem().quickPlay(false, "splashsound.ogg", false, 0, 0, 0, 0, 0);
		SEngine.getSoundSystem().newStreamingSource(false,"engine", "engineidleloop.ogg", false, 0, 0, 0, 0, 0);
		SEngine.getSoundSystem().queueSound("engine", "engineidle.ogg");
		SEngine.getSoundSystem().setLooping("engine", true);
		SEngine.getSoundSystem().play("engine");
		
		LoadTex.init();
		Game.initGame();
		Game.startGame();
		SEngine.dispose();
		Engine.kill();
	}

}
