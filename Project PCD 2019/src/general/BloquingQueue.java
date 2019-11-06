package general;

public interface BloquingQueue {

	public void offer();
	public Object poll();
	public int size();
	public void clear();
}
