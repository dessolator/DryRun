package dryrun.game.objects.bonus;

import dryrun.game.objects.GameObject;

public class MachineGun extends Bonus {
	
	private static boolean puckedUp = false;//pokupljen i odmah se aktivira
	
	public MachineGun(float coordX, float coordY) {
		super(coordX, coordY);
		// TODO Auto-generated constructor stub
	}
	public void collided(GameObject o){
		
	}

}
