package dryrun.game.gui.menus;

import dryrun.game.common.ServerButtonProto;
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
	private List<ServerButton> serverButtons;
	private List<ServerButtonProto> buttonsToAdd;
	private static float currentPosition = displayHeight/2;
	
	public JoinMenu() {
		
		myButtons = new ArrayList<Button>();
		buttonsToAdd = Collections.synchronizedList(new ArrayList<ServerButtonProto>());
		serverButtons = Collections.synchronizedList(new ArrayList<ServerButton>());
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
	@Override
	public void update(){
		for(int j=0;j<buttonsToAdd.size();j++){
			for(int i=0;i<serverButtons.size();i++){
				if(serverButtons.get(i).getMyText().getMyString().equals(buttonsToAdd.get(j).t)){
					break;
				}
				else{
					serverButtons.add(new ServerButton(buttonsToAdd.get(j).x,buttonsToAdd.get(j).y,buttonsToAdd.get(j).t));
					break;
				}
			}
			if(serverButtons.isEmpty()){
				serverButtons.add(new ServerButton(buttonsToAdd.get(j).x,buttonsToAdd.get(j).y,buttonsToAdd.get(j).t));
			}
		}
		if(System.nanoTime()<(long)menuCalled+(long)125000000){	
			Mouse.next();//if the cooldown hasn't passed flip to next mouse event.
		}
		else{
			while(Mouse.next()){
				if(myButtons!=null)
					for(Button b:myButtons){
						b.update();//else check if any of the buttons were pressed.
					}
				if(serverButtons!=null)
					for(ServerButton s:serverButtons){
						s.update();
					}
			}
		}
	}
	
	
	
	public void createServersFrame() {
		serverFrame = new BasicFrame(3*displayWidth/4,
				displayHeight/2,
				displayWidth/3,
				displayHeight/3);
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

	public void loadServerList() {
		currentPosition=displayHeight/2;
		List<InetAddress> l = Game.getPossibleServers();
		for(int i = 0 ; i < l.size(); i++) {
			String str=new String((l.get(i).getHostAddress()));
			ServerButtonProto temp=new ServerButtonProto(3*displayWidth/4,currentPosition,str);
			boolean contained=false;
			for(int j=0;j<buttonsToAdd.size();j++){
				if(buttonsToAdd.get(j).t.equals(str)){
					contained=true;
					break;
				}
			}
			if(!contained){
				buttonsToAdd.add(temp);
				currentPosition -= 41;
			}
		}
		
	}

	public List<ServerButtonProto> getButtonsToAdd() {
		return buttonsToAdd;
	}

	public void setButtonsToAdd(List<ServerButtonProto> buttonsToAdd) {
		this.buttonsToAdd = buttonsToAdd;
	}
		
}


