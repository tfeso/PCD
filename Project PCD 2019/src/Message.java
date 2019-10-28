import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable {
	
	private String type;
	private int rotation;
	
	public Message(String type, int rotation) {
		this.type = type;
		this.rotation = rotation;
	}
	
	public Message(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	

}
