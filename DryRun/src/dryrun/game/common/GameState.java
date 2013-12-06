package dryrun.game.common;

public enum GameState {
	MainMenu,
	
	NewGameMenu,
		NetworkGameMenu,
			CreateGameScreen,
			JoinGameScreen,
				Game,
	SettingsScreen,
	
	ExitGameDialog;

}

