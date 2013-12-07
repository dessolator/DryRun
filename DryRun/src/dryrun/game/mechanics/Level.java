package dryrun.game.mechanics;

import org.newdawn.slick.opengl.Texture;

import dryrun.game.engine.Drawable;
import dryrun.game.objects.TextureHolder;

public class Level implements Drawable {
	TextureHolder th;
	ArrayList<Player> players;
	ArrayList<Wall> walls;
	ArrayList<Bonus> bonuses;
	ArrayList<Checkpoint> checkpoints;
	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDimX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDimY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return 0;
	}

}
