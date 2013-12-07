package dryrun.game.engine;

import game.common.GameObject;


public interface Collidable {
	//desila se kolizija
	public void collided(GameObject o);
}