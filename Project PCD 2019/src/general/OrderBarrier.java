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

	public synchronized void ClientbarrierEntry() {
		while (tasksCompleted != numberOfTasks) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clear();
	}
	
	public synchronized void WorkerbarrierEntry() {
		tasksCompleted++;
		notifyAll();
	}
	
	public synchronized int size() {
		return tasksCompleted;
	}
	
	public synchronized void clear() {
		tasksCompleted = 0;
	}
	
	public synchronized Order getOrder() {
		return order;
	}
	
	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
}