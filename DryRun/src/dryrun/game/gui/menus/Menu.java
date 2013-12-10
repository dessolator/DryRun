
package dryrun.game.gui.menus;

import dryrun.game.engine.DrawObject;
import dryrun.game.engine.Drawable;
import dryrun.game.engine.Updateable;
import dryrun.game.gui.menus.misc.frames.Frame;
import dryrun.game.gui.misc.buttons.Button;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;


public abstract class Menu implements Drawable, Updateable{
	protected ArrayList<Button> myButtons;//The buttons in the menu
	protected Texture background;//The menu background
	protected Frame myFrame;//The menu frame (the thing around the buttons)
	protected static long menuCalled;//cooldown timer for menu buttons
	
	@Override
	public void render() {
		Mouse.setGrabbed(false);//show the mouse
		DrawObject.draw(this);//draw the background
		if(myFrame!=null) myFrame.render();//draw the frame
		if (myButtons!=null) {
			for (Button b:myButtons)
				if(b!=null)b.render();//draw the buttons
		}
	}
	@Override
	public Texture getTexture() {
		return background;
	}
	@Override
	public float getX() {
		return (float)Display.getWidth()/2;
	}
	@Override
	public float getDimX() {
		return (float)Display.getWidth();
	}
	@Override
	public float getY() {
		return (float)Display.getHeight()/2;
	}
	@Override
	public float getDimY(){
		return (float)Display.getHeight();
	}
	@Override
	public void update() {
		if(System.nanoTime()<(long)menuCalled+(long)125000000){	
			Mouse.next();//if the cooldown hasn't passed flip to next mouse event.
		}
		else{
			while(Mouse.next()){
				if(myButtons!=null)
				for(Button b:myButtons){
					b.update();//else check if any of the buttons were pressed.
				}
			}
		}
	}
	/**
	 * @return the menuCalled
	 */
	public static long getMenuCalled() {
		return menuCalled;
	}
	/**
	 * @param menuCalled the menuCalled to set
	 */
	public static void setMenuCalled(long menuCalled) {
		Menu.menuCalled = menuCalled;
	}
	@Override
	public float getTexX1() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getTexX2() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public float getTexY1() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getTexY2() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public double getAngle() {
		return 0;
	}

}

