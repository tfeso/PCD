import java.util.ArrayList;

public class Mesa {

	private int maxLugares = 10;
	private ArrayList<Javali> listaJavalis;
	
	
	public Mesa(String mesa) {
		listaJavalis = new ArrayList<Javali>();
	}
	

	public synchronized void get() {
		
		listaJavalis.get(1);
	}
	
	public synchronized void put(Javali javali) {
		
		listaJavalis.add(javali);	
	}

	public boolean isEmpty() {
		
		if(listaJavalis.isEmpty())
			return true;
		return false;
	}

	public boolean isFull() {
		
		if(listaJavalis.size() == maxLugares)
			return true;
		return false;
	}
	
	private void criarCozinheirosGlutoes() {
		
		for(int i = 0; i < 10; i++) {
			new Cozinheiro(i, this).start();
			new Glutao(i, this).start();
		}
	}
	
	public static void main(String[] args) {
		
		Mesa mesa = new Mesa("Mesa");
		mesa.criarCozinheirosGlutoes();
	}
	
}
