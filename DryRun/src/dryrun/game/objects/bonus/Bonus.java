package dryrun.game.objects.bonus;


import dryrun.game.engine.Collidable;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.objects.GameObject;

public abstract class Bonus extends GameObject implements Collidable, Drawable {
	private static final int MAX_ACTIVE_BONUSES_PER_LEVEL=6;
	private static int bonusesSpawned=0;

	public Bonus(float coordX, float coordY) {
		this.getMyValues().setCoordX(coordX);
		this.getMyValues().setCoordY(coordY);
	}
	
	@Override
	public void render() {
		DrawObject.draw(this);
	}

	
	public static void spawnBonus(){		
		if (bonusesSpawned<MAX_ACTIVE_BONUSES_PER_LEVEL){
			/*double factor=Math.random();
			if(factor>0.86)
				Game.getLevel().addBonus(new MachineGunBonus(b.getCoordX(),b.getCoordY()));
			else if(factor>0.72)
				Game.getLevel().addBonus(new SpeedUpBonus(b.getCoordX(),b.getCoordY()));
			else if(factor>0.58)
				Game.getLevel().addBonus(new Rocketbonus(b.getCoordX(),b.getCoordY()));
			else if(factor>0.44)
				Game.getLevel().addBonus(new RoadSpike(b.getCoordX(),b.getCoordY()));
			else if(factor>0.30)
				PaddleGame.getLevel().addBonus(new Explosive(b.getCoordX(),b.getCoordY()));
			else if(factor>0.16)
				Game.getLevel().addBonus(new Mines(b.getCoordX(),b.getCoordY()));
						
			BonusesSpawned++;*/			
		}		
	}
	
	public abstract void undo();
	
}
