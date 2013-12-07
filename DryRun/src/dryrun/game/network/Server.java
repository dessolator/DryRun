package dryrun.game.network;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import static dryrun.game.network.NetConstants.*;

public class Server implements NetFramework {
	private ServerSocket myTcpSockets;
	private DatagramSocket myUdpSocket;
	private ArrayList<ServerThread> myThreads;
	
	public Server(){
		try {
			myTcpSockets= new ServerSocket(TCPPORT);
			myUdpSocket= new DatagramSocket(UDPPORT);
			myThreads = new ArrayList<ServerThread>();
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
	
	
	
	@Override
	public void send(Packet p) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<GameStatePacket> receive() {
		// TODO Auto-generated method stub
		return null;
	}

}
