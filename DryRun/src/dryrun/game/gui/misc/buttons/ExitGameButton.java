package dryrun.game.gui.misc.buttons;

import game.RaceGame;


public class ExitGameButton extends Button {

	public ExitGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Exit");		
	}

	@Override
	public void pressed() {
		RaceGame.setTerminate(true);
	}

}
