package dryrun.game.network;

import java.net.*;
import java.util.*;
import java.io.*;

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
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis()-startTime < 3000000000l) {}//TODO timer u novi thread
		gServ.obavesti();
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
