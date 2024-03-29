package messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import client.Order;
import client.Task;

public class MessagesType {

	// 0.. CLIENT
	public static Message newClient() {
		return new Message("001", "", null, null, null, null);
	}

	// 1.. WORKER
	public static Message newWorker(int rotation) {
		return new Message("101", Integer.toString(rotation), null, null, null, null);
	}
		
	public static Message updateWorkers(HashMap<Integer, Integer> workersToUpdateMap) {
		String workersActive = "";
		for(int rotation : workersToUpdateMap.keySet()) {
			if(workersToUpdateMap.get(rotation) > 0)
				workersActive += rotation + ";";
		}
		return new Message("103", "", workersActive, null, null, null);
	}

	// 2.. TASK
	public static Message newTask(ArrayList<Task> tasksList, UUID idClient) {
		return new Message("201", idClient.toString(), null, tasksList, null, null);
	}
	
	public static Message getTask() {
		return new Message("204", "", "", null, null, null);
	}
	
	public static Message taskDelivery(Task task) {
		return new Message("205", "", "", null, task, null);
	}

	public static Message endTask() {
		return new Message("202", "", null, null, null, null);
	}
	
	public static Message taskFromWorker(Task task) {
		return new Message("206", "", null, null, task, null);
	}

	public static Message workerIsDead() {
		return new Message("207", "", null, null, null, null);
	}
	
	// 3.. ORDER
	public static Message newOrder() {
		return new Message("301", "", null, null, null, null);
	}

	public static Message endOrder(Order order) {
		return new Message("302", "", null, null, null, order);
	}	
}