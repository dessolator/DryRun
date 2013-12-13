package dryrun.game.engine.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;




import dryrun.game.common.GameObjectValues;
import dryrun.game.objects.Player;
import static dryrun.game.engine.network.NetConstants.*;

public class GameStatePacket implements Serializable  {
//	private ArrayList<GameObjectValues> myObjects=new ArrayList<GameObjectValues>();
	private GameObjectValues [] myObjects;
	private int i=0;
	
	public GameStatePacket(){
		//myObjects = new GameObjectValues[MAX_PLAYERS-1];
		myObjects = new GameObjectValues[5];
	}
	
	public GameStatePacket(GameObjectValues[] p) {
		myObjects=p;
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
		//BufferedOutputStream bos = new BufferedOutputStream(baos,8096);//optimization maybe?
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
			//BufferedInputStream bis=new BufferedInputStream(bais,8096);//optimization maybe?
			ObjectInputStream ois;
			ois = new ObjectInputStream(bais);
			p = (GameStatePacket) ois.readObject();
		} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e){e.printStackTrace();}
		return p;
		
	}
	

//	public GameStatePacket test(){
//		GameStatePacket packet= new GameStatePacket();
//		Player player=new Player("test1","",450,350);
//		packet.put(player.getMyValues());
//		return packet;
//	}
	
	
	
	
	
	
	
	

	

}
