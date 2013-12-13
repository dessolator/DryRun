package dryrun.game.objects;

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
import dryrun.game.mechanics.Game;
import dryrun.game.mechanics.Level;
import dryrun.game.common.GameObjectValues;
import dryrun.game.common.cars.CarModel;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.GameObject;
import dryrun.game.engine.interfaces.Collidable;
import dryrun.game.objects.bonus.Bonus;
import static dryrun.game.engine.network.NetConstants.P2M;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class Player extends GameObject implements Collidable {
	//player related
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
    public static int health = 400;
	public static int ammo = 0; 
	public static int minescount = 0;
	public static double nitro = 0.0;
	public static int rockets = 0;
	private boolean first;
	
	public static final int MAX_HEALTH=400;

	//constructor
	public Player(String n, CarModel carModel, float x, float y){
		super(x,y,0,0);
		myModel=carModel;
		BodyDef boxDef = new BodyDef();//make new jbox2d body definition
        boxDef.position.set(x*P2M,y*P2M);//set the position in meters
        boxDef.linearDamping=0.5f;
        boxDef.angularDamping=0.7f;
        boxDef.type = BodyType.DYNAMIC;//almost all game objects are dynamic
        PolygonShape boxShape = new PolygonShape();//define shape as a polygon
        boxShape.setAsBox(myModel.dimX/2, myModel.dimY/2);//namely as a box
        Body box = Game.getMyLevel().world.createBody(boxDef);//create a body base on the body definition
        FixtureDef boxFixture = new FixtureDef();//create a fixture definition
        boxFixture.density = 222.222f;//adjust density
        boxFixture.shape = boxShape;//set the fixture shape to the previously created polygon
        box.createFixture(boxFixture);//attach the fixture to the body(or rather vice verca)
//     	Level.bodies.add(box);//TODO add to the Level list of boddies
        this.myBody=box;//attach the body to the game object
        box.setUserData(this);//set the body userData to it's parent object
		myValues = new GameObjectValues(myBody.getWorldCenter().x, myBody.getWorldCenter().y, myBody.getAngle(), name);
		myValues.setCoordX(x);
		myValues.setCoordY(y);
//		myValues.setDimX(myModel.dimX);
//		myValues.setDimY(myModel.dimY);	
		name=n;
		first=true;
		
	}
	public GameObjectValues getMyValues(){
		return new GameObjectValues(myBody.getWorldCenter().x, myBody.getWorldCenter().y, myBody.getAngle(), name);
	}
	
	//player update input should be here
	public void update() {
		if(first){
			mainPlayerX=Game.getMyLevel().getMyPlayer().myBody.getWorldCenter().x;
			mainPlayerY=Game.getMyLevel().getMyPlayer().myBody.getWorldCenter().y;
		first=!first;
		}
		readInput();
	}

	Vec2 multiply(Vec2 a,float b){
		return new Vec2(a.x*b,a.y*b);
	}
	
	private void readInput() {
		//System.out.println("x: " + myBody.getWorldCenter().x + ", y:" + myBody.getWorldCenter().y);
		//TODO rearange
		Vec2 position= myBody.getWorldCenter();//body position
		Vec2 direction=new Vec2((float)Math.cos(myBody.getAngle()),(float)Math.sin(myBody.getAngle()));//(cos(a),sin(a))
		Vec2 axialPoint = new Vec2 (position.x-direction.x*myModel.axialPointOffset,position.y-direction.y*myModel.axialPointOffset);//position - axial offset
		Vec2 accelForce = multiply(direction,myModel.accelForce);//Facc *dir
		Vec2 brakeForce = multiply(direction,myModel.brakeForce);//Fbra * dir
		Vec2 revBrakeForce=multiply(brakeForce,-1);//-Fbra *dir
		Vec2 reverseForce = multiply(direction, myModel.reverseForce);//Frev *dir
		Vec2 currentVelocity=myBody.getLinearVelocity();//V
		Vec2 desiredVelocity=multiply(direction,Vec2.dot(direction, currentVelocity));// dir * (dir dot V)
		Vec2 velocityDifference=new Vec2(desiredVelocity.x-currentVelocity.x,desiredVelocity.y-currentVelocity.y);//Vdes - Vcurr
		Vec2 impulse = multiply(velocityDifference,myBody.getMass()); //Vdes*M
		
		if ( impulse.length() > (myModel.maxTireGrip*currentVelocity.length()))
			impulse =multiply(impulse, ((myModel.maxTireGrip*currentVelocity.length()) / impulse.length()));
		
		myBody.applyLinearImpulse(impulse,position);
		mainPlayerOldX=mainPlayerX;
		mainPlayerOldY=mainPlayerY;		
//		System.out.println(myBody.getLinearVelocity().length());
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			//System.out.println(myBody.getLinearVelocity().length());
			if((this.myBody.getLinearVelocity().x*Math.cos(this.myBody.getAngle()))>=0){
				this.myBody.applyForce(accelForce,axialPoint);
			}
			else{
				this.myBody.applyForce(brakeForce,	axialPoint);
				
			}
		}
	
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){			
			if((float)(this.myBody.getLinearVelocity().x*Math.cos(this.myBody.getAngle())) <= 0){
				this.myBody.applyForce(reverseForce,axialPoint);
			}
			else{
				this.myBody.applyForce(revBrakeForce,	axialPoint);
				
			}
			
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			if((this.myBody.getLinearVelocity().x*Math.cos(this.myBody.getAngle()))>=0)
				this.myBody.setTransform(this.myBody.getWorldCenter(), this.myBody.getAngle()+(this.myModel.turnAngle*(((myBody.getLinearVelocity().length())>10)?10:myBody.getLinearVelocity().length())));
			else
				this.myBody.setTransform(this.myBody.getWorldCenter(), this.myBody.getAngle()-(this.myModel.turnAngle*(((myBody.getLinearVelocity().length())>10)?10:myBody.getLinearVelocity().length())));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			if((this.myBody.getLinearVelocity().x*Math.cos(this.myBody.getAngle()))>=0)
				this.myBody.setTransform(this.myBody.getWorldCenter(), this.myBody.getAngle()-(this.myModel.turnAngle*(((myBody.getLinearVelocity().length())>10)?10:myBody.getLinearVelocity().length())));
			else
				this.myBody.setTransform(this.myBody.getWorldCenter(), this.myBody.getAngle()+(this.myModel.turnAngle*(((myBody.getLinearVelocity().length())>10)?10:myBody.getLinearVelocity().length())));
		}
		mainPlayerX=this.myBody.getWorldCenter().x;
		mainPlayerY=this.myBody.getWorldCenter().y;

		glTranslatef(-(mainPlayerX-mainPlayerOldX)/P2M, (mainPlayerY-mainPlayerOldY)/P2M, 0);		
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
		return -this.myBody.getAngle();
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
	
	public float getDimX(){
		return myModel.dimX/P2M;
	}
	
	public float getDimY(){
		return myModel.dimY/P2M;
	}

	//bonus related methods
	
	public int getMinescount() {
		return minescount;
	}


	public void setMinescount(int i) {
		minescount+=1;		
	}


	public void setNitro(int i) {
		nitro = i;
		
	}


	public void addHealth(int i) {
		health +=i;
		if(health>=MAX_HEALTH){
			health = MAX_HEALTH;
			}
		}
	}
