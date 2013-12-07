package dryrun.game.network;

import java.io.IOException;
import java.net.*;
import java.util.*;
import static dryrun.game.network.NetConstants.*;

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
	
	public ArrayList<InetAddress> findServers() {
		byte [] broadcast = "find_server".getBytes();
		try {
			DatagramPacket data = new DatagramPacket(broadcast,broadcast.length,InetAddress.getByName("255.255.255.255"),UDPPORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		ArrayList<InetAddress> servers = new ArrayList<InetAddress> ();
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis()-startTime<3000000000l) {
			byte [] receiveMessage = new byte[20];
			DatagramPacket receivePacket = new DatagramPacket(receiveMessage,receiveMessage.length);
			try {
				udpSocket.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String server = new String(receivePacket.getData()).trim();
			if (server.equals("find_server_reply")) {
				servers.add(receivePacket.getAddress());
			}
		}
		return servers;		
	}
	
	public void send(GameStatePacket gState) {
		
	}
	
	public ArrayList<GameStatePacket> receive() {
		return null;
	}
}
