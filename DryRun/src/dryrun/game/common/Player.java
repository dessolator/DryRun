package dryrun.game.common;

import dryrun.game.common.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Movable;
import dryrun.game.engine.Updateable;
import dryrun.game.objects.GameObject;

public class Player extends GameObject implements Drawable, Movable, Updateable {
	private PlayerValues myStats;
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}
	
	public void setMyStats(GameObjectValues stats) {
		myStats=(PlayerValues) stats;
	}

}
