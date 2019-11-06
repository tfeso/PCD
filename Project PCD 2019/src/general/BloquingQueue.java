package general;

import client.Task;

public interface BloquingQueue {

	public void offer(Task t) throws InterruptedException;
	public Task poll() throws InterruptedException;
	public int size();
	public void clear();
}
