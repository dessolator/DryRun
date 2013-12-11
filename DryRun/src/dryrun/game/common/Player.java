package dryrun.game.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import dryrun.game.mechanics.Level;
import dryrun.game.objects.TextureHolder;
import dryrun.game.common.cars.CarModel;
import dryrun.game.engine.Collidable;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Movable;
import dryrun.game.engine.Tex;
import dryrun.game.objects.GameObject;
import dryrun.game.objects.bonus.Bonus;
import dryrun.game.objects.bonus.Timer;
import static dryrun.game.network.NetConstants.P2M;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class Player extends GameObject implements Collidable {
	//player related
//	private ArrayList<Timer> myTimers;
	private CarModel myModel;
	protected List<GameObject> myObjects=Collections.synchronizedList(new ArrayList<GameObject>());
	private String name;

	//bonus related 
	//if a player picks up certain bonus the count increased 
	//for a certain amount ...
    static float mainPlayerX;
    static float mainPlayerOldX;
    static float mainPlayerY;
    static float mainPlayerOldY;
	public static int ammo = 0; 
	public static int minescount = 0;
	public static double nitro = 0.0;
	public static int rockets = 0;

	//constructor
	public Player(String n, CarModel carModel, float x, float y){
		super(x,y,0,0);
		myModel=carModel;
		BodyDef boxDef = new BodyDef();//make new jbox2d body definition
        boxDef.position.set(x*P2M,y*P2M);//set the position in meters
        boxDef.type = BodyType.DYNAMIC;//almost all game objects are dynamic
        PolygonShape boxShape = new PolygonShape();//define shape as a polygon
        boxShape.setAsBox(myModel.dimX/2, myModel.dimY/2);//namely as a box
        Body box = Level.world.createBody(boxDef);//create a body base on the body definition
        FixtureDef boxFixture = new FixtureDef();//create a fixture definition
        boxFixture.density = 222.222f;//adjust density
        boxFixture.shape = boxShape;//set the fixture shape to the previously created polygon
        box.createFixture(boxFixture);//attach the fixture to the body(or rather vice verca)
//     	Level.bodies.add(box);//TODO add to the Level list of boddies
        this.myBody=box;//attach the body to the game object
        box.setUserData(this);//set the body userData to it's parent object
		myValues = new GameObjectValues();
		myValues.setCoordX(x);
		myValues.setCoordY(y);
		myValues.setDimX(myModel.dimX);
		myValues.setDimY(myModel.dimY);	
		name=n;
		mainPlayerX=this.myBody.getWorldCenter().x;
		mainPlayerY=this.myBody.getWorldCenter().y;
	}
	
	
	//player update input should be here
	public void update() {
		readInput();
	}

	
	private void readInput() {
		//TODO if fwd
		//apply force along axis from axialPoint
		//if back
		//apply force opposing axis from axialPoint
		//if left
		//setTransformation
		//if right
		//setTransform
		mainPlayerOldX=mainPlayerX;
		mainPlayerOldY=mainPlayerY;		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			if((this.myBody.getLinearVelocity().x*Math.cos(this.myBody.getAngle()))>=0){
				this.myBody.applyForce(new Vec2((float)(this.myModel.accelForce*Math.cos(this.getAngle())),(float)(this.myModel.accelForce*Math.sin(this.getAngle()))),	new Vec2((this.myBody.getWorldCenter().x-(float) (Math.cos(this.myBody.getAngle())*this.myModel.axialPointOffset)),(this.myBody.getWorldCenter().y-(float) (Math.sin(this.myBody.getAngle())*this.myModel.axialPointOffset))));
//				System.out.println(this.myBody.getLinearVelocity().x+":"+this.myBody.getLinearVelocity().y);
			}
			else{
				this.myBody.applyForce(new Vec2((float)(this.myModel.brakeForce*Math.cos(this.getAngle())),(float)(this.myModel.brakeForce*Math.sin(this.getAngle()))),	new Vec2((this.myBody.getWorldCenter().x-(float) (Math.cos(this.myBody.getAngle())*this.myModel.axialPointOffset)),(this.myBody.getWorldCenter().y-(float) (Math.sin(this.myBody.getAngle())*this.myModel.axialPointOffset))));
				
			}
		}
	
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
//			float moo=(float)(this.myBody.getLinearVelocity().x*Math.cos(this.myBody.getAngle()));
//			double moo=(this.myBody.getLinearVelocity().x*Math.cos(this.myBody.getAngle()));
//			double foo=0.0d;
			if(false){
				this.myBody.applyForce(new Vec2((float)(-this.myModel.reverseForce*Math.cos(this.getAngle())),(float)(-this.myModel.reverseForce*Math.sin(this.getAngle()))),new Vec2((this.myBody.getWorldCenter().x-(float) (Math.cos(this.myBody.getAngle())*this.myModel.axialPointOffset)),(this.myBody.getWorldCenter().y-(float) (Math.sin(this.myBody.getAngle())*this.myModel.axialPointOffset))));
			}
			else{
				this.myBody.applyForce(new Vec2((float)(this.myModel.brakeForce*Math.cos(this.getAngle())),(float)(this.myModel.brakeForce*Math.sin(this.getAngle()))),	new Vec2((this.myBody.getWorldCenter().x-(float) (Math.cos(this.myBody.getAngle())*this.myModel.axialPointOffset)),(this.myBody.getWorldCenter().y-(float) (Math.sin(this.myBody.getAngle())*this.myModel.axialPointOffset))));
				
			}
//			System.out.println(this.myBody.getLinearVelocity().x+":"+this.myBody.getLinearVelocity().y);
			
		}
		mainPlayerX=this.myBody.getWorldCenter().x;
		mainPlayerY=this.myBody.getWorldCenter().y;
//		System.out.println("something");
		glTranslatef(-(mainPlayerX-mainPlayerOldX)/P2M, -(mainPlayerY-mainPlayerOldY)/P2M, 0);
		
		
//			this.myBody.applyForce(direction, point);
//			this.myBody.applyTorque(torque);
		
	}

	@Override
	public void render() {
		
		DrawObject.draw(this);
	}
	

	
	@Override
	public void collided(Player b) {
		
	}	
	
	//bonus 
	public void applyBonus(Bonus bonus) { }

	public void incMachinegunAmmo(int i) {
		ammo += i; 		
		}
	
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getAngle() {
		return Math.toDegrees(this.myBody.getAngle());
	}
	

	
	public List<GameObject> getMyObjects(){
		return myObjects;
		}


	@Override
	public Texture getTexture() {
		return this.myModel.myTex.getMyTexture();
	}


	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public float getTexX2() {
		return 1;
	}


	@Override
	public float getTexY1() {
		return 0;
	}


	@Override
	public float getTexY2() {
		return 1;
	}


}
