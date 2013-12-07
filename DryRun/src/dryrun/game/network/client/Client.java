package dryrun.game.network.client;

import java.net.*;
import java.util.*;
import java.io.*;

import dryrun.game.common.GameObjectValues;
import dryrun.game.network.ConcurrentCircularBuffer;
import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import dryrun.game.network.Packet;
import static dryrun.game.network.NetConstants.*;

public class Client implements NetFramework {
	private DatagramSocket udpSocket;
	private Socket tcpSocket;
	private volatile boolean connected;
	private volatile int serverPort;
	private InetAddress serverAddress;
	private ConcurrentCircularBuffer myBuffer;
	private ConcurrentCircularBuffer receiveBuffer;
	
	
 	public Client() {
		try {
			udpSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	public void findServers(ArrayList<InetAddress> servers) {
		GetServers gServ = new GetServers(this,servers);
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
