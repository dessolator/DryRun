package dryrun.game.gui.misc.buttons;




public class NewGameButton extends Button {

	
	public NewGameButton(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY,"New Game");
	}

	@Override
	public void pressed() {
	//	RaceGame.setCurrentGameState(GameState.NewGameMenu);
		System.out.println("Button works");
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
