package dryrun.game.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;
import static dryrun.game.network.NetConstants.*;

public class GameStatePacket  {
//	private ArrayList<GameObjectValues> myObjects=new ArrayList<GameObjectValues>();
	private GameObjectValues [] myObjects;
	private int i=0;
	
	public GameStatePacket(){
		myObjects = new GameObjectValues[MAX_PLAYERS-1];
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
			p = getPacket(ois);
		} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e){e.printStackTrace();}
		return p;
		
	}
	
	protected  static GameStatePacket getPacket(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		return (GameStatePacket) ois.readObject();
	}
	
	
	
	
	
	

	

}
