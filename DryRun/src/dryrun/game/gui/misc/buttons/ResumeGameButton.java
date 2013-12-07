package dryrun.game.gui.misc.buttons;


public class ResumeGameButton extends Button {

	public ResumeGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Resume Game");
	}

	@Override
	public void pressed() {
		//RaceGame.setCurrentGameState(GameState.Game);

	}

	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return 0;
	}

}
