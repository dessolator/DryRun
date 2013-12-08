package dryrun.game.network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;
import dryrun.game.network.ConcurrentCircularBuffer;
import dryrun.game.network.NetFramework;
import static dryrun.game.network.NetConstants.*;


public class Server implements NetFramework {
	private DatagramSocket myUdpSocket;
	private ArrayList<ServerThread> myThreads;
	private RefreshReplyThread rrt=null;
	private ConnectAcceptorThread cat=null;
	private ConcurrentCircularBuffer buffer;
	
	private String name;
	
	public int numOfPlayers=0;
	
	private static Server server=null; //Server is unique

	
	
	public ArrayList<Socket> mySockets=new ArrayList<Socket>(); //TODO make private
	
	public static Server getServer(){  //Server getter
		if (server==null) server = new Server("");
		return server;
	}
	
	public static Server getServer(String n){  //Server getter
		if (server==null) server = new Server(n);
		return server;
	}
	protected Server(String s){
		try {
			name=s;
			buffer=new ConcurrentCircularBuffer();
			myUdpSocket= new DatagramSocket(UDPPORT);  	//Port for receiving and replying refresh requests
			myThreads = new ArrayList<ServerThread>();	//array of objects which contain all info for communicating with a single connected client
			
		} catch (IOException e) {e.printStackTrace();}  
		
		
	}
	
	private void getRefresh(){							//TODO make singleton!!
			if(rrt==null){
				rrt =new RefreshReplyThread(myUdpSocket);	//creates a thread which listens to refresh requests
				rrt.start();								//and starts it
			}
			else System.out.print("Refresh thread already running.\n");
		
	}
	
	private void getConnect(){
		if(cat==null){
			try {
				cat = new ConnectAcceptorThread(this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(cat!=null){cat.start();}
		}else{System.out.print("ConnectAcceptorThread.");}
	}

	
	public void host(){						//function called by the game when a user requests to host a game
			System.out.println("Creating refresh and connect SrvThreads");
			getRefresh();					//listen to refresh
			System.out.println("Created refresh thread");
			getConnect();					//listen to connect requests
			System.out.println("Created ConnectRef thread");
			
	}
	
	public void startGame(){for(int i=0; i<myThreads.size();i++)myThreads.get(i).start();} 
	//this method is executed by the engine thread
	//to begin sending packets and start the loader.
	
	
	public void CreateClThread(int currentUdp, String split[], InetAddress ip,DataInputStream tcpin, DataOutputStream tcpout) throws SocketException{
		myThreads.add(new ServerThread(currentUdp, split, ip,buffer,tcpin,tcpout));
	} //Creation of a new ClientThread, this method is executed in the ConnectAcceptorThread.
	
	ConcurrentCircularBuffer getBuffer(){return buffer;} //returns the buffer.
	
	@Override
	public void send(GameObjectValues[] p) {
		for(int i=0; i<myThreads.size();i++) myThreads.get(i).send(p); //queues a packet for broadcasting to clients
	}

	@Override
	public GameObjectValues[] receive() { //returns a single clients gameObjectValues[]
		try {
			return buffer.pop();
		} catch (InterruptedException e) {e.printStackTrace();}
		return null;
	}




}
