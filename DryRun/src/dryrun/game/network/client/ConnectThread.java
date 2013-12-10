package dryrun.game.network.client;

import static dryrun.game.network.NetConstants.*;
import dryrun.game.common.StringObject;
import dryrun.game.network.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.mechanics.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.DatagramSocket;
import java.net.SocketException;

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
		try {
			sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] b = null;
		try { 
			dis.readFully(b=new byte[dis.available()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String str = new String(b);
		System.out.println(str);
		String []split = str.split("#");
		if (split[0].equals(CONNECT_ACC)) {
			client.setUDPSocket();
			client.setConnectedFlag(true);
		}
		if (split[0].equals(CONNECT_REF)) {
			client.setConnectedFlag(false);
			System.out.println("ConnectThread CONNECT_REF");
			return;
		}
		
		/*int sizeOfArray = 0;
		while(sizeOfArray<10000) {
			try {
				sizeOfArray = dis.available();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		while(sizeOfArray<10000)  { // OVO PROVERITI PRILIKOM SLANJA PRVOG GAME STATE PAKETA
			try {
				dis.readFully(b=new byte[sizeOfArray = dis.available()]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			dis.read(b = new byte[dis.available()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GameStatePacket firstGamePositions = GameStatePacket.read(b);
		GameObjectValues [] gov = firstGamePositions.objects();
		System.out.println(((StringObject)gov[0]).message);
		
		
	}
}
