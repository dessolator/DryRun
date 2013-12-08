package dryrun.game.gui.menus;

import dryrun.game.engine.DrawObject;

import dryrun.game.gui.menus.misc.frames.BasicFrame;
import dryrun.game.gui.misc.buttons.*;

import java.util.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureLoader;
import java.io.*;
import dryrun.game.gui.menus.misc.frames.*;
import dryrun.game.mechanics.Game;

import java.net.*;
import static dryrun.game.gui.misc.buttons.ButtonConstants.*;

public class JoinMenu extends Menu {
	private Frame serverFrame;
	private List<Button> serverButtons;
	private boolean refreshTriggered;
	
	public JoinMenu() {
		
		myButtons = new ArrayList<Button>();
		myFrame = new BasicFrame(Display.getWidth()/4,
				Display.getHeight()/2,
				Display.getWidth()/3,
				Display.getHeight()/3);
		myButtons.add(new RefreshButton(Display.getWidth()/4,
				Display.getHeight()/2 + Display.getHeight()/10 + Display.getHeight()/30));
		myButtons.add(new BackButton(Display.getWidth()/4,
				Display.getHeight()/2 + Display.getHeight()/30));
		
		try {
			background = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/MenuBackground.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		Mouse.setGrabbed(false);//show the mouse
		if (refreshTriggered) {
			Game.getMyLobbyMenu().createServerButtons();
			Game.getMyLobbyMenu().createServersFrame();
		}
		DrawObject.draw(this);//draw the background
		if(myFrame!=null) myFrame.render();//draw the frame
		if (myButtons!=null) {
			for (Button b:myButtons)
				if(b!=null)b.render();//draw the buttons
		}
		if(serverFrame!=null) serverFrame.render();
		if (serverButtons!=null) {
			for(Button b: serverButtons) {
				if (b!=null) b.render();
			}
		}
	}
	
	public void createServersFrame() {
		serverFrame = new BasicFrame(3*displayWidth/4,
				displayHeight/2,
				displayWidth/3,
				displayHeight/3);
	}
	
	public void createServerButtons() {
		float currentPosition = displayHeight/2;
		serverButtons = Collections.synchronizedList(new ArrayList<Button>());
		List<InetAddress> l = Game.getPossibleServers();
		for(int i = 0 ; i < l.size(); i++) {
			String s=new String((l.get(i).getHostAddress()));
			serverButtons.add(new ServerButton(3*displayWidth/4,currentPosition,s));
			currentPosition -= 41;
		}
	}
	
	
	public void deleteServerButtons() {
		if (serverButtons!=null) serverButtons.clear();
	}

	public Frame getServerFrame() {
		return serverFrame;
	}

	public void setServerFrame(Frame serverFrame) {
		this.serverFrame = serverFrame;
	}

	public boolean isRefreshTriggered() {
		return refreshTriggered;
	}

	public void setRefreshTriggered(boolean refreshTriggered) {
		this.refreshTriggered = refreshTriggered;
	}
		
}


