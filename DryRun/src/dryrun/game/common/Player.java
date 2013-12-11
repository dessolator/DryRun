package dryrun.game.common;

import static dryrun.game.engine.LoadTex.playerTex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jbox2d.common.Vec2;
import org.lwjgl.input.Keyboard;
import dryrun.game.objects.TextureHolder;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Movable;
import dryrun.game.engine.Tex;
import dryrun.game.objects.GameObject;
import dryrun.game.objects.bonus.Bonus;
import dryrun.game.objects.bonus.Timer;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class Player extends GameObject implements Movable {
	//player related
	private ArrayList<Timer> myTimers;

	protected List<GameObject> myObjects=Collections.synchronizedList(new ArrayList<GameObject>());
	private String name;
	private Vec2 velocity;
	private static double speed=0;//implementirati kasnije
	private double rangle = (double)(Math.PI/180);
	private double angle = 0;
	private Vec2 direction;
	
	public Vec2 getDirection() {
		return direction;
	}


	public void setDirection(Vec2 direction) {
		this.direction = direction;
	}

	//is w or s clicked
	private static boolean something=false;
	
	//speed related constants
	private static final float maxSpeed=8;
	private static final double maxThrottle= 0.5; 
	private static final double maxrthrottle = -0.2;
	private static final float maxReverse = -3;
	
	
	//speed related changes d
	private static double throttle = 0.00;
	private double dthrottle = 0.025;	
	private double breaks = -0.15;
	private double inertivebreaks = 0.08;

	//bonus related 
	//if a player picks up certain bonus the count increased 
	//for a certain amount ...
	public int ammo = 0; 
	public int minescount = 0;
	public double nitro = 0.0;
	public int health = 0;

	//constructor
	public Player(String n, String carType, float x, float y, float dimx, float dimy){
		super(x, y, dimx, dimy);
		name=n;		
		direction = new Vec2(1,0);
		velocity = new Vec2(4, 0);
		holder=new TextureHolder(playerTex,new Tex(0f,0f,1f,1f));
	}
	
	
	//player update input should be here
	public void update() {		
	}
	
	
	//direction logic
	public void setDirectionR(){
		float	oldx = direction.x;
		float	oldy = direction.y;
			direction.x=(float) ( oldx * Math.cos(-rangle) - oldy*Math.sin(-rangle));			
			direction.y =(float)( oldx*Math.sin(-rangle) + oldy*Math.cos(-rangle));				
	}
	
	
	public void setDirectionL(){
		float	oldx = direction.x;
		float	oldy = direction.y;
			direction.x=(float) ( oldx * Math.cos(rangle) - oldy*Math.sin(rangle));			
			direction.y =(float)( oldx*Math.sin(rangle) + oldy*Math.cos(rangle)); 
			}
	
	
	//speed calculation Logic	
	public void calcSpeedUp(){
		if(speed < 0) {
			speed-=breaks;
			if(speed > 0) speed =0; 
		}
		if(speed == 0) throttle = 0;
		if(speed < maxSpeed || speed >= 0 ){			
			if(throttle < maxThrottle) throttle+=dthrottle;
			speed += throttle;
			if(speed > maxSpeed) speed = maxSpeed;
			if(throttle > maxThrottle) throttle = maxThrottle;
		}		
	}
	
	
	public void calcSpeedDown(){
		if(speed > 0 && speed <= maxSpeed){
			speed += breaks; 
			if(speed < 0) speed = 0;
		}
		if(speed == 0) throttle = 0;
		if(speed <= 0 && speed >= maxReverse){
			if(throttle >  maxrthrottle) throttle-=dthrottle;
			speed += throttle;			
			if(speed < maxReverse) speed = maxReverse;
			if(throttle < maxrthrottle) throttle = maxrthrottle;
		}
	}
	
	public void returnToStatic(){
		if(speed > 0){ 
			speed-=inertivebreaks;
			if(speed < 0) speed = 0;
		}
		else if(speed < 0) {
			speed += inertivebreaks;
			if (speed > 0) speed = 0;
		}
		throttle =0;
	}
	

	
	//very complicated move method
	@Override
	public void move(int i) {
		switch(i){
		
		case 1:{ //blocking when all buttons pressed			
			break;
			}
		
		case 2:{ //stisnuto napred ili strelica napred	
			
			
				if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
					setDirectionL();
					angle -= rangle;
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){					
					setDirectionR();
					angle += rangle;			
				}		
				velocity.x =(float) speed*direction.x;
				velocity.y =(float) speed*direction.y;
				
				
				
				// setting player coords
				myValues.setCoordX((myValues.getCoordX()+velocity.x));
				myValues.setCoordY((myValues.getCoordY()+velocity.y));
				//speed calculation
				calcSpeedUp();
				//screen movement
				glTranslatef(-velocity.x, velocity.y, 0);			
				break;
			
		}
		
		case -2:{ //stisnuto nazad ili s
			if( speed<=0 ){
				if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
					setDirectionR();
					angle+= rangle;			
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){				
					setDirectionL();
					angle -= rangle;
				}			
			}
			if( speed>0){
				if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
					setDirectionL();
					angle -= rangle;
				}
				
				if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){					
					setDirectionR();
					angle += rangle;			
				}	
			}
			
			velocity.x =(float) speed*direction.x;
			velocity.y =(float) speed*direction.y;			
			myValues.setCoordX((myValues.getCoordX()+velocity.x));
			myValues.setCoordY((myValues.getCoordY()+velocity.y));
			//speed calcuation
			calcSpeedDown();
			
			//screen movement
			glTranslatef(-velocity.x, velocity.y, 0);
			break;
			}
		}
	}

	@Override
	public void render() {
		DrawObject.draw(this);
	}
	

	//timer methods
	public ArrayList<Timer> getMytimers() {
		return myTimers;
	}

	public void setMyTimers(ArrayList<Timer> mytimers) {
		this.myTimers = mytimers;
	}
	
	public void addTimer(Timer t){
		myTimers.add(t);
	}
	
	public void checkTimers(){
		for(int i=0; i<myTimers.size(); i++){
			if(myTimers.get(i).isPassed()) {
			 myTimers.get(i).getMyBonus().undo();
			 myTimers.remove(i--);
			}		
		}	
	}	
	
	
		

	// player keyboard input used to render movement
	
	public void playerInput(){//all movement keys pressed
		//all movement keys pressed	
		if(	(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT))&&
					(Keyboard.isKeyDown(Keyboard.KEY_S) ||Keyboard.isKeyDown(Keyboard.KEY_DOWN))
					&& ((Keyboard.isKeyDown(Keyboard.KEY_W) ||Keyboard.isKeyDown(Keyboard.KEY_UP))&&
							(Keyboard.isKeyDown(Keyboard.KEY_D) ||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)))){
				Player.something=true;
				this.move(1);
				}
		//W and S pressed at same time
		if((Keyboard.isKeyDown(Keyboard.KEY_S) ||Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				&& ((Keyboard.isKeyDown(Keyboard.KEY_W) ||Keyboard.isKeyDown(Keyboard.KEY_UP)))){
			Player.something=false;
			this.move(1);
			}
		else{			
			if(Keyboard.isKeyDown(Keyboard.KEY_W) ||Keyboard.isKeyDown(Keyboard.KEY_UP)){//if UP was pressed
				Player.something=true;				
				this.move(2);	
				}			
			if(Keyboard.isKeyDown(Keyboard.KEY_S) ||Keyboard.isKeyDown(Keyboard.KEY_DOWN)){//if DOWN was pressed 
				Player.something=true;					
				this.move(-2);	
				}
		}
			
			
			//nista nije pritisnuto
			if(Player.something == false) {			
				if(speed != 0){
					if(speed > 0){
						if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
							setDirectionL();
							angle-=rangle;							
						}				
						if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){					
							setDirectionR();
							angle+=rangle;			
						}
					}
					if(speed < 0){
						if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
							setDirectionR();
							angle+=rangle;
							
						}				
						if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){					
							setDirectionL();
							angle-=rangle;			
						}
					}
									
					velocity.x =(float) speed*direction.x;
					velocity.y =(float)speed*direction.y;				
					myValues.setCoordX((myValues.getCoordX()+velocity.x));
					myValues.setCoordY((myValues.getCoordY()+velocity.y));	
					glTranslatef(-velocity.x, velocity.y, 0);
				}				
				returnToStatic();
			}				
			else Player.something=false;
	}
	@Override
	public void collided(Player b) {
		// TODO Auto-generated method stub
		
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
		return angle;
	}
	
	public List<GameObject> getMyObjects(){return myObjects;}	

	public  int getAmmo() {
		return ammo;
	}


	public  void setAmmo(int ammo) {
		this.ammo = ammo;
	}


	public  int getMinescount() {
		return minescount;
	}


	public  void setMinescount(int minescount) {
		this.minescount = minescount;
	}


	public  double getNitro() {
		return nitro;
	}


	public void setNitro(double nitro) {
		this.nitro = nitro;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int rockets) {
		this.health = rockets;
	}
}
