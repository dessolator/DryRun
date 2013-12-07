package dryrun.game.network.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import dryrun.game.network.Packet;
import static dryrun.game.network.NetConstants.*;

public class ServerThread extends Thread implements NetFramework  {
	private DatagramSocket mySocket;
	InetAddress myAdr;
	private Player myPlayer;
	
	
	public ServerThread(int port){
		try {
			mySocket=new DatagramSocket(port);
		} catch (SocketException e) {e.printStackTrace();}
	}

	public ServerThread(int i, String[] split) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(Packet p) {
		byte x[]=p.write();
		try {
			mySocket.send(new DatagramPacket(x, x.length, myAdr, UDP_GSCL_PORT));
		} catch (IOException e) {e.printStackTrace();}
		
	}

	@Override
	public ArrayList<GameStatePacket> receive() {
		
	}

}
