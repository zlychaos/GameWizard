package zhllz.gamewizard.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
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
	
	public ServerConnection waitForPlayer(ICommunicatable player){
		Socket skt;
		ServerConnection conn = null;
		try {
			skt = waiting_server.accept();
			conn = new ServerConnection(skt, player);
			connections.add(conn);
			new Thread(conn).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeServer(){
		for(ServerConnection sc : connections ){
			sc.closeConnection();
		}
	}
	
	//Tester
	public static void main(String[] args) {
		Random rand = new Random();
		System.out.println((new Date()).toString()+rand.nextInt());
		
		
		Server s = new Server(4119);
		
		ICommunicatable player1 = new ICommunicatable(){

			@Override
			public String getResponse(String request) {
				return "player1 " + request;
			}
			
		};
		ICommunicatable player2 = new ICommunicatable(){

			@Override
			public String getResponse(String request) {
				return "player2 " + request;
			}
			
		};
		
		ServerConnection sc1 = s.waitForPlayer(player1);
		ServerConnection sc2 = s.waitForPlayer(player2);
		
		
		try {
			s.broadcast("This is a broadcast!");
			for(int i=0;i<15;i++){
				int choice = rand.nextInt(2)+1;
				String input = null;
				if(choice == 1){
					input = sc1.waitForInput("This is a promt!");
				}
				else if(choice == 2){
					input = sc2.waitForInput("This is a promt!");
				}
				else{
					input = "Something wrong";
				}
				if(input == null){
					break;
				}
				System.out.println("i: "+Integer.toString(i)+"choice: "+Integer.toString(choice)+" | "+input);
			}
			
			s.closeServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


}
