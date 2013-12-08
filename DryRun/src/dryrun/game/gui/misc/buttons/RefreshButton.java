package dryrun.game.gui.misc.buttons;

import dryrun.game.network.client.Client;

public class RefreshButton extends Button {

	public RefreshButton(float coordX, float coordY) {
		super(coordX, coordY, "Refresh");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		Client.getClient().findServers();
	}

}
