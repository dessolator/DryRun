package dryrun.game.network;

import java.net.*;
import java.util.*;

public class Client implements NetFramework {
	private DatagramSocket udpSocket;
	private Socket tcpSocket;
	
	public Client() {
		try {
			udpSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Server> findServers() {
		
	}
	
	public void send(GameStatePacket gState) {
		
	}
	
	public ArrayList<GameStatePacket> receive() {
		return null;
	}
}
