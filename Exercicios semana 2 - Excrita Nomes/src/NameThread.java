public class NameThread extends Thread{
	
	private int identifier;
	
	public NameThread(int identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public void run() {
		
		
			try {
				for(int i = 0; i < 9; i++) {
				System.out.println(identifier);
				sleep(1000 + (int)(Math.random() * (2000 - 1000)));
				}
			} catch (InterruptedException e) {
				System.out.println("Acabou pois foi interrompida!");
			}
	}
	
	public static void main(String[] args) throws InterruptedException {

		NameThread t1 = new NameThread(1);
		NameThread t2 = new NameThread(2);
		
		t1.start();
		t2.start();
		
		//t1.join();
		//t2.join();
		
		sleep(12000);
		t1.interrupt();
		t2.interrupt();
		
		System.out.println("Programa terminou!");
	}
}
