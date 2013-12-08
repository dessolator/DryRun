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
import java.util.List;

public class GetServers extends Thread {
	private DatagramSocket comm;
	private boolean endOfThread = false;
	private List<InetAddress> listOfAddress;
	private Client client;
	
	public GetServers(Client cli, List<InetAddress> lOfAdd) {
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
				comm.send(broadcastMessage);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println(new String(broadcastMessage.getData()).trim());
			
			while (!endOfThread) {
				byte [] receiveMessage = new byte[20];
				DatagramPacket receivePacket = new DatagramPacket(receiveMessage,receiveMessage.length);
				try {
					comm.receive(receivePacket);
				} catch (IOException e) {
					endOfThread = true;
					continue;
				}
				
				String server = new String(receivePacket.getData()).trim();
				if (server.equals(FIND_SERVER_R)) {
					if (receivePacket.getAddress()==null) System.out.println("paket null");
					if (listOfAddress == null) System.out.println("listOfAddress null");
					System.out.println(FIND_SERVER_R);
					listOfAddress.add(receivePacket.getAddress());
				}
			
			}
			
		}
	}
	
	public void obavesti() {
		comm.close();
	}
}