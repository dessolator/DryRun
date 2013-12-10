package dryrun.game.network.server;

import static dryrun.game.network.NetConstants.CONNECT_ACC;
import static dryrun.game.network.NetConstants.CONNECT_REF;
import static dryrun.game.network.NetConstants.CONNECT_REQ;
import static dryrun.game.network.NetConstants.MAX_PLAYERS;
import static dryrun.game.network.NetConstants.TCPPORT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import dryrun.game.common.Player;
import dryrun.game.mechanics.Game;

public class ConnectAcceptorThread extends Thread {
	private Server myServer;  //myDaddy
	private ServerSocket SrvSocket;
	private static volatile int currentUdp = 50010; //assigning UDP from 50010
	
	public ConnectAcceptorThread (Server srv) throws IOException{
		myServer=srv; SrvSocket= new ServerSocket(TCPPORT);
	}
	
	public void run(){
		while(!interrupted()){
			try {
				byte[] b=null;
				Socket s;

				if(myServer.numOfPlayers>=MAX_PLAYERS)continue; //if I reached maxplayers im gonna be stuck in an infinite loop
				
				s=SrvSocket.accept(); //I block on this line if there's no connectReq
				//TCP init
				ObjectInputStream dis= new ObjectInputStream(s.getInputStream()); 
				ObjectOutputStream dos= new ObjectOutputStream(s.getOutputStream());

				//reading TCP packet.
				String str=null;
				try {
					System.out.println("about to read str:");
					str = (String)dis.readObject();
					System.out.println(str);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}										
				System.out.println(str);
				String split[]=str.split("#+"); //NAME MUST NOT BE #
				
				
				if (split[0].equals(CONNECT_REQ) && myServer.numOfPlayers<MAX_PLAYERS){ //if packet is CONNECT_REQ and I have not reached maxPlayers
					System.out.println("usao u if");
					myServer.numOfPlayers++; //then increase number of connected players
					
					System.out.println("accepting connection");
					str = new String(CONNECT_ACC+"#"+currentUdp);
					dos.writeObject(str);//notify the client that it's request is accepted
					myServer.CreateClThread(currentUdp++, split, s.getInetAddress(),dis,dos,s); //create a serverside thread which serves this client
				}
				else{
					System.out.print("usao u else");
					dos.writeBytes(CONNECT_REF);//TODO close s
				}
				
				
			} catch (IOException e) { e.printStackTrace();}
		
		}
		
	}
	
	public void obavesti() throws IOException{SrvSocket.close();interrupt();}
	
}


