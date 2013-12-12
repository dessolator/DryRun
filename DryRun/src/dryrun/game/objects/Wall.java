package dryrun.game.objects.bonus;

import dryrun.game.common.Player;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.GameObject;
import dryrun.game.engine.interfaces.Collidable;

public abstract class Wall extends GameObject implements Collidable{

	public Wall(float x, float y, float dimx, float dimy){
		super(x, y, dimx, dimy);		
	}
	
	@Override
	public void render() {		
	}

	@Override
	public void collided(Player o) {
		// TODO Auto-generated method stub
		//add collision detection
	}

}
