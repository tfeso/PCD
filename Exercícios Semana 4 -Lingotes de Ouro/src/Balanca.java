import java.text.DecimalFormat;

public class Balanca {

	private double currentWeight;
	private final double WEIGHT_LINGOTE = 12.5;
	private GUI gui;
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	public Balanca(GUI gui) {
		currentWeight = 0.0;
		this.gui = gui;
	}

	public synchronized void putGold(double gold) throws InterruptedException {
		System.out.println("Tentar colocar gold: " + gold);
		
			while(currentWeight >= WEIGHT_LINGOTE) {
				wait();
			} 
		System.out.println("Colocado na balança: " + gold);
		currentWeight += gold;
		System.out.println("Peso atual na balança: " + currentWeight);
		gui.setTestInTextField(df2.format(currentWeight).replace(",", "."));
		System.out.println("GUI atualizada");
		notifyAll();
	}

	public synchronized void getGold() throws InterruptedException {
		System.out.println("Tentar retirar gold..");

			while(currentWeight < WEIGHT_LINGOTE) {
				wait();
			}

		System.out.println("Gold retirado!");
		currentWeight -= WEIGHT_LINGOTE;
		System.out.println("Peso atual na balança: " + currentWeight);
		gui.setTestInTextField(df2.format(currentWeight).replace(",", "."));
		System.out.println("GUI atualizada");
		notifyAll();
	}

}
