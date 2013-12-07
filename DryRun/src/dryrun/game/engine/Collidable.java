package dryrun.game.engine;

import dryrun.game.common.*;
import dryrun.game.objects.*;


public interface Collidable {
	//desila se kolizija
	public void collided(GameObject o);
}