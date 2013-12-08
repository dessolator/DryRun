package dryrun.game.mechanics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import dryrun.game.common.GameObjectValues;
import dryrun.game.common.Player;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.engine.Updateable;
import dryrun.game.network.NetFramework;
import dryrun.game.objects.TextureHolder;
import dryrun.game.objects.bonus.Bonus;

public class Level implements Drawable,Updateable {
	protected TextureHolder th;
	protected ArrayList<Player> players;
	protected ArrayList<Wall> walls;
	protected ArrayList<Bonus> bonuses;
	protected ArrayList<Checkpoint> checkpoints;
	protected NetFramework net;
	protected Player myPlayer;
	protected String myName;
	protected Player bonusPlayer;
	
	public Level(String myName){
		try {
			th=new TextureHolder(TextureLoader.getTexture("PNG", new FileInputStream(new File("res/button-sprite.png"))),new Tex(0f,((float)3/10+(float)1/200),1f,((float)4/10+(float)1/200)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.myName=myName;
		players=new ArrayList<Player>();
		walls=new ArrayList<Wall>();
		bonuses=new ArrayList<Bonus>();
		checkpoints=new ArrayList<Checkpoint>();
	}
	
	public void setNetFramework(NetFramework n){
		net=n;
	}
	
	
	public void addPlayer(String name,String carType){
		Player p=new Player(name,carType);
		players.add(p);
		if(name.equals(myName)){
			myPlayer=p;
		}
		
	}
	
	@Override
	public Texture getTexture() {
		return th.getMyTexture();
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
		parseAndUpdate(net.receive());
		myPlayer.update();
		for(Wall w: walls){
			w.update();
		}
		for(Bonus b:bonuses){
			b.update();
		}
		for(Checkpoint c:checkpoints){
			c.update();
		}
		GameObjectValues p[]=new GameObjectValues[5];
		p[0]=myPlayer.getMyValues();
		net.send(p);
	}

	protected void parseAndUpdate(GameObjectValues[] receive) {
		// TODO Auto-generated method stub
		
	}

}
