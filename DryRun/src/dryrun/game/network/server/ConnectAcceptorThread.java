package dryrun.game.network.server;

import static dryrun.game.network.NetConstants.CONNECT_ACC;
import static dryrun.game.network.NetConstants.CONNECT_REF;
import static dryrun.game.network.NetConstants.CONNECT_REQ;
import static dryrun.game.network.NetConstants.MAX_PLAYERS;
import static dryrun.game.network.NetConstants.TCPPORT;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

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
				
				myServer.mySockets.add(s=SrvSocket.accept()); //I block on this line if there's no connectReq
				//TCP init
				DataInputStream dis= new DataInputStream(s.getInputStream()); 
				DataOutputStream dos= new DataOutputStream(s.getOutputStream());
				
				//reading TCP packet.
				dis.readFully(b=new byte[dis.available()]);										
				String str=new String(b); //splitting packet
				String split[]=str.split("#+"); //NAME MUST NOT BE #
				
				
				if (split[0]==CONNECT_REQ && myServer.numOfPlayers<MAX_PLAYERS){ //if packet is CONNECT_REQ and I have not reached maxPlayers
					myServer.numOfPlayers++; //then increase number of connected players
					dos.writeBytes(CONNECT_ACC+"#"+currentUdp);//notify the client that it's request is accepted
					myServer.CreateClThread(currentUdp++, split, s.getInetAddress(),dis,dos); //create a serverside thread which serves this client
				}
				else{
					dos.writeBytes(CONNECT_REF);//TODO close s
				}
				
				
			} catch (IOException e) { e.printStackTrace();}
		
		}
		
	}
	
	public void Obavesti() throws IOException{SrvSocket.close();interrupt();}
	
}


