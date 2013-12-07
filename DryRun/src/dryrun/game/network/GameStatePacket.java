package dryrun.game.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;

@SuppressWarnings("serial")
public class GameStatePacket extends Packet {
	private ArrayList<GameObjectValues> myObjects=new ArrayList<GameObjectValues>();
	
	GameStatePacket(){}
	
	public void put(GameObjectValues gov){
		myObjects.add(gov);
	}
	
	public GameObjectValues[] get(){
		return (GameObjectValues[]) myObjects.toArray();
	}
	
	
	
	
	
	
	
	

	@Override
	protected Packet getPacket(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		return (GameStatePacket) ois.readObject();
	}

}
