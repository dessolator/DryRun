package dryrun.game.engine;

import dryrun.game.objects.GameObject;

public class GamePhysics {
	//ovo je inicijalna kolizija koja ce da radi .. reimplementirati ako se nadje bolji nacin
	
	public static boolean hit(GameObject a, GameObject b){
		GameObject left=(a.getX()<b.getX())?a:b;
		GameObject right=(a.getX()<b.getX())?b:a;
		if((left.getX()+left.getDimX()/2)<(right.getX()-right.getDimX()/2)){//if right edge of left object is to the left of the left edge of the right object they can't possibly be colliding.
			return false;
		}
		GameObject upper=(a.getY()<b.getY())?b:a;
		GameObject lower=(a.getY()<b.getY())?a:b;
		if((lower.getY()+lower.getDimY()/2)<(upper.getY()-upper.getDimY()/2)){//same logic as above.
			return false;
		}
		return true;
		
	}
}
