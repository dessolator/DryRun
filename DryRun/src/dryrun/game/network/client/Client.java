package dryrun.game.network.client;

import java.net.*;
import java.util.*;
import java.io.*;

import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import dryrun.game.network.Packet;
import static dryrun.game.network.NetConstants.*;

public class Client implements NetFramework {
	private DatagramSocket udpSocket;
	private Socket tcpSocket;
	private volatile boolean connected;
	private volatile int serverPort;
	
	
	
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
	
	public void connectToServer(InetAddress serverAddress, String playerName, int typeOfAutomobile) {
		try {
			tcpSocket = new Socket(serverAddress,TCPPORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ConnectThread cThread = new ConnectThread(this,serverAddress,playerName,typeOfAutomobile);
		cThread.start();
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
	
	public DatagramSocket getUDPSocket() {
		return udpSocket;
	}
	
	public void send(Packet p) {
		
	}
	
	public ArrayList<GameStatePacket> receive() {
		return null;
	}
	
	
}
