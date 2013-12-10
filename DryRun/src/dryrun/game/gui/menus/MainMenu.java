package dryrun.game.gui.menus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static dryrun.game.engine.LoadTex.mainMenuBackground;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.gui.menus.misc.frames.BasicFrame;
import dryrun.game.gui.misc.buttons.*;


public class MainMenu extends Menu {
	public MainMenu(){
		myButtons=new ArrayList<Button>();
		myFrame=new BasicFrame(
				Display.getWidth()/2,
				Display.getHeight()/2,
				Display.getWidth()/3,
				Display.getHeight()/3
				);
		myButtons.add(new ExitGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2-Display.getHeight()/10+Display.getHeight()/30)
		);
		myButtons.add(new SettingsButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/30)
		);
		myButtons.add(new NewGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/10+Display.getHeight()/30)
		);
		background=mainMenuBackground;
		
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
