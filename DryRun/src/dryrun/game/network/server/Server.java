package dryrun.game.network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import dryrun.game.network.Packet;
import static dryrun.game.network.NetConstants.*;


public class Server implements NetFramework {
	private DatagramSocket myUdpSocket;
	private ArrayList<ServerThread> myThreads;
	
	public int numOfPlayers=0;

	
	
	public ArrayList<Socket> mySockets=new ArrayList<Socket>();
	
	public Server(){
		try {
			myUdpSocket= new DatagramSocket(UDPPORT);
			myThreads = new ArrayList<ServerThread>();
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
	
	private void getRefresh(){
			RefreshReplyThread rrt = new RefreshReplyThread(myUdpSocket);
			rrt.start();
			
			
		
	}
	
	
	public void host(){
			getRefresh();
			getConnect();//TODO make getConnect a singleton
	}
	
	private void getConnect(){
		ConnectAcceptorThread Cat=null;
		try {
			Cat = new ConnectAcceptorThread(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Cat!=null){Cat.start();}
		
	}
	
	
	public void CreateClThread(int currentUdp, String split[], InetAddress ip) throws SocketException{
		myThreads.add(new ServerThread(currentUdp, split, ip));
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
