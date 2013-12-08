package dryrun.game.objects;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;
import org.newdawn.slick.opengl.Texture;

import dryrun.game.engine.*;
import dryrun.game.common.*;

public abstract class GameObject implements Drawable,Updateable,ContactListener,Collidable{
	protected TextureHolder holder;
	protected GameObjectValues myValues;
	
	public GameObject(float x, float y, float dimx, float dimy){
			myValues = new GameObjectValues();
			myValues.setCoordX(x);
			myValues.setCoordY(y);
			myValues.setDimX(dimx);
			myValues.setDimY(dimy);		
	}
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

	public Texture getTexture() {
		return holder.getMyTexture();
	}
	
	public void setTexture(Texture t){
		holder = new TextureHolder(t, new Tex(0, 1, 0, 1));		
	}
	
	public void SetTex(Tex t){
		holder.setMyCoords(t);	
	}
	
	public float getX() {
		return myValues.getCoordX();
	}

	public float getY() {		
		return myValues.getCoordY();
	}

	
	public float getDimX() {		
		return myValues.getDimX();
	}

	public float getDimY() {		
		return myValues.getDimY();
	}

	
	public void setX(float x) {
		myValues.setCoordX(x);
	}

	
	public void setY(float y) {		
	    myValues.setCoordY(y);
	}

	
	public void setDimX(float dimx) {		
		myValues.setDimX(dimx);
	}


	public void setDimY(float dimy) {		
		myValues.setDimY(dimy);
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
	@Override
	public void beginContact(Contact arg0) {
	}
	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
