package dryrun.game.engine.network.server;

import static dryrun.game.engine.network.NetConstants.CONNECT_ACC;
import static dryrun.game.engine.network.NetConstants.CONNECT_REF;
import static dryrun.game.engine.network.NetConstants.CONNECT_REQ;
import static dryrun.game.engine.network.NetConstants.MAX_PLAYERS;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import dryrun.game.engine.network.GameStatePacket;

public class TCPThread extends Thread {
	private Server myServer;
	private Socket s;
	private ObjectInputStream dis; 
	private ObjectOutputStream dos;
	private int udpPort;
	private GameStatePacket gsp=null;
	

	public TCPThread(Server server,Socket s, int udp) {
		myServer=server;
		this.s=s;
		udpPort=udp;
		try{
			dis = new ObjectInputStream(s.getInputStream());
			dos = new ObjectOutputStream(s.getOutputStream());
			dos.flush();
		}catch(IOException e){e.printStackTrace();}
		
		System.out.println("created tcp streams and its thread.");
	}
	
	public void run(){
		
		connect();
		
			while(gsp==null)
				try {
					gsp.wait();
				} catch (InterruptedException e) {e.printStackTrace();}
	
		
		try {
			dos.writeObject(gsp);
		} catch (IOException e) {e.printStackTrace();}
		
		
		
	}
	
	public void connect(){
		
		//reading TCP packet.
		String str=null;
		try {
			System.out.println("about to read str:");
			str = (String)dis.readObject();
			System.out.println(str);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}										
		System.out.println(str);
		String split[]=str.split("#+"); //NAME MUST NOT BE #
		
		
		if (split[0].equals(CONNECT_REQ) && myServer.numOfPlayers<MAX_PLAYERS){ //if packet is CONNECT_REQ and I have not reached maxPlayers
			System.out.println("usao u if");
			myServer.numOfPlayers++; //then increase number of connected players
			
			System.out.println("accepting connection");
			str = new String(CONNECT_ACC+"#"+udpPort);
			try {
				dos.writeObject(str);//notify the client that it's request is accepted			
				myServer.CreateClThread(udpPort, split, s.getInetAddress(),this);//create a serverside thread which serves this client
			} catch (SocketException e) {e.printStackTrace();}
			  catch (IOException e) {e.printStackTrace();}
		}
		else{
			System.out.print("usao u else");
			try {
				dos.writeBytes(CONNECT_REF);
			} catch (IOException e) {e.printStackTrace();}
			
		}
		
		
	}
	
	public void close(){
		try {
			s.close();
		} catch (IOException e) {e.printStackTrace();}
		
	}
	
	public void setGameStatePacket(GameStatePacket gsp){this.gsp=gsp;}
	public GameStatePacket getGameStatePacket(){return gsp;}

}
