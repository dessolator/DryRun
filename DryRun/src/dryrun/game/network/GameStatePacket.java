package dryrun.game.network;

import java.io.IOException;
import java.io.ObjectInputStream;

import dryrun.game.common.GameObjectValues;

public class GameStatePacket extends Packet {
	private int length=0;
	ArrayList<GameObjectValues> myObjects=
	
	GameStatePacket(){}
	
	
	
	
	
	
	
	
	
	

	@Override
	protected Packet getPacket(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		return (GameStatePacket) ois.readObject();
	}

}
