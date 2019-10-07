
public class Cozinheiro extends Thread {

	private int id;
	private Mesa mesa;

	public Cozinheiro(int id, Mesa mesa) {
		this.id = id;
		this.mesa = mesa;
	}

	private synchronized void cozinharJavali() {

		while(mesa.isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		mesa.put(new Javali());
		notifyAll(); //notifayAll ou notifay?
	}

	@Override
	public void run() {
		cozinharJavali();
	}

}
