package zhllz.gamewizard.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements Runnable {
	
	public ServerSocket waiting_server;
	public Vector<ServerConnection> connections;
	
	public Server(int port) {
		super();
		try {
			waiting_server = new ServerSocket(port);
			connections = new Vector<ServerConnection>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void broadcast(String msg) throws IOException{
		for(ServerConnection sc : connections){
			sc.sendBroadcast(msg); 
		}
	}

	@Override
	public void run() {
		while(true){
			
			Socket skt;
			try {
				skt = waiting_server.accept();
				ServerConnection conn = new ServerConnection(skt);
				connections.add(conn);
				new Thread(conn).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
