package dryrun.game.gui.misc.buttons;



public class SettingsButton extends Button {


	public SettingsButton(float coordX, float coordY) {
		super(coordX, coordY,"Settings");		
	}
	@Override
	public void pressed() {
		super.pressed();
	//	RaceGame.setCurrentGameState(GameState.SettingsScreen);
		
	}
	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
