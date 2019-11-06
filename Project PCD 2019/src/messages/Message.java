package messages;
import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable {

	private String code;
	private String content;

	public Message(String code, String content) {
		this.code = code;
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public String getContent() {
		return content;
	}
}