package zhllz.gamewizard.communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {
	
	public Scanner sc;
	public Socket s;
	public BufferedReader inFromServer;
	public DataOutputStream outToServer;
	
	Random rand = new Random();

	public boolean should_listen;
	public Listener listener;
	
	public boolean waiting_for_response;
	public String request_id;
	public String response;
	
	public String userInput;
	
	
	public boolean requested;// There is request from the server yet to respond
	
	//A listener thread that keeps waiting for messages from server
	//Print the broadcast right away hearing broadcast messages
	//Store other messages 
	public class Listener implements Runnable{
		
		public void handleResponse(JSONObject jsonMsg) throws JSONException{
			String ID = jsonMsg.getString(StrController.Resp_ID);
			if(waiting_for_response && ID.equals(request_id)){
				response = jsonMsg.getString(StrController.RESPONSE);
			}
			else{
				System.out.println("An wanted response received...");
			}
			
		}
		
		public void handleRequest(JSONObject jsonMsg) throws JSONException{
			String ID = jsonMsg.getString(StrController.Req_ID);
			String request = jsonMsg.getString(StrController.REQUEST);
			try {
				reactToRequest(request, ID);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while(Client.this.should_listen){
				String jsonMsg = null;
				try {
					jsonMsg = inFromServer.readLine();
					if(jsonMsg == null){
						Client.this.should_listen = false;
						break;
					}
					JSONObject jo = new JSONObject(jsonMsg);
					String type = jo.getString(StrController.TYPE);
					if(type.equals(StrController.BROADCAST)){
						String msg = jo.getString(StrController.MSG);
						reactToBroadcast(msg);
					}
					else if(type.equals(StrController.REQUEST)){
						this.handleRequest(jo);
					}
					else if(type.equals(StrController.RESPONSE)){
						this.handleResponse(jo);
					}
//					else if(type.equals(StrController.RESPONSE_AND_REQUEST)){
//						this.handleResponse(jo);
//						this.handleRequest(jo);
//					}
					else{
						System.out.println("An unknown msg received..");
					}
				} catch (IOException e) {
					Client.this.should_listen = false;
					e.printStackTrace();
				} catch (JSONException e) {
					System.out.println("An un-formatted msg received.." + jsonMsg);
					e.printStackTrace();
					
				}
			}
		}
		
	}
	
	
	
	public void reactToBroadcast(String broadcast){
		System.out.println(broadcast);
	}
	
	public void reactToRequest(String promt, String req_id) throws InterruptedException{
		setRequested(true);
		
		System.out.println(promt);
		while(this.userInput == null){
			Thread.sleep(100);
		}
		//System.out.println(userInput);
		JSONObject resp = new JSONObject();
		try {
			resp.put(StrController.TYPE ,StrController.RESPONSE);
			resp.put(StrController.Resp_ID, req_id);
			resp.put(StrController.RESPONSE, userInput);
			this.outToServer.writeBytes(resp.toString()+"\n");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			this.should_listen = false;
			e.printStackTrace();
		}
		
		setRequested(false);
		this.userInput = null;
	}
	
	public String sendRequest(String request) throws InterruptedException{
		
		String response = null;
		
		try {
			JSONObject req = new JSONObject();
			this.request_id = (new Date()).toString()+rand.nextInt();
			req.put(StrController.TYPE ,StrController.REQUEST);
			req.put(StrController.Req_ID, this.request_id);
			req.put(StrController.REQUEST, request);
			this.outToServer.writeBytes(req.toString()+"\n");
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
		}
		return response;

	}
	
	public boolean isRequested() {
		return requested;
	}

	public void setRequested(boolean requested) {
		this.requested = requested;
	}

	public void game_start() {
		
		try {
			String ip = "localhost";
			int port = 4119;
			
			s = new Socket(ip, port);
			sc = new Scanner(System.in);
			
			inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
			outToServer = new DataOutputStream(s.getOutputStream());
			
			this.should_listen = true;
			this.waiting_for_response = false;
			this.setRequested(false);
			
			listener = new Listener();
			new Thread(listener).start();
			
			while(this.should_listen){
				String input = sc.nextLine();
				//System.out.println(input);
				if(isRequested()){
					if(this.userInput == null)
						this.userInput = input;
					else
						System.out.println("Hold on, still responding to last request..");
				}
				else{
					String res = sendRequest(input);
					System.out.println(res);
				}
				
			}
			
			Thread.sleep(1000);
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
	
	public static void main(String[] args) {
		Client client = new Client();
		client.game_start();
	}

}

