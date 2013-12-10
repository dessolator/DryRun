package dryrun.game.network.client;

import java.net.*;
import java.util.*;
import java.io.*;

import dryrun.game.common.GameObjectValues;
import dryrun.game.mechanics.Game;
import dryrun.game.network.ConcurrentCircularBuffer;
import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import static dryrun.game.network.NetConstants.*;

import dryrun.game.common.Player;
import dryrun.game.common.PlayerValues;

public class Client implements NetFramework {
	private DatagramSocket udpSocket;
	private Socket tcpSocket;
	private volatile boolean connected;
	private volatile int serverPort;
	private InetAddress serverAddress;
	private ConcurrentCircularBuffer myBuffer;
	private ConcurrentCircularBuffer receiveBuffer;
	private Player player;
	private static Client client = null;
	
	
 	protected Client() {
		try {
			udpSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
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
			e.printStackTrace();
		}
		
		serverAddress = servAddr;
		ConnectThread cThread = new ConnectThread(this,serverAddress,playerName,typeOfAutomobile);
		cThread.start();
		myBuffer = new ConcurrentCircularBuffer();
		receiveBuffer = new ConcurrentCircularBuffer();
	}
	
	public void sync () {
		
	}
	
	public void setServerPort(int p) {
		serverPort = p;
	}
	
	public void setUDPSocket() {
		try {
			udpSocket = new DatagramSocket(UDPPORT);
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
		try {
			return receiveBuffer.pop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ConcurrentCircularBuffer getSenderBuffer() {
		return myBuffer;
	}
	
	public ConcurrentCircularBuffer getReceiveBuffer() {
		return receiveBuffer;
	}
	
	
	
	
}
