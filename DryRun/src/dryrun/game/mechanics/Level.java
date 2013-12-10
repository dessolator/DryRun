package dryrun.game.mechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.common.Player;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.objects.TextureHolder;
import dryrun.game.objects.bonus.Bonus;

@SuppressWarnings("serial")
public class Level implements Drawable, Serializable {
	public TextureHolder th;
	public ArrayList<Player> players;
	public ArrayList<Wall> walls;
	public ArrayList<Bonus> bonuses;
	public ArrayList<Checkpoint> checkpoints;
	
	public static final int MAX_BONUSES = 30;
	

	
	public Level(){
		try {
			th=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(0f,((float)3/10+(float)1/200),1f,((float)4/10+(float)1/200)));
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

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}



	public float[] getBonusLocX() {
		return null;
		// TODO Auto-generated method stub
		
	}



	public float[] getBonusLocY() {
		return null;
		// TODO Auto-generated method stub
		
	}

}
