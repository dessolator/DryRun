package dryrun.game.gui.misc.buttons;
import dryrun.game.mechanics.*;

public class ExitGameButton extends Button {

	public ExitGameButton(float coordX, float coordY) {
		super(coordX, coordY, "Exit");		
	}

	@Override
	public void pressed() {
		super.pressed();
		Game.setTerminate(true);
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}


}
