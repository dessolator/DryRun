package dryrun.game.objects.bonus;

import org.jbox2d.common.Vec2;

import dryrun.game.common.Player;

public class Heal extends Bonus {

	public Heal(float x, float y, float dimx, float dimy, int myInd,
			Vec2 position) {
		super(x, y, dimx, dimy, myInd, position);	
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyBonus(Player p) {
		// TODO Auto-generated method stub
		
	}

}
