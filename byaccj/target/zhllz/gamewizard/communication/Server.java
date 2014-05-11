package zhllz.gamewizard.communication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
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
			printLocalIP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void broadcast(String msg) {
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
	
	public void printLocalIP() throws SocketException{
        System.out.println("IP address of this host will be given below. Choose one for clients to connect.");
        System.out.println("If the server and clients are on the same host, \"127.0.0.1\" might be best.");
        System.out.println("If the server and clients are on the hosts within one LAN, IP that starts with 192 might be best choice.");
        System.out.println("The programmer is not sure about other cases. Two ways you can try if connection fails:");
        System.out.println("\t1. Use another ip address given below.\n\t2. Look into firewalls and other stuff of your networking environment. "
                + "Maybe the port you are using is blocked or your host only receive packets from certain addresses."
                + "\n\t3. Maybe BigBrother is trying to control you!( hopfully not )");
        System.out.println("The ip address of this host is as follows. (I know you have been waiting. Sorry, here they are:)");
        Enumeration e=NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n=(NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while(ee.hasMoreElements())
            {
                InetAddress i= (InetAddress) ee.nextElement();
                System.out.println(i.getHostAddress());
            }
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

			@Override
			public void getOffLine() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isOnline() {
				// TODO Auto-generated method stub
				return true;
			}
			
		};
		ICommunicatable player2 = new ICommunicatable(){

			@Override
			public String getResponse(String request) {
				return "player2 " + request;
			}

			@Override
			public void getOffLine() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isOnline() {
				// TODO Auto-generated method stub
				return true;
			}
			
		};
		
		ServerConnection sc1 = s.waitForPlayer(player1);
		ServerConnection sc2 = s.waitForPlayer(player2);
		
		
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
		
		
		
	}


}
