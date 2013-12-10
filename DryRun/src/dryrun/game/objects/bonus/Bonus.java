package dryrun.game.objects.bonus;


import dryrun.game.common.BonusCreator;
import dryrun.game.common.Player;
import dryrun.game.engine.Collidable;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.objects.GameObject;

public abstract class Bonus extends GameObject implements Collidable, Drawable {
	private int myInd;
	private static final float BONUS_DIMX=30;
	private static final float BONUS_DIMY=30;
	
	
	public static void setOwner(BonusCreator b){}
	
	public Bonus( int index, float x, float y) {
		super(x, y, BONUS_DIMX, BONUS_DIMY);
		myInd=index;
	}
	
	@Override
	public void render() {
		if(amISpawned()){
			DrawObject.draw(this);
		}
	}
	

	public void collided(Player p){
		applyBonus(p);
		BonusCreator.notifyPickedUp(myInd);
		
		
	}
	
	private void applyBonus(Player p) {}

	public boolean amISpawned(){return BonusCreator.isBonusSpawned(myInd);}
	
	
	public abstract void undo();
	
}
