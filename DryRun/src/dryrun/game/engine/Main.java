package dryrun.game.engine;

import dryrun.game.mechanics.Game;

public class Main {

	public static void main(String[] args) {
		Engine.init();
//		LoadTex.init();
		Game.startGame();
		Engine.kill();
	}

}
