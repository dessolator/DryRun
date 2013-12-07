package dryrun.game.network.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import dryrun.game.common.GameObjectValues;
import dryrun.game.common.Player;
import dryrun.game.network.ConcurrentCircularBuffer;
import dryrun.game.network.GameStatePacket;
import dryrun.game.network.NetFramework;
import dryrun.game.network.Packet;
import static dryrun.game.network.NetConstants.*;

public class ServerThread implements NetFramework  {
	private DatagramSocket mySocket;
	private Player myPlayer;
	private InetAddress clientAddr;
	
	private ServerSender sender;
	private ServerReceiver receiver;
	private ConcurrentCircularBuffer myRecBuffer;
	private ConcurrentCircularBuffer mySendBuffer;

	public ServerThread(int i, String[] split, InetAddress cladr) throws SocketException {
		mySocket=new DatagramSocket(i);
		
		myRecBuffer=new ConcurrentCircularBuffer();
		mySendBuffer=new ConcurrentCircularBuffer();
		//TODO SET MYPLAYER DATA
	}
	
	//public ConcurrentCircularBuffer getBuffer(){return myBuffer;}
	
	public void run(){
		
	}
	

	@Override
	public void send(GameObjectValues[] p) {
		new GameStatePacket
	}

	@Override
	public ArrayList<GameStatePacket> receive() {
		return null;
		
	}

	public ConcurrentCircularBuffer getReceiveBuffer() {return myRecBuffer;}
	
	public ConcurrentCircularBuffer getSendBuffer() {return mySendBuffer;}

	public InetAddress clientAddress() {return clientAddr;}

}
