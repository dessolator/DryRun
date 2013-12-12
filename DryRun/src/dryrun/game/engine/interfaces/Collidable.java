package dryrun.game.engine.interfaces;

import dryrun.game.objects.Player;

//used to impplemets collision function where needed
public interface Collidable {
	
	public void collided(Player b);
	
}