package dryrun.game.engine;

import org.lwjgl.opengl.Display;

import dryrun.game.mechanics.Game;

public class Main {
	public static int i=0;

	public static void main(String[] args) {
		Engine.init();
		LoadTex.init();
		for(i=0; i< 200; i++){
		new LoadingScreen().init();
		Display.update();
		}		
		Game.initGame();
		Game.startGame();
		
	
		
		Engine.kill();
	}

}
