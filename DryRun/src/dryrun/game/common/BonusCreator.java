package dryrun.game.common;

import dryrun.game.mechanics.Game;
import dryrun.game.mechanics.Level;
import dryrun.game.objects.bonus.*;

public class BonusCreator extends Player {
	private static float locX[];
	private static float locY[];
	
	public static boolean isItSpawned[];
	
	public static long timeSincePickedUp[];

	public BonusCreator() {
		super("Bonus", "", 0, 0, 0, 0);
		Bonus.setOwner(this);
		loadBonusLoc();
		for(int i=0;i<Level.MAX_BONUSES;i++){
				isItSpawned[i]=true;
				spawnBonus(locX[i],locY[i],i);
				timeSincePickedUp[i]=System.nanoTime();
			}
		}
		
	
	
	public static void loadBonusLoc(){locX=Game.getMyLevel().getBonusLocX(); Game.getMyLevel().getBonusLocY();}
	
	
	
	public synchronized void spawnBonus(float locX, float locY,int ind){
			double factor=Math.random();
			this.getMyStats().getObjList().remove(ind);
			if(factor>0.75)
				this.getMyStats().getObjList().add(ind, new MachineGunBonus(ind,locX,locY));			
	//		else if(factor>0.5)
	//			this.getMyStats().getObjList().add(ind, new NitroBonus(ind,locX,locY));
	//		else if(factor>0.25)
	//			this.getMyStats().getObjList().add(ind, new Rocketbonus(ind,locX,locY));
			
	//		else this.getMyStats().getObjList().add(ind, new Mines(ind,locX,locY));
	}



	public synchronized void check() {
		for(int i=0; i<Level.MAX_BONUSES;i++){
			if(!isItSpawned[i]&&timeSincePickedUp[i]-System.nanoTime()>15000){
				spawnBonus(locX[i],locY[i], i);
			}
		}
		
	}



	public static synchronized void notifyPickedUp(int i) {
		isItSpawned[i]=false;
		timeSincePickedUp[i]=System.nanoTime();
		
	}



	public static synchronized boolean isBonusSpawned(int i) {
		
		return isItSpawned[i];
	}

}
