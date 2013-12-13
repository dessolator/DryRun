package dryrun.game.mechanics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import dryrun.game.common.GameObjectValues;
import dryrun.game.common.cars.bmwM5;
import dryrun.game.engine.network.NetFramework;
import dryrun.game.engine.physics.CollisionListener;
import dryrun.game.objects.Checkpoint;
import dryrun.game.objects.Player;
import dryrun.game.objects.Wall;

@SuppressWarnings("serial")
public class ServerLevel extends Level {
        World world=new World(new Vec2(0, 0));
		private float[] playerPositionX={1,1,2,2};//TODO take another look
		private float[] playerPositionY={1,2,1,2};
		private int playerIndex=0;
        private static CollisionListener myListener =new CollisionListener();

    public ServerLevel(NetFramework nf, String name) {
            super(nf,name);
            world.setContactListener(myListener);
            myPlayer=new Player(name,new bmwM5(),playerPositionX[playerIndex],playerPositionY[playerIndex++]);
            players.add(myPlayer);
    }
    public void initialState() {
    	//TODO might need to load GOV
		p=new GameObjectValues[5];
		int i=0;
        for(Player player:players){
            p[i++]=player.getMyValues();
            if(i>=5)break;
        }
            net.startGame(p);
    }    	

       
    @Override
    public void update() {
        parseAndUpdate(net.receive());//read and set other player states
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
