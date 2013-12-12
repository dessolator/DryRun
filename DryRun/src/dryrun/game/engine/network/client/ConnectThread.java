package dryrun.game.engine.network.client;

import static dryrun.game.engine.network.NetConstants.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.common.GameState;



import dryrun.game.engine.network.*;
import dryrun.game.mechanics.Game;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;


public class ConnectThread extends Thread {
	private InetAddress serverAddress;
	private String playerName;
	private Client client;
	
	public ConnectThread(Client cli, InetAddress servAddr, String playName) {
		client = cli;
		serverAddress = servAddr;
		playerName = playName;
	}
	
	public void run() {
		
		ObjectInputStream dis = null;
		ObjectOutputStream dos = null;
	
		try {
			dos = new ObjectOutputStream(client.getTCPSocket().getOutputStream());
			dos.flush();
			dis = new ObjectInputStream(client.getTCPSocket().getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("connect thread");
		}
		
		System.out.println("Streams created");
		String s = new String(CONNECT_REQ + "#" + playerName);
		try {
			dos.writeObject(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		String str = null;
		
		try {
			str = (String) dis.readObject();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(str);
		String []split = str.split("#");
		if (split[0].equals(CONNECT_ACC)) {
			int srvPort = Integer.parseInt(split[1]);
			System.out.println(srvPort);
			client.setUDPSocket(srvPort);
			client.setConnectedFlag(true);
		}
		if (split[0].equals(CONNECT_REF)) {
			client.setConnectedFlag(false);
			System.out.println("ConnectThread CONNECT_REF");
			return;
		}

		GameStatePacket p = null;
		
		try {
			sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
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
		client.initBuffer().push(gov);
		System.out.println("primio paket");
		
		ClientReceiver cliRec = client.getClientReceiver();
		cliRec = new ClientReceiver(client);
		cliRec.start();
		
		ClientSender cliSen = client.getClientSender();
		cliSen= new ClientSender(client);
		cliSen.start();
		
		Game.setCurrentGameState(GameState.Game);
	}
}
