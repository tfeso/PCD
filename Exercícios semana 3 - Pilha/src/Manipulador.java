public class Manipulador extends Thread {
	
	
	private int identifier;
	private SharedResource pilha;
	
	public Manipulador(int identifier, SharedResource pilha) {
		this.identifier = identifier;
		this.pilha = pilha;
	}
	
	@Override
	public void run() {

		System.out.println("Arrancou a thread " + identifier);
		pilha.push(7);
		pilha.push(3);
		pilha.push(1);
		pilha.empty();
		pilha.peek();
		pilha.pop();
		pilha.pop();
		pilha.size();
		System.out.println("Acabou a thread " + identifier);
	}
	
	

}
