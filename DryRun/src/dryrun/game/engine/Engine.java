package dryrun.game.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Engine {
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static boolean fullscreen=false;
	public static boolean vsync = true;
	
	
	public static void init(){		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setFullscreen(fullscreen);//fullscreen? 
			Display.setResizable(true);
			Display.create();//create Display
			Mouse.create();			
			Keyboard.create();
			Display.setVSyncEnabled(vsync);//vsync ukljucen
			
			
			//inicijalzacija gl
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			glClearColor(0, 0, 0, 1);
			glDisable(GL_DEPTH_TEST);
			glClear(GL_COLOR_BUFFER_BIT);
			glColor3f(1, 0, 0);
			glLoadIdentity();
			glEnable(GL_TEXTURE_2D);			
			
			
			
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void kill(){
		Display.destroy();
		Mouse.destroy();
		Keyboard.destroy();
	}

}
