package dryrun.game.network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import dryrun.game.network.Packet;
import static dryrun.game.network.NetConstants.*;


public class Server implements NetFramework {
	private ServerSocket SrvSocket;
	private DatagramSocket myUdpSocket;
	private ArrayList<ServerThread> myThreads;
	private ArrayList<Socket> mySockets=new ArrayList<Socket>();
	private int numOfPlayers=0; //TODO SHOULD GO IN GAMESTATE
	
	
	private static volatile int refreshThreadExists = 0;
	private static volatile int killRefreshThread = 0;
	private static volatile int currentUdp = 50010;
	

	
	public Server(){
		try {
			SrvSocket= new ServerSocket(TCPPORT);
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
						if(s==FIND_SERVER){//TODO string.equals
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
		while(true){
			getRefresh();
			getConnect();//TODO make getConnect a singleton
			
			
		}
	}
	
	private void getConnect(){
		new Thread(){
			public void run(){
				try {
					byte[] b=null;
					Socket s;
					//TODO if number of players exceeded
					mySockets.add(s=SrvSocket.accept());
					numOfPlayers++;
					DataInputStream dis= new DataInputStream(s.getInputStream());
					DataOutputStream dos= new DataOutputStream(s.getOutputStream());
					dis.readFully(b=new byte[dis.available()]);										//TODO string split
					String str=new String(b);
					String split[]=str.split("#+"); //NAME MUST NOT BE #
					
					
					if (split[0]==CONNECT_REQ && numOfPlayers<MAX_PLAYERS){ //TODO IMPLEMENT MAX PLAYERS
						dos.writeBytes(CONNECT_ACC+"#"+currentUdp);//TODO allow getting playerName, PlayerCar
						myThreads.add(new ServerThread(currentUdp++, split));
					}
					else{
						dos.writeBytes(CONNECT_REF);//TODO close s
					}
					
					
				} catch (IOException e) { e.printStackTrace();}
				
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
