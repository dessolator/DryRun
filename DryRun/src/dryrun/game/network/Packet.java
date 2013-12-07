package dryrun.game.network;

import java.io.*;

@SuppressWarnings("serial")
public abstract class Packet implements Serializable {
	
	public Packet(){}
	
	
	public static byte[] write(){ //SERIALIZING
		byte[] x = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);	
			Packet p = new Packet();
			oos.writeObject(p);
			x=baos.toByteArray();
			oos.close();
		} catch (IOException e) {e.printStackTrace();}
		return x;
	}
	
	public static Packet read(byte[] x){ //DESERIALIZING
		Packet p=null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(x);
			ObjectInputStream ois;
			ois = new ObjectInputStream(bais);
			p = getPacket(ois);
		} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e){e.printStackTrace();}
		return p;
		
	}
	
	protected abstract Packet getPacket(ObjectInputStream ois) throws ClassNotFoundException, IOException; //must be defined in all children as return (<CastType>) ois.readObject();

}
