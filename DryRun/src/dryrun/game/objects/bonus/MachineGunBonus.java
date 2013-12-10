package dryrun.game.objects.bonus;

import dryrun.game.common.Player;
import dryrun.game.objects.GameObject;

public class MachineGunBonus extends Bonus {
	
	//ukoliko je nepotrebno izbaciti pickedUp
	public final int bId=1;
	
	public MachineGunBonus(int ind, float locX, float locY) {
		super(ind, locX, locY);	
	}

	public void applyBonus(Player p){
		p.incMachinegunAmmo(50);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void undo(){}


	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}	

}

