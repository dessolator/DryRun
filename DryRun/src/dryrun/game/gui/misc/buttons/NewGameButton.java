package dryrun.game.gui.misc.buttons;

import dryrun.game.common.GameState;
import dryrun.game.mechanics.Game;




public class NewGameButton extends Button {

	
	public NewGameButton(float coordX, float coordY) {
		super(coordX, coordY, "New Game");
	}

	@Override
	public void pressed() {
		Game.setCurrentGameState(GameState.JoinGame);//TODO temporary
	}


}
