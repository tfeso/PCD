package utilsMethods;


public interface Barrier {

	public void ClientbarrierEntry();
	public void WorkerbarrierEntry();
	public int size();
	public void clear();
}
