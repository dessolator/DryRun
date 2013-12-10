package dryrun.game.mechanics;

import dryrun.game.common.Player;



public class AiCheckpoint extends Checkpoint {
	private float x;
	private float y;
	
	AiCheckpoint(float x, float y, float dimx, float dimy, float optx, float opty){
		super(x,y,dimx,dimy);
		this.x=optx;
		this.y=opty;
	}
	
	public float[] getOptimalCoord(){
		float a[]=new float[2];
		a[0]=x;
		a[1]=y;
		return a;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void collided(Player o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

}
