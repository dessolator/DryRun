package dryrun.game.gui.misc.buttons;

import java.net.InetAddress;

import dryrun.game.network.client.Client;

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

	}

}
