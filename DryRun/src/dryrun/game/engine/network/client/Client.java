package dryrun.game.engine.network.client;

import java.net.*;

import java.io.*;

import dryrun.game.common.GameObjectValues;
import dryrun.game.mechanics.Game;
import static dryrun.game.engine.network.NetConstants.*;
import dryrun.game.engine.network.ConcurrentCircularBuffer;
import dryrun.game.engine.network.NetFramework;


public class Client implements NetFramework {
	private ClientSender clientSender;		// clientSender nit - udp konekcija
	private ClientReceiver clientReceiver;  // clientReceiver nit - udp konekcija
	
	private ObjectInputStream ois = null; //streams for tcp
	private ObjectOutputStream oos = null;//communication
	
	private DatagramSocket udpSocket;	// udpSocket za komunikaciju sa serverom ( server vraca port )
	private Socket tcpSocket;			// tcpSocket za komunikaciju sa serverom ( TCPPORT - static )
	private volatile boolean connected; // provericu kasnije
	private volatile int serverPort;	// port koji vraca server
	private InetAddress serverAddress;	// adresa servera
	private ConcurrentCircularBuffer myBuffer;	// buffer za clientSender nit
	private ConcurrentCircularBuffer receiveBuffer; // bufer za clientReceiver nit
	
	private ConcurrentCircularBuffer myInitBuffer; // first init buffer
												   // sluzi samo za pocetne koordinate ostalih player-a
	
	private static Client client = null; // singleton
	
	

 	protected Client() {
		try {
			udpSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
 	
 	// unistava tj zatvara sokete, unistava niti za komunikaciju
 	// i postavlja client = null
 	public static void disposeClient() {
 		System.out.println("dispose");
 		if (clientExist()) {
 			System.out.println("dispose client");
 			getClient().closeSockets();
 			getClient().killThreads();
 			client = null;
 		}
 		

 	}
 	
 	// ispitivanje postojanja klijenta
 	public static boolean clientExist() {
 		if (client!=null) return true;
 		return false;
 	}
 	
 	// ubijanje komunikacionih niti
 	private void killThreads() {
 		if (clientReceiver!=null) clientReceiver.obavesti();
 		if (clientSender!=null)   clientSender.obavesti();
 	}
 	
 	// zatvaranje socketa
 	private void closeSockets() {
 		udpSocket.close();
 		try {
			if (tcpSocket!=null) tcpSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 		System.out.println("Sockets close");
 	}
 	
 	
 	// singleton - ako klijent postoji vraca ga
 	// ako ne postoji vraca false
 	public static Client getClient() {
 		if(client==null) {
 			client = new Client();
 			System.out.println("Join created client.");
 		}
 		return client;
 	}
 	
 	// refresh - salje broadcast - traze se dostupni serveri
	public void findServers() {
		GetServers gServ = new GetServers(this,Game.getPossibleServers());
		gServ.start();
		
		DestroyGetServersThread destroy = new DestroyGetServersThread(gServ);
		destroy.start();
	}
	
	// vezivanje na zadati server
	public void connectToServer(InetAddress servAddr, String playerName, int typeOfAutomobile) {
		try {
			tcpSocket = new Socket(servAddr,TCPPORT);
		} catch (IOException e) {
			System.out.println("upao ovde");
			e.printStackTrace();
		}
		myInitBuffer = new ConcurrentCircularBuffer();
		myBuffer = new ConcurrentCircularBuffer();
		receiveBuffer = new ConcurrentCircularBuffer();
		
		serverAddress = servAddr;
		ConnectThread cThread = new ConnectThread(this,serverAddress,playerName,oos,ois);
		cThread.start();
		
		
	}
	
	
	
	public void setConnectedFlag(boolean flag) {
		connected = flag;
	}
	
	public void setServerPort(int p) {
		serverPort = p;
	}
	
	public void setUDPSocket(int srvPort) {
		serverPort = srvPort;
		try {
			udpSocket = new DatagramSocket(serverPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getTCPSocket() {
		return tcpSocket;
	}
	
	public DatagramSocket getUDPSocket() {
		return udpSocket;
	}
	
	public int serverPort() {
		return serverPort;
	}
	
	public InetAddress serverAddress() {
		return serverAddress;
	}
	
	// implementirane metode interfejsa NetFramework
	public void send(GameObjectValues [] p) { 
		myBuffer.push(p);
	}
	
	public GameObjectValues[] receive() {
		return receiveBuffer.pop();
	}

	// dohvatanje buffer-a za komunikaciju
 	public ConcurrentCircularBuffer initBuffer() {
 		return myInitBuffer;
 	}
 	
	public ConcurrentCircularBuffer getSenderBuffer() {
		return myBuffer;
	}
	
	public ConcurrentCircularBuffer getReceiveBuffer() {
		return receiveBuffer;
	}

	// implementirane metode iz NetFramework-a
	@Override
	public void startGame(GameObjectValues[] p){
		
	}

	@Override
	public GameObjectValues[] startGame() {
		return myInitBuffer.pop();
	}
	
	// dohvatanje sender i receiver niti
	public ClientSender getClientSender() {
		return clientSender;
	}
	
	public ClientReceiver getClientReceiver() {
		return clientReceiver;
	}
}
