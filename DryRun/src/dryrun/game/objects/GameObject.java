package dryrun.game.objects;
import org.newdawn.slick.opengl.Texture;

import dryrun.game.engine.*;
import dryrun.game.common.*;

public abstract class GameObject implements Drawable{
	private Tex coords;
	private Texture myTexture=null;
	GameObjectValues myValues;
	@Override
	
	public Texture getTexture() {
		return myTexture;
	}
	
	public void setTexture(Texture t){
		this.myTexture=t;
	}
	
	public void SetTex(Tex t){
		coords = t;	
	}
	

	@Override
	public float getX() {
		return myValues.getCoordX();
	}

	@Override
	public float getY() {		
		return myValues.getCoordY();
	}

	@Override
	public float getDimX() {		
		return myValues.getDimX();
	}

	@Override
	public float getDimY() {		
		return myValues.getDimY();
	}

	@Override
	public float getTexX1() {
		
		return coords.getX1();
	}

	@Override
	public float getTexX2() {
		
		return coords.getX2();
	}

	@Override
	public float getTexY1() {
		
		return coords.getY1();
	}

	@Override
	public float getTexY2() {
		
		return coords.getY2();
	}

}
