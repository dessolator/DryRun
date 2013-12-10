package dryrun.game.engine;

import dryrun.game.mechanics.Game;

public class Main {

	public static void main(String[] args) {
		Engine.init();
<<<<<<< HEAD
		new Thread(){
			public void run(){
				Game.initGame();
			}
		}.start();
		new LoadingScreen().init();
=======
//		LoadTex.init();
		Game.startGame();
>>>>>>> refs/remotes/origin/master
		Engine.kill();
	}

}
