package dryrun.game.gui.menus;

import dryrun.game.gui.menus.misc.frames.BasicFrame;
import dryrun.game.gui.misc.buttons.*;
import java.util.*;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;
import java.io.*;

public class JoinMenu extends Menu {

	public JoinMenu() {
		myButtons = new ArrayList<Button>();
		myFrame = new BasicFrame(Display.getWidth()/4,
				Display.getHeight()/2,
				Display.getWidth()/3,
				Display.getHeight()/3);
		myButtons.add(new RefreshButton(Display.getWidth()/4,
				Display.getHeight()/2 + Display.getHeight()/10 + Display.getHeight()/30));
		myButtons.add(new BackButton(Display.getWidth()/4,
				Display.getHeight()/2 + Display.getHeight()/30));
		
		try {
			background = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
