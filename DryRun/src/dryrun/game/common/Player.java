package dryrun.game.common;

import java.util.ArrayList;

import dryrun.game.common.*;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Movable;
import dryrun.game.engine.Updateable;
import dryrun.game.objects.GameObject;
import dryrun.game.objects.bonus.Timer;

public class Player extends GameObject implements Drawable, Movable, Updateable {
	private PlayerValues myStats;
	private ArrayList<Timer> myTimers;
	private String name;
	
	public Player(String n){
		name=n;
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