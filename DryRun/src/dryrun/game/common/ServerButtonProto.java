package dryrun.game.common;

import java.net.*;

public class ServerButtonProto {
	public float x;
	public float y;
	public String t;
	public InetAddress myAddress;
	
	public ServerButtonProto(InetAddress myAddr, float x, float y, String t) {
		super();
		this.x = x;
		this.y = y;
		this.t = t;
		myAddress = myAddr;
	}
	

}
