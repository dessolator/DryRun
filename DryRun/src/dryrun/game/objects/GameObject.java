package dryrun.game.objects;
import org.newdawn.slick.opengl.Texture;

import dryrun.game.engine.*;
import dryrun.game.common.*;

public abstract class GameObject implements Drawable{
	private TextureHolder holder;
	private GameObjectValues myValues;
	public TextureHolder getHolder() {
		return holder;
	}

	public void setHolder(TextureHolder holder) {
		this.holder = holder;
	}

	public GameObjectValues getMyValues() {
		return myValues;
	}

	public void setMyValues(GameObjectValues myValues) {
		this.myValues = myValues;
	}

	@Override
	
	public Texture getTexture() {
		return holder.getMyTexture();
	}
	
	public void setTexture(Texture t){
		holder.setMyTexture(t);
	}
	
	public void SetTex(Tex t){
		holder.setMyCoords(t);	
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
		
		return holder.getMyCoords().getX1();
	}

	@Override
	public float getTexX2() {
		
		return holder.getMyCoords().getX2();
	}

	@Override
	public float getTexY1() {
		
		return holder.getMyCoords().getY1();
	}

	@Override
	public float getTexY2() {
		
		return holder.getMyCoords().getY2();
	}

}
