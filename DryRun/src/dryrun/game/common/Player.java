package dryrun.game.common;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.Velocity;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.objects.*;
import dryrun.game.common.*;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Movable;
import dryrun.game.engine.Tex;
import dryrun.game.engine.Updateable;
import dryrun.game.objects.GameObject;
import dryrun.game.objects.bonus.Timer;

public class Player extends GameObject implements Movable {
	//player related
	private PlayerValues myStats;
	private ArrayList<Timer> myTimers;
	private String name;
	private String carType;
	private Vec2 velocity;
	private static double speed=0;//implementirati kasnije
	private double rangle = (double)(Math.PI/160);
	private double angle = 0;
	public Vec2 direction;
	private static boolean something=false;// da li je nesto kliknuto na tastaturi od koriscenih tastera
	
	//speed related constants
	private static final float maxSpeed=5;
	private static final double maxThrottle= 0.5; 
	private static final double maxrthrottle = -0.2;
	private static final float maxReverse = -2;
	//speed related changes d
	private static double throttle = 0.00;
	private double dthrottle = 0.05;	
	private double breaks = -0.1;
	private double inertivebreaks = 0.001;


	
	public Player(String n, String carType, float x, float y, float dimx, float dimy){
		super(x, y, dimx, dimy);
		this.carType=carType;
		name=n;	
		myStats=new PlayerValues();
		direction = new Vec2(1,0);
		velocity = new Vec2(4, 0);
		try {			
			holder=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/player.png"))),new Tex(0f,0f,1f,1f));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerValues getMyStats() {
		return myStats;
	}

	public void setMyStats(PlayerValues myStats) {
		this.myStats = myStats;
	}

	public ArrayList<Timer> getMytimers() {
		return myTimers;
	}

	public void setMyTimers(ArrayList<Timer> mytimers) {
		this.myTimers = mytimers;
	}

	public void update() {
	}
	
	//direction logic
	public void setDirectionR(){
		float	oldx = direction.x;
		float	oldy = direction.y;
			direction.x=(float) ( oldx * Math.cos(-rangle) - oldy*Math.sin(-rangle));			
			direction.y =(float)( oldx*Math.sin(-rangle) + oldy*Math.cos(-rangle));				
	}
	
	
	public void setdirectionL(){
		float	oldx = direction.x;
		float	oldy = direction.y;
			direction.x=(float) ( oldx * Math.cos(rangle) - oldy*Math.sin(rangle));			
			direction.y =(float)( oldx*Math.sin(rangle) + oldy*Math.cos(rangle)); 
			}
	
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
	
	public void setVelocityandAngleR(){
		velocity.x = (float)speed*direction.x;
		velocity.y = (float)speed*direction.y;
		angle+=rangle;
	}	
	public void setVelocityandAngleL(){
		velocity.x =(float) speed*direction.x;
		velocity.y =(float)speed*direction.y;
		angle-=rangle;
	}
	
	
	@Override
	public void move(int i) {
		switch(i){
		
		case 1:{
			System.out.println("mindfuck");
			break;
		}
		
		case 2:{ //stisnuto napred ili strelica napred			
			if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
				setdirectionL();
				angle -= rangle;
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){					
				setDirectionR();
				angle += rangle;			
			}		
			velocity.x =(float) speed*direction.x;
			velocity.y =(float) speed*direction.y;
			
			calcSpeedUp();
			
			// setting player coords
			myValues.setCoordX((myValues.getCoordX()+velocity.x));
			myValues.setCoordY((myValues.getCoordY()+velocity.y));
			
			//glPushMatrix();
			//glTranslatef(-myValues.getCoordX(), -myValues.getCoordY(), 0);
			//glPopMatrix();
			break;
		}
		case -2:{ //stisnuto nazad ili s
			
			if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
				setDirectionR();
				angle+= rangle;			
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){				
				setdirectionL();
				angle -= rangle;
			}	
			velocity.x =(float) speed*direction.x;
			velocity.y =(float)speed*direction.y;
			
			calcSpeedDown();
			
			myValues.setCoordX((myValues.getCoordX()+velocity.x));
			myValues.setCoordY((myValues.getCoordY()+velocity.y));
			//glPushMatrix();
			//glTranslatef(myValues.getCoordX(), myValues.getCoordY(), 0);
			//glPopMatrix();
			break;
			}
		}
	}

	@Override
	public void render() {
		DrawObject.draw(this);
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setMyStats(GameObjectValues stats) {
		myStats=(PlayerValues) stats;
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
	
	
		
//		pokusaj kretanja objekata

	public void playerInput(){//all movement keys pressed
			if(	(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT))&&
					(Keyboard.isKeyDown(Keyboard.KEY_S) ||Keyboard.isKeyDown(Keyboard.KEY_DOWN))
					|| ((Keyboard.isKeyDown(Keyboard.KEY_W) ||Keyboard.isKeyDown(Keyboard.KEY_UP))&&
							(Keyboard.isKeyDown(Keyboard.KEY_S) ||Keyboard.isKeyDown(Keyboard.KEY_DOWN)))){
				Player.something=true;
				this.move(1);				
			}			
			
			if(Keyboard.isKeyDown(Keyboard.KEY_W) ||Keyboard.isKeyDown(Keyboard.KEY_UP)){//if UP was pressed
				Player.something=true;				
				this.move(2);				
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S) ||Keyboard.isKeyDown(Keyboard.KEY_DOWN)){//if DOWN was pressed 
				Player.something=true;					
				this.move(-2);				
			}	
			System.out.println("speed = "+speed+"  something = "+something);
			
			//nista nije pritisnuto
			if(Player.something == false) {				
				if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){				
					setdirectionL();
					angle-=rangle;
					
				}				
				if(Keyboard.isKeyDown(Keyboard.KEY_D)||Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){					
					setDirectionR();
					angle+=rangle;			
				}
				returnToStatic();
				velocity.x =(float) speed*direction.x;
				velocity.y =(float)speed*direction.y;				
				myValues.setCoordX((myValues.getCoordX()+velocity.x));
				myValues.setCoordY((myValues.getCoordY()+velocity.y));					
			}	
			
			else Player.something=false;
	}
}
