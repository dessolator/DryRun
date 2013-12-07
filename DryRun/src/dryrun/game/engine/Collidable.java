package dryrun.game.engine;

import dryrun.game.common.*;
import dryrun.game.objects.*;
import dryrun.game .objects.GameObject;


public interface Collidable {
	//desila se kolizija
	public void collided(GameObject o);
}