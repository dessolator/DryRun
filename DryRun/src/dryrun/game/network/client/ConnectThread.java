package dryrun.game.network.client;

import static dryrun.game.network.NetConstants.*;
import dryrun.game.common.StringObject;
import dryrun.game.network.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.mechanics.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		
		ObjectInputStream dis = null;
		ObjectOutputStream dos = null;
	
		try {
			dis = new ObjectInputStream(client.getTCPSocket().getInputStream());
			dos = new ObjectOutputStream(client.getTCPSocket().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String s = new String(CONNECT_REQ + "#" + playerName + "#" + typeOfAutomobile);
		try {
			dos.writeObject(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String str = null;
		try { 
			try {
				str = (String) dis.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		GameStatePacket p = null;

		try {
			p = (GameStatePacket) dis.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		GameObjectValues [] gov = p.get();
		System.out.println(gov[0].getCoordX());
		System.out.println(gov[0].getCoordY());
		System.out.println(gov[0].getDimX());
		System.out.println(gov[0].getDimY());
	}
}
