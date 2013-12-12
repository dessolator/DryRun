package dryrun.game.gui.misc.buttons;

import dryrun.game.engine.network.client.Client;
import dryrun.game.mechanics.Game;

public class RefreshButton extends Button {

	private boolean refreshRunning=false;
	public RefreshButton(float coordX, float coordY) {
		super(coordX, coordY, "Refresh");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		Game.getMyLobbyMenu().deleteServerButtons();
		Game.getMyLobbyMenu().getButtonsToAdd().clear();
		Game.getPossibleServers().clear();
		Client.getClient().findServers();
		if(!refreshRunning){
			refreshRunning=true;
			new Thread(){
				public void run(){
					long startTime=System.nanoTime();
					while(System.nanoTime()-startTime<3000000000l){
						Game.getMyLobbyMenu().loadServerList();
						try {
							sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					refreshRunning=false;
				}
			}.start();
		}

	}

}
