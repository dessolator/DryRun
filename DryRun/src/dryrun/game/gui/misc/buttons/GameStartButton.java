package dryrun.game.gui.misc.buttons;


import dryrun.game.network.GameStatePacket;
import dryrun.game.network.server.Server;

public class GameStartButton extends Button {

	public GameStartButton(float coordX, float coordY) {
		super(coordX, coordY, "Start game");
	}

	@Override
	public void pressed() {
		System.out.println("stisut start game");
		if (Server.getServer().getMyThreads().size()!=0) {
		}
		
	}

}
