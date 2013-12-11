package dryrun.game.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GameObjectValues implements Serializable{
	private float coordX;
	private float coordY;
	private float dimX;
	private float dimY;

	//!!!dzoni and goksi review pls!!! 
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
	//getters and setters
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
