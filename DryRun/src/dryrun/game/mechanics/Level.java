package dryrun.game.mechanics;

import java.io.Serializable;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import static dryrun.game.engine.LoadTex.levelBackground;
import dryrun.game.common.GameObjectValues;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Tex;
import dryrun.game.engine.TextureHolder;
import dryrun.game.engine.interfaces.Drawable;
import dryrun.game.engine.interfaces.Updateable;
import dryrun.game.engine.network.NetFramework;
import dryrun.game.engine.network.client.Client;
import dryrun.game.engine.physics.CollisionListener;
import dryrun.game.objects.BonusThread;
import dryrun.game.objects.Player;
import dryrun.game.objects.Wall;
import dryrun.game.objects.Checkpoint;



@SuppressWarnings("serial")
public class Level implements Drawable, Serializable,Updateable {
	
	public TextureHolder th;//Level Background texture
	public ArrayList<Player> players;//arrayList of all the Players
	public ArrayList<Wall> walls;//arrayList of all the walls
//	public ArrayList<Bonus> bonuses;//arrayList of all the bonuses
	
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
//	private static Player myPlayer;
	private static int maxPlayers = 4;
	private int numOfPlayers = 0;
	

	
	public ArrayList<Checkpoint> checkpoints;//arrayList of all the Checkpoints
	public static final World world = new World(new Vec2(0, 0));//world for box2d purposes	

	private static CollisionListener myListener =new CollisionListener();
	protected static Player myPlayer;
	protected static NetFramework net;
		
	public Level(NetFramework nf){
		net=nf;
		world.setContactListener(myListener);
		th=new TextureHolder(levelBackground,new Tex(0,1,0,1));//load the texture
		players=new ArrayList<Player>();
	}
	
	@Override
	public void render() {
		DrawObject.draw(this);

	}
	
	@Override
	public void update() {
//        parseAndUpdate(net.receive());
//        myPlayer.update();
//        for(Wall w: walls){
//                w.update();
//        }
//        for(Bonus b:bonuses){
//                b.update();
//        }
//        for(Checkpoint c:checkpoints){
//                c.update();
//        }
//        GameObjectValues p[]=new GameObjectValues[5];
//        p[0]=myPlayer.getMyValues();
//        net.send(p);
		world.step(1/60f, 8, 3);
}	
	protected void parseAndUpdate(GameObjectValues[] receive) {
		for(int i=0;i<5;i++){
			for(Player p :players){
				if(p.getName().equals(receive[i].getName())){
					p.myBody.setTransform(new Vec2(receive[i].getCoordX(),receive[i].getCoordY()), 0);
					
				}
			}
		}
		// TODO Auto-generated method stub

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
		return Display.getWidth()*15;//TODO map stretch factor
	}

	@Override
	public float getDimY() {
		return Display.getHeight()*15;//TODO map stretch factor
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

	public Player getMyPlayer() {
		return myPlayer;
	}
	public void setMyPlayer(Player myPlayer) {
		Level.myPlayer=myPlayer;
	}

	public void initialState() {
		GameObjectValues[] p=net.startGame();
		parseAndUpdate(p);		
	}
}
