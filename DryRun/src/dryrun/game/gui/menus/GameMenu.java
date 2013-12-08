package dryrun.game.gui.menus;
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

		
		
		
		
		try {
			background = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
