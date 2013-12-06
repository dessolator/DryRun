package game.gui.misc.buttons;

import game.GameState;
import game.RaceGame;

public class MainMenuButton extends Button {

	public MainMenuButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY, "Main Menu");
	}

	@Override
	public void pressed() {
		RaceGame.setCurrentGameState(GameState.MainMenu);
	}

}
