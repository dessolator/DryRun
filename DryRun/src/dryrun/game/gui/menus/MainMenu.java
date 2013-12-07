package dryrun.game.gui.menus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.gui.menus.misc.frames.BasicFrame;
import dryrun.game.gui.misc.buttons.*;


public class MainMenu extends Menu {
	public MainMenu(){
		myButtons=new ArrayList<Button>();
		myFrame=new BasicFrame(
				Display.getWidth()/2,
				(int)((float)Display.getHeight()/2.5),
				Display.getWidth()/5,
				Display.getHeight()/2
				);
		myButtons.add(new ExitGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2-Display.getHeight()/14+Display.getHeight()/30)
		);
		myButtons.add(new SettingsButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/30)
		);
		myButtons.add(new NewGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/14+Display.getHeight()/30)
		);
		try {
			background=TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
