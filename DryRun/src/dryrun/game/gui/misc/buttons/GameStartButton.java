package dryrun.game.gui.misc.buttons;

import dryrun.game.common.StringObject;
import dryrun.game.network.GameStatePacket;
import dryrun.game.network.server.Server;

public class GameStartButton extends Button {

	public GameStartButton(float coordX, float coordY) {
		super(coordX, coordY, "Start game");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		System.out.println("stisut start game");
		if (Server.getServer().getServerThreads().size()!=0) {
			StringObject s = new StringObject();  
			GameStatePacket p = new GameStatePacket();
			p.put(s);
			Server.getServer().startGame(p);
		}
		
	}

}
