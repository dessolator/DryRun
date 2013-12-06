package dryrun.game.gui.misc.buttons;

import game.GameState;
import game.RaceGame;



public class NewGameButton extends Button {

	
	public NewGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"New Game");
	}

	@Override
	public void pressed() {
		RaceGame.setCurrentGameState(GameState.NewGameMenu);
	}

}
