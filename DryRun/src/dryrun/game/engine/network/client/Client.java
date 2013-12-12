package dryrun.game.engine.network.client;

import java.net.*;

import java.io.*;

import dryrun.game.common.GameObjectValues;
import dryrun.game.mechanics.Game;
import static dryrun.game.engine.network.NetConstants.*;
import dryrun.game.engine.network.ConcurrentCircularBuffer;
import dryrun.game.engine.network.NetFramework;


public class Client implements NetFramework {
	private ClientSender clientSender;
	private ClientReceiver clientReceiver;
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	private DatagramSocket udpSocket;
	private Socket tcpSocket;
	private volatile boolean connected;
	private volatile int serverPort;
	private InetAddress serverAddress;
	private ConcurrentCircularBuffer myBuffer;
	private ConcurrentCircularBuffer receiveBuffer;
	
	private ConcurrentCircularBuffer myInitBuffer; // first init buffer
	
	private static Client client = null; // singleton
	
	
 	protected Client() {
		try {
			udpSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
 	
 	public ConcurrentCircularBuffer initBuffer() {
 		return myInitBuffer;
 	}
 	
 	public static void disposeClient() {
 		System.out.println("dispose");
 		if (getClient()!=null) {
 			System.out.println("dispose client");
 			getClient().closeSockets();
 			client = null;
 		}

 	}
 	
 	private void closeSockets() {
 		udpSocket.close();
 		try {
			if (tcpSocket!=null) tcpSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 		System.out.println("Sockets close");
 	}
 	
 	public static Client getClient() {
 		if(client==null) {
 			client = new Client();
 			System.out.println("Join created client.");
 		}
 		return client;
 	}
 	
	public void findServers() {
		GetServers gServ = new GetServers(this,Game.getPossibleServers());
		gServ.start();
		
		DestroyGetServersThread destroy = new DestroyGetServersThread(gServ);
		destroy.start();
	}
	
	public void connectToServer(InetAddress servAddr, String playerName, int typeOfAutomobile) {
		try {
			tcpSocket = new Socket(servAddr,TCPPORT);
		} catch (IOException e) {
			System.out.println("upao ovde");
			e.printStackTrace();
		}
		
		serverAddress = servAddr;
		ConnectThread cThread = new ConnectThread(this,serverAddress,playerName,oos,ois);
		cThread.start();
		myBuffer = new ConcurrentCircularBuffer();
		receiveBuffer = new ConcurrentCircularBuffer();
		myInitBuffer = new ConcurrentCircularBuffer();
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
	
	public void setConnectedFlag(boolean flag) {
		connected = flag;
	}
	
	public Socket getTCPSocket() {
		return tcpSocket;
	}
	
	public int serverPort() {
		return serverPort;
	}
	
	public InetAddress serverAddress() {
		return serverAddress;
	}
	
	public DatagramSocket getUDPSocket() {
		return udpSocket;
	}

	public void send(GameObjectValues [] p) { 
		myBuffer.push(p);
	}
	
	public GameObjectValues[] receive() {
		return receiveBuffer.pop();
	}

	public ConcurrentCircularBuffer getSenderBuffer() {
		return myBuffer;
	}
	
	public ConcurrentCircularBuffer getReceiveBuffer() {
		return receiveBuffer;
	}

	@Override
	public void startGame(GameObjectValues[] p){
		
	}

	@Override
	public GameObjectValues[] startGame() {
		return myInitBuffer.pop();
	}
	
	public ClientSender getClientSender() {
		return clientSender;
	}
	
	public ClientReceiver getClientReceiver() {
		return clientReceiver;
	}
	
	
	
}
