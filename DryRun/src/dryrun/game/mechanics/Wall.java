package dryrun.game.mechanics;

import dryrun.game.engine.Collidable;
import dryrun.game.engine.DrawObject;
import dryrun.game.objects.GameObject;

public abstract class Wall extends GameObject implements Collidable{

	public Wall(float x, float y, float dimx, float dimy){
		getMyValues().setCoordX(x);
		getMyValues().setCoordY(y);
		getMyValues().setDimX(dimx);
		getMyValues().setDimY(dimy);		
	}
	
	@Override
	public void render() {		
	}

	@Override
	public void collided(GameObject o) {
		// TODO Auto-generated method stub
		//add collision detection
	}

}
