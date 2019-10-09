
public class Cozinheiro extends Thread {

	private int id;
	private Mesa mesa;

	public Cozinheiro(int id, Mesa mesa) {
		this.id = id;
		this.mesa = mesa;
	}	

	@Override
	public void run() {
		System.out.println("Cozinheiro " + id + " arrancou!");
		while(!interrupted()) {
			mesa.put(new Javali(mesa.getNumJavalis() + 1, this));
		}
	}

}
