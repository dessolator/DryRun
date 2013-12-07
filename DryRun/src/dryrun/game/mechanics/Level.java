package dryrun.game.mechanics;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.common.Player;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.engine.Updateable;
import dryrun.game.objects.TextureHolder;
import dryrun.game.objects.bonus.Bonus;

public class Level implements Drawable,Updateable {
	TextureHolder th;
	ArrayList<Player> players;
	ArrayList<Wall> walls;
	ArrayList<Bonus> bonuses;
	ArrayList<Checkpoint> checkpoints;
	
	public Level(){
		th=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(0f,((float)3/10+(float)1/200),1f,((float)4/10+(float)1/200)));
		
	}
	
	@Override
	public Texture getTexture() {
<<<<<<< HEAD
		return th.getMyTexture();
=======
		// TODO Auto-generated method stub
		return null;
>>>>>>> refs/heads/master
	}

	@Override
	public void render() {
		for(Player p :players){
			p.render();
		}
		for(Wall w: walls){
			w.render();
		}
		for(Bonus b:bonuses){
			b.render();
		}
	}

	@Override
	public float getX() {
		return Display.getWidth()/2;
	}

	@Override
	public float getY() {
		return Display.getHeight()/2;
	}

	@Override
	public float getDimX() {
		return Display.getWidth();
	}

	@Override
	public float getDimY() {
		return Display.getHeight();
	}

	@Override
	public float getTexX1() {
		return 0;
	}

	@Override
	public float getTexX2() {
		return 1;
	}

	@Override
	public float getTexY1() {
		return 0;
	}

	@Override
	public float getTexY2() {
		return 1;
	}

	@Override
	public void update() {
		for(Player p :players){
			p.update();
		}
		for(Wall w: walls){
			w.update();
		}
		for(Bonus b:bonuses){
			b.update();
		}
		for(Checkpoint c:checkpoints){
			c.update();
		}
	}

}
