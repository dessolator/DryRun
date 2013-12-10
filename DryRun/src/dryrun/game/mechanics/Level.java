package dryrun.game.mechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.common.Player;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.engine.Updateable;
import dryrun.game.objects.TextureHolder;
import dryrun.game.objects.bonus.Bonus;


public class Level implements Drawable, Serializable,Updateable {
	public TextureHolder th;
	public ArrayList<Player> players;
	public ArrayList<Wall> walls;
	public ArrayList<Bonus> bonuses;
	public ArrayList<Checkpoint> checkpoints;
	public static final World world = new World(new Vec2(0, 0));
    public static final Set<Body> bodies = new HashSet<Body>();
	
	public static final int MAX_BONUSES = 30;
	

	
	public Level(){
		try {
			th=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/raceTrack.png"))),new Tex(0,1,0,1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

	
	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return th.getMyTexture();
	}

	@Override
	public void render() {
		DrawObject.draw(this);

	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return Display.getWidth()/2;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return Display.getHeight()/2;
	}

	@Override
	public float getDimX() {
		// TODO Auto-generated method stub
		return Display.getWidth()*10;
	}

	@Override
	public float getDimY() {
		// TODO Auto-generated method stub
		return Display.getHeight()*10;
	}

	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void update() {
		world.step(1 / 60f, 8, 3);
		// TODO Auto-generated method stub
		
	}

}
