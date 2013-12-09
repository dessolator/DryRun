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
	private PlayerValues myStats;
	private ArrayList<Timer> myTimers;
	private String name;
	private String carType;
	private Vec2 velocity;
	private static float speed=4;//implementirati kasnije
	private double rangle = (double)(Math.PI/160);
	private double angle = 0;
	public Vec2 direction;


	
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

	@Override
	public void move(int i) {
		switch(i){
		case 1:{//stisnuto desno
			setDirectionR();
			velocity.x = speed*direction.x;
			velocity.y = speed*direction.y;
			angle+=rangle;
			break;			
			}
		
		case -1:{//stisnuto levi
			setdirectionL();
			velocity.x = speed*direction.x;
			velocity.y = speed*direction.y;
			angle-=rangle;
			break;				
		}
				
			
		case 2:{ //stisnuto napred ili strelica
			myValues.setCoordX((myValues.getCoordX()+velocity.x));
			myValues.setCoordY((myValues.getCoordY()+velocity.y));
			//glPushMatrix();
			//glTranslatef(-myValues.getCoordX(), -myValues.getCoordY(), 0);
			//glPopMatrix();
			break;
		}
		case -2:{ //stisnuto nazad ili s
			myValues.setCoordX((myValues.getCoordX()-velocity.x));
			myValues.setCoordY((myValues.getCoordY()-velocity.y));
		//	glPushMatrix();
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

	public void playerInput(){
			if(Keyboard.isKeyDown(Keyboard.KEY_A)||Keyboard.isKeyDown(Keyboard.KEY_LEFT)){//if left was pressed
				this.move(-1);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)|| Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){//if right was pressed
				this.move(+1);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W) ||Keyboard.isKeyDown(Keyboard.KEY_UP)){//if UP was pressed
				this.move(2);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S) ||Keyboard.isKeyDown(Keyboard.KEY_DOWN)){//if DOWN was pressed 
				this.move(-2);
			}
			
		}
}
