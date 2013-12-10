package dryrun.game.engine;

import org.lwjgl.opengl.Display;

import dryrun.game.mechanics.Game;

public class Main {
	public static int i=0;

	public static void main(String[] args) {
		Engine.init();
		LoadTex.init();
		Game.initGame();
		Game.startGame();		
		Engine.kill();
	}

}
