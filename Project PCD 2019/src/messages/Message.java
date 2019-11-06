package messages;
import java.io.Serializable;
import java.util.ArrayList;

import client.Task;

public class Message implements Serializable {

	private String code;
	private String content;
	private String workersToUpdateStr;
	private ArrayList<Task> tasksList;
	private Task taskDelivery;

	public Message(String code, String content, String workersToUpdateStr, ArrayList<Task> tasksList, Task taskDelivery) {
		this.code = code;
		this.content = content;
		this.workersToUpdateStr = workersToUpdateStr;
		this.tasksList = tasksList;
		this.taskDelivery = taskDelivery;
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
	
	public ArrayList<Task> getTasksList() {
		return tasksList;
	}
	
	public Task getTaskDelivery() {
		return taskDelivery;
	}
	
}