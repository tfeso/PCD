
public class Glutao extends Thread {

	private int id;
	private Mesa mesa;
	
	public Glutao(int id, Mesa mesa) {
		this.id = id;
		this.mesa = mesa;
	}
	
	@Override
	public void run() {
		System.out.println("Glutão " + id + " arrancou!");
		while(!interrupted()) {
			System.out.println("O glutão " + id + " vai tentar comer!");
			mesa.get();
		}
	}
	
}
