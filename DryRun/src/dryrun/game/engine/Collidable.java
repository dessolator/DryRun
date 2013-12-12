package dryrun.game.engine;

import dryrun.game.common.Player;

//used to impplemets collision function where needed
public interface Collidable {
	
	public void collided(Player b);
	
}