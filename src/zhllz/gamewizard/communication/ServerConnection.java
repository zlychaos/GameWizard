package zhllz.gamewizard.communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection {
//public class ServerConnection implements Runnable {
	
	Socket conn;
	BufferedReader inFromClient;
	DataOutputStream outToClient;
	
	public ServerConnection(Socket conn) throws IOException {
		super();
		this.conn = conn;
		inFromClient = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		outToClient = new DataOutputStream(conn.getOutputStream());
	}
	
//	//Find response for commands
//	public String getResult(String command){
//		String result = null;
//		if(command==null)
//			return result;
//		return result;
//	}
	
	public String waitForInput(String promt) throws IOException{
		outToClient.writeBytes(promt+"\n");
		String command = inFromClient.readLine();
		return command;
	}
	
	public void sendBroadcast(String msg) throws IOException{
		outToClient.writeBytes(MsgType.BROADCAST+MsgType.separator+msg+"\n");
	}
//	
//	@Override
//	public void run() {
//		
//		while(true){
//			try {
//				Thread.sleep(100);
//				String command = inFromClient.readLine();
//				//System.out.println(command);
//				String response = getResult(command);
//				if(response==null){
//					conn.close();
//					// TODO The player is offline
//					break;
//				}
//				outToClient.writeBytes(response+"\n");
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
//
}
