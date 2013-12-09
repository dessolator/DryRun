package dryrun.game.mechanics;
import dryrun.game.engine.Collidable;
import dryrun.game.objects.GameObject;

public abstract class Checkpoint extends GameObject implements Collidable {
	public Checkpoint(float x, float y, float dimx, float dimy){
		super(x, y, dimx, dimy);
	}
	
	@Override
	public void render() {		
	}
	

}

