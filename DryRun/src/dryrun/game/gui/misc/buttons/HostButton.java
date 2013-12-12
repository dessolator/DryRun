package dryrun.game.gui.misc.buttons;

import dryrun.game.mechanics.Game;
import dryrun.game.common.*;
import dryrun.game.engine.network.server.Server;
public class HostButton extends Button {

	public HostButton(float coordX, float coordY) {
		super(coordX, coordY, "Host");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		super.pressed();
		// TODO Auto-generated method stub
		Game.setCurrentGameState(GameState.HostGameScreen);
		Server.getServer();
		Server.getServer().host();
	}

}
