package dryrun.game.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;
import dryrun.game.common.Player;
import static dryrun.game.network.NetConstants.*;

public class GameStatePacket implements Serializable  {
//	private ArrayList<GameObjectValues> myObjects=new ArrayList<GameObjectValues>();
	private GameObjectValues [] myObjects;
	private int i=0;
	
	public GameStatePacket(){
		//myObjects = new GameObjectValues[MAX_PLAYERS-1];
		myObjects = new GameObjectValues[1];
	}
	
	public void put(GameObjectValues gov){
		if (i<MAX_PLAYERS-1) myObjects[i++] = gov;
	}
	
	public GameObjectValues[] get(){
		//return (GameObjectValues[]) myObjects.toArray();
		return myObjects;
	}
	
	// added : goksiii
	public GameObjectValues[] objects() {
		return myObjects;
	}
	
	public static byte[] write(GameStatePacket p){ //SERIALIZING
		byte[] x = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);	
			oos.writeObject(p);
			x=baos.toByteArray();
			oos.close();
		} catch (IOException e) {e.printStackTrace();}
		return x;
	}
	
	public static GameStatePacket read(byte[] x){ //DESERIALIZING
		GameStatePacket p=null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(x);
			ObjectInputStream ois;
			ois = new ObjectInputStream(bais);
			p = (GameStatePacket) ois.readObject();
		} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e){e.printStackTrace();}
		return p;
		
	}
	

	public GameStatePacket test(){
		GameStatePacket packet= new GameStatePacket();
		Player player=new Player("test1","",450,350,133,60);
		packet.put(player.getMyValues());
		return packet;
	}
	
	
	
	
	
	
	
	

	

}
