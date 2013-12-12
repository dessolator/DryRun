package dryrun.game.mechanics;

import java.io.Serializable;
import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static dryrun.game.engine.LoadTex.levelBackground;
import dryrun.game.common.BonusThread;
import dryrun.game.common.Player;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Tex;
import dryrun.game.engine.Updateable;
import dryrun.game.network.server.Server;
import dryrun.game.objects.TextureHolder;



@SuppressWarnings("serial")
public class Level implements Drawable, Serializable,Updateable {
	
	public TextureHolder th;//Level Background texture
	public ArrayList<Player> players;//arrayList of all the Players
	public ArrayList<Wall> walls;//arrayList of all the walls
	
	//bonus related	
	public static int positionsX[] ={};//predefined postiions
	public static int positionsY[] ={};// FILL IT UP KESLEEEERRRRRR
	public static final int MAX_BONUSES = 30;//max bonuses
	public static BonusThread bonusGenerator=null;

	public static Vec2 pos1 = new Vec2(5, 5);
	public static Vec2 pos2 = new Vec2(5, 15);
	public static Vec2 pos3 = new Vec2(15, 5);
	public static Vec2 pos4 = new Vec2(15, 15);
	//player related
	private static Player myPlayer;
	private static int maxPlayers = 4;
	private int numOfPlayers = 0;
	

	
	public ArrayList<Checkpoint> checkpoints;//arrayList of all the Checkpoints
	public static final World world = new World(new Vec2(0, 0));//world for box2d purposes	

	

	
	public Level(Player p){
			th=new TextureHolder(levelBackground,new Tex(0f,1f,0f,1f));//load the texture			
			if(Server.getServer()!=null) {
				bonusGenerator = new BonusThread();				
				players.add(new Player("ime","bmw",positionsX[numOfPlayers] ,positionsY[numOfPlayers] , 200, 100));
				numOfPlayers++;
			}
	}
	
	@Override
	public void render() {
		DrawObject.draw(this);

	}
	
	@Override
	public void update() {
		world.step(1 / 60f, 8, 3);		
	}	
	
	/*
	 * Getters and Setters
	 */
	@Override
	public Texture getTexture() {
		return th.getMyTexture();
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
		return Display.getWidth()*10;//TODO map stretch factor
	}

	@Override
	public float getDimY() {
		return Display.getHeight()*10;//TODO map stretch factor
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
	public double getAngle() {
		return 0;
	}
}
