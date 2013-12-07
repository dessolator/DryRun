package dryrun.game.objects;
import org.newdawn.slick.opengl.Texture;

import dryrun.game.engine.*;
import dryrun.game.common.*;

public class GameObject implements Drawable{
	private Tex coords;
	private Texture myTexture=null;
	GameObjectValues myValues;
	@Override
	
	public Texture getTexture() {
		return myTexture;
	}
	
	public void SetTex(Tex t){
		coords = t;	
	}
	
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDimX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDimY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return 0;
	}

}
