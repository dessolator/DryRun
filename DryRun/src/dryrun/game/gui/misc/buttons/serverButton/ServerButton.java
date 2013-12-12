package dryrun.game.gui.misc.buttons.serverButton;

import java.net.InetAddress;

import dryrun.game.common.GameState;
import dryrun.game.engine.network.client.Client;
import dryrun.game.gui.misc.buttons.Button;
import dryrun.game.mechanics.Game;

public class ServerButton extends Button {
	private InetAddress serverAddress;
	
	public ServerButton(InetAddress servAddr, float coordX, float coordY, String myText) {
		super(coordX, coordY, myText);
		serverAddress = servAddr;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		Client.getClient().connectToServer(serverAddress, "goksiii", 1);
		Game.setCurrentGameState(GameState.WaitServerReply);

	}

}
