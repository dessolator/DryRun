package dryrun.game.engine;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.opengl.Texture;

import dryrun.game.engine.interfaces.Collidable;
import dryrun.game.engine.interfaces.Drawable;
import dryrun.game.engine.interfaces.Updateable;
import dryrun.game.mechanics.Level;
import dryrun.game.common.GameObjectValues;
import static dryrun.game.engine.network.NetConstants.P2M;


public abstract class GameObject implements Drawable,Updateable,Collidable{
	
//	protected TextureHolder holder;
	protected GameObjectValues myValues;
	public Body myBody;//TODO make me protected
	
	
	public GameObject(float x, float y, float dimx, float dimy){
//		BodyDef boxDef = new BodyDef();//make new jbox2d body definition
//        boxDef.position.set(x*P2M,y*P2M);//set the position in meters
//        boxDef.type = BodyType.DYNAMIC;//almost all game objects are dynamic
//        PolygonShape boxShape = new PolygonShape();//define shape as a polygon
//        boxShape.setAsBox(dimx*P2M/2, dimy*P2M/2);//namely as a box
//        Body box; //= Level.world.createBody(boxDef);//create a body base on the body definition
//        FixtureDef boxFixture = new FixtureDef();//create a fixture definition
//        boxFixture.density = 0.1f;//adjust density
//        boxFixture.shape = boxShape;//set the fixture shape to the previously created polygon
//        box.createFixture(boxFixture);//attach the fixture to the body(or rather vice verca)
//     	Level.bodies.add(box);//TODO add to the Level list of boddies
//        this.myBody=box;//attach the body to the game object
//        box.setUserData(this);//set the body userData to it's parent object
//		myValues = new GameObjectValues();
//		myValues.setCoordX(x);
//		myValues.setCoordY(y);
//		myValues.setDimX(dimx);
//		myValues.setDimY(dimy);		
	}

	public GameObjectValues getMyValues() {
		return myValues;
	}

//	public Texture getTexture() {
//		return holder.getMyTexture();
//	}
	
	public float getX() {
		return myBody.getWorldCenter().x/P2M;
	}

	public float getY() {		
		return myBody.getWorldCenter().y/P2M;
	}

	
	public float getDimX() {		return 0;
//		return myValues.getDimX()/P2M;
	}

	public float getDimY() {	return 0;	
//		return myValues.getDimY()/P2M;
	}

	
	public void setX(float x) {
		myBody.setTransform(new Vec2(x*P2M, myBody.getWorldCenter().y), 0);
	}

	
	public void setY(float y) {		
		myBody.setTransform(new Vec2(myBody.getWorldCenter().x,y*P2M) , 0);
	}

	
//	public void setDimX(float dimx) {		
////		myValues.setDimX(dimx);
//	}
//
//
//	public void setDimY(float dimy) {		
////		myValues.setDimY(dimy);
//	}
	

//	public float getTexX1() {
//		
//		return holder.getMyCoords().getX1();
//	}
//
//	public float getTexX2() {
//		
//		return holder.getMyCoords().getX2();
//	}
//
//
//	public float getTexY1() {
//		
//		return holder.getMyCoords().getY1();
//	}
//
//
//	public float getTexY2() {
//		
//		return holder.getMyCoords().getY2();
//	}
	

}
