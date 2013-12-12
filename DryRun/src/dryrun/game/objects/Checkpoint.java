package dryrun.game.objects;
import org.lwjgl.opengl.Display;

import dryrun.game.engine.DrawObject;
import dryrun.game.engine.GameObject;
import dryrun.game.engine.interfaces.Collidable;

//class used to represent checkpoints on a map
public abstract class Checkpoint extends GameObject implements Collidable {
	public Checkpoint(float x, float y, float dimx, float dimy){
		super(x, y, dimx, dimy);
	}
	
	//crtanje
	public void render() {	
		DrawObject.draw(this);
	}
	

}

