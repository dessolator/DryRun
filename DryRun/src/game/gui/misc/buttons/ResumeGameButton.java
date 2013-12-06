package game.gui.misc.buttons;

import game.GameState;
import game.RaceGame;

public class ResumeGameButton extends Button {

	public ResumeGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Resume Game");
	}

	@Override
	public void pressed() {
		RaceGame.setCurrentGameState(GameState.Game);

	}

}
