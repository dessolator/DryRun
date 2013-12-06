package game.gui.misc.buttons;

import game.GameState;
import game.RaceGame;


public class SettingsButton extends Button {


	public SettingsButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Settings");		
	}
	@Override
	public void pressed() {
		RaceGame.setCurrentGameState(GameState.SettingsScreen);
		
	}

}
