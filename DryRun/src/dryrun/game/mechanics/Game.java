package dryrun.game.mechanics;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.GL11.*;
import dryrun.game.common.GameState;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Updateable;
import dryrun.game.gui.menus.*;



public class Game {
	private static MainMenu myMainMenu;
	private static boolean terminate=false;	
	private static GameState currentGameState=GameState.MainMenu;
	private static PauseMenu myPauseMenu; 
	
static{		
		//myLevel=new Level(currentLevel);
		myMainMenu=new MainMenu();
		myPauseMenu=new PauseMenu();
		//mySettingsMenu=new SettingsMenu();
		//myScoresMenu=new ScoresMenu();//TODO probably have menus as static?		
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
			case CreateGameScreen:
				return null;
			case ExitGameDialog:
				return null;
			case Game:
				return null;
			case JoinGameScreen:
				return null;
			case NetworkGameMenu:
				return null;
			case NewGameMenu:
				return null;
			case SettingsScreen:
				return null;
			default:
				return null;
		}
	}

	private static Drawable getCurrentDraw() {
		switch(currentGameState){
		case MainMenu:
			return myMainMenu;
		case CreateGameScreen:
			return null;
		case ExitGameDialog:
			return null;
		case Game:
			return null;
		case JoinGameScreen:
			return null;
		case NetworkGameMenu:
			return null;
		case NewGameMenu:
			return null;
		case SettingsScreen:
			return null;
		default:
			return null;
	}
	}

	public static boolean isTerminate() {
		return terminate;
	}

}
