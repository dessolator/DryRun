package dryrun.game.engine;



import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import static dryrun.game.engine.LoadTex.tex;



//class used to represent the loading screen
public class LoadingScreen implements Drawable {
	
	public LoadingScreen(){	}	
	
		//	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return tex.getMyTexture();
		}
	
	@Override
	public void render() {
		DrawObject.draw(this);
		Display.update();
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
		return tex.getMyCoords().getX1();
		}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		 	return tex.getMyCoords().getX2();
		}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return tex.getMyCoords().getY1();
		}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return tex.getMyCoords().getY2();
		}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
		}

}
