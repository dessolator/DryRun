package dryrun.game.engine;

import org.newdawn.slick.opengl.Texture;

public interface Drawable {
	
	public Texture getTexture();

	public void render();
	
	public float getX();
	
	public float getY();
	
	public float getDimX();
	
	public float getDimY();

}
