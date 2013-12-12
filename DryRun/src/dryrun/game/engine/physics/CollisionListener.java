package dryrun.game.engine.physics;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import dryrun.game.common.Player;
import dryrun.game.engine.interfaces.Collidable;

public class CollisionListener implements ContactListener {

	@Override
	public void beginContact(Contact arg0) {
		Object a=arg0.getFixtureA().getBody().getUserData();//first object involved in this shitstorm
		Object b=arg0.getFixtureB().getBody().getUserData();//second object involved in this shitstorm
		
		Class<?>[] array=a.getClass().getInterfaces();//Interface array for the first object
		System.out.println("collided");
		for(int i=0;i<array.length;i++){
			try {
				if(array[i].equals(Class.forName("dryrun.game.engine.Collidable"))&& b.getClass().equals(Class.forName("dryrun.game.common.Player"))){//if the first object implements collidable and the second object is a player
					((Collidable)a).collided((Player)b);//call collision
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		array=b.getClass().getInterfaces();//Interface array for the second object
		for(int i=0;i<array.length;i++){
			try {
				if(array[i].equals(Class.forName("dryrun.game.engine.Collidable"))&& a.getClass().equals(Class.forName("dryrun.game.common.Player"))){//if the second object implements collidable and the first object is a player
					((Collidable)b).collided((Player)a);//call collision
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		

	}

	@Override
	public void endContact(Contact arg0) {
		System.out.println("ended contact");

	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
//		arg0.setEnabled(false);

	}

}
