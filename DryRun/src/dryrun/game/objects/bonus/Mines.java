package dryrun.game.objects.bonus;

import org.jbox2d.common.Vec2;

<<<<<<< HEAD
import org.newdawn.slick.opengl.Texture;

import dryrun.game.common.Player;
import dryrun.game.engine.Tex;
import dryrun.game.objects.TextureHolder;
import static dryrun.game.engine.LoadTex.mine;
=======
import dryrun.game.objects.Player;
>>>>>>> refs/remotes/origin/master

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

	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return 0;
	}

}
