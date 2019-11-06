package general;

public class OrderBarrier implements Barrier {

	private int numberOfTasks;
	private int tasksCompleted;
	
	public OrderBarrier(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public synchronized void barrierEntry() {
		tasksCompleted++;
		while (size() != numberOfTasks) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		clear();
	}
	
	public int size() {
		return tasksCompleted;
	}
	
	public void clear() {
		tasksCompleted = 0;
	}
}