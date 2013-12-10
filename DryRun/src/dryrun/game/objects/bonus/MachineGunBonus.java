package dryrun.game.objects.bonus;

import org.jbox2d.common.Vec2;

import dryrun.game.common.Player;


public class MachineGunBonus extends Bonus {
	
	//ukoliko je nepotrebno izbaciti pickedUp
	public final int bId=1;
	
	public MachineGunBonus(float x, float y, float dimx, float dimy, int myInd,
			Vec2 position) {
		super(x, y, dimx, dimy, myInd, position);	
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

