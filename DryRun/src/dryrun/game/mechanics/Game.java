package dryrun.game.mechanics;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.GL11.*;
import dryrun.game.engine.Drawable;
import dryrun.game.gui.menus.*;



public class Game {
	private static MainMenu myMainMenu;
	private static boolean terminate=false;	
	private static int currentGameState=0;
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
			//gameloop yet to be done 
			
			
			Display.sync(60);
			Display.update();			
		}		
	}
	
	public static boolean isTerminate() {
		return terminate;
	}

}
