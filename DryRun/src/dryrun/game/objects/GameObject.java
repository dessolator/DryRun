package dryrun.game.objects;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.opengl.Texture;
import static dryrun.game.network.NetConstants.P2M;
import dryrun.game.engine.*;
import dryrun.game.mechanics.Level;
import dryrun.game.common.*;

public abstract class GameObject implements Drawable,Updateable,Collidable{
	protected TextureHolder holder;
	protected GameObjectValues myValues;
	protected Body myBody;
	

	
	
	public GameObject(float x, float y, float dimx, float dimy){
		BodyDef boxDef = new BodyDef();//make new jbox2d body definition
        boxDef.position.set(x*P2M,y*P2M);//set the position in meters
        boxDef.type = BodyType.DYNAMIC;//almost all game objects are dynamic
        PolygonShape boxShape = new PolygonShape();//define shape as a polygon
        boxShape.setAsBox(0.75f, 0.75f);//namely as a box
        Body box = Level.world.createBody(boxDef);
        FixtureDef boxFixture = new FixtureDef();
        boxFixture.density = 0.1f;
        boxFixture.shape = boxShape;
        box.createFixture(boxFixture);
        Level.bodies.add(box);
        this.myBody=box;
        box.setUserData(this);
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

}
