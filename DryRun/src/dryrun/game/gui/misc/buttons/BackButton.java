package dryrun.game.gui.misc.buttons;

import dryrun.game.common.GameState;
import dryrun.game.mechanics.Game;

public class BackButton extends Button {

	public BackButton(float coordX, float coordY) {
		super(coordX, coordY, "Back");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressed() {
		Game.goBack();
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
