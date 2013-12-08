package dryrun.game.gui.menus;

import org.newdawn.slick.opengl.TextureLoader;
import java.io.*;

public class HostMenu extends Menu {

	public HostMenu() {
		// TODO Auto-generated constructor stub
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
