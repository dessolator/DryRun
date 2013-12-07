package dryrun.game.gui.menus.misc.frames;


import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.objects.TextureHolder;

import org.newdawn.slick.opengl.Texture;

public abstract class Frame implements Drawable {
	protected TextureHolder frame;
	private float coordX;
	private float coordY;
	private float dimX;
	public Frame(float coordX, float coordY, float dimX, float dimY) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.dimX = dimX;
		this.dimY = dimY;
	}

	private float dimY;
	@Override
	public void render() {
		DrawObject.draw(this);
	}

	@Override
	public Texture getTexture() {
		return frame.getMyTexture();
	}

	@Override
	public float getX() {
		return coordX;
	}

	@Override
	public float getDimX() {
		return dimX;
	}

	@Override
	public float getY() {
		return coordY;
	}

	@Override
	public float getDimY() {
		return dimY;
	}

}
