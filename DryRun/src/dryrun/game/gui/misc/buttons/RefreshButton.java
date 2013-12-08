package dryrun.game.gui.misc.buttons;

import dryrun.game.mechanics.Game;
import dryrun.game.network.client.Client;

public class RefreshButton extends Button {

	public RefreshButton(float coordX, float coordY) {
		super(coordX, coordY, "Refresh");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		Game.getMyLobbyMenu().setRefreshTriggered(true);
		
		Game.getMyLobbyMenu().deleteServerButtons();
		Game.getPossibleServers().clear();
		Client.getClient().findServers();
		
		
		
		//new SomeThr().start();
		//Game.getMyLobbyMenu().createServerButtons();
		//Game.getMyLobbyMenu().createServersFrame();
	}

}
