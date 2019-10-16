import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable {
	
	private String message;
	//private String conversation;
	private String username;
	
	public Message(String message, String username) {
		
		this.message = message;
		//this.conversation = conversation;
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getMessage() {
		return message;
	}
	
	

}
