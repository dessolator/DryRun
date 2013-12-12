package dryrun.game.common.cars;


import dryrun.game.objects.TextureHolder;

public abstract class CarModel{
	public TextureHolder myTex;
	public float axialPointOffset;//in meters
	public float maxTireGrip;
	public float accelForce;//in newtons
	public float brakeForce;//in newtons
	public float reverseForce;//in newtons
	public float turnAngle;//in radians
	public float dimX;//in meters
	public float dimY;//in meters
	public float mass;//in kilograms
	public float maxSpeed;//in meters/second
	public  float maxReverse;//in meters/second
}
