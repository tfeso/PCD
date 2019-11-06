package messages;

public class MessagesType {

	// 0.. CLIENT
	public static Message newClient() {
		return new Message("001", "");
	}

	public static Message endClient() {
		return new Message("002", "");
	}

	// 1.. WORKER
	public static Message newWorker(int rotation) {
		return new Message("101", Integer.toString(rotation));
	}

	public static Message endWorker(int rotation) {
		return new Message("102", Integer.toString(rotation));
	}

	// 2.. TASK
	public static Message newTask() {
		return new Message("201", "");
	}

	public static Message endTask() {
		return new Message("202", "");
	}

	// 3.. ORDER
	public static Message newOrder() {
		return new Message("301", "");
	}

	public static Message endOrder() {
		return new Message("302", "");
	}	
}