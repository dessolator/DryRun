package dryrun.game.gui.misc.buttons;




public class CancelButton extends Button {

	public CancelButton(float coordX, float coordY) {
		super(coordX, coordY, "Cancel");
	}

	@Override
	public void pressed() {
	//	RaceGame.setCurrentGameState(GameState.MainMenu);

	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}



}
