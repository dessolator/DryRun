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
	private Server myServer;
	private ServerSocket SrvSocket;
	private static volatile int currentUdp = 50010;
	
	public ConnectAcceptorThread (Server srv) throws IOException{
		myServer=srv; SrvSocket= new ServerSocket(TCPPORT);
	}
	
	public void run(){
		while(!interrupted()){
			try {
				byte[] b=null;
				Socket s;
				//TODO if number of players exceeded
				myServer.mySockets.add(s=SrvSocket.accept());
				myServer.numOfPlayers++;
				DataInputStream dis= new DataInputStream(s.getInputStream());
				DataOutputStream dos= new DataOutputStream(s.getOutputStream());
				dis.readFully(b=new byte[dis.available()]);										//TODO string split
				String str=new String(b);
				String split[]=str.split("#+"); //NAME MUST NOT BE #
				
				
				if (split[0]==CONNECT_REQ && myServer.numOfPlayers<MAX_PLAYERS){ //TODO IMPLEMENT MAX PLAYERS
					dos.writeBytes(CONNECT_ACC+"#"+currentUdp);//TODO allow getting playerName, PlayerCar
					myServer.CreateClThread(currentUdp++, split, s.getInetAddress(),dis,dos);
				}
				else{
					dos.writeBytes(CONNECT_REF);//TODO close s
				}
				
				
			} catch (IOException e) { e.printStackTrace();}
		
		}
		
	}
	
	public void Obavesti() throws IOException{SrvSocket.close();interrupt();}
	
}


