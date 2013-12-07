package dryrun.game.network;

import java.net.*;
import java.util.ArrayList;

public class ServerThread extends Thread implements NetFramework  {
	private DatagramSocket mySocket;
	InetAddress myAdr;
	
	
	public ServerThread(int port){
		try {
			mySocket=new DatagramSocket(port);
		} catch (SocketException e) {e.printStackTrace();}
	}

	@Override
	public void send(Packet p) {
		byte x[]=p.write();
		mySocket(new DatagramPacket(x, x.length, arg2, arg3))
	}

	@Override
	public ArrayList<GameStatePacket> receive() {
		// TODO Auto-generated method stub
		return null;
	}

}
