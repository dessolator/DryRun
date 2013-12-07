package dryrun.game.gui.misc.buttons;




public class ScoresButton extends Button {

	
	public ScoresButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"Scores");
	}

	@Override
	public void pressed() {
		//RaceGame.setCurrentGameState(3);//TODO what do we do here?!?!?!?!?
		
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
