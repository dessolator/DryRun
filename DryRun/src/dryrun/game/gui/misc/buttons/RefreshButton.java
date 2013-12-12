package dryrun.game.gui.misc.buttons;

import dryrun.game.mechanics.Game;
import dryrun.game.network.client.Client;

public class RefreshButton extends Button {

	private boolean refreshRunning=false;
	public RefreshButton(float coordX, float coordY) {
		super(coordX, coordY, "Refresh");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		super.pressed();
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
