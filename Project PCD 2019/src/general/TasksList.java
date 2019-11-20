package general;

import java.util.LinkedList;
import java.util.Queue;

public class TasksList<T> {

	private Queue<T> tasksList;
	private final static int LIMIT = 100000;
	
	public TasksList() {
		tasksList = new LinkedList<>();
	}
	
	public synchronized void offer(T task) throws InterruptedException {
		while(size() >= LIMIT) {
			wait();
		}
		tasksList.add(task);
		notifyAll();
	}

	public synchronized T poll() throws InterruptedException {
		while(size() == 0) {
			wait();
		}
		T temp = tasksList.remove();
		notifyAll();
		return temp;
	}

	public synchronized int size() {
		return tasksList.size();
	}

	public synchronized void clear() {
		if(tasksList.size() > 0)
			tasksList.clear();
	}

}