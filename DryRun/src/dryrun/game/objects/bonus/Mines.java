package dryrun.game.objects.bonus;

import org.jbox2d.common.Vec2;

import dryrun.game.common.Player;
import dryrun.game.engine.Tex;
import dryrun.game.objects.TextureHolder;
import static dryrun.game.engine.LoadTex.mine;

public class Mines extends Bonus {

	public Mines(float x, float y, float dimx, float dimy, int myInd, Vec2 position) {
		super(x, y, dimx, dimy, myInd, position);
		this.holder = new TextureHolder(mine, new Tex(0f,0f,1f,1f));
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
		p.setMinescount(p.getMinescount()+1);
	}

}
