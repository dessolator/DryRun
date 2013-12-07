package dryrun.game.mechanics;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import dryrun.game.common.GameState;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Updateable;
import dryrun.game.gui.menus.*;



public class Game {
	private static MainMenu myMainMenu;
	private static boolean terminate=false;	
	private static GameState currentGameState=GameState.MainMenu;
	
static{		
		//myLevel=new Level(currentLevel);
		myMainMenu=new MainMenu();
		//mySettingsMenu=new SettingsMenu();	
	}
	
	public static void startGame(){
		while((!Display.isCloseRequested())&& !isTerminate()) {
			glClear(GL_COLOR_BUFFER_BIT);
			
			getCurrentUpdate().update();
			getCurrentDraw().render();
			//gameloop yet to be done 
			
			
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
				return null;
			case SplashScreen:
				return null;
			default:
				return null;
		}
	}

	private static Drawable getCurrentDraw() {
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
			return null;
		case SplashScreen:
			return null;
		default:
			return null;
	}
	}

	public static boolean isTerminate() {
		return terminate;
	}

}
