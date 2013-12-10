package dryrun.game.common;





import org.jbox2d.common.Vec2;
import org.lwjgl.opengl.Display;


import dryrun.game.mechanics.Level;
import dryrun.game.objects.bonus.*;



public class BonusCreator extends Player {
	//zaduzen da inicira bonuse, i respawn
	private static BonusCreator b=null;
	
	
	public static long timeSincePickedUp[];
	//vracanje singleton bonus creatora
	public static BonusCreator getBonusCreator(){
		if(BonusCreator.b==null) return new BonusCreator();
		else return b;
	}
	
	public BonusCreator getB(){
		return b;
	}

	private BonusCreator(){
		super("generator","bonusa",0,0,0,0);
		for(int i=0; i<Level.MAX_BONUSES; i++) {
			timeSincePickedUp[i]=0;
			float locX = Level.positionsX[i];
			float locY = Level.positionsY[i];
			this.myObjects.set(i, spawnBonuses(locX, locY, i));						
		}
	}
	
	
	public static void loadBonusLoc(int ind){
		for(int i=0; i< Level.MAX_BONUSES; i++){
				
			}
		}

	
	public synchronized Bonus spawnBonuses(float locX, float locY,int ind){
			double factor=Math.random();
			
			if(factor>0.75){ 
				return new MachineGunBonus(locX, locY, Display.getWidth()/10, Display.getHeight()/10 ,ind,  new Vec2(locX, locY));
			}
				
			else if(factor>0.5  && factor <= 0.75){
				return new Mines(locX, locY, Display.getWidth()/10, Display.getHeight()/10 ,ind,  new Vec2(locX, locY));
			}
				
			else if(factor>0.25 && factor <= 0.5){
				return new Heal(locX, locY, Display.getWidth()/10, Display.getHeight()/10 ,ind,  new Vec2(locX, locY));
				
			}
				
			else if(factor>=0.00  && factor <= 0.25){
				return new NitroBonus(locX, locY, Display.getWidth()/10, Display.getHeight()/10 ,ind,  new Vec2(locX, locY));
			}
			else return null;
	}
	
	public synchronized void reSpawnBonuses(int ind){
		double factor=Math.random();
		
		if(factor>0.75){}
			
		else if(factor>0.5  && factor <= 0.75){}
			
		else if(factor>0.25 && factor <= 0.5){}
			
		else if(factor>0.15  && factor <= 0.25){}
			
		else if(factor>0  && factor <= 0.15){}			

}


	public synchronized void check() {
		for(int i=0; i<Level.MAX_BONUSES;i++){			
			if(!((Bonus)(this.getMyObjects().get(i))).isAmIspawned() && (timeSincePickedUp[i]-System.nanoTime()>15000)){
			//	respawnBonuses(i);
			}
		}
		
	}


	public synchronized void notifyPickedUp(int i) {
		((Bonus)(this.getMyObjects().get(i))).setAmIspawned(false);
		timeSincePickedUp[i]=System.nanoTime()*1000;		
	}


}
