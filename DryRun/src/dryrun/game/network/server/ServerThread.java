package dryrun.game.network.server;

import java.net.*;
import dryrun.game.common.GameObjectValues;
import dryrun.game.common.Player;
import dryrun.game.network.ConcurrentCircularBuffer;

public class ServerThread  {
	private DatagramSocket mySocket;
	private Player myPlayer;
	private InetAddress clientAddr;
	
	private ServerSender sender;
	private ServerReceiver receiver;
	private ConcurrentCircularBuffer myRecBuffer;
	private ConcurrentCircularBuffer mySendBuffer;
	private ServerLoader Ldr;
	
	


	public ServerThread(int i, String[] split, InetAddress cladr, Server srv) throws SocketException {
		mySocket=new DatagramSocket(i);
		Ldr = new ServerLoader(srv);
		myRecBuffer=new ConcurrentCircularBuffer();
		mySendBuffer=new ConcurrentCircularBuffer();
		//TODO SET MYPLAYER DATA
	}
	
	//public ConcurrentCircularBuffer getBuffer(){return myBuffer;}
	
	public void start(){sender.start();receiver.start(); Ldr.start();}
	

	public void send(GameObjectValues[] p) {
		mySendBuffer.push(p);
	}

	public GameObjectValues[] receive() {
		try {
			return myRecBuffer.pop();
		} catch (InterruptedException e) {e.printStackTrace();}
		return null;
	}

	public ConcurrentCircularBuffer getReceiveBuffer() {return myRecBuffer;}
	
	public ConcurrentCircularBuffer getSendBuffer() {return mySendBuffer;}

	public InetAddress clientAddress() {return clientAddr;}
	
	public DatagramSocket getUDPSocket(){return mySocket;}

}
