
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

//public class ServerConnection {
public class ServerConnection implements Runnable {
	
	Socket conn;
	BufferedReader inFromClient;
	DataOutputStream outToClient;
	ICommunicatable player;
	
	Random rand = new Random();
	public boolean waiting_for_response;
	public String request_id;
	public String response;
	
	public boolean should_listen;
	
	public ServerConnection(Socket conn, ICommunicatable player) throws IOException {
		super();
		this.conn = conn;
		this.player = player;
		inFromClient = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		outToClient = new DataOutputStream(conn.getOutputStream());
		
		this.response = null;
		this.waiting_for_response = false;
		this.should_listen = true;
	}
	
	public String waitForInput(String promt){
		
		String response = null;
		
		try {
			JSONObject req = new JSONObject();
			this.request_id = (new Date()).toString()+rand.nextInt();
			req.put(StrController.TYPE ,StrController.REQUEST);
			req.put(StrController.Req_ID, this.request_id);
			req.put(StrController.REQUEST, promt);
			this.outToClient.writeBytes(req.toString()+"\n");
			this.waiting_for_response = true;
			
			while(this.should_listen){
				if(this.response == null)
					Thread.sleep(100);
				else{
					response = this.response;
					this.response = null;
					break;
				}
			}
			this.waiting_for_response = false;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			this.should_listen = false;
			e.printStackTrace();
		} catch (InterruptedException e) {
			this.should_listen = false;
			e.printStackTrace();
		}
		return response;
	}
	
	public void sendBroadcast(String msg) {
		
		if(!player.isOnline())
			return;
		
		try {
			JSONObject jo = new JSONObject();
			jo.put(StrController.TYPE, StrController.BROADCAST);
			jo.put(StrController.MSG, msg);
			outToClient.writeBytes(jo.toString()+"\n");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			player.getOffLine();
			e.printStackTrace();
		}
		
		
	}
	
	public void closeConnection(){
		should_listen = false;
		try {
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		while(should_listen){
			try {
				
				String jsonMsg = inFromClient.readLine();
				if(jsonMsg == null){
					player.getOffLine();
					should_listen = false;
					conn.close();
					break;
				}
				
				JSONObject jo = new JSONObject(jsonMsg);
				String type = jo.getString(StrController.TYPE);
				
				if(type.equals(StrController.REQUEST)){
					String Req_ID = jo.getString(StrController.Req_ID);
					String request = jo.getString(StrController.REQUEST);
					String res = player.getResponse(request);
					JSONObject resp = new JSONObject();
					resp.put(StrController.TYPE, StrController.RESPONSE);
					resp.put(StrController.Resp_ID, Req_ID);
					resp.put(StrController.RESPONSE, res);
					outToClient.writeBytes(resp.toString()+"\n");
				}
				else if(type.equals(StrController.RESPONSE)){
					String ID = jo.getString(StrController.Resp_ID);
					if(waiting_for_response && ID.equals(request_id)){
						this.response = jo.getString(StrController.RESPONSE);
					}
					else{
						System.out.println("Debug: "+request_id+" | "+ID);
						System.out.println("An unwanted response received...");
					}
				}
				else{
					System.out.println("An unknown msg received..");
				}
			} catch (SocketException e) {
				player.getOffLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				should_listen = false;
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
