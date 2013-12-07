package dryrun.game.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;
import static dryrun.game.network.NetConstants.*;
@SuppressWarnings("serial")
public class GameStatePacket extends Packet {
//	private ArrayList<GameObjectValues> myObjects=new ArrayList<GameObjectValues>();
	private GameObjectValues [] myObjects = new GameObjectValues[4];
	private int i=0;
	
	GameStatePacket(){}
	
	public void put(GameObjectValues gov){
		if (i<MAX_PLAYERS-1) myObjects[i++] = gov;
	}
	
	public GameObjectValues[] get(){
		//return (GameObjectValues[]) myObjects.toArray();
		return myObjects;
	}
	
	
	
	
	
	
	
	

	@Override
	protected Packet getPacket(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		return (GameStatePacket) ois.readObject();
	}

}
