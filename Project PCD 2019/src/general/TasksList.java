package general;

import client.Task;
import java.util.LinkedList;

public class TasksList implements BloquingQueue {

	private LinkedList<Task> tasksList;
	private final static int LIMIT = 2;
	
	public TasksList() {
		tasksList = new LinkedList<Task>();
	}
	
	public synchronized void offer(Task task) throws InterruptedException {
		while(size() == LIMIT) {
			wait();
		}
		tasksList.add(task);
		notifyAll();
	}

	public synchronized Task poll() throws InterruptedException {
		while(size() == 0) {
			wait();
		}
		notifyAll();
		return tasksList.removeFirst();
	}

	public int size() {
		return tasksList.size();
	}

	public void clear() {
		if(tasksList.size() > 0)
			tasksList.clear();
	}
}