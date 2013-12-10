package dryrun.game.common;

import org.jbox2d.common.Vec2;

import dryrun.game.engine.DrawObject;
import dryrun.game.objects.GameObject;

public class shotLine extends GameObject {
	private Vec2 direction;
	private float sourceX;
	private float sourceY;
	
	public shotLine(Player p){
		super(p.getX()+(p.getDirectionX()*5*p.getDimX()/2),p.getY()+(p.getDirectionY()*25000*p.getDimY()/2), 10, 50000);
		sourceX=p.getX()+p.getDirectionX()*p.getDimX()/2;
		sourceY=p.getY()+p.getDirectionY()*p.getDimY()/2;
				
		direction = p.direction;
		
	}

	@Override
	public void render() {
		DrawObject.draw(this);
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
	public void collided(Player b) {
		// TODO Auto-generated method stub
		
	}
}
