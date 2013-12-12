package dryrun.game.common.cars;

import static dryrun.game.engine.LoadTex.bmwM5;
import dryrun.game.engine.Tex;
import dryrun.game.objects.TextureHolder;

public class bmwM5 extends CarModel{
	
	
	
	public bmwM5(){
		myTex=new TextureHolder(bmwM5,new Tex(0,0,1,1));
		axialPointOffset=2;//in meters
		maxTireGrip=600f;//in meter kilograms /second
		accelForce=20626;//in newtons
		brakeForce=40262;//in newtons
		reverseForce=10313;//in newtons
		turnAngle=0.00276f;//in radians/second
		dimX=4.8f;//in meters
		dimY=1.89f;//in meters
		mass=2000;//in kilograms
		maxSpeed=85.7f;//in meters/second
		maxReverse=13.6f;//in meters/second
	}
}
