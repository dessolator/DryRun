package dryrun.game.engine.network.server;

import static dryrun.game.engine.network.NetConstants.MAX_PLAYERS;
import static dryrun.game.engine.network.NetConstants.TCPPORT;

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
		TCPThread t=null;
		while(!interrupted()){

				Socket s=null;

				if(myServer.numOfPlayers>=MAX_PLAYERS)continue; //if I reached maxplayers im gonna be stuck in an infinite loop
				System.out.println("blocking until connect");
				
				try {
					s=SrvSocket.accept();	//I block on this line if there's no connectReq
				} catch (IOException e) {e.printStackTrace();} 
				
				System.out.println("past block");
				//TCP init
				
				t=new TCPThread(myServer,s,currentUdp++);
				t.start();
				
				
		
		}
		
	}
	
	public void obavesti() throws IOException{SrvSocket.close();interrupt();}
	
}


