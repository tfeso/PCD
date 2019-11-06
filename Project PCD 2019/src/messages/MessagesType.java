package messages;

import java.util.HashMap;

public class MessagesType {

	// 0.. CLIENT
	public static Message newClient() {
		return new Message("001", "", null);
	}

	// 1.. WORKER
	public static Message newWorker(int rotation) {
		return new Message("101", Integer.toString(rotation), null);
	}
		
	public static Message updateWorkers(HashMap<Integer, Integer> workersToUpdateMap) {
		String workersActive = "";
		for(int rotation : workersToUpdateMap.keySet()) {
			if(workersToUpdateMap.get(rotation) > 0)
				workersActive += rotation + ";";
				
		}
		return new Message("103", "", workersActive);
	}

	// 2.. TASK
	public static Message newTask() {
		return new Message("201", "", null);
	}

	public static Message endTask() {
		return new Message("202", "", null);
	}

	// 3.. ORDER
	public static Message newOrder() {
		return new Message("301", "", null);
	}

	public static Message endOrder() {
		return new Message("302", "", null);
	}	
}