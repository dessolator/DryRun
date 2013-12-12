package dryrun.game.objects.bonus;


import org.jbox2d.common.Vec2;

import dryrun.game.engine.DrawObject;
import dryrun.game.engine.GameObject;
import dryrun.game.engine.TextureHolder;
import dryrun.game.engine.interfaces.Collidable;
import dryrun.game.engine.interfaces.Drawable;
import dryrun.game.objects.BonusCreator;
import dryrun.game.objects.Player;


public abstract class Bonus extends GameObject implements Collidable, Drawable {
	//bonus ima index, dodeljen pri kreiranju
	//ima flag da li je sada spawnovan
	//ima poizciju x,y
	protected int myInd;
	protected boolean amIspawned = false;
	protected Vec2 position;		
	protected TextureHolder holder;
	//konstruktor
	public Bonus(float x, float y, float dimx, float dimy, int myInd,
			Vec2 position) {
		super(x, y, dimx, dimy);
		this.myInd = myInd;
		this.position = position;
	}

	//interfejs overloaded metode
	@Override
	public void render() {
		if(isAmIspawned()){
			DrawObject.draw(this);
		}
	}
	

	public void collided(Player p){
		applyBonus(p);
		BonusCreator.getBonusCreator().notifyPickedUp(myInd);		
	}
	
	public abstract void applyBonus(Player p);	

	
	//getters and setters	
	public boolean isAmIspawned() {
		return amIspawned;
	}

	public void setAmIspawned(boolean amIspawned) {
		this.amIspawned = amIspawned;
	}

	public Vec2 getPosition() {
		return position;
	}

	public void setPosition(Vec2 position) {
		this.position = position;
	}

	public abstract void undo();
	
}
