package dryrun.game.network;

import static dryrun.game.network.NetConstants.CONNECT_ACC;
import static dryrun.game.network.NetConstants.CONNECT_REF;
import static dryrun.game.network.NetConstants.CONNECT_REQ;
import static dryrun.game.network.NetConstants.TCPPORT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectThread extends Thread {
	private InetAddress serverAddress;
	private String playerName;
	private int typeOfAutomobile;
	private Client client;
	
	public ConnectThread(Client cli, InetAddress servAddr, String playName, int typeOfAuto) {
		client = cli;
		serverAddress = servAddr;
		playerName = playName;
		typeOfAutomobile = typeOfAuto;
	}
	
	public void run() {
		DataInputStream dis = null;
		DataOutputStream dos = null;
	
		try {
			dis = new DataInputStream(client.getTCPSocket().getInputStream());
			dos = new DataOutputStream(client.getTCPSocket().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String s = new String(CONNECT_REQ + "#" + playerName + "#" + typeOfAutomobile);
		try {
			dos.writeBytes(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] b = null;
		try { 
			dis.readFully(b=new byte[dis.available()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = new String(b);
		String []split = str.split("#");
		if (split[0].equals(CONNECT_ACC)) {
			client.setServerPort(Integer.parseInt(split[1]));//TODO kreiraj UDP soket na fiksnom portu i vrati ga klijentu
			client.setConnectedFlag(true);
		}
		if (split[0].equals(CONNECT_REF)) {
			client.setConnectedFlag(false);
		}
	}
}
