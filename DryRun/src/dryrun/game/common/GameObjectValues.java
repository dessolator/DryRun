package dryrun.game.common;


import java.io.Serializable;

@SuppressWarnings("serial")
public class GameObjectValues implements Serializable{
	private float coordX;
	private float coordY;
	private float angle;
	private String name;

	//!!!dzoni and goksi review pls!!! 

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}	
}
