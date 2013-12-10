package dryrun.game.gui.menus;
import static dryrun.game.engine.LoadTex.mainMenuBackground;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;

import dryrun.game.gui.misc.buttons.*;
import dryrun.game.gui.menus.misc.frames.*;

public class GameMenu extends Menu {

	public GameMenu() {
		myButtons = new ArrayList<Button>();
		myFrame = new BasicFrame(Display.getWidth()/2,
				Display.getHeight()/2,
				Display.getWidth()/3,
				Display.getHeight()/3);
		myButtons.add(new HostButton(Display.getWidth()/2,
				Display.getHeight()/2 + Display.getHeight()/10 + Display.getHeight()/30));
	
		myButtons.add(new JoinButton(Display.getWidth()/2,
				Display.getHeight()/2 + Display.getHeight()/30));
	
		myButtons.add(new BackButton(Display.getWidth()/2,
				Display.getHeight()/2 - Display.getHeight()/10 + Display.getHeight()/30));
		background=mainMenuBackground;
		
		
		
		
		
		
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
