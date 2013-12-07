package dryrun.game.engine;

import dryrun.game .objects.GameObject;


public interface Collidable {
	//desila se kolizija
	public void collided(GameObject o);
}