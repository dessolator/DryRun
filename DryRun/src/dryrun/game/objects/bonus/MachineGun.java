package dryrun.game.objects.bonus;

import dryrun.game.common.Player;
import dryrun.game.objects.GameObject;

public class MachineGun extends Bonus {
	//ukoliko je nepotrebno izbaciti pickedUp
	private static boolean puckedUp = false;//pokupljen i odmah se aktivira
	
	public MachineGun(float coordX, float coordY, float dimx, float dimy) {
		super(coordX, coordY, dimx, dimy);		
	}
	
	
	@Override
	public void collided(Player o){
		Timer t = new Timer(0, this);
		o.addTimer(t);		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void undo(){}	

}

