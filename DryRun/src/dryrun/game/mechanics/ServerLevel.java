package dryrun.game.mechanics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import dryrun.game.common.GameObjectValues;
import dryrun.game.common.cars.bmwM5;
import dryrun.game.engine.network.NetFramework;
import dryrun.game.engine.physics.CollisionListener;
import dryrun.game.objects.Player;

@SuppressWarnings("serial")
public class ServerLevel extends Level {
		private float[] playerPositionX={640,1,2,2};//TODO take another look
		private float[] playerPositionY={360,300,1,2};

		private int playerIndex=0;
        private static CollisionListener myListener =new CollisionListener();

    public ServerLevel(NetFramework nf ) {
    	super(nf,"");
        world.setContactListener(myListener);
    }
    public void addPrimaryPlayer(String name){
    	myPlayer=new Player(name,new bmwM5(),playerPositionX[playerIndex],playerPositionY[playerIndex++]);
        players.add(myPlayer);
    }
    public void initialState() {
    	//TODO might need to load GOV
		p=new GameObjectValues[5];
		int i=0;
        for(Player player:players){
            p[i++]=player.getMyValues();
            System.out.println("prvi paket srvr:");
            System.out.println(player.getName()+" x:"+player.getX()+"y:"+player.getY());
            if(i>=5)break;
        }
            net.startGame(p);
    }    	

       
    @Override
    public void update() {
    	if(Player.printServerState.get())
    		System.out.println("going into state update");
    	GameObjectValues []temp=net.receive();
    	if(Player.printServerState.get()){
    		if(temp!=null){
				if(temp[0]!=null)System.out.println(""+temp[0].getName()+" : "+temp[0].getCoordX()+" : "+temp[0].getCoordY());
				if(temp[1]!=null)System.out.println(""+temp[1].getName()+" : "+temp[1].getCoordX()+" : "+temp[1].getCoordY());
    		}
    	}
		if(temp!=null)
			parseAndUpdate(temp);
		
		myPlayer.update();//update player state

        world.step(1f/60f, 8, 3);//step the world
        //TODO might need to load GOV
        /*
         * package up gameState.
         */
        GameObjectValues p[]=new GameObjectValues[5];
        int i=0;
        for(Player player:players){
            p[i++]=player.getMyValues();
            if(i>=5)break;
        }
        net.send(p);//send game state
    }
    public void addPlayer(String name) {
		players.add(new Player(name,new bmwM5(),playerPositionX[playerIndex],playerPositionY[playerIndex++]));
	}
}
