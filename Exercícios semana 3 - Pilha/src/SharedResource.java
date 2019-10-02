public class SharedResource implements PilhaMetodos {
	
	private int position;
	private int[] pilha;
	
	public SharedResource(int size) {
		pilha = new int[size];
		position = -1;
	}
	
	@Override
	public boolean empty() {
		
		return(position == -1);
	}



	@Override
	public int peek() {
		
		if(empty())
			throw new IllegalStateException();
		else
			System.out.println("Peek : " + pilha[position]);
		return pilha[position];
	}

	@Override
	public synchronized int pop() {
		
		if(empty())
			throw new IllegalStateException();
		else {
			int posPop = pilha[position];
			pilha[position] = 0;
			position--;
			System.out.println("Pop : " + posPop);
			return posPop;
		}
	}



	@Override
	public synchronized void push(int valor) {
		
		if(size() == pilha.length) {
			throw new IllegalStateException();
		}
		position++;
		pilha[position] = valor;
		System.out.println("Push: " + valor + " na posição " + position);
	}



	@Override
	public int size() {

		if(empty())
			return 0;
		else {
			return position + 1;
		}
	}
	
	@Override
	public String toString() {

		String aux = "";
		for(int i = 0; i < pilha.length; i++) {
		
			aux+= i + " : " + pilha[i] + System.lineSeparator();  
		}
		
		return aux;
	}
	

	public static void main(String[] args) throws InterruptedException {

		SharedResource p = new SharedResource(20);
		
		Manipulador m1 = new Manipulador(1, p);
		Manipulador m2 = new Manipulador(2, p);
		Manipulador m3 = new Manipulador(3, p);
//		Manipulador m4 = new Manipulador(4, p);
//		Manipulador m5 = new Manipulador(5, p);
//		Manipulador m6 = new Manipulador(6, p);
		m1.start();
		m2.start();
		m3.start();
//		m4.start();
//		m5.start();
//		m6.start();
		m1.join();
		m2.join();
		m3.join();
//		m4.join();
//		m5.join();
//		m6.join();
		
		System.out.println(p.toString());
	}

}
