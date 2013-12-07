package dryrun.game.network;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import static dryrun.game.network.NetConstants.*;

public class Server implements NetFramework {
	private ServerSocket myTcpSockets;
	private DatagramSocket myUdpSocket;
	private ArrayList<ServerThread> myThreads;
	private int numOfPlayers=0;
	
	
	private static volatile int refreshThreadExists = 0;
	private static volatile int killRefreshThread = 0;
	
	
	public Server(){
		try {
			myTcpSockets= new ServerSocket(TCPPORT);
			myUdpSocket= new DatagramSocket(UDPPORT);
			myThreads = new ArrayList<ServerThread>();
		} catch (IOException e) {e.printStackTrace();}
		
		
	}
	
	private void getRefresh(){
		if(refreshThreadExists==0){
			refreshThreadExists=1;
			new Thread(){
				public void run(){
					byte x[] = new byte [100];
					while(killRefreshThread==0){
						DatagramPacket receive = new DatagramPacket(x, 100);
						try {
							myUdpSocket.receive(receive);
						} catch (IOException e) {refreshThreadExists=0; e.printStackTrace(); break;}
						String s = new String(receive.getData()).trim();
						if(s==FIND_SERVER){
							s=FIND_SERVER_R;
							x=s.getBytes();
							DatagramPacket reply = new DatagramPacket(x, x.length, receive.getAddress(), receive.getPort());
							try {
								myUdpSocket.send(reply);
							} catch (IOException e) {refreshThreadExists=0; e.printStackTrace(); break;}
						}
					}refreshThreadExists=0;
				}
			}.start();
		}
	}
	
	
	public void host(){
		while(1){
			getRefresh();
			getConnect();
			
			
		}
	}
	
	private void getConnect(){
		new Thread(){
			public void run(){
				
			}
		}.start();
	}
	
	
	@Override
	public void send(Packet p) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<GameStatePacket> receive() {
		// TODO Auto-generated method stub
		return null;
	}

}
