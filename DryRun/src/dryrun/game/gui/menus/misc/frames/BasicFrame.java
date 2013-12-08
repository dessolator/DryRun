package dryrun.game.gui.menus.misc.frames;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import dryrun.game.engine.Tex;
import dryrun.game.objects.TextureHolder;

import org.newdawn.slick.opengl.TextureLoader;

public class BasicFrame extends Frame {

	public BasicFrame(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		try {
			frame=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/basicFrame.png"))),new Tex(0f,0f,1f,1f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * funkcije ispod ne moraju da budu implementirane za sada, po potrebi 
	 * vracaju koordinate dela teksture
	 * 
	 * */
	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return frame.getMyCoords().getX1();
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return frame.getMyCoords().getX2();
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return frame.getMyCoords().getY1();
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return frame.getMyCoords().getY2();
	}

	
}
