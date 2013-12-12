package dryrun.game.gui.menus.misc.frames;

import org.lwjgl.opengl.Display;

import dryrun.game.gui.menus.misc.text.TimesNewRomanText;
import static dryrun.game.engine.LoadTex.waitFrame;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Tex;
import dryrun.game.engine.TextureHolder;

public class WaitFrame extends Frame {
	
	private TimesNewRomanText myText;
	
	public WaitFrame(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);
		myText = new TimesNewRomanText (Display.getWidth()/2,Display.getHeight()/2,"Waiting for server..." );
		frame = new TextureHolder(waitFrame,new Tex(0f,0f,1f,1f));
		// TODO Auto-generated constructor stub
	}
	
	public void render() {
		DrawObject.draw(this);
		myText.render();
	}

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

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
