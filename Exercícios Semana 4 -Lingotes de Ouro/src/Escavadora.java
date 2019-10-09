import java.text.DecimalFormat;
import java.util.Random;

public class Escavadora extends Thread {
	
	private int id;
	private double ouroRecolhido;
	private Balanca balanca;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public Escavadora(int id, Balanca balanca) {
		this.id = id;
		this.balanca = balanca;
		ouroRecolhido = 0.0;
	}
	
	@Override
	public void run() {
		System.out.println("Escavadora " + id + " iniciou a escavação..");
		while(!isInterrupted()) {
			balanca.putGold(randomGold());
		}
		System.out.println("Escavadora " + id + " terminou o run");
	}
	
	private double randomGold() {
		double gold = Double.parseDouble(df2.format(new Random().nextDouble()).replace(",", "."));
		ouroRecolhido += gold;
		System.out.println("Escavadora " + id + " escavou " + gold + " de ouro!");
		return gold;
	}

}
