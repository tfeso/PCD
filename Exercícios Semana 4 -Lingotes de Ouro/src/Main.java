import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		GUI gui = new GUI();
		Balanca balanca = new Balanca(gui);
		
		ArrayList<Thread> listThreads = new ArrayList<Thread>();
		listThreads.add(new Escavadora(1001, balanca));
		listThreads.add(new Escavadora(1002, balanca));
		listThreads.add(new Ourives("Tiago", balanca));
		gui.setListThreads(listThreads);
		
		for(Thread t: listThreads) {
			t.start();
		}
		
		for(Thread t: listThreads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("-----------------------------");
		
		

		
	}

}
