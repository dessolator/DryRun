package dryrun.game.engine;



import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import dryrun.game.engine.interfaces.Drawable;
import static dryrun.game.engine.LoadTex.tex;



//class used to represent the loading screen
public class LoadingScreen implements Drawable {
	private static float x1=0;
	private static float x2=1/8;
	private static float y1=0;
	private static float y2=1;
	private static int i=0;
	public LoadingScreen(){}	
	
		//	@Override
	public Texture getTexture() {
		return tex.getMyTexture();
	}
	
	@Override
	public void render() {
		tex.getMyCoords().setX1(x1);
		tex.getMyCoords().setY1(y1);
		tex.getMyCoords().setX2(x2);
		tex.getMyCoords().setY2(y2);
		DrawObject.draw(this);
		Display.update();
		x1=((float)i/8);
		x2=((float)(i+1)/8);
		i++;
		if(i==7)
			i=0;
		
	}

	@Override
	public float getX() {
		return (float)Display.getWidth()/2;
	}

	@Override
	public float getY() {
		return (float)Display.getHeight()/2;
	}

	@Override
	public float getDimX() {
		return (float)Display.getWidth()/20;
	}

	@Override
	public float getDimY() {
		return (float)Display.getHeight()/8;
	}

	@Override
	public float getTexX1() {
		return tex.getMyCoords().getX1();
	}

	@Override
	public float getTexX2() {
		 	return tex.getMyCoords().getX2();
	}

	@Override
	public float getTexY1() {
		return tex.getMyCoords().getY1();
	}

	@Override
	public float getTexY2() {
		return tex.getMyCoords().getY2();
	}

	@Override
	public double getAngle() {
		return 0;
	}

}
