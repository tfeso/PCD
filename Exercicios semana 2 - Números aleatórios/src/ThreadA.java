import java.util.Random;

public class ThreadA extends Thread {
	
	private int counter;
	private Random rnd;
	
	public ThreadA() {
		counter = 0;
		rnd = new Random();
	}

	@Override
	public void run() {
		while(!interrupted()) {
			System.out.println("Thread A: " + (rnd.nextInt(9000) + 1000));
			counter++;
		}
		System.out.println("Thread A ended!");
	}
	
	public int getCounter() {
		return counter;
	}
	
}
