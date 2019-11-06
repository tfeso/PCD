package messages;
import java.io.Serializable;

public class Message implements Serializable {

	private String code;
	private String content;
	private String workersToUpdateStr;

	public Message(String code, String content, String workersToUpdateStr) {
		this.code = code;
		this.content = content;
		this.workersToUpdateStr = workersToUpdateStr;
	}

	public String getCode() {
		return code;
	}

	public String getContent() {
		return content;
	}
	
	
	public String getWorkersToUpdate() {
		return workersToUpdateStr;
	}
	
}