package zhllz.gamewizard.communication;

public interface ICommunicatable {
	
	public String getResponse(String request);
	public boolean isOnline();
	public void getOffLine();

}
