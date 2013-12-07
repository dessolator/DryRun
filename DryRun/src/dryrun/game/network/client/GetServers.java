package dryrun.game.network.client;

import static dryrun.game.network.NetConstants.FIND_SERVER;
import static dryrun.game.network.NetConstants.FIND_SERVER_R;
import static dryrun.game.network.NetConstants.UDPPORT;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class GetServers extends Thread {
	private DatagramSocket comm;
	private boolean endOfThread = false;
	private ArrayList<InetAddress> listOfAddress;
	private Client client;
	
	public GetServers(Client cli, ArrayList<InetAddress> lOfAdd) {
		client = cli;
		listOfAddress = lOfAdd;
		try {
			comm = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(!interrupted()) {
			byte [] broadcast = FIND_SERVER.getBytes();
			DatagramPacket broadcastMessage = null;
			try {
				broadcastMessage = new DatagramPacket(broadcast,broadcast.length,InetAddress.getByName("255.255.255.255"),UDPPORT);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				client.getUDPSocket().send(broadcastMessage);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
			while (!endOfThread) {
				byte [] receiveMessage = new byte[20];
				DatagramPacket receivePacket = new DatagramPacket(receiveMessage,receiveMessage.length);
				try {
					client.getUDPSocket().receive(receivePacket);
				} catch (IOException e) {
					endOfThread = true;
					continue;
				}
				
				String server = new String(receivePacket.getData()).trim();
				if (server.equals(FIND_SERVER_R)) {
					listOfAddress.add(receivePacket.getAddress());
				}
			
			}
		}
	}
	
	public void obavesti() {
		comm.close();
		interrupt();
	}
}