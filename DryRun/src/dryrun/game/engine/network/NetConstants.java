package dryrun.game.engine.network;

public class NetConstants {
	public static final int TCPPORT = 50000;
	public static final int UDPPORT = 50001;
	public static final int UDP_GSCL_PORT = 50002; //UDP GameState port.
	public static final String FIND_SERVER = "find_servers";
	public static final String FIND_SERVER_R = "find_server_reply";
	public static final String CONNECT_REQ = "want_to_connect";
	public static final String CONNECT_ACC = "connect_req_accept";
	public static final String CONNECT_REF = "connect_req_refuse";
	public static final int MAX_PLAYERS=4;
	public static final float P2M = (float)1/50;
	public static final float M2P = 50;
	public static final int SIZE_OF_BUFFER = 50;
}
