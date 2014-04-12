package zhllz.gamewizard.communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static Scanner sc;
	public static Socket s;
	public static BufferedReader inFromServer;
	public static DataOutputStream outToServer;
	
	public static String server_response;
	public static boolean get_server_response;
	
	public static boolean should_listen;
	public static Listener listener;
	
	//A listener thread that keeps waiting for messages from server
	//Print the broadcast right away hearing broadcast messages
	//Store other messages 
	public static class Listener implements Runnable{

		@Override
		public void run() {
			
			while(should_listen){
				
				try {
					Thread.sleep(100);
					server_response = inFromServer.readLine();
					if(server_response==null){
						System.out.println("Server close the connection.\n"
								+ " Did you just get wrong with the username/password three times?\n"
								+ "If that's the case, wait for 60 sec for the next try.\n");
						should_listen=false;
						break;
					}
//					System.out.println(server_response);
					String[] res = server_response.split("#---->>#");
					if(res[0].equals("broadcast")){
						System.out.println("\n"+"Broadcast: "+res[1]);
						System.out.print("command: ");
					}
					else if(!get_server_response)
						get_server_response = true;
					else
						throw new IOException("Client can not handle so many messages");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					if(should_listen){
						e1.printStackTrace();
						should_listen=false;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					should_listen=false;
				}
				
			}
			
			
		}
		
	}
		
	public static String readFromServer() throws InterruptedException{
		while(should_listen){
			Thread.sleep(10);
			if(get_server_response){
				String msg = server_response;
				get_server_response = false;
				return msg;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String ip = "localhost";
			int port = 4119;
			
			s = new Socket(ip, port);
			sc = new Scanner(System.in);
			
			inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
			outToServer = new DataOutputStream(s.getOutputStream());
			
			should_listen = true;
			
			if(inFromServer==null || outToServer==null){
				System.out.println("Can not connect...\n");
				should_listen = false;
			}
			
			listener = new Listener();
			new Thread(listener).start();
			
			while(should_listen){
				System.out.print("command: ");
				String command = sc.nextLine();
				
				outToServer.writeBytes(command+"\n");
					
				String response = readFromServer();
				System.out.println(response);
				
			}
			should_listen = false;
			Thread.sleep(200);
			s.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sc.nextLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sc.nextLine();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sc.nextLine();
		}

	}

}

