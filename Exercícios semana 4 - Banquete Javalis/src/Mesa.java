import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Mesa {

	private int maxLugares = 10;
	private LinkedList<Javali> listaJavalis;
	private int numJavalis = 0;
	private int counter = 0;


	public Mesa(String mesa) {
		listaJavalis = new LinkedList<Javali>();
	}


	public synchronized void get() {

		while(listaJavalis.isEmpty()) {

			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		listaJavalis.removeFirst();
		System.out.println("Comeu o javali " + numJavalis);
		numJavalis--;
		notifyAll();
	}

	public int getCounter() {
		return counter;
	}

	public synchronized void put(Javali javali) {

		while(listaJavalis.size() == maxLugares) {
			try {
				System.out.println("O cozinheiro " + javali.getId() + " está à espera!");
				wait();
			} catch (InterruptedException e) {
			}
		}
		listaJavalis.add(javali);
		System.out.println("Colocou o javali " + numJavalis + " na mesa!");
		numJavalis++;
		counter++;
		notifyAll();
	}

	public int getNumJavalis() {
		return numJavalis;
	}

	public static void main(String[] args) {

		Mesa mesa = new Mesa("Mesa");
		ArrayList<Thread> listaThreads;
		listaThreads = new ArrayList<Thread>();

		for(int i = 1; i <= 10; i++) {

			listaThreads.add(new Cozinheiro(i, mesa));
			listaThreads.add(new Glutao(i, mesa));
		}

		for(Thread t : listaThreads) {
			t.start();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Thread t : listaThreads) {
				t.interrupt();
		}

		for(Thread t : listaThreads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Merda Join");
			}
		}
		System.out.println("-------------------------------------------------");
		System.out.println(mesa.getCounter());

	}

}
