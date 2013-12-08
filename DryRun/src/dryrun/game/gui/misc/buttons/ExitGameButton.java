package dryrun.game.gui.misc.buttons;
import dryrun.game.mechanics.*;

public class ExitGameButton extends Button {

	public ExitGameButton(float coordX, float coordY) {
		super(coordX, coordY, "Exit");		
	}

	@Override
	public void pressed() {
		Game.setTerminate(true);
	}


}
