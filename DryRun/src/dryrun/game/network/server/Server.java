package dryrun.game.network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;
import dryrun.game.network.ConcurrentCircularBuffer;
import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import static dryrun.game.network.NetConstants.*;


public class Server implements NetFramework {
	private DatagramSocket myUdpSocket;
	private ArrayList<ServerThread> myThreads;
	private RefreshReplyThread rrt=null;
	private ConnectAcceptorThread cat=null;
	private ConcurrentCircularBuffer buffer;
	
	public int numOfPlayers=1;
	
	private static Server server=null; //Server is unique

	
	
	//public ArrayList<Socket> mySockets=new ArrayList<Socket>(); //TODO make private
	
	public static Server getServer(){  //Server getter
		if (server==null) {server = new Server();System.out.println("Server Object created");}
		return server;
	}
	
	public static void disposeServer(){
		System.out.print("disposing server");
		if (getServer()!=null){
			System.out.println("- disposed");
			getServer().killListenerThreads();
			try {
				getServer().terminate();
			} catch (IOException e) {e.printStackTrace();}
			server=null;
			}
		}
	
	public void terminate() throws IOException{
		for(int i=1;i<myThreads.size();i++)myThreads.get(i).terminate();
	}
	
	protected Server(){
		try {

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

	public ArrayList<ServerThread> getServerThreads() {
		return myThreads;
	}
	
	public void host(){						//function called by the game when a user requests to host a game
			System.out.println("Creating refresh and connect SrvThreads");
			getRefresh();					//listen to refresh
			System.out.println("Created refresh thread");
			getConnect();					//listen to connect requests
			System.out.println("Created ConnectRef thread");
			
	}
	public void startGame(){
		for(int i=0; i<myThreads.size();i++)myThreads.get(i).send();
		
	}
	
	public void startGame(GameStatePacket p){
		
		
		for(int i=0; i<myThreads.size();i++)myThreads.get(i).startGame(p);
		//may need some sleep
		for(int i=0; i<myThreads.size();i++)myThreads.get(i).start();
		killListenerThreads();
	
	
	} 
	//this method is executed by the engine thread
	//to begin sending packets and start the loader.
	
	public void killListenerThreads(){
		try {
			cat.obavesti();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rrt.obavesti();
		cat=null;
		rrt=null;
		}
	

	
	
	public void CreateClThread(int currentUdp, String split[], InetAddress ip,ObjectInputStream tcpin, ObjectOutputStream tcpout, Socket s) throws SocketException{
		System.out.println("Creating Client Thread.");
		myThreads.add(new ServerThread(currentUdp, split, ip,buffer,tcpin,tcpout, s));
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

	public ArrayList<ServerThread> getMyThreads() {
		return myThreads;
	}

	public void setMyThreads(ArrayList<ServerThread> myThreads) {
		this.myThreads = myThreads;
	}




}
