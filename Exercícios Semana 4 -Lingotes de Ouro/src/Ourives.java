import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Ourives extends Thread {

	private String name;
	private ArrayList<Lingote> listLingotes;
	private Balanca balanca;

	public Ourives(String name, Balanca balanca) {
		this.name = name;
		this.balanca = balanca;
		listLingotes = new ArrayList<Lingote>();
	}

	@Override
	public void run() {
		System.out.println("Ourives " + name + " iniciou o trabalho..");

		while(!isInterrupted()) {
			try {
				balanca.getGold();
				System.out.println("Ourives " + name + " vai descansar..");
				sleep(3000);
				System.out.println("Ourives " + name + " acordou!");
				listLingotes.add(new Lingote(this));
				System.out.println("Ourives " + name + " criou um lingote!");

			} catch (InterruptedException e) {
				//JOptionPane.showMessageDialog(null, "Ourives catch Interrupted!");

			}
			System.out.println("Ourives " + name + " terminou o run");
		}
	}




}
