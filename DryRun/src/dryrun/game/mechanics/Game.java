package dryrun.game.mechanics;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.common.GameState;
import dryrun.game.common.Player;
import dryrun.game.common.PlayerValues;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Updateable;
import dryrun.game.gui.menus.*;



public class Game {
	private static MainMenu myMainMenu;
	private static GameMenu myGameMenu;
	private static boolean terminate=false;	
	private static GameState currentGameState=GameState.MainMenu;
	
static{		
		//myLevel=new Level(currentLevel);
		myMainMenu=new MainMenu();
		myMainMenu = new MainMenu();
		myGameMenu = new GameMenu();
		
		//mySettingsMenu=new SettingsMenu();	
	}
	
	public static void startGame(){
		Player p = new Player("Kesler",Display.getWidth()/2, Display.getHeight()/2, Display.getWidth()/6, Display.getHeight()/10);
	
		while((!Display.isCloseRequested())&& !terminate) {
			glClear(GL_COLOR_BUFFER_BIT);
			getCurrentUpdate().update();
			getCurrentDraw().render();
			//gameloop yet to be done 
			p.render();
			
			Display.sync(60);
			Display.update();			
		}		
	}
	
	private static Updateable getCurrentUpdate() {
		switch(currentGameState){
			case MainMenu:
				return myMainMenu;
			case Game:
				return null;
			case HostGameScreen:
				return null;
			case HostJoinMenu:
				return null;
			case JoinGame:
				return null;
			case LobbyScreen:
				return null;
			case PlayMenu:
				return myGameMenu ;
			case SplashScreen:
				return null;
			default:
				return null;
		}
	}
	public static goBack(){
		switch(currentGameState){
			case MainMenu:
				currentGameState= GameState.MainMenu;
				break;
			case Game:
				currentGameState= GameState.Game;
				break;
			case HostGameScreen:
				currentGameState= GameState.PlayMenu;
				break;
			case HostJoinMenu:
				currentGameState= GameState.PlayMenu;
				break;
			case JoinGame:
				currentGameState= GameState.HostJoinMenu;
				break;
			case LobbyScreen:
				currentGameState= GameState.PlayMenu;
				break;
			case PlayMenu:
				currentGameState= GameState.MainMenu;
				break;
			case SplashScreen:
				currentGameState= GameState.SplashScreen;
				break;
			default:
				currentGameState= GameState.MainMenu;
				break;
}
}

	private static Drawable getCurrentDraw() {
		switch(currentGameState){
		case MainMenu:
			return myMainMenu;
		case Game:
			return myGameMenu;
		case HostGameScreen:
			return null;
		case HostJoinMenu:
			return null;
		case JoinGame:
			return null;
		case LobbyScreen:
			return null;
		case PlayMenu:
			return myGameMenu;
		case SplashScreen:
			return null;
		default:
			return null;
	}
	}

	public static boolean isTerminate() {
		return terminate;
	}

	public static GameState getCurrentGameState() {
		return currentGameState;
	}

	public static void setCurrentGameState(GameState currentGameState) {
		Game.currentGameState = currentGameState;
	}

}
