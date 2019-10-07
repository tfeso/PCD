
public class Glutao extends Thread {

	private int id;
	private Mesa mesa;
	
	public Glutao(int id, Mesa mesa) {
		this.id = id;
		this.mesa = mesa;
	}
	
	private void comerJavali() {

		while(mesa.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		mesa.get();
		notifyAll(); //notifayAll ou notifay?
	}
	
	@Override
	public void run() {

		comerJavali();
	}
	
}
