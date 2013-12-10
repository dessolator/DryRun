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
import dryrun.game.objects.TextureHolder;
import static dryrun.game.engine.LoadTex.loading1;
import static dryrun.game.engine.Main.i;
public class LoadingScreen implements Drawable {
	protected TextureHolder background;
	
	
	public LoadingScreen() {
		super();	
			switch((i%3)){
			case 0: {
				background=new TextureHolder (loading1,new Tex(0f, 0f, (float)1/8f, 1f));
				break;
			}
			case 1: {
				background=new TextureHolder (loading1,new Tex((float)1/8f, 0f, (float)2/8f, 1f));
				break;}
			case 2: {
				background=new TextureHolder (loading1,new Tex((float)2/8f, 0f, (float)3/8f, 1f));
				break;}
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
		return background.getMyTexture();
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
		return (float)Display.getWidth()/20;
	}

	@Override
	public float getDimY() {
		// TODO Auto-generated method stub
		return (float)Display.getHeight()/8;
	}

	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return background.getMyCoords().getX1();
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		 	return background.getMyCoords().getX2();
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return background.getMyCoords().getY1();
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return background.getMyCoords().getY2();
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
