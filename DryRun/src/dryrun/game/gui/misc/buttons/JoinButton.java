package dryrun.game.gui.misc.buttons;

import dryrun.game.common.GameState;
import dryrun.game.mechanics.Game;

public class JoinButton extends Button {

	public JoinButton(float coordX, float coordY) {
		super(coordX, coordY, "Join");
		
	}

	@Override
	public void pressed() {
		// TODO Auto-generated method stub
		Game.setCurrentGameState(GameState.LobbyScreen);
	}

}
