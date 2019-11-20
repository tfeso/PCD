package general;
import java.io.Serializable;
import client.Order;

public class OrderBarrier implements Barrier, Serializable {

	private int numberOfTasks;
	private int tasksCompleted;
	private Order order;
	
	public OrderBarrier(int numberOfTasks, Order order) {
		this.numberOfTasks = numberOfTasks;
		this.order = order;
		tasksCompleted = 0;
	}

	public synchronized void barrierEntry() {
		while (tasksCompleted != numberOfTasks) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clear();
	}
	
	public synchronized void barrierEntryTeste() {
		tasksCompleted++;
		notifyAll();
	}
	
	/*
	public synchronized void barrierEntry() {
		tasksCompleted++;
		while (size() == numberOfTasks) {
			notifyAll();
			clear();
		}
	}*/
	
	public synchronized int size() {
		return tasksCompleted;
	}
	
	public synchronized void clear() {
		tasksCompleted = 0;
	}
	
	public synchronized Order getOrder() {
		return order;
	}
}