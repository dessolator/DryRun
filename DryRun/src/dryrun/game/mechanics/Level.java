package dryrun.game.mechanics;

import java.io.Serializable;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import static dryrun.game.engine.LoadTex.levelBackground;
import dryrun.game.common.GameObjectValues;
import dryrun.game.common.cars.bmwM5;
import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Tex;
import dryrun.game.engine.TextureHolder;
import dryrun.game.engine.interfaces.Drawable;
import dryrun.game.engine.interfaces.Updateable;
import dryrun.game.engine.network.NetFramework;
import dryrun.game.objects.BonusThread;
import dryrun.game.objects.Player;



@SuppressWarnings("serial")
public class Level implements Drawable, Serializable,Updateable {
	
	public TextureHolder th;//Level Background texture
	public ArrayList<Player> players;//arrayList of all the Players
//	public ArrayList<Wall> walls;//arrayList of all the walls
	
	//bonus related	
	public static int positionsX[] ={ 20, 270};//predefined postiions
	public static int positionsY[] ={ 20, 270};// FILL IT UP KESLEEEERRRRRR
	public static final int MAX_BONUSES = 30;//max bonuses
	public static BonusThread bonusGenerator=null;

//	public static final Vec2 pos1 = new Vec2(5, 5);
//	public static final Vec2 pos2 = new Vec2(5, 15);
//	public static final Vec2 pos3 = new Vec2(15, 5);
//	public static final Vec2 pos4 = new Vec2(15, 15);
	//player related
//	private static Player myPlayer;
//	private static int maxPlayers = 4;
//	private int numOfPlayers = 0;
	

	
//	public ArrayList<Checkpoint> checkpoints;//arrayList of all the Checkpoints
	protected static Player myPlayer;
	protected static NetFramework net;
	private static String playerName;
	protected GameObjectValues p[];
	public World world;
		
	public Level(NetFramework nf,String name){
		world=new World(new Vec2(0, 0));
		Level.playerName=name;
		net=nf;
		th=new TextureHolder(levelBackground,new Tex(0,1,0,1));//load the texture
		players=new ArrayList<Player>();
		p=new GameObjectValues[5];
	}
	
	public void initialState() {
		GameObjectValues[] temp=net.startGame();
		for(int i=0; i<temp.length;i++)if(temp[i]!=null)System.out.println("not null - "+temp[i].getName());else{System.out.println("null");}
		initialParse(temp);		
	}
	private void initialParse(GameObjectValues[] receive){
		for(GameObjectValues v:receive){
			if(v!=null){
				Player temp=new Player(v.getName(),new bmwM5(),v.getCoordX(),v.getCoordY());
//				System.out.println(v.getName());
//				System.out.println(v.getCoordX());
//				System.out.println(v.getCoordY());
				if(v.getName().equals(playerName))
					myPlayer=temp;
				players.add(temp);
			}
		}
	}
	
	protected void parseAndUpdate(GameObjectValues[] receive) {
		if(receive==null)return;
		for(GameObjectValues v:receive){
			if(v!=null){
				for(Player p: players){
					if(p!=null){
//						System.out.println(p.getName()+" : "+v.getCoordX()+" : "+v.getCoordY());
						if(p.getName().equals(v.getName())){
						
							p.myBody.setTransform(new Vec2(v.getCoordX(),v.getCoordY()), v.getAngle());
							p.setX(v.getCoordX()); p.setY(v.getCoordY());
							p.setValues(v); //NEW LINE GameObjectValues never seem to be set
						}						
					}
				}
			}
		}
	}
	
	@Override
	public void render() {
		
		DrawObject.draw(this);
		for(Player p:players){
			p.render();
		}
	}
	
	@Override
	public void update() {
		if(Player.printClientState.get())
    		System.out.println("going into state update");
		GameObjectValues []temp=net.receive();
		if(temp!=null){
			if(Player.printClientState.get()&&temp[0]!=null&&temp[1]!=null){
				System.out.println(""+temp[0].getName()+" : "+temp[0].getCoordX()+" : "+temp[0].getCoordY());
				System.out.println(""+temp[1].getName()+" : "+temp[1].getCoordX()+" : "+temp[1].getCoordY());
			}
		}
		if(temp!=null)
			parseAndUpdate(temp);

      myPlayer.update();

        world.step(1f/60f, 8, 3);
        p=new GameObjectValues[3];
        p[0]=myPlayer.getMyValues();
        net.send(p);
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
//	public void setMyPlayer(Player myPlayer) {
//		Level.myPlayer=myPlayer;
//	}

	public void addPlayer(String name) {}

	public void addPrimaryPlayer(String string) {
		
	}

	
}
