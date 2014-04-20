package zhllz.gamewizard.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	
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
	
	public ServerConnection waitForPlayer(ICommunicatable game){
		Socket skt;
		ServerConnection conn = null;
		try {
			skt = waiting_server.accept();
			conn = new ServerConnection(skt, game);
			connections.add(conn);
			new Thread(conn).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//Tester
	public static void main(String[] args) {
		Server s = new Server(4119);
		
		ICommunicatable game = new ICommunicatable(){

			@Override
			public String getResponse(String request) {
				return request;
			}
			
		};
		
		ServerConnection sc1 = s.waitForPlayer(game);
		ServerConnection sc2 = s.waitForPlayer(game);
		
		
		try {
			s.broadcast("This is a broadcast!");
			String input = sc1.waitForInput("This is a promt!");
			System.out.println(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


}
