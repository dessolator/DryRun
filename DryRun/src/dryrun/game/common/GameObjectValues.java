package dryrun.game.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import dryrun.game.network.GameStatePacket;

import java.io.*;

public class GameObjectValues implements Serializable{
	private float coordX;
	private float coordY;
	private float dimX;
	private float dimY;
	
/*	public byte[] write(){ //SERIALIZING
		byte[] x = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);		
			oos.writeObject(this);
			x=baos.toByteArray();
			oos.close();
		} catch (IOException e) {e.printStackTrace();}
		return x;
	}
	
	public GameObjectValues read(byte[] x){ //DESERIALIZING
		GameObjectValues gov=null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(x);
			ObjectInputStream ois;
			ois = new ObjectInputStream(bais);
			gov = getGOT(ois);
		} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e){e.printStackTrace();}
		return gov;
		
	}
	
	protected GameObjectValues getGOT(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		return (GameObjectValues) ois.readObject();
	}
*/
	public float getCoordX() {
		return coordX;
	}
	public void setCoordX(float coordX) {
		this.coordX = coordX;
	}
	public float getCoordY() {
		return coordY;
	}
	public void setCoordY(float coordY) {
		this.coordY = coordY;
	}
	public float getDimX() {
		return dimX;
	}
	public void setDimX(float dimX) {
		this.dimX = dimX;
	}
	public float getDimY() {
		return dimY;
	}
	public void setDimY(float dimY) {
		this.dimY = dimY;
	}
	
	
	
	
}
