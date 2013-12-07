package dryrun.game.network;

public class NetConstants {
	static final int TCPPORT = 50000;
	static final int UDPPORT = 50001;
	static final int UDP_GSCL_PORT = 50002; //UDP GameState port.
	static final String FIND_SERVER = "find_server";
	static final String FIND_SERVER_R = "find_server_reply";
	static final String CONNECT_REQ = "want_to_connect";
	static final String CONNECT_ACC = "connect_req_accept";
}
