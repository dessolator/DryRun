package dryrun.game.gui.menus;

import org.lwjgl.opengl.Display;

import dryrun.game.gui.menus.misc.frames.WaitFrame;
import static dryrun.game.engine.LoadTex.mainMenuBackground;

public class WaitServerMenu extends Menu {

	public WaitServerMenu() {
		// TODO Auto-generated constructor stub
		myFrame= new WaitFrame(Display.getWidth()/2,Display.getHeight()/2,Display.getWidth()/2,Display.getHeight()/6);
		background = mainMenuBackground;
	}
	
}

