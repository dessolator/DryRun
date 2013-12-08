package dryrun.game.network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;
import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import static dryrun.game.network.NetConstants.*;


public class Server implements NetFramework {
	private DatagramSocket myUdpSocket;
	private ArrayList<ServerThread> myThreads;
	
	public int numOfPlayers=0;
	
	
	private static volatile int refreshThreadExists = 0;
	private static volatile int killRefreshThread = 0;
	
	
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
	
	
	public void CreateClThread(int currentUdp, String split[]){
		myThreads.add(new ServerThread(currentUdp, split));
	}
	
	
	
	@Override
	public void send(GameObjectValues[] p) {
		for(int i=0; i<myThreads.size();i++) myThreads.get(i).send(p);
	}

	@Override
	public GameObjectValues[] receive() {
		GameObjectValues a[];
		a=new GameObjectValues [myThreads.size()];
		for(int i=0; i<myThreads.size();i++)a[i]=myThreads.get(i).receive();
		return a;
	}

	@Override
	public DatagramSocket getUDPSocket() {return myUdpSocket;}

}
