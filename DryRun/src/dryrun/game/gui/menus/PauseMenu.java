package dryrun.game.gui.menus;

import static org.lwjgl.opengl.GL11.glColor4f;
import dryrun.game.engine.DrawObject;
import dryrun.game.gui.menus.misc.frames.BasicFrame;
import dryrun.game.gui.misc.buttons.*;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;


public class PauseMenu extends Menu {
	public PauseMenu(){
		myButtons=new ArrayList<Button>();
		myFrame=new BasicFrame(
				Display.getWidth()/2,
				Display.getHeight()/2,
				Display.getWidth()/5,
				Display.getHeight()/2
				);
		myButtons.add(new MainMenuButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
		myButtons.add(new ResumeGameButton(
				Display.getWidth()/2,
				Display.getHeight()/2+Display.getHeight()/14+Display.getHeight()/30,
				Display.getWidth()/10,
				Display.getHeight()/15)
		);
	}

	@Override
	public void render() {
		//RaceGame.getMyLevel().render();//TODO FIX ME!!!!!!
		glColor4f(0f, 0f, 0f,0.5f);
		DrawObject.drawColoredRect(getCoordX(), getCoordY(), getDimX(), getDimY());
		if(Mouse.isGrabbed())Mouse.setGrabbed(false);
		myFrame.render();
		for (Button b:myButtons)
			b.render();
	}

	@Override
	public Texture getTexture() {
		return null;
	}

	
}
