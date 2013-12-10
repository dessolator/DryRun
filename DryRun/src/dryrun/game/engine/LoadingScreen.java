package dryrun.game.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.mechanics.Game;

public class LoadingScreen implements Drawable {
	protected Texture background;

	
	public LoadingScreen() {
		super();
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
	
	public void init() {
		System.out.println("cao");
		//while(!Game.theEnd) {
			
			render();
	}
		//	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return background;
	}
	
	@Override
	public void render() {
		Mouse.setGrabbed(true);
		DrawObject.draw(this);
		// TODO Auto-generated method stub

	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return (float)Display.getWidth()/2;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return (float)Display.getHeight()/2;
	}

	@Override
	public float getDimX() {
		// TODO Auto-generated method stub
		return (float)Display.getWidth();
	}

	@Override
	public float getDimY() {
		// TODO Auto-generated method stub
		return (float)Display.getHeight();
	}

	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
