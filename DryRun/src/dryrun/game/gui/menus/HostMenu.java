package dryrun.game.gui.menus;

import org.lwjgl.opengl.Display;
import static dryrun.game.engine.LoadTex.mainMenuBackground;
import dryrun.game.gui.menus.misc.frames.*;
import java.util.*;
import dryrun.game.gui.misc.buttons.*;


public class HostMenu extends Menu {


	public HostMenu() {
		// TODO Auto-generated constructor stub
		myFrame = new BasicFrame(Display.getWidth()/2,Display.getHeight()/2,
				Display.getWidth()/3,Display.getHeight()/3);
		myButtons = new ArrayList<Button>();
		myButtons.add(new GameStartButton(Display.getWidth()/2,
				Display.getHeight()/2 + Display.getHeight()/10 + Display.getHeight()/30));
		myButtons.add(new BackButton(Display.getWidth()/2,
				Display.getHeight()/2 + Display.getHeight()/30));
		background=mainMenuBackground;
	}

}
