package dryrun.game.objects.bonus;

import dryrun.game.common.Player;
import dryrun.game.objects.GameObject;

public class MachineGun extends Bonus {
	
	private static boolean puckedUp = false;//pokupljen i odmah se aktivira
	
	public MachineGun(float coordX, float coordY) {
		super(coordX, coordY);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void collided(Player o){
		Timer t = new Timer(0, this);
		o.addTimer(t);		
	}

	public void undo(){}

}

