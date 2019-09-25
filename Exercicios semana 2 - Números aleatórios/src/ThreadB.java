import java.util.Random;

public class ThreadB extends Thread {

	private int counter;
	private Random rnd;
	public ThreadB() {
		counter = 0;
		rnd = new Random();
	}
	
	@Override
	public void run() {
		try {
			while(!interrupted()) {
				System.out.println("Thread B: " + (rnd.nextInt(8)+1));
				counter++;
				sleep(5);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread B interrompida.");
		}
		System.out.println("Thread B ended!");
	}
	
	public int getCounter() {
		return counter;
	}
}
