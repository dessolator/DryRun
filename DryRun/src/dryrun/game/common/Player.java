package dryrun.game.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
	private String car_Type;
	
	public Player(String n, String car, float x, float y, float dimx, float dimy){
		super(x, y, dimx, dimy);
		name = n;	
		car_Type = car; 
		myStats=new PlayerValues();
		try {			
			holder=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/player.png"))),new Tex(0f,0f,1f,1f));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	public String getCar_Type() {
		return car_Type;
	}
	public void setCar_Type(String car_Type) {
		this.car_Type = car_Type;
	}
	public ArrayList<Timer> getMyTimers() {
		return myTimers;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		DrawObject.draw(this);

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

}