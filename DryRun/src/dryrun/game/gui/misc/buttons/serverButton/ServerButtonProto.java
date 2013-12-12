package dryrun.game.gui.misc.buttons.serverButton;

import java.net.*;

public class ServerButtonProto {
	
	public float x;
	public float y;
	public String t;
	public InetAddress myAddress;
	//constructor
	public ServerButtonProto(InetAddress myAddr, float x, float y, String t) {
		super();
		this.x = x;
		this.y = y;
		this.t = t;
		myAddress = myAddr;
	}
	

}
