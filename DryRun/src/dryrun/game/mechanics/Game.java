package dryrun.game.mechanics;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;
import static org.lwjgl.opengl.GL11.*;

import dryrun.game.gui.menus.*;



public class Game {
	private static MainMenu myMainMenu;
	private static boolean terminate=false;	
	private static int currentGameState=0;
	private static PauseMenu myPauseMenu; 
	
	
	public static void startGame(){
		while((!Display.isCloseRequested())&& !isTerminate()) {
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			
			Display.sync(60);
			Display.update();			
		}		
	}
	
	public static boolean isTerminate() {
		return terminate;
	}

}
