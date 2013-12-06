package dryrun.game.gui.misc.buttons;

import game.GameState;
import game.RaceGame;


public class CancelButton extends Button {

	public CancelButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Cancel");
	}

	@Override
	public void pressed() {
		RaceGame.setCurrentGameState(GameState.MainMenu);

	}

}
