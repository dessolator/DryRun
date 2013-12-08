package dryrun.game.gui.menus;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;
import java.io.*;
import dryrun.game.gui.menus.misc.frames.*;
import java.util.*;
import dryrun.game.gui.misc.buttons.*;

public class HostMenu extends Menu {


	public HostMenu() {
		// TODO Auto-generated constructor stub
		myFrame = new BasicFrame(Display.getWidth()/4,Display.getHeight()/2,
				Display.getWidth()/3,Display.getHeight()/3);
		myButtons = new ArrayList<Button>();
		myButtons.add(new BackButton(Display.getWidth()/4,
				Display.getHeight()/2 + Display.getHeight()/10 + Display.getHeight()/30));
		try {
			background = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
