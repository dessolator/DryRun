package dryrun.game.gui.menus.misc.frames;


import static dryrun.game.engine.LoadTex.basicFrame;
import dryrun.game.engine.Tex;
import dryrun.game.objects.TextureHolder;


public class BasicFrame extends Frame {

	public BasicFrame(float coordX, float coordY, float dimX, float dimY) {
		super(coordX, coordY, dimX, dimY);

			frame=new TextureHolder(basicFrame,new Tex(0f,0f,1f,1f));
		
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
	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
