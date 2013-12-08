package dryrun.game.gui.misc.buttons;

import dryrun.game.mechanics.Game;
import dryrun.game.network.server.Server;
import dryrun.game.common.*;
public class HostButton extends Button {

	public HostButton(float coordX, float coordY) {
		super(coordX, coordY, "Host");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		Game.setCurrentGameState(GameState.HostGameScreen);
		Server.getServer();
	}

}
