package dryrun.game.gui.misc.buttons;




public class NewGameButton extends Button {

	
	public NewGameButton(float coordX, float coordY) {
		super(coordX, coordY, "New Game");
	}

	@Override
	public void pressed() {
	//	RaceGame.setCurrentGameState(GameState.NewGameMenu);
		System.out.println("Button works");
	}


}
