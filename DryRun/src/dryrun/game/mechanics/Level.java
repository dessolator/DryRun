package dryrun.game.mechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.common.Player;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.objects.TextureHolder;
import dryrun.game.objects.bonus.Bonus;

public class Level implements Drawable {
	TextureHolder th;
	ArrayList<Player> players;
	ArrayList<Wall> walls;
	ArrayList<Bonus> bonuses;
	ArrayList<Checkpoint> checkpoints;
	
	public Level(){
		try {
			th=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(0f,((float)3/10+(float)1/200),1f,((float)4/10+(float)1/200)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		players=new ArrayList<Player>();
		walls=new ArrayList<Wall>();
		bonuses=new ArrayList<Bonus>();
		checkpoints=new ArrayList<Checkpoint>();
	}
	
	@Override
	public Texture getTexture() {
		
		return th.getMyTexture();
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
