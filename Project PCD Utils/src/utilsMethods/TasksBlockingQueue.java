package utilsMethods;


import java.util.LinkedList;
import java.util.Queue;

public class TasksBlockingQueue {

	private Queue<Task> tasksList;
	private final static int LIMIT = 100000;
	
	public TasksBlockingQueue() {
		tasksList = new LinkedList<>();
	}
	
	public synchronized void offer(Task task) throws InterruptedException {
		while(size() >= LIMIT) {
			wait();
		}
		tasksList.add(task);
		notifyAll();
	}

	public synchronized Task poll(int rotation) throws InterruptedException {
		while(size() == 0 || !(getTaskByRotationBool(rotation))) {
			wait();
		}
		Task temp = getTaskByRotation(rotation);
		tasksList.remove(temp);
		notifyAll();
		return temp;
	}
	
	private boolean getTaskByRotationBool(int rotation) {
		for(Task t : tasksList) {
			if( t.getRotation() == rotation ){
				return true;
			}
		}
		return false;
	}

	public int size() {
		return tasksList.size();
	}

	public void clear() {
		if(tasksList.size() > 0)
			tasksList.clear();
	}
	
	public synchronized Task getTaskByRotation(int rotation) {
		for(Task t : tasksList) {
			if( t.getRotation() == rotation )
				return t;
		}
		return null;
	}

}